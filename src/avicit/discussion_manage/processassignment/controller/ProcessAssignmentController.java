package avicit.discussion_manage.processassignment.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import sun.invoke.empty.Empty;

import com.fasterxml.jackson.core.type.TypeReference;

import avicit.discussion_manage.checkassigntask.service.CheckAssignTaskAftertreatmentService;
import avicit.discussion_manage.discussionmanage.service.DiscussionManageService;
import avicit.discussion_manage.processassignment.dto.ProcessAssignmentDTO;
import avicit.discussion_manage.processassignment.dto.ProcessRouteDTO;
import avicit.discussion_manage.processassignment.service.ProcessAssignmentService;
import avicit.discussion_manage.proposalmanage.service.ProposalManageService;
import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.web.AvicitResponseBody;

/**
 * @classname: ProcessAssignmentController
 * @description: 定义 工艺分工人员任务表 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/processassignment/ProcessAssignmentController")
public class ProcessAssignmentController implements LoaderConstant {
	private static final Logger logger = LoggerFactory.getLogger(ProcessAssignmentController.class);

	@Autowired
	private ProcessAssignmentService service;
	@Autowired
	private DiscussionManageService manageService;
	@Autowired
	private ProposalManageService proposalservice;
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
	@RequestMapping(value = "ProcessAssignmentInfo")
	public ModelAndView toProcessAssignment(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/processassignment/ProcessAssignmentManage");
		request.setAttribute("url",
				"platform/discussion_manage/processassignment/ProcessAssignmentController/operation/");
		return mav;
	}
	
	/**
	 * 跳转到审查管理页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "ProcessTeskInfo")
	public ModelAndView toProcessTesk(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/processassignment/ProcessTeskManage");
		request.setAttribute("url",
				"platform/discussion_manage/processassignment/ProcessAssignmentController/operation/");
		return mav;
	}
	
	
	/**
	 * 跳转到路线分工页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "ProcessAssignmentDivision")
	public ModelAndView toProcessAssignmentDivision(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		String strId=request.getParameter("strId");
		List<Map<String, Object>> result = null;
		List<String> arr = new ArrayList<String>();
		try {
			result = service.gettreeProcessassignmentByStrId(strId);
			for (Map<String, Object> map : result) {
				String id = (String) map.get("strId");
				int count = manageService.findDiscussionBystrId(id);
				if (count==0) {
					String StrCode = (String) map.get("classCode");
					arr.add(StrCode);
				}
			}
			if (arr.size()==0) {
				mav.addObject("flag", OpResult.success);
				/*mav.setViewName("avicit/discussion_manage/processassignment/ProcessAssignmentDivision");
				request.setAttribute("url",
						"platform/discussion_manage/processassignment/ProcessAssignmentController/operation/");
				return mav;*/
			}else{
				/*mav.addObject("arr", arr);
				mav.setViewName("avicit/discussion_manage/processassignment/NotailManage");*/
				mav.addObject("arr", arr);
				mav.addObject("flag", OpResult.failure);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mav;
	}

	@RequestMapping(value = "/operation/getTreeGridByPage/{strId}")
	@ResponseBody
	public Map<String, Object> getTreeGridByPage(HttpServletRequest request,
			@PathVariable(value = "strId") String strId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> result = null;
		try {
			result = service.gettreeProcessassignmentByStrId(strId);

		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return map;
		}
		map.put("total", result.size());
		map.put("rows", result);
		logger.debug("成功获取SysLookupType分页数据");
		return map;
	}

	@RequestMapping(value = "/operation/getProcessAssignmentsByPage")
	@ResponseBody
	public Map<String, Object> togetProcessAssignmentByPage(PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<ProcessAssignmentDTO> queryReqBean = new QueryReqBean<ProcessAssignmentDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		String sfnConditions = ServletRequestUtils.getStringParameter(request, "sdfConditons", "");// 自定义查询条件
		ProcessAssignmentDTO param = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QueryRespBean<ProcessAssignmentDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat, new TypeReference<ProcessAssignmentDTO>() {
			});
			//applyUserId当前登录人部门id，用来过滤对应人员所看到数据
			String applyUserId = SessionHelper.getLoginSysUser(request).getId();
			param.setDutyUserId(applyUserId);
			queryReqBean.setSearchParams(param);
		}
		try {
			result = service.searchProcessAssignmentByPage(queryReqBean, sfnConditions);

		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return map;
		}

		for (ProcessAssignmentDTO dto : result.getResult()) {
			if ("2".equals(dto.getTaskType())) {
				String userId = dto.getDutyUserId();
				String strId = dto.getStrId();
				int count = proposalservice.getProposalByUserIdAndStrId(userId, strId);
				if (count>0) {
					int isClose = proposalservice.getProposalIsClose(userId, strId);
					if (isClose>0) {
						dto.setTaskStatus("未完成审查");
					}else{
						dto.setTaskStatus("已完成审查");
					}
					dto.setStatus("已发表");
				}else{
					dto.setStatus("未发表");
				}
			}
			dto.setDutyUserName(sysUserLoader.getSysUserNameById(dto.getDutyUserId()));
			String deptId = sysUserDeptPositionLoader.getChiefDeptIdBySysUserId(dto.getDutyUserId());
			dto.setDutyDeptName(sysDeptLoader.getSysDeptNameBySysDeptId(deptId, SessionHelper.getCurrentLanguageCode()));
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
	public ModelAndView toOpertionPage(@PathVariable String type, @PathVariable String id, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		if (!"null".equals(id)) {// 编辑窗口或者详细页窗口
			// 主表数据
			// LogBase logBase = LogBaseFactory.getLogBase(request);
			ProcessAssignmentDTO dto = service.queryProcessAssignmentByPrimaryKey(id);

			request.setAttribute("processAssignmentDTO", dto);
		}
		mav.setViewName("avicit/discussion_manage/processassignment/" + "ProcessAssignment" + type);
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
	public ModelAndView toSaveProcessAssignmentDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request, "data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			ProcessAssignmentDTO processAssignmentDTO = JsonHelper.getInstance().readValue(jsonData, dateFormat,
					new TypeReference<ProcessAssignmentDTO>() {
					});
			if ("".equals(processAssignmentDTO.getId())) {// 新增
				service.insertProcessAssignment(processAssignmentDTO);
			} else {// 修改
				service.updateProcessAssignmentSensitive(processAssignmentDTO);
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
	 * 路线保存数据
	 * 
	 * @param id
	 *            主键id
	 * @param demoBusinessTripDTO
	 *            保存的对象
	 * @return
	 */
	@RequestMapping(value = "/operation/proSave", method = RequestMethod.POST)
	@ResponseBody
	public AvicitResponseBody toSaveProcessRouteDTO(HttpServletRequest request) {
		String	strId =  ServletRequestUtils.getStringParameter(request, "strId", "");
		String	dutyUserId =  ServletRequestUtils.getStringParameter(request, "dutyUserId", "");
		String datas = ServletRequestUtils.getStringParameter(request, "datas", "");
		if (datas == "") {
			return new AvicitResponseBody(OpResult.success);
		}
		String newDatas = datas.replace("routeId", "id");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			List<ProcessRouteDTO> list = JsonHelper.getInstance().readValue(newDatas, dateFormat,
					new TypeReference<List<ProcessRouteDTO>>() {
					});
			boolean  flag = false;//是否已分工
			for (ProcessRouteDTO demo : list) {
				// 热表单位 拆分，去重
				String[] thUnit = demo.getThermalUnit().split(",");
				if (thUnit.length > 1) {
					thUnit = service.getrouteList(thUnit);
				}
				// 制造单位拆分，去重
				String[] manuUnit = demo.getManufacturingUnit().split("-");
				if (manuUnit.length > 1) {
					manuUnit = service.getrouteList(manuUnit);
				}
				// 使用单位拆分，去重
				String[] useUnit = demo.getUseUnit().split(",");
				if (useUnit.length > 1) {
					useUnit = service.getrouteList(useUnit);
				}

				if (thUnit.length > 0 || manuUnit.length > 0) {
					//热表或者制造任意有更新 ,先删除现有路线分工 ,重新分工
					service.deleteFectoryBySid(demo.getStrId());
					//热表或者制造任意有更新 ,先删除现有分工的审查任务 ,重新分配
					service.deleteFactoryDto(demo);
				}
				if (thUnit.length > 0) {
					// 热表单位生成路线
					service.insertFactoryDto(demo, thUnit, "1");
					flag = true;
				}

				if (manuUnit.length > 0) {
					// 制造单位生成路线
					service.insertFactoryDto(demo, manuUnit, "2");
					flag = true;
				}
				if("".equals(demo.getThermalUnit())&&"".equals(demo.getManufacturingUnit())&&"".equals(demo.getUseUnit())){
					service.deleteFactoryDto(demo);
				}
				if ("".equals(demo.getId()) || null == demo.getId()) {
					service.insertProcessRoute(demo);
				} else {
					service.updateProcessRoute(demo);
					
				}
			}
			//工艺分工后处理 By heps 2017-8-5
			if(true == flag){
				checkAssignTaskAftertreatmentService.updateCheckAssignTaskAftertreatment(strId, "5",dutyUserId);
			}
			return new AvicitResponseBody(OpResult.success);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			return new AvicitResponseBody(OpResult.failure, ex.getMessage());
		}

	}

	/**
	 * 按照id批量删除数据
	 * 
	 * @param ids
	 *            id数组
	 * @return
	 */
	@RequestMapping(value = "/operation/delete", method = RequestMethod.POST)
	public ModelAndView toDeleteProcessAssignmentDTO(@RequestBody String[] ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			// LogBase logBase = LogBaseFactory.getLogBase( request);
			service.deleteProcessAssignmentByIds(ids);
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
