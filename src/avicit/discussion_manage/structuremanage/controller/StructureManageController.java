package avicit.discussion_manage.structuremanage.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
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
import avicit.platform6.api.sysuser.dto.SysDept;
import avicit.platform6.api.sysuser.dto.SysRole;
import avicit.platform6.api.sysuser.dto.SysUser;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.excel.export.AbstraceExcelExport;
import avicit.platform6.core.excel.imp.ExcelImport;
import avicit.platform6.core.excel.imp.entity.ExcelHeader;
import avicit.platform6.core.excel.imp.inf.ImportOpt;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.modules.system.excelimport.orguser.AbstractExcelImport;
import avicit.platform6.modules.system.sysorguser.sysuser.service.SysUserLoader;

import avicit.discussion_manage.majormanage.dto.MajorManageDTO;
import avicit.discussion_manage.structuralrelationship.service.StructuralRelationshipService;
import avicit.discussion_manage.structureDataSynchronize.util.ExportExcel;
import avicit.discussion_manage.structuremanage.dao.StructureManageDao;
import avicit.discussion_manage.structuremanage.dto.ResearchDTO;
import avicit.discussion_manage.structuremanage.dto.StructureManageDTO;
import avicit.discussion_manage.structuremanage.service.StructureManageService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.itextpdf.text.log.SysoLogger;

import avicit.discussion_manage.majormanage.dto.InstituteCensorManageDTO;
import avicit.discussion_manage.majormanage.service.InstituteCensorManageService;
import avicit.discussion_manage.majormanage.service.MajorManageService;
import avicit.discussion_manage.modelInterveneCheck.ws.impl.T;

