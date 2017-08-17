package avicit.discussion_manage.factorycomplex.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import avicit.discussion_manage.factorycomplex.dto.ExcelFactoryComplexDTO;
import avicit.discussion_manage.factorycomplex.dto.FactoryCensorManageDTO;
import avicit.discussion_manage.factorycomplex.dto.FactoryComplexDTO;
import avicit.discussion_manage.factorycomplex.service.FactoryCensorManageService;
import avicit.discussion_manage.factorycomplex.service.FactoryComplexService;
import avicit.discussion_manage.profconfiguration.dto.ProfConfigurationDTO;
import avicit.discussion_manage.profconfiguration.service.ProfConfigurationService;
import avicit.discussion_manage.structuremanage.controller.FactoryComplexExcutor;
import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.api.sysuser.dto.SysDept;
import avicit.platform6.api.sysuser.dto.SysUser;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.excel.imp.ExcelImport;
import avicit.platform6.core.excel.imp.entity.ExcelHeader;
import avicit.platform6.core.excel.imp.inf.ImportOpt;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.modules.system.excelimport.orguser.AbstractExcelImport;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @classname: FactoryComplexController
 * @description: 定义 厂区车间表 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/factorycomplex/FactoryComplexController")
public class FactoryComplexController extends AbstractExcelImport implements LoaderConstant {
	private static final Logger logger = LoggerFactory
			.getLogger(FactoryComplexController.class);

	@Autowired
	private FactoryComplexService service;
	@Autowired
	private FactoryCensorManageService serviceSub;
	@Autowired
	private ProfConfigurationService proService;

