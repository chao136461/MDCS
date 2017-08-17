package avicit.discussion_manage.disputememo.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;

import avicit.discussion_manage.disputememo.dto.DisputeMemoDTO;
import avicit.discussion_manage.disputememo.service.DisputeMemoService;
import avicit.discussion_manage.proposalmanage.dto.ProposalManageDTO;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @classname: DisputeMemoController
 * @description: 定义 争议备忘 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/disputememo/DisputeMemoController")
public class DisputeMemoController implements LoaderConstant {
	private static final Logger logger = LoggerFactory
			.getLogger(DisputeMemoController.class);

	@Autowired
	private DisputeMemoService service;

	/**
	 * 跳转到管理页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "DisputeMemoInfo")
	public ModelAndView toDisputeMemo(HttpServletRequest request,
			HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/disputememo/DisputeMemoManage");
		request.setAttribute("url",
				"platform/discussion_manage/disputememo/DisputeMemoController/operation/");
		return mav;
	}

	@RequestMapping(value = "/operation/getDisputeMemosByPage")
	@ResponseBody
	public Map<String, Object> togetDisputeMemoByPage(
			PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<DisputeMemoDTO> queryReqBean = new QueryReqBean<DisputeMemoDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param",
				"");
		String sfnConditions = ServletRequestUtils.getStringParameter(request,
				"sdfConditons", "");// 自定义查询条件
		DisputeMemoDTO param = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QueryRespBean<DisputeMemoDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat,
					new TypeReference<DisputeMemoDTO>() {
					});
			queryReqBean.setSearchParams(param);
		}
		try {
			result = service.searchDisputeMemoByPage(queryReqBean,
					sfnConditions);

		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return map;
		}

		for (DisputeMemoDTO dto : result.getResult()) {

			dto.setSendToAlias(sysUserLoader.getSysUserNameById(dto.getSendTo()));

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
			DisputeMemoDTO dto = service.queryDisputeMemoByPrimaryKey(id);

			dto.setSendToAlias(sysUserLoader.getSysUserNameById(dto.getSendTo()));

			request.setAttribute("disputeMemoDTO", dto);
		}
		mav.setViewName("avicit/discussion_manage/disputememo/" + "DisputeMemo"
				+ type);
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
	public ModelAndView toSaveDisputeMemoDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request,
				"data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			DisputeMemoDTO disputeMemoDTO = JsonHelper.getInstance().readValue(
					jsonData, dateFormat, new TypeReference<DisputeMemoDTO>() {
					});
			if ("".equals(disputeMemoDTO.getId())) {// 新增
				service.insertDisputeMemo(disputeMemoDTO);
			} else {// 修改
				service.updateDisputeMemoSensitive(disputeMemoDTO);
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
	public ModelAndView toDeleteDisputeMemoDTO(@RequestBody String[] ids,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			// LogBase logBase = LogBaseFactory.getLogBase( request);
			service.deleteDisputeMemoByIds(ids);
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
	 * 添加或展示争议
	 * 
	 * @param type
	 *            操作类型详情还是编辑
	 * @param id
	 *            编辑数据的id
	 * @param request
	 *            请求
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operation/disputed")
	public ModelAndView toDisputedPage(HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		
		DisputeMemoDTO dto = service.queryDisputeMemoByFrenKey(id);
		if (dto!=null) {// 编辑窗口或者添加窗口
			//dto.setSendToAlias(sysUserLoader.getSysUserNameById(dto.getSendTo()));

			request.setAttribute("disputeMemoDTO", dto);
		}else{
			DisputeMemoDTO disDto = new DisputeMemoDTO();
			disDto.setProId(id);
			request.setAttribute("disputeMemoDTO", disDto);
		}
		mav.setViewName("avicit/discussion_manage/discussionmanage/ProposalDisputedAdd");
		return mav;
	}
	
	
}