/**
 * @classname: StructureManageController
 * @description: 定义 装配件结构管理 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/structuremanage/StructureManageController")
public class StructureManageController extends AbstractExcelImport implements
		LoaderConstant {
	private static final Logger logger = LoggerFactory
			.getLogger(StructureManageController.class);

	@Autowired
	private StructureManageService service;
	@Autowired
	private MajorManageService majorService;
	@Autowired
	private InstituteCensorManageService instituteService;
	@Autowired
	private  StructuralRelationshipService relationshipService;
	@Autowired
	private StructureManageDao dao;

	/**
	 * 跳转到管理页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "StructureManageInfo")
	public ModelAndView toStructureManage(HttpServletRequest request,
			HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/structuremanage/StructureManageManage");
		request.setAttribute(
				"url",
				"platform/discussion_manage/structuremanage/StructureManageController/operation/");
		return mav;
	}

	/**
	 * 跳转到管理页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "StructureManagesInfo")
	public ModelAndView toStructureManages(HttpServletRequest request,
			HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/structuremanage/StructureManageManages");
		request.setAttribute(
				"url",
				"platform/discussion_manage/structuremanage/StructureManageController/operation/");
		return mav;
	}

	@RequestMapping(value = "/operation/getStructureManagesByPage")
	@ResponseBody
	public Map<String, Object> togetStructureManageByPage(
			PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<StructureManageDTO> queryReqBean = new QueryReqBean<StructureManageDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param",
				"");
		String sfnConditions = ServletRequestUtils.getStringParameter(request,
				"sdfConditons", "");// 自定义查询条件
		StructureManageDTO param = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		QueryRespBean<StructureManageDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat,
					new TypeReference<StructureManageDTO>() {
					});
			queryReqBean.setSearchParams(param);
		}
		try {
			result = service.searchStructureManageByPage(queryReqBean,
					sfnConditions);

		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return map;
		}

		for (StructureManageDTO dto : result.getResult()) {
			dto.setDetail("详细信息");
			dto.setDesignerId(sysUserLoader.getSysUserNameById(dto.getDesignerId()));
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
			StructureManageDTO dto = service
					.queryStructureManageByPrimaryKey(id);
			/*dto.setDesignerId(sysUserLoader.getSysUserNameById(dto.getDesignerId()));*/
			request.setAttribute("structureManageDTO", dto);
		}
		mav.setViewName("avicit/discussion_manage/structuremanage/"
				+ "StructureManage" + type);
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
	public ModelAndView toSaveStructureManageDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request,
				"data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			StructureManageDTO structureManageDTO = JsonHelper.getInstance()
					.readValue(jsonData, dateFormat,
							new TypeReference<StructureManageDTO>() {
							});
			if ("".equals(structureManageDTO.getId())) {// 新增
				service.insertStructureManage(structureManageDTO);
			} else {// 修改
				service.updateStructureManageSensitive(structureManageDTO);
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
	public ModelAndView toDeleteStructureManageDTO(@RequestBody String[] ids,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			// LogBase logBase = LogBaseFactory.getLogBase( request);
			service.deleteStructureManageByIds(ids);
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
	 * 601所审查人员Excel导入
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author xul
	 */
	@RequestMapping(value = "/operation/importExcelData")
	public Map<String, String> importExcelData(HttpServletRequest request)
			throws Exception {
		// 以下三行代码是Excel导入的基本信息
		Map<String, String> map = new HashMap<String, String>(2);
		// 取到前台传的json对象
		String datas = ServletRequestUtils.getStringParameter(request, "datas",
				"");
		// 将json对象转化成map格式
		Map<String, String> param = JsonHelper.getInstance().readValue(datas,
				new TypeReference<HashMap<String, String>>() {
				});
		// 创建公共Excel导入器
		ExcelImport excelImport = new ExcelImport();
		// 添加导入执行需要的参数
		excelImport.setInfo(param);
		// 创建业务自己的导入执行器
		majorExcutor majorExcutor = new majorExcutor("审查人员导入");
		final List<ExcelHeader> headers = majorExcutor.getHeaders();
		/* final List<SysDept> deptlist = new ArrayList<>(); */
		final Map<String, String> deptMap = new HashMap<String, String>();
		for (int i = 0; i < headers.size(); i++) {
			ExcelHeader excelHeader = headers.get(i);
			if (!excelHeader.getKey().equals("itemCode")&&!excelHeader.getKey().equals("isvci")) {
				SysDept sysdept = sysDeptLoader.getSysDeptBySysDeptName(
						excelHeader.getValue(),
						SessionHelper.getSystemDefaultLanguageCode(request));
				deptMap.put(excelHeader.getKey(), sysdept.getId());

			}
		}
		// 加载定义的执行器到公共Excel导入器
		excelImport.setExcutor(majorExcutor);
		// 为执行器添加持久化方法，创健一个内部类实现方法；
		majorExcutor.setImportOpt(new ImportOpt() {

			@Override
			public void import2Db(Map<String, Object> data) throws Exception {
				import2D(data, deptMap);
			}
		});
		// import2Db()该方法为导入器的回调方法，传递经过校验器验证的一条Excel数据给业务系统自己完成持久化操作，
		// data对象中的Key为导入器中配置的ExcelHeader中的key。

		// 调用执行器的导入方法
		excelImport.importExcel();
		// 保存导入记录到数据中
		super.generate2Db(excelImport.getResult(),
				SessionHelper.getApplicationId(),
				SessionHelper.getSystemDefaultLanguageCode(request),
				SessionHelper.getLoginName(request));
		map.put("flag", "success");
		map.put("msg", excelImport.getResult().getMessage());
		return map;

	}

	public void import2D(Map<String, Object> data, Map<String, String> deptMap)
			throws Exception {
		// 实例化对象
		ResearchDTO researchDTO = new ResearchDTO();
		// 把map对象转换为json字符串
		String str = JsonHelper.getInstance().writeValueAsString(data);
		// //json对象转换为实体对象

		researchDTO = JsonHelper.getInstance().readValue(str, ResearchDTO.class);
		MajorManageDTO manageDTO = new MajorManageDTO();
		String isvci = researchDTO.getIsvci();

		if (isvci.equals("是")) {
			String itemcode = researchDTO.getItemCode();
			List<String> StrIdList = relationshipService.getStrIdByCode(itemcode);
			for (int i = 0; i < StrIdList.size(); i++) {
				if (i != 0) {
					for (String obj : deptMap.keySet()) {
						String id = ComUtil.getId();
						String itemId = StrIdList.get(i);
						String majId = majorService.findParamyId(
								deptMap.get(obj), itemId);
						if (null != majId && !"".equals(majId)) {
							majorService.deleteMajorManageById(majId);
							manageDTO.setId(id);
							manageDTO.setStrId(itemId);
							manageDTO.setStdId(deptMap.get(obj));
							majorService.insertMajorManage(manageDTO);
							if (obj.equals("intensity")) {
								importInstituteCensor(itemId, id,
										researchDTO.getIntensity());
							}
							if (obj.equals("corrector")) {
								importInstituteCensor(itemId, id,
										researchDTO.getCorrector());
							}
							if (obj.equals("quality")) {
								importInstituteCensor(itemId, id,
										researchDTO.getQuality());
							}
							if (obj.equals("reliable")) {
								importInstituteCensor(itemId, id,
										researchDTO.getReliable());
							}
							if (obj.equals("standard")) {
								importInstituteCensor(itemId, id,
										researchDTO.getStandard());
							}
							if (obj.equals("material")) {
								importInstituteCensor(itemId, id,
										researchDTO.getMaterial());
							}
							if (obj.equals("normalized")) {
								importInstituteCensor(itemId, id,
										researchDTO.getNormalized());
							}
							if (obj.equals("weight")) {
								importInstituteCensor(itemId, id,
										researchDTO.getWeight());
							}
						} else {
							manageDTO.setId(id);
							manageDTO.setStrId(itemId);
							manageDTO.setStdId(deptMap.get(obj));
							majorService.insertMajorManage(manageDTO);
							if (obj.equals("intensity")) {
								importInstituteCensor(itemId, id,
										researchDTO.getIntensity());
							}
							if (obj.equals("corrector")) {
								importInstituteCensor(itemId, id,
										researchDTO.getCorrector());
							}
							if (obj.equals("quality")) {
								importInstituteCensor(itemId, id,
										researchDTO.getQuality());
							}
							if (obj.equals("reliable")) {
								importInstituteCensor(itemId, id,
										researchDTO.getReliable());
							}
							if (obj.equals("standard")) {
								importInstituteCensor(itemId, id,
										researchDTO.getStandard());
							}
							if (obj.equals("material")) {
								importInstituteCensor(itemId, id,
										researchDTO.getMaterial());
							}
							if (obj.equals("normalized")) {
								importInstituteCensor(itemId, id,
										researchDTO.getNormalized());
							}
							if (obj.equals("weight")) {
								importInstituteCensor(itemId, id,
										researchDTO.getWeight());
							}
						}
					}
			}
			}
		} else {
			for (String obj : deptMap.keySet()) {
				String id = ComUtil.getId();
				String itemcode = researchDTO.getItemCode();
				String itemId = service.findByCode(itemcode).getId();
				String majId = majorService.findParamyId(deptMap.get(obj),
						itemId);
				if (null != majId && !"".equals(majId)) {
					majorService.deleteMajorManageById(majId);
				}
				manageDTO.setId(id);
				manageDTO.setStrId(itemId);
				manageDTO.setStdId(deptMap.get(obj));
				majorService.insertMajorManage(manageDTO);
				if (obj.equals("intensity")) {
					importInstituteCensor(itemId, id,
							researchDTO.getIntensity());
				}
				if (obj.equals("corrector")) {
					importInstituteCensor(itemId, id,
							researchDTO.getCorrector());
				}
				if (obj.equals("quality")) {
					importInstituteCensor(itemId, id, researchDTO.getQuality());
				}
				if (obj.equals("reliable")) {
					importInstituteCensor(itemId, id, researchDTO.getReliable());
				}
				if (obj.equals("standard")) {
					importInstituteCensor(itemId, id, researchDTO.getStandard());
				}
				if (obj.equals("material")) {
					importInstituteCensor(itemId, id, researchDTO.getMaterial());
				}
				if (obj.equals("normalized")) {
					importInstituteCensor(itemId, id,
							researchDTO.getNormalized());
				}
				if (obj.equals("weight")) {
					importInstituteCensor(itemId, id, researchDTO.getWeight());
				}

			}
		}
	}
	/**
	 * 子表导入
	 * @author xul
	 * */
	public void importInstituteCensor(String itemId,String id,String data)
			throws Exception {
		InstituteCensorManageDTO instituteCensorManageDTO = new InstituteCensorManageDTO();
		SysUser excelUser = null;
		
		if (data.contains("-")) {
			String [] excelUsers = data.split("-");
			for (int i = 0; i < excelUsers.length; i++) {
				instituteCensorManageDTO.setId(ComUtil.getId());
				instituteCensorManageDTO.setStrId(itemId);
				instituteCensorManageDTO.setMajId(id);
				instituteCensorManageDTO.setStatus("1");
				String excelUse = excelUsers[i];
				excelUser = sysUserLoader.getSysUserByLoginName(excelUse);
				String deptid = sysUserDeptPositionLoader.getChiefDeptIdBySysUserId(excelUser.getId());
				List<SysRole> roses = sysRoleLoader.getRolesByUserId(excelUser.getId(), "1");
				instituteCensorManageDTO.setDutyUserId(excelUser.getId());
				instituteCensorManageDTO.setDutyDeptId(deptid);
				for (SysRole sysrole : roses) {
					instituteCensorManageDTO.setDutyUserRole(sysrole.getId());
				}
				instituteService.insertInstituteCensorManage(instituteCensorManageDTO);
			}
		}else{
			instituteCensorManageDTO.setId(ComUtil.getId());
			instituteCensorManageDTO.setStrId(itemId);
			instituteCensorManageDTO.setMajId(id);
			instituteCensorManageDTO.setStatus("1");
			excelUser = sysUserLoader.getSysUserByLoginName(data);
			String deptid = sysUserDeptPositionLoader
					.getChiefDeptIdBySysUserId(excelUser.getId());
			List<SysRole> roses = sysRoleLoader.getRolesByUserId(
					excelUser.getId(), "1");
			instituteCensorManageDTO.setDutyUserId(excelUser.getId());
			instituteCensorManageDTO.setDutyDeptId(deptid);
			for (SysRole sysrole : roses) {
				instituteCensorManageDTO.setDutyUserRole(sysrole.getId());
			}
			instituteService.insertInstituteCensorManage(instituteCensorManageDTO);
		}
	}
	@RequestMapping(value = "/operation/getStructureManagesCombox")
	@ResponseBody
	public String togetStructureManageCombox(
			PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<StructureManageDTO> queryReqBean = new QueryReqBean<StructureManageDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param",
				"");
		String comboxJson="";
		StructureManageDTO param = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<StructureManageDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json, dateFormat,
					new TypeReference<StructureManageDTO>() {
					});
			queryReqBean.setSearchParams(param);
		}
		try {
			Gson gs = new Gson();
			result = service.searchStructureManage(queryReqBean);
			
			comboxJson=gs.toJson(result);

		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return comboxJson;
		}
		
		return comboxJson;
	}
	
	
	/**
	 * 保存数据
	 * 
	 * @param id
	 *            主键id
	 * @param demoBusinessTripDTO
	 *            保存的对象
	 */
	@RequestMapping(value = "/operation/exportData")
	public void exportData(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };
		ExportExcel<T> excel = new ExportExcel<>();
		List dataset = new ArrayList<>();
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			excel.exportExcel("ceshi", headers, dataset,out, "yyyy-MM-dd");
			//out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        JOptionPane.showMessageDialog(null, "导出成功!");  
	        System.out.println("excel导出成功！");

	}
	
	
}
