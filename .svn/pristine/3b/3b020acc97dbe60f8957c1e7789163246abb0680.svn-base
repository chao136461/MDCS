package avicit.discussion_manage.regionconfiguration.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
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
import avicit.platform6.api.syslookup.SysLookupAPI;
import avicit.platform6.api.syslookup.dto.SysLookupSimpleVo;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;

import avicit.discussion_manage.regionconfiguration.dto.RegionConfigurationDTO;
import avicit.discussion_manage.regionconfiguration.service.RegionConfigurationService;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @classname: RegionConfigurationController
 * @description: 定义 区间配置表 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/regionconfiguration/RegionConfigurationController")
public class RegionConfigurationController implements LoaderConstant {
	private static final Logger logger = LoggerFactory
			.getLogger(RegionConfigurationController.class);

	@Autowired
	private RegionConfigurationService service;
	
	@Autowired
	private SysLookupAPI sysLookupAPI;

	/**
	 * 跳转到管理页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "RegionConfigurationInfo")
	public ModelAndView toRegionConfiguration(HttpServletRequest request,
			HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/regionconfiguration/RegionConfigurationManage");
		request.setAttribute(
				"url",
				"platform/discussion_manage/regionconfiguration/RegionConfigurationController/operation/");
		return mav;
	}

	@RequestMapping(value = "/operation/getRegionConfigurationsByPage")
	@ResponseBody
	public Map<String, Object> togetRegionConfigurationByPage(
			PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<RegionConfigurationDTO> queryReqBean = new QueryReqBean<RegionConfigurationDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param",
				"");
		String sfnConditions = ServletRequestUtils.getStringParameter(request,
				"sdfConditons", "");// 自定义查询条件
		RegionConfigurationDTO param = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QueryRespBean<RegionConfigurationDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat,
					new TypeReference<RegionConfigurationDTO>() {
					});
			queryReqBean.setSearchParams(param);
		}
		try {
			result = service.searchRegionConfigurationByPage(queryReqBean,
					sfnConditions);

		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return map;
		}

		for (RegionConfigurationDTO dto : result.getResult()) {

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
			RegionConfigurationDTO dto = service
					.queryRegionConfigurationByPrimaryKey(id);

			request.setAttribute("regionConfigurationDTO", dto);
		}
		Collection<SysLookupSimpleVo> list = sysLookupAPI.getLookUpListByType("REGION_LEVEL");
		mav.addObject("list", list);
		
		Collection<SysLookupSimpleVo> state = sysLookupAPI.getLookUpListByType("REGION_STATE");
		
		mav.addObject("state", state);
		mav.setViewName("avicit/discussion_manage/regionconfiguration/" + "RegionConfiguration" + type);
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
	public ModelAndView toSaveRegionConfigurationDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request,
				"data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			RegionConfigurationDTO regionConfigurationDTO = JsonHelper.getInstance().readValue(jsonData, dateFormat,new TypeReference<RegionConfigurationDTO>() {});
			if ("".equals(regionConfigurationDTO.getId())) {// 新增
				//如果是一级时开始天数默认为0
				if("1".equals(regionConfigurationDTO.getRegionLevel())){
					regionConfigurationDTO.setBeginDay("0");
				}
				
				service.insertRegionConfiguration(regionConfigurationDTO);
			} else {// 修改
				service.updateRegionConfigurationSensitive(regionConfigurationDTO);
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
	public ModelAndView toDeleteRegionConfigurationDTO(
			@RequestBody String[] ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			// LogBase logBase = LogBaseFactory.getLogBase( request);
			service.deleteRegionConfigurationByIds(ids);
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
	 * 通过型号和等级查询单条记录
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operation/fingData", method = RequestMethod.POST)
	public ModelAndView findRegionConfiguration(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		RegionConfigurationDTO dto = null;
		try {
			String regionLevel = ServletRequestUtils.getStringParameter(request, "regionLevel","");
			String typeId = ServletRequestUtils.getStringParameter(request, "typeId","");
			//等级为一级时，查询是否有一级已经存在
			if("1".equals(regionLevel)){
				dto = service.findRegionConfiguration(regionLevel, typeId);
				if(dto != null && !"".equals(dto)){
					return mav.addObject("flag", OpResult.failure);
				}
			}
			//等级为二级时，查询是否有二级已经存在
			if("2".equals(regionLevel)){ 
				dto = service.findRegionConfiguration(regionLevel, typeId);
				if(dto == null || "".equals(dto)){
					//如果二级不存在，则查询是否存在一级
					regionLevel = "1";
					dto = service.findRegionConfiguration(regionLevel, typeId);
					if(dto == null || "".equals(dto)){
						return mav.addObject("flag", "flag2");
					}
					return mav.addObject("dto",dto);
				}else{
					return mav.addObject("flag", OpResult.failure);
				}
			}
			//等级为三级时，查询是否有三级已经存在
			if("3".equals(regionLevel)){ 
				dto = service.findRegionConfiguration(regionLevel, typeId);
				if(dto == null || "".equals(dto)){
					//如果三级不存在，则查询是否存在二级
					regionLevel = "2";
					dto = service.findRegionConfiguration(regionLevel, typeId);
					if(dto == null || "".equals(dto)){
						return mav.addObject("flag", "flag3");
					}
					return mav.addObject("dto",dto);
				}else{
					return mav.addObject("flag", OpResult.failure);
				}
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return mav;
		
	}
}