	/**
	 * 跳转到管理页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "FactoryComplexInfo")
	public ModelAndView toFactoryComplexInfo(HttpServletRequest request,
			HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		String className = request.getParameter("className");
		try {
			className = URLDecoder.decode(className, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		request.setAttribute("id", id);
		request.setAttribute("className", className);
		
		mav.setViewName("avicit/discussion_manage/factorycomplex/FactoryComplexManage");
		request.setAttribute("url",
				"platform/discussion_manage/factorycomplex/FactoryComplexController/operation/");
		request.setAttribute(
				"surl",
				"platform/discussion_manage/factorycomplex/FactoryComplexController/operation/sub/");
		return mav;
	}
	/**
	 * 跳转到部门人员维护页面
	 * 
	 * @param request
	 *            请求
	 * @param reponse
	 *            响应
	 * @return
	 */
	@RequestMapping(value = "FactoryComplexTemplate")
	public ModelAndView toFactoryComplexTemplate(HttpServletRequest request,
			HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/factorycomplex/FactoryComplexTemplate");
		request.setAttribute("url",
				"platform/discussion_manage/factorycomplex/FactoryComplexController/operation/");
		request.setAttribute(
				"surl",
				"platform/discussion_manage/factorycomplex/FactoryComplexController/operation/sub/");
		return mav;
	}
	@RequestMapping(value = "/operation/getFactoryComplexsByPage")
	@ResponseBody
	public Map<String, Object> toGetFactoryComplexByPage(
			PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<FactoryComplexDTO> queryReqBean = new QueryReqBean<FactoryComplexDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param",
				"");
		String sfnConditions = ServletRequestUtils.getStringParameter(request,
				"sdfConditons", "");// 自定义查询条件
		FactoryComplexDTO param = null;
		QueryRespBean<FactoryComplexDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json,
					new TypeReference<FactoryComplexDTO>() {
			});
			queryReqBean.setSearchParams(param);
		}
		try {
			result = service.searchFactoryComplexByPage(queryReqBean,
					sfnConditions);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			return map;
		}
		
		for (FactoryComplexDTO dto : result.getResult()) {
			
			dto.setWorkshopIdAlias(sysDeptLoader.getSysDeptNameBySysDeptId(
					dto.getWorkshopId(),
					SessionHelper.getCurrentLanguageCode(request)));
			
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
		String className = request.getParameter("factorName");
		String  factoryId = request.getParameter("factoryId");
		if (!"null".equals(id)) {// 编辑窗口或者详细页窗口
			// 主表数据
			FactoryComplexDTO dto = service.queryFactoryComplexByPrimaryKey(id);

			dto.setWorkshopIdAlias(sysDeptLoader.getSysDeptNameBySysDeptId(
					dto.getWorkshopId(),
					SessionHelper.getCurrentLanguageCode(request)));

			request.setAttribute("factoryComplexDTO", dto);
		}
		request.setAttribute("factoryId", factoryId);
		try {
			className = URLDecoder.decode(className, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		request.setAttribute("className", className);
		mav.setViewName("avicit/discussion_manage/factorycomplex/"
				+ "FactoryComplex" + type);
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
	public ModelAndView toSaveFactoryComplexDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			String jsonData = ServletRequestUtils.getStringParameter(request,
					"data", "");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			FactoryComplexDTO factoryComplexDTO = JsonHelper.getInstance()
					.readValue(jsonData, dateFormat,
							new TypeReference<FactoryComplexDTO>() {
							});
			String factoryid = service.findByDeptId(factoryComplexDTO.getWorkshopId(),factoryComplexDTO.getStrId());
			if (null!=factoryid&&!"".equals(factoryid)) {
				String factorDept = sysDeptLoader.getSysDeptNameBySysDeptId(factoryComplexDTO.getWorkshopId(),SessionHelper.getCurrentLanguageCode(request));
				throw new DaoException(factorDept+"已经存在，请从新选择");
			}
			if ("".equals(factoryComplexDTO.getId())) {// 新增
				if ("".equals(factoryComplexDTO.getStrId())) {
					String id = ComUtil.getId();
					factoryComplexDTO.setId(id);
					factoryComplexDTO.setTemplate("Y");
				}
				service.insertFactoryComplex(factoryComplexDTO);
			} else {// 修改
				
				service.updateFactoryComplexSensitive(factoryComplexDTO);
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
	public ModelAndView toDeleteFactoryComplexDTO(@RequestBody String[] ids,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			service.deleteFactoryComplexByIds(ids);
			mav.addObject("flag", OpResult.success);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			mav.addObject("error", ex.getMessage());
			mav.addObject("flag", OpResult.failure);
			return mav;
		}
		return mav;
	}

	/****************************
	 * 子表操作***************************** /** 按照pid查找子表数据
	 * 
	 * @param ids
	 *            id数组
	 * @return
	 */
	@RequestMapping(value = "/operation/sub/getFactoryCensorManage/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> toGetFactoryCensorManageByPid(
			@PathVariable String pid, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>(1);
		try {
			List<FactoryCensorManageDTO> list = serviceSub
					.queryFactoryCensorManageByPid(pid);

			for (FactoryCensorManageDTO dto : list) {

				dto.setDutyUserRoleAlias(sysRoleLoader.getSysRoleNameById(dto
						.getDutyUserRole()));
				dto.setDutyUserIdAlias(sysUserLoader.getSysUserNameById(dto
						.getDutyUserId()));

				dto.setDutyDeptIdAlias(sysDeptLoader.getSysDeptNameBySysDeptId(
						dto.getDutyDeptId(),
						SessionHelper.getCurrentLanguageCode(request)));

			}

			map.put("total", list.size());
			map.put("rows", list);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			return map;
		}
		return map;
	}

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
	@RequestMapping(value = "/operation/sub/{type}/{id}")
	public ModelAndView toOpertionSubPage(@PathVariable String type,
			@PathVariable String id, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		if (!"Add".equals(type)) {// 编辑窗口或者详细页窗口
			// 主表数据
			FactoryCensorManageDTO dto = serviceSub
					.queryFactoryCensorManageByPrimaryKey(id);

			dto.setDutyUserRoleAlias(sysRoleLoader.getSysRoleNameById(dto
					.getDutyUserRole()));
			dto.setDutyUserIdAlias(sysUserLoader.getSysUserNameById(dto
					.getDutyUserId()));

			dto.setDutyDeptIdAlias(sysDeptLoader.getSysDeptNameBySysDeptId(
					dto.getDutyDeptId(),
					SessionHelper.getCurrentLanguageCode(request)));

			request.setAttribute("factoryCensorManageDTO", dto);
		} else {
			request.setAttribute("pid", id);
		}
		mav.setViewName("avicit/discussion_manage/factorycomplex/"
				+ "FactoryCensorManage" + type);
		return mav;
	}

	/**
	 * 保存数据
	 * 
	 * @param id
	 *            主键id
	 * @param factoryCensorManageDTO
	 *            保存的对象
	 * @return
	 */
	@RequestMapping(value = "/operation/sub/save", method = RequestMethod.POST)
	public ModelAndView toSaveFactoryCensorManageDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request,
				"data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			FactoryCensorManageDTO factoryCensorManageDTO = JsonHelper
					.getInstance().readValue(jsonData, dateFormat,
							new TypeReference<FactoryCensorManageDTO>() {
							});
			if ("".equals(factoryCensorManageDTO.getId())) {// 新增
				serviceSub.insertFactoryCensorManage(factoryCensorManageDTO);
			} else {// 修改
				serviceSub
						.updateFactoryCensorManageSensitive(factoryCensorManageDTO);
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
	@RequestMapping(value = "/operation/sub/delete", method = RequestMethod.POST)
	public ModelAndView toDeleteFactoryCensorManageDTO(
			@RequestBody String[] ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			serviceSub.deleteFactoryCensorManageByIds(ids);
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
	 * FactoryExcel导入
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operation/factoryUser/import")
	public Map<String, String> exportImport(HttpServletRequest request)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>(2);
		String datas = ServletRequestUtils.getStringParameter(request, "datas",
				"");
		Map<String, String> param = JsonHelper.getInstance().readValue(datas,
				new TypeReference<HashMap<String, String>>() {
				});
		// 创建公共Excel导入器
		ExcelImport excelImport = new ExcelImport();

		// 添加导入执行需要的参数
		excelImport.setInfo(param);
		// 创建业务自己的导入执行器
		// 用户导入执行器
		FactoryComplexExcutor supplierExecutor = new FactoryComplexExcutor("用户导入");
		//deptMap车间集合
		final Map<String, String> deptMap = new HashMap<String,String>();
		for (int i = 0,length=supplierExecutor.getHeaders().size(); i < length; i++) {//遍历表头
			//表头集合
			ExcelHeader excelHeader = supplierExecutor.getHeaders().get(i);
			//根据车间名称获取车间ID
			SysDept sysDept = sysDeptLoader.getSysDeptBySysDeptName(excelHeader.getValue(), SessionHelper.getCurrentLanguageCode(request));
			deptMap.put(excelHeader.getKey(), sysDept.getId());
		}
		excelImport.setExcutor(supplierExecutor);
		
		// 为执行器添加持久化方法
		supplierExecutor.setImportOpt(new ImportOpt() {
			@Override
			public void import2Db(Map<String, Object> data) throws Exception {
				importDb(data,deptMap);
			}
		});
		excelImport.importExcel();
		// 保存导入记录到数据中
		super.generate2Db(excelImport.getResult(),
				SessionHelper.getApplicationId(),
				SessionHelper.getSystemDefaultLanguageCode(),
				SessionHelper.getLoginName(request));
		map.put("flag", "success");
		map.put("msg", excelImport.getResult().getMessage());
		return map;

	}
	/**
	 * 内部方法,仅供import导入调用
	 * @param data
	 * @param deptMap
	 * @throws Exception
	 */
	private void importDb(Map<String, Object> data,Map<String, String> deptMap) throws Exception{
		// 把map对象转换为json字符串
		String str = JsonHelper.getInstance().writeValueAsString(data);
		// json对象转换为对象
		ExcelFactoryComplexDTO excelFactoryComplexDTO = JsonHelper.getInstance()
			.readValue(str, ExcelFactoryComplexDTO.class);
		excelFactoryComplexDTO.setId(ComUtil.getId());
		FactoryComplexDTO factoryComplexDTO = new FactoryComplexDTO();
		for (String key : deptMap.keySet()) {//遍历车间集合
			String factoryid = service.findByDeptId(deptMap.get(key),"");
			if(factoryid!=null){//对应车间模板存在则删除
				service.deleteFactoryComplexById(factoryid);
			}
			//平台生成id供主子表关系使用
			String id = ComUtil.getId();
			factoryComplexDTO.setId(id);//车间表主键id
			factoryComplexDTO.setWorkshopId(deptMap.get(key));//车间id
			factoryComplexDTO.setTemplate("Y");//模板状态
			//添加
			service.insertFactoryComplex(factoryComplexDTO);
			if(key=="part"){//零件
				factoryCensorUtil(id, excelFactoryComplexDTO.getPart());
			}
			if(key=="assembly"){//装配
				factoryCensorUtil(id, excelFactoryComplexDTO.getAssembly());
			}
			if(key=="material"){//材料
				factoryCensorUtil(id, excelFactoryComplexDTO.getMaterial());
			}
			if(key=="metallurgy"){//冶金
				factoryCensorUtil(id, excelFactoryComplexDTO.getMetallurgy());
			}
			if(key=="tooling"){//工装
				factoryCensorUtil(id, excelFactoryComplexDTO.getTooling());
			}
			if(key=="lofting"){//模线
				factoryCensorUtil(id, excelFactoryComplexDTO.getLofting());
			}
			if(key=="standardization"){//标准化
				factoryCensorUtil(id, excelFactoryComplexDTO.getStandardization());
			}
			if(key=="procurement"){//采购
				factoryCensorUtil(id, excelFactoryComplexDTO.getProcurement());
			}
			if(key=="verification"){//检验
				factoryCensorUtil(id, excelFactoryComplexDTO.getVerification());
			}
		}
	}
	/**
	 * 仅供import导入调用，执行添加操作
	 * @param id 为主表factoryComplexDTO的主键ID
	 * @param data 用户
	 * @throws Exception
	 */
	private void factoryCensorUtil(String id,String data) throws Exception{
		FactoryCensorManageDTO factoryCensorManageDTO = new FactoryCensorManageDTO();
		if(data.contains("-")){//如果导入的用户为多个
			String [] sysid=data.split("-");//按“-”分割
			for (int i = 0,length=sysid.length; i <length; i++) {
				//按名字查询用户ID
				SysUser sysUser = sysUserLoader.getSysUserByLoginString(sysid[i]);
				if(sysUser!=null){
					//用户ID
					factoryCensorManageDTO.setDutyUserId(sysUser.getId());
					factoryCensorManageDTO.setFcId(id);//字表关联主表id
					//添加
					serviceSub.insertFactoryCensorManage(factoryCensorManageDTO);
				}
			}
		}else{//单个
			//按名字查询用户ID
			SysUser sysUser = sysUserLoader.getSysUserByLoginString(data);
			if(sysUser!=null){
				//用户ID
				factoryCensorManageDTO.setDutyUserId(sysUser.getId());
				factoryCensorManageDTO.setFcId(id);//字表关联主表id
				//添加
				serviceSub.insertFactoryCensorManage(factoryCensorManageDTO);
			}
		}
	}
	/**
	 * 创区模板导出数据
	 * @param typeId 机型
	 * @throws Exception
	 */
	@RequestMapping(value = "/operation/factoryUser/imports")
	public void exportDocList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String typeId = request.getParameter("typeId");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 设置单元格表头
		response.reset();
		response.setContentType("application/msxls");
		response.setHeader("Content-Disposition", "inline; filename="
				+ new String("厂区专业模板.xls".getBytes("GBK"), "ISO8859-1"));
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=30");
		// 设置列文字格式
		WritableFont fontTitle = new WritableFont(
				WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false,
				UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat formatTitle = new WritableCellFormat(fontTitle);
		formatTitle.setAlignment(jxl.format.Alignment.CENTRE);
		formatTitle.setBackground(Colour.LIGHT_GREEN);
		// 设置单元格文字格式
		WritableFont fontBody = new WritableFont(WritableFont.createFont("宋体"),
				10, WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE);
		WritableCellFormat formatBody = new WritableCellFormat(fontBody);
		formatBody.setAlignment(jxl.format.Alignment.CENTRE);
		NumberFormat nf = new NumberFormat("0.00");
		WritableCellFormat wcfN = new WritableCellFormat(fontBody, nf);
		wcfN.setAlignment(jxl.format.Alignment.CENTRE);
		//配置对象
		ProfConfigurationDTO param = new ProfConfigurationDTO();
		param.setTypeId(typeId);//机型
		param.setScene("320");//320厂
		//表头集合
		List<String> list = new ArrayList<String>();
		//配置的创区机型 对应专业对象
		ProfConfigurationDTO prodto = proService.searchProfConfigurationByTypeId(param);
		if(prodto.getProfession()!=null||!"".equals(prodto.getProfession())){
			String str = prodto.getProfession();
			if(str.contains(",")){//如果包含多个则截取
				String [] deptid=str.split(",");//按“,”分割
				for (int i = 0,length=deptid.length; i <length; i++) {
					String  professionAlia = sysDeptLoader.getSysDeptNameBySysDeptId(deptid[i],SessionHelper.getCurrentLanguageCode(request));
					list.add(professionAlia);
				}
			}else{//不截取
				String  professionAlia = sysDeptLoader.getSysDeptNameBySysDeptId(str,SessionHelper.getCurrentLanguageCode(request));
				list.add(professionAlia);
			}
		}
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(response.getOutputStream());
			// 设置excel第一列
			WritableSheet sheet = book.createSheet("厂区专业模板", 0);
			for (int i = 0; i < list.size(); i++) {
				Label label = new Label(i, 0, list.get(i), formatTitle);
				sheet.addCell(label);
				// 设置单元格宽度
				sheet.setColumnView(i, 30);
			}
		} catch (Exception e) {
			logger.debug("exportDocList出现异常",e);
			e.printStackTrace();
		} finally {
			// 写出并关闭流
			if (book != null) {
				try {
					book.write();
					book.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
