package avicit.discussion_manage.proposalmanage.controller;

import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.fasterxml.jackson.core.type.TypeReference;

import avicit.discussion_manage.discussionmanage.dao.DiscussionManageDao;
import avicit.discussion_manage.discussionmanage.dto.DiscussionManageDTO;
import avicit.discussion_manage.proposalmanage.dto.ProposalManageDTO;
import avicit.discussion_manage.proposalmanage.service.ProposalManageService;
import avicit.discussion_manage.regionconfiguration.service.RegionConfigurationService;
import avicit.discussion_manage.structuremanage.service.StructureManageService;
import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.api.sysuser.dto.SysUser;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.discussion_manage.checkassigntask.service.CheckAssignTaskAftertreatmentService;


/**
 * @classname: ProposalManageController
 * @description: 定义 建议管理 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/proposalmanage/ProposalManageController")
public class ProposalManageController implements LoaderConstant {
	private static final Logger logger = LoggerFactory.getLogger(ProposalManageController.class);

	@Autowired
	private ProposalManageService service;
	@Autowired
	private StructureManageService strservice;
	@Autowired
	private CheckAssignTaskAftertreatmentService checkAssignTaskAftertreatmentService;
	@Autowired
	private RegionConfigurationService regionService;
	@Autowired
	private DiscussionManageDao disdao;

	/**
	 * 跳转到管理页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "ProposalManageInfo")
	public ModelAndView toProposalManage(HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/proposalmanage/ProposalManageManage");
		request.setAttribute("url", "platform/discussion_manage/proposalmanage/ProposalManageController/operation/");
		return mav;
	}

	@RequestMapping(value = "/operation/getProposalManagesByPage")
	@ResponseBody
	public Map<String, Object> togetProposalManageByPage(PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<ProposalManageDTO> queryReqBean = new QueryReqBean<ProposalManageDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		String sfnConditions = ServletRequestUtils.getStringParameter(request, "sdfConditons", "");// 自定义查询条件
		ProposalManageDTO param = null;
		String instance = "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		QueryRespBean<ProposalManageDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat, new TypeReference<ProposalManageDTO>() {
			});
			instance = service.queryStructInstanceByStrId(param.getStrId());
			if(param.getProposalUserDept()!=null&&!"".equals(param.getProposalUserDept())){
				String deptNameId = sysDeptLoader.getTopSysDeptNameBySysDeptId(param.getProposalUserDept(),"zh_CN");
				if("601研究所".equals(deptNameId)){//所
					param.setComplex("1");
				}else if("320制造厂".equals(deptNameId)){//厂
					param.setComplex("2");
				}else{
					param.setComplex("");
				}
			}
			param.setProposalUserDept("");
			queryReqBean.setSearchParams(param);
		}
		try {
			result = service.searchProposalManageByPage(queryReqBean, sfnConditions);

		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return map;
		}
		ProposalManageDTO searchParams = queryReqBean.getSearchParams();
		//获取前台的专业过滤条件。 再根据后台拼接的建议列表进行判断，如果前台有过滤条件 那么把其他的全部干掉。addby wuc
		for (Iterator<ProposalManageDTO> dto = result.getResult().iterator();dto.hasNext();) {
			ProposalManageDTO  dd = dto.next();
			dd.setProposalUserIdAlias(sysUserLoader.getSysUserNameById(dd.getProposalUserId()));
			if (dd.getDeptName() != null && !"".equals(dd.getDeptName())) {
				dd.setDeptName(sysDeptLoader.getSysDeptNameBySysDeptId(dd.getDeptName(),
						SessionHelper.getCurrentLanguageCode(request)));
			} else {
				if(searchParams.getSerdeptNames()!=null &&!"".equals(searchParams.getSerdeptNames())){
					dto.remove();
				}else{					
					dd.setDeptName("其它");
				}
			}
			dd.setReplierUserIdAlias(sysUserLoader.getSysUserNameById(dd.getReplierUserId()));
			//建议时间不为空
			if(dd.getPublicationTime()!=null&&!"".equals(dd.getPublicationTime())){
			//建议时间毫秒数
			long timer = dd.getPublicationTime().getTime();
			//系统时间毫秒数
			long data = new Date().getTime();
				//建议时间和当前系统时间差
				long day = (data-timer)/(24*3600000);
				//根据建议时间和建议类型查询对应颜色区间
				String color = regionService.findRegionColor(day, instance);
				dd.setProposalColor(color);
			}
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
		String strId = request.getParameter("strId");
		String code= request.getParameter("classCode");
		String instancenumber= request.getParameter("instancenumber");
		String classCode = URLDecoder.decode(code, "UTF-8");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String currData = dateFormat.format(new Date());
		String str = service.getInfoById();
		if (!"null".equals(id)) {// 编辑窗口或者详细页窗口
			// 主表数据
			// LogBase logBase = LogBaseFactory.getLogBase(request);
			ProposalManageDTO dto = service.queryProposalManageByPrimaryKey(id);

			dto.setProposalUserIdAlias(sysUserLoader.getSysUserNameById(dto.getProposalUserId()));

			dto.setProposalUserDeptAlias(sysDeptLoader.getSysDeptNameBySysDeptId(dto.getProposalUserDept(),
					SessionHelper.getCurrentLanguageCode(request)));

			dto.setReplierUserIdAlias(sysUserLoader.getSysUserNameById(dto.getReplierUserId()));

			request.setAttribute("proposalManageDTO", dto);
		}
		mav.addObject("strId", strId);
		mav.addObject("classCode", classCode);
		mav.addObject("instancenumber", instancenumber);
		mav.addObject("currdate", currData);
		mav.addObject("str", str);

		mav.setViewName("avicit/discussion_manage/proposalmanage/" + "ProposalManage" + type);
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
	@RequestMapping(value = "/operation/saveDto", method = RequestMethod.POST)
	public ModelAndView toSaveManageDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		ProposalManageDTO dto = new ProposalManageDTO();
		String strId = ServletRequestUtils.getStringParameter(request, "strId", "");
		try {
			String str = service.getInfoById();
			String applyUserId = SessionHelper.getLoginSysUser(request).getId();
			String applyDeptId = SessionHelper.getCurrentDeptId(request);
			String comple="";
			//if(!"8a58ab4d4c2141fd014c217cdc5102b6".equals(applyDeptId)){
				String deptNameId = sysDeptLoader.getTopSysDeptNameBySysDeptId(applyDeptId,"zh_CN");
				if("601研究所".equals(deptNameId)){//所
					comple="1";
				}else if("320制造厂".equals(deptNameId)){//厂
					comple="2";
				}else{
					comple="";
				}
			//}
			dto.setStrId(strId);
			dto.setProposalCode(str);
			dto.setProposedType("2");
			dto.setProposalName("无建议");
			dto.setProposalUserId(applyUserId);
			dto.setProposalMain("无建议");
			dto.setProposalUserDept(applyDeptId);
			dto.setParentId("-1");
			dto.setStatus("0");
			dto.setPublicationTime(new Date());
			dto.setComplex(comple);//厂所
			service.insertProposalManage(dto);
			//审查任务后处理 By heps 2017-8-5
			checkAssignTaskAftertreatmentService.updateCheckAssignTaskAftertreatment(strId, "1", applyUserId);
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
	 * 保存数据
	 * 
	 * @param id
	 *            主键id
	 * @param demoBusinessTripDTO
	 *            保存的对象
	 * @return
	 */
	@RequestMapping(value = "/operation/save", method = RequestMethod.POST)
	public ModelAndView toSaveProposalManageDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request, "data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			ProposalManageDTO proposalManageDTO = JsonHelper.getInstance().readValue(jsonData, dateFormat,
					new TypeReference<ProposalManageDTO>() {
					});
			//if ("".equals(proposalManageDTO.getId())) {// 新增
				String applyDeptId = SessionHelper.getCurrentDeptId(request);
				String comple="";
				//if(!"8a58ab4d4c2141fd014c217cdc5102b6".equals(applyDeptId)){
					String deptNameId = sysDeptLoader.getTopSysDeptNameBySysDeptId(applyDeptId,"zh_CN");
					if("601研究所".equals(deptNameId)){//所
						comple="1";
					}else if("320制造厂".equals(deptNameId)){//厂
						comple="2";
					}else{
						comple="";
					}
				//}
				proposalManageDTO.setPublicationTime(new Date());
				proposalManageDTO.setComplex(comple);
				service.insertProposalManage(proposalManageDTO);
				//审查任务后处理 By heps 2017-8-5
				checkAssignTaskAftertreatmentService.updateCheckAssignTaskAftertreatment(proposalManageDTO.getStrId(), "1",proposalManageDTO.getReplierUserId());

			/*} else {// 修改
				service.updateProposalManageSensitive(proposalManageDTO);
			}*/
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
	public ModelAndView toDeleteProposalManageDTO(@RequestBody String[] ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			// LogBase logBase = LogBaseFactory.getLogBase( request);
			service.deleteProposalManageByIds(ids);
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
	 * 根据id查询讨论区数据
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
	@RequestMapping(value = "/operation/forum")
	public ModelAndView toOpertionForum(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		try {
			//获取回复数组集合
			List<ProposalManageDTO> dto = service.queryProposalManageByParentId(id);
			//主键查询单条对象
			ProposalManageDTO proposalManageDTO = service.queryProposalManageByPrimaryKey(id);
			for (ProposalManageDTO pro : dto) {//遍历集合，人或部门别名
				pro.setProposalUserIdAlias(sysUserLoader.getSysUserNameById(pro.getProposalUserId()));
				String deptName = sysDeptLoader.getSysDeptNameBySysDeptId(pro.getProposalUserDept(),
						SessionHelper.getCurrentLanguageCode(request));
				pro.setProposalUserDeptAlias(deptName);
				pro.setReplierUserIdAlias(sysUserLoader.getSysUserNameById(pro.getReplierUserId()));
				//根据id获取顶部门名称
				/*if(!"8a58ab4d4c2141fd014c217cdc5102b6".equals(pro.getProposalUserDept())){
					String deptNameIds = sysDeptLoader.getTopSysDeptNameBySysDeptId(pro.getProposalUserDept(),"zh_CN");
					pro.setDeptNameIds(deptNameIds+"/"+deptName);
				}*/
				pro.setDeptNameIds(deptName);
				SysUser sysUser = sysUserLoader.getSysUserById(pro.getReplierUserId());
				//手机
				pro.setMobile(sysUser.getMobile()==null?"无":sysUser.getMobile());
				//座机
				pro.setOfficeTel(sysUser.getOfficeTel()==null?"无":sysUser.getOfficeTel());
			}
			//根据零件ID查询零件code
			String strcode = strservice.queryStructureManageByPrimaryKey(proposalManageDTO.getStrId()).getClassCode();
			proposalManageDTO.setStrCode(strcode);
			proposalManageDTO.setProposalUserIdAlias(sysUserLoader.getSysUserNameById(proposalManageDTO
					.getProposalUserId()));
			String deptName = sysDeptLoader.getSysDeptNameBySysDeptId(proposalManageDTO.getProposalUserDept(),
							SessionHelper.getCurrentLanguageCode(request));
			proposalManageDTO.setProposalUserDeptAlias(deptName);
			//根据id获取顶级部门名称
			String deptNameIds = sysDeptLoader.getTopSysDeptNameBySysDeptId(proposalManageDTO.getProposalUserDept(),"zh_CN");
			proposalManageDTO.setDeptNameIds(deptNameIds+"/"+deptName);
			SysUser  sysUser = sysUserLoader.getSysUserById(proposalManageDTO.getProposalUserId());
			//手机
			proposalManageDTO.setMobile(sysUser.getMobile()==null?"无":sysUser.getMobile());
			//座机
			proposalManageDTO.setOfficeTel(sysUser.getOfficeTel()==null?"无":sysUser.getOfficeTel());
			mav.addObject("flag", OpResult.success);
			mav.addObject("datas", dto);
			mav.addObject("proposal", proposalManageDTO);
		} catch (Exception e) {
			logger.debug(e.getMessage());
			mav.addObject("error", e.getMessage());
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}
	/**
	 * 讨论区回复数据添加
	 * 
	 * @param applyUserId:回复人,value:回复内容,applyDeptId:回复人专业,time:回复时间,parentId:被回Id
	 *            
	 * @param request
	 *            请求
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operation/replyAdd")
	public ModelAndView toSaveReplyManageDTO(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request, "data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			ProposalManageDTO proposalManageDTO = JsonHelper.getInstance().readValue(jsonData, dateFormat,
					new TypeReference<ProposalManageDTO>() {
					});
			String id = service.insertProposalManage(proposalManageDTO);
			
			mav.addObject("flag", OpResult.success);
			mav.addObject("id", id);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			mav.addObject("error", ex.getMessage());
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}
	/**
	 * 讨论区建议状态改变
	 * 
	 * @param id当前建议id
	 *            
	 * @param request
	 *            请求
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operation/commonality")
	public ModelAndView toSaveCommonalityManageDTO(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request, "data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			ProposalManageDTO proposalManageDTO = JsonHelper.getInstance().readValue(jsonData, dateFormat,
					new TypeReference<ProposalManageDTO>() {
			});
			
			
			service.updateProposalManageSensitive(proposalManageDTO);
			
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
	 * 讨论区默认部门
	 * 
	 * @param id当前部门ID
	 *            
	 * @param request
	 *            请求
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operation/topDeptNumber")
	public ModelAndView toTopDeptNumber(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		String id= request.getParameter("id");
		try {
			String comple="";
			//if(!"8a58ab4d4c2141fd014c217cdc5102b6".equals(id)){
				String deptNameId = sysDeptLoader.getTopSysDeptNameBySysDeptId(id,"zh_CN");
				if("601研究所".equals(deptNameId)){//所
					comple="1";
				}else if("320制造厂".equals(deptNameId)){//厂
					comple="2";
				}else{
					comple="0";
				}
			/*}else{//0代表全部
				comple="0";
			}*/
			//返回前台
			mav.addObject("flag", OpResult.success);
			mav.addObject("complex",comple);
			
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			mav.addObject("error", ex.getMessage());
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}
	
	
	/**
	 * 根据厂区所区的不同，加载当前零件所对应的 厂区专业+所区专业名称。
	 * @param complex
	 * @author wuc
	 * @param strid
	 * @param request 请求
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operation/getdeptLi")
	@ResponseBody
	public Map<String, Object> getdeptLi(HttpServletRequest request) throws Exception {
		HashMap<String, Object> maps = new HashMap<String, Object>();
		String complex= request.getParameter("complex");
		String strid= request.getParameter("strid");
		try {
			// 零件对应的专业以及人员
			 List<DiscussionManageDTO> list = new ArrayList<>();
			if (complex != null) {
				if("1".equals(complex)){//所
					list = disdao.findMajorDeptByStrId(strid);
				}else if("2".equals(complex)){//厂
					list = disdao.findFactoryDeptByStrId(strid);
				}else{//其它
					list = disdao.findDeptByStrId(strid);
				}
			}
			List<Map<String, String >> listmap= new ArrayList<>();
			for (Iterator<DiscussionManageDTO> dto = list.iterator();dto.hasNext();) {
				DiscussionManageDTO  dd = dto.next();
				Map<String,String> map = new HashMap<>();
				//获取ID所对应的专业名称
				String deptName =sysDeptLoader.getSysDeptNameBySysDeptId(dd.getWorkshopId(),
						SessionHelper.getCurrentLanguageCode(request));
				//放入MAP中
				if(deptName!=null && !"".equals(deptName)){
					map.put(deptName, dd.getWorkshopId());
					listmap.add(map);
				}
			}
			//返回前台
			maps.put("flag", OpResult.success);
			maps.put("rows",listmap);
			
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			maps.put("error", ex.getMessage());
			maps.put("flag", OpResult.failure);
			return maps;
		}
		return maps;
	}
}
