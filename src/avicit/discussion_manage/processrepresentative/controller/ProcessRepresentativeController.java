package avicit.discussion_manage.processrepresentative.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;

import avicit.discussion_manage.checkassigntask.service.CheckAssignTaskAftertreatmentService;
import avicit.discussion_manage.processrepresentative.dto.ProcessRepresentativeDTO;
import avicit.discussion_manage.processrepresentative.service.ProcessRepresentativeService;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;

/**
 * @classname: ProcessRepresentativeController
 * @description: 定义 工艺代表任务表 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/processrepresentative/ProcessRepresentativeController")
public class ProcessRepresentativeController implements LoaderConstant {
	private static final Logger logger = LoggerFactory
			.getLogger(ProcessRepresentativeController.class);

	@Autowired
	private ProcessRepresentativeService service;
	@Autowired
	private CheckAssignTaskAftertreatmentService checkAssignTaskAftertreatmentService;
	/**
	 * 跳转到管理页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "ProcessRepresentativeInfo")
	public ModelAndView toProcessRepresentative(HttpServletRequest request,
			HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/processrepresentative/ProcessRepresentativeManage");
		request.setAttribute(
				"url",
				"platform/discussion_manage/processrepresentative/ProcessRepresentativeController/operation/");
		return mav;
	}

	@RequestMapping(value = "/operation/getProcessRepresentativesByPage")
	@ResponseBody
	public Map<String, Object> togetProcessRepresentativeByPage(
			PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<ProcessRepresentativeDTO> queryReqBean = new QueryReqBean<ProcessRepresentativeDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param",
				"");
		String sfnConditions = ServletRequestUtils.getStringParameter(request,
				"sdfConditons", "");// 自定义查询条件
		ProcessRepresentativeDTO param = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QueryRespBean<ProcessRepresentativeDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat,
					new TypeReference<ProcessRepresentativeDTO>() {
					});
			queryReqBean.setSearchParams(param);
		}
		try {
			result = service.searchProcessRepresentativeByPage(queryReqBean,
					sfnConditions);

		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return map;
		}

		for (ProcessRepresentativeDTO dto : result.getResult()) {
			dto.setProcessUserIdAlias(sysUserLoader.getSysUserNameById(dto
					.getProcessUserId()));
			dto.setDutyUserName(sysUserLoader.getSysUserNameById(dto
					.getDutyUserId()));
		}

		map.put("total", result.getPageParameter().getTotalCount());
		map.put("rows", result.getResult());
		logger.debug("成功获取SysLookupType分页数据");
		return map;
	}

	// 打开编辑或者添加页
	/**
	 * 根据id选择跳转到新建页还是编辑页
	 * 
	 * @param type
	 *            操作类型新建还是编辑
	 * @param id
	 *            编辑数据的id
	 * @param request
	 *            请求
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operation/{type}/{id}")
	public ModelAndView toOpertionPage(@PathVariable String type,
			@PathVariable String id, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		if (!"null".equals(id)) {// 编辑窗口或者详细页窗口
			// 主表数据
			// LogBase logBase = LogBaseFactory.getLogBase(request);
			ProcessRepresentativeDTO dto = service
					.queryProcessRepresentativeByPrimaryKey(id);

			dto.setProcessUserIdAlias(sysUserLoader.getSysUserNameById(dto
					.getProcessUserId()));

			request.setAttribute("processRepresentativeDTO", dto);
		}
		mav.setViewName("avicit/discussion_manage/processrepresentative/"
				+ "ProcessRepresentative" + type);
		return mav;
	}

	/**
	 * 保存数据
	 * 
	 * @param id
	 *            主键id
	 * @param demoBusinessTripDTO
	 *            保存的对象
	 * @return
	 */
	@RequestMapping(value = "/operation/save", method = RequestMethod.POST)
	public ModelAndView toSaveProcessRepresentativeDTO(
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request,
				"data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			ProcessRepresentativeDTO processRepresentativeDTO = JsonHelper
					.getInstance().readValue(jsonData, dateFormat,
							new TypeReference<ProcessRepresentativeDTO>() {
							});
			if ("".equals(processRepresentativeDTO.getId())) {// 新增
				service.insertProcessRepresentative(processRepresentativeDTO);
			} else {// 修改
				service.updateProcessRepresentativeSensitive(processRepresentativeDTO);
				//工艺分工人员指派后处理 By heps 2017-8-5
				checkAssignTaskAftertreatmentService.updateCheckAssignTaskAftertreatment(processRepresentativeDTO.getStrId(), "4", processRepresentativeDTO.getDutyUserId());
			}
			mav.addObject("flag", OpResult.success);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			mav.addObject("error", ex.getMessage());
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;

	}

	/**
	 * 按照id批量删除数据
	 * 
	 * @param ids
	 *            id数组
	 * @return
	 */
	@RequestMapping(value = "/operation/delete", method = RequestMethod.POST)
	public ModelAndView toDeleteProcessRepresentativeDTO(
			@RequestBody String[] ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			// LogBase logBase = LogBaseFactory.getLogBase( request);
			service.deleteProcessRepresentativeByIds(ids);
			mav.addObject("flag", OpResult.success);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			mav.addObject("error", ex.getMessage());
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}
}
