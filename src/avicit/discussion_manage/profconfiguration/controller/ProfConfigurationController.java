package avicit.discussion_manage.profconfiguration.controller;

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
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;

import avicit.discussion_manage.factorycomplex.service.FactoryComplexService;
import avicit.discussion_manage.profconfiguration.dto.ProfConfigurationDTO;
import avicit.discussion_manage.profconfiguration.service.ProfConfigurationService;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @classname: ProfConfigurationController
 * @description: 定义 专业配置表 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/profconfiguration/ProfConfigurationController")
public class ProfConfigurationController implements LoaderConstant {
	private static final Logger logger = LoggerFactory
			.getLogger(ProfConfigurationController.class);

	@Autowired
	private ProfConfigurationService service;
	
	@Autowired
	private FactoryComplexService factoryComplexService;

	/**
	 * 跳转到管理页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "ProfConfigurationInfo")
	public ModelAndView toProfConfiguration(HttpServletRequest request,
			HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/profconfiguration/ProfConfigurationManage");
		request.setAttribute(
				"url",
				"platform/discussion_manage/profconfiguration/ProfConfigurationController/operation/");
		return mav;
	}

	@RequestMapping(value = "/operation/getProfConfigurationsByPage")
	@ResponseBody
	public Map<String, Object> togetProfConfigurationByPage(
			PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<ProfConfigurationDTO> queryReqBean = new QueryReqBean<ProfConfigurationDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param",
				"");
		String sfnConditions = ServletRequestUtils.getStringParameter(request,
				"sdfConditons", "");// 自定义查询条件
		
		ProfConfigurationDTO param = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QueryRespBean<ProfConfigurationDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat,
					new TypeReference<ProfConfigurationDTO>() {
					});
			
			queryReqBean.setSearchParams(param);
		}
		try {
			result = service.searchProfConfigurationByPage(queryReqBean,
					sfnConditions);

		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return map;
		}

		for (ProfConfigurationDTO dto : result.getResult()) {
				if(dto.getProfession() !=null && !"".equals(dto.getProfession())){
					String[] strs = dto.getProfession().split(",");
					StringBuffer buf =new StringBuffer();
					for (String string : strs) {
						String  professionAlia = sysDeptLoader.getSysDeptNameBySysDeptId(string,SessionHelper.getCurrentLanguageCode(request));
						buf.append(professionAlia);
						buf.append(";");
					}
					String professionAlias =  buf.toString();
					dto.setProfessionAlias(professionAlias);
					
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
	public ModelAndView toOpertionPage(@PathVariable String type,
			@PathVariable String id, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		if (!"null".equals(id)) {// 编辑窗口或者详细页窗口
			// 主表数据
			// LogBase logBase = LogBaseFactory.getLogBase(request);
			ProfConfigurationDTO dto = service.queryProfConfigurationByPrimaryKey(id);

			if(dto.getProfession() !=null && !"".equals(dto.getProfession())){
				String[] strs = dto.getProfession().split(",");
				StringBuffer buf =new StringBuffer();
				for (String string : strs) {
					String  professionAlia = sysDeptLoader.getSysDeptNameBySysDeptId(string,SessionHelper.getCurrentLanguageCode(request));
					buf.append(professionAlia);
					buf.append(";");
				}
				String professionAlias =  buf.toString();
				dto.setProfessionAlias(professionAlias);
				
			}

			request.setAttribute("profConfigurationDTO", dto);
		}
		mav.setViewName("avicit/discussion_manage/profconfiguration/"
				+ "ProfConfiguration" + type);
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
	public ModelAndView toSaveProfConfigurationDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		QueryReqBean<ProfConfigurationDTO> queryReqBean = new QueryReqBean<ProfConfigurationDTO>();
		ProfConfigurationDTO searchParams = new ProfConfigurationDTO();
		String pid = ServletRequestUtils.getStringParameter(request,
				"pid", "");
		String jsonData = ServletRequestUtils.getStringParameter(request,
				"data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			ProfConfigurationDTO profConfigurationDTO = JsonHelper.getInstance().readValue(jsonData, dateFormat,new TypeReference<ProfConfigurationDTO>() {});
			//给厂区添加专业
			factoryComplexService.factoryDeptUtil(profConfigurationDTO.getProfession());
			
			//给厂所赋值
			if(pid!=null && !"".equals(pid)){
				profConfigurationDTO.setScene(pid);
			}
			if ("".equals(profConfigurationDTO.getId())) {// 新增
				if("601".equals(profConfigurationDTO.getScene())){
					//根据型号和类别验证是否有已经存在的相同数据
					searchParams.setTypeId(profConfigurationDTO.getTypeId());
					searchParams.setCategory(profConfigurationDTO.getCategory());
					queryReqBean.setSearchParams(searchParams);
					List<ProfConfigurationDTO> list =service.searchProfConfiguration(queryReqBean);
					if(null == list || list.size() ==0 ){
						service.insertProfConfiguration(profConfigurationDTO);
					}else{
						 return mav.addObject("flag2", "flag2");
					}
				}else{
					//根据型号和厂所验证是否有已经存在的相同数据
					searchParams.setTypeId(profConfigurationDTO.getTypeId());
					searchParams.setScene(profConfigurationDTO.getScene());
					queryReqBean.setSearchParams(searchParams);
					List<ProfConfigurationDTO> list =service.searchProfConfiguration(queryReqBean);
					if(null == list || list.size() ==0 ){
						service.insertProfConfiguration(profConfigurationDTO);
					}else{
						return mav.addObject("flag2", "flag2");
					}
				}	
					
			} else {// 修改
				service.updateProfConfigurationSensitive(profConfigurationDTO);
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
	public ModelAndView toDeleteProfConfigurationDTO(@RequestBody String[] ids,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			// LogBase logBase = LogBaseFactory.getLogBase( request);
			service.deleteProfConfigurationByIds(ids);
			mav.addObject("flag", OpResult.success);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			mav.addObject("error", ex.getMessage());
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}
	
	@RequestMapping(value = "/operation/getProfConfigurations")
	@ResponseBody
	public ModelAndView getProfConfigurations(
			 HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		QueryReqBean<ProfConfigurationDTO> queryReqBean = new QueryReqBean<ProfConfigurationDTO>();
		ProfConfigurationDTO  dto=new ProfConfigurationDTO();
		String factory = ServletRequestUtils.getStringParameter(request,
				"factory", "");
		String typeId = ServletRequestUtils.getStringParameter(request,
				"typeId", "");
		/*if("601".equals(factory)){
			dto.setCategory("2");
		}*/
		dto.setTypeId(typeId);
		dto.setScene(factory);
		queryReqBean.setSearchParams(dto);
		List<ProfConfigurationDTO> list=null;
		try {
			list = service.searchProfConfiguration(queryReqBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<String> nameList=new ArrayList<String>();
		for (ProfConfigurationDTO profConfigurationDTO : list) {
			String profession =profConfigurationDTO.getProfession();
			String[] professionId=profession.split(",");
			for(int i=0;i<professionId.length;i++){
				String professionName =sysDeptLoader.getSysDeptNameBySysDeptId(professionId[i], SessionHelper.getCurrentLanguageCode());
				nameList.add(professionName);
			}
		}
		mav.addObject("nameList",nameList);
		return mav;
		}
		
	
}
