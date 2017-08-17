package avicit.discussion_manage.discussionmanage.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import avicit.platform6.api.sysuser.dto.SysUser;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;


import avicit.discussion_manage.discussionmanage.dto.DiscussionInfoDTO;

import avicit.discussion_manage.discussionmanage.dto.DiscussionManageDTO;
import avicit.discussion_manage.discussionmanage.service.DiscussionManageService;
import avicit.discussion_manage.modelInterveneCheck.dto.InterveneDTO;
import avicit.discussion_manage.structuralrelationship.dto.AddFormDataDTO;
import avicit.discussion_manage.structuremanage.service.StructureManageService;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @classname: DiscussionManageController
 * @description: 定义 讨论区管理 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/discussionmanage/DiscussionManageController")
public class DiscussionManageController implements LoaderConstant {
	private static final Logger logger = LoggerFactory
			.getLogger(DiscussionManageController.class);

	@Autowired
	private DiscussionManageService service;
	@Autowired
	private StructureManageService structureManageService;

	/**
	 * 跳转到管理页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "DiscussionManageInfo")
	public ModelAndView toDiscussionManage(HttpServletRequest request,
			HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/discussionmanage/DiscussionManageManage");
		request.setAttribute(
				"url",
				"platform/discussion_manage/discussionmanage/DiscussionManageController/operation/");
		return mav;
	}

	@RequestMapping(value = "/operation/getDiscussionManagesByPage")
	@ResponseBody
	public Map<String, Object> togetDiscussionManageByPage(
			PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<DiscussionManageDTO> queryReqBean = new QueryReqBean<DiscussionManageDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param",
				"");
		String sfnConditions = ServletRequestUtils.getStringParameter(request,
				"sdfConditons", "");// 自定义查询条件
		DiscussionManageDTO param = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QueryRespBean<DiscussionManageDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat,
					new TypeReference<DiscussionManageDTO>() {
					});
			queryReqBean.setSearchParams(param);
		}
		try {
			result = service.searchDiscussionManageByPage(queryReqBean,
					sfnConditions);

		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return map;
		}

		for (DiscussionManageDTO dto : result.getResult()) {

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
		String tlCode=service.getInfoById();
		if (!"null".equals(id)) {// 编辑窗口或者详细页窗口
			// 主表数据
			// LogBase logBase = LogBaseFactory.getLogBase(request);
			DiscussionManageDTO dto = service
					.queryDiscussionManageByPrimaryKey(id);

			request.setAttribute("discussionManageDTO", dto);
		}
		mav.addObject("tlCode", tlCode);
		mav.setViewName("avicit/discussion_manage/discussionmanage/"
				+ "DiscussionManage" + type);
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
	public ModelAndView toSaveDiscussionManageDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request,
				"data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			DiscussionManageDTO discussionManageDTO = JsonHelper.getInstance()
					.readValue(jsonData, dateFormat,
							new TypeReference<DiscussionManageDTO>() {
							});
			String strId =discussionManageDTO.getStrId();
			if ("".equals(discussionManageDTO.getId())) {// 新增
				Map<String, Object>  param = new HashMap<String, Object>();
				String id = discussionManageDTO.getStrId();
				String clientIp  = SessionHelper.getClientIp(request);
				String sysUserId = SessionHelper.getLoginSysUserId(request);
				param.put("id", id);
				param.put("clientIp", clientIp);
				param.put("sysUserId", sysUserId);
				service.insertDiscussionManage(discussionManageDTO,param,strId);
				String classType = structureManageService.findClassType(strId);
				if (classType.equals("0")) {
					//根据角色code返回角色对象（工艺代表）
					List<SysUser> userlist = sysUserRoleLoader.getSysUserListBySysRoleCode("tec_represent");
					service.insertProcessGongyiBystrId(strId,userlist);
				}
			} else {// 修改
				service.updateDiscussionManageSensitive(discussionManageDTO);
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
	public ModelAndView toDeleteDiscussionManageDTO(@RequestBody String[] ids,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			// LogBase logBase = LogBaseFactory.getLogBase( request);
			service.deleteDiscussionManageByIds(ids);
			mav.addObject("flag", OpResult.success);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			mav.addObject("error", ex.getMessage());
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}

	@RequestMapping(value = "/operation/getDeptUserDetail/{id}", method = RequestMethod.POST)
	public Map<String, Object> getDeptUser(PageParameter pageParameter,HttpServletRequest request,@PathVariable String id){
		QueryReqBean<DiscussionManageDTO> queryReqBean = new QueryReqBean<DiscussionManageDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String,Object> map = new HashMap<String, Object>();
		QueryRespBean<DiscussionManageDTO> result =null;
		try {
			result = service.searchDeptUserByStrId(queryReqBean, id);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			return map;  
		}
		for (DiscussionManageDTO udto : result.getResult()) {
			String deptusers= udto.getDutyUserIds();
			String dutyUsers="";
			if (deptusers!=null&&!"".equals(deptusers)) {
				if (deptusers.contains(",")) {
					String [] users = deptusers.split(",");
					for (int i = 0; i < users.length; i++) {
						dutyUsers+= sysUserLoader.getSysUserNameById(users[i])+",";
					}
					dutyUsers=dutyUsers.substring(0,dutyUsers.length()-1);
				}else{
					dutyUsers=sysUserLoader.getSysUserNameById(deptusers);
				}
				
				udto.setDutyUserIds(dutyUsers);
			}
			udto.setWorkshopId(sysDeptLoader.getSysDeptNameBySysDeptId(udto.getWorkshopId(),SessionHelper.getCurrentLanguageCode(request)));
			
		}
		map.put("total",result.getPageParameter().getTotalCount());
		map.put("rows", result.getResult());
		logger.debug("成功获取分页数据");
		return map;

	}
	/**
	 * 按照零件id查询讨论区信息
	 * @param id
	 * @return list
	 */
	@RequestMapping(value = "/selectDiscussionData/{strId}", method = RequestMethod.POST)
	public Map<String, Object> selectDiscussionData(PageParameter pageParameter,@PathVariable(value="strId")String strId,HttpServletRequest request)throws Exception{
		QueryReqBean<DiscussionInfoDTO> queryReqBean = new QueryReqBean<DiscussionInfoDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String,Object> map = new HashMap<String, Object>();
		QueryRespBean<DiscussionInfoDTO> result =null;
		try {
			result = service.selectDiscussionData(queryReqBean,strId);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			return map;  
		}
		map.put("total",result.getPageParameter().getTotalCount());
		map.put("rows", result.getResult());
		logger.debug("成功获取分页数据");
		return map;
	}
	
	
	/**
	 * 加载模型干涉表格数据通过当前选择节点
	 * @param String 零件id
	 * @return Map
	 */
	@RequestMapping(value = "/getInterveneDataByStrId/{strId}")
	public Map<String, Object> getInterveneDataByStrId(PageParameter pageParameter,@PathVariable(value="strId")String strId,HttpServletRequest request)throws Exception{
		QueryReqBean<InterveneDTO> queryReqBean = new QueryReqBean<InterveneDTO>();
		String instancenumber = ServletRequestUtils.getStringParameter(request, "instancenumber", "");
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String,Object> map = new HashMap<String, Object>();
		QueryRespBean<InterveneDTO> result =null;
		try {
			result = service.getInterveneDataByStrId(queryReqBean,strId,instancenumber);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			return map;  
		}
		map.put("total",result.getPageParameter().getTotalCount());
		map.put("rows", result.getResult());
		logger.debug("成功获取分页数据");
		return map;
	}
	
	/**
	 * 强制通过干涉检查结果
	 * @param String[] 
	 * @return ModelAndView
	 * author by heps
	 */
	@RequestMapping(value = "/updateInterveneResult", method = RequestMethod.POST)
	public ModelAndView updateInterveneResult(@RequestBody String[] ids,HttpServletRequest request)throws Exception{
		ModelAndView mav = new ModelAndView();
		try {
			service.updateInterveneResult(ids);
			mav.addObject("flag", OpResult.success);
			return mav;
		} catch (Exception e) {
			logger.error(e.getMessage());
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
	}
}
