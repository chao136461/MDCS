package avicit.discussion_manage.majormanage.controller;

import java.io.IOException;
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
import avicit.discussion_manage.majormanage.service.MajorManageService;
import avicit.discussion_manage.majormanage.service.InstituteCensorManageService;
import avicit.discussion_manage.majormanage.dto.InstituteCensorManageDTO;
import avicit.discussion_manage.majormanage.dto.MajorManageDTO;
import avicit.discussion_manage.profconfiguration.dto.ProfConfigurationDTO;
import avicit.discussion_manage.profconfiguration.service.ProfConfigurationService;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @classname: MajorManageController
 * @description: 定义 所区专业管理 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/majormanage/MajorManageController")
public class MajorManageController implements LoaderConstant {
	private static final Logger logger = LoggerFactory
			.getLogger(MajorManageController.class);

	@Autowired
	private MajorManageService service;
	@Autowired
	private InstituteCensorManageService serviceSub;
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
	@RequestMapping(value = "MajorManageInfo/{id}")
	public ModelAndView toMajorManageInfo(HttpServletRequest request,
			HttpServletResponse reponse,@PathVariable String id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/majormanage/MajorManageManage");
		request.setAttribute("url",
				"platform/discussion_manage/majormanage/MajorManageController/operation/");
		request.setAttribute("surl",
				"platform/discussion_manage/majormanage/MajorManageController/operation/sub/");
		request.setAttribute("id", id);
		return mav;
	}

	@RequestMapping(value = "/operation/getMajorManagesByPage")
	@ResponseBody
	public Map<String, Object> toGetMajorManageByPage(
			PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<MajorManageDTO> queryReqBean = new QueryReqBean<MajorManageDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param",
				"");
		String sfnConditions = ServletRequestUtils.getStringParameter(request,
				"sdfConditons", "");// 自定义查询条件
		MajorManageDTO param = null;
		QueryRespBean<MajorManageDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json,
					new TypeReference<MajorManageDTO>() {
					});
			queryReqBean.setSearchParams(param);
		}
		try {
			result = service.searchMajorManageByPage(queryReqBean,
					sfnConditions);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			return map;
		}

		for (MajorManageDTO dto : result.getResult()) {
			dto.setStdName(sysDeptLoader.getSysDeptNameBySysDeptId(
					dto.getStdId(),
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
		if (!"null".equals(id)) {// 编辑窗口或者详细页窗口
			// 主表数据
			MajorManageDTO dto = service.queryMajorManageByPrimaryKey(id);

			request.setAttribute("majorManageDTO", dto);
		}
		mav.setViewName("avicit/discussion_manage/majormanage/" + "MajorManage"
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
	public ModelAndView toSaveMajorManageDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			String jsonData = ServletRequestUtils.getStringParameter(request,
					"data", "");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			MajorManageDTO majorManageDTO = JsonHelper.getInstance().readValue(
					jsonData, dateFormat, new TypeReference<MajorManageDTO>() {
					});
			if ("".equals(majorManageDTO.getId())) {// 新增
				service.insertMajorManage(majorManageDTO);
			} else {// 修改
				service.updateMajorManageSensitive(majorManageDTO);
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
	public ModelAndView toDeleteMajorManageDTO(@RequestBody String[] ids,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			service.deleteMajorManageByIds(ids);
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
	@RequestMapping(value = "/operation/sub/getInstituteCensorManage/{pid}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> toGetInstituteCensorManageByPid(
			@PathVariable String pid, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>(1);
		try {
			List<InstituteCensorManageDTO> list = serviceSub
					.queryInstituteCensorManageByPid(pid);

			for (InstituteCensorManageDTO dto : list) {
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
			InstituteCensorManageDTO dto = serviceSub
					.queryInstituteCensorManageByPrimaryKey(id);
			dto.setDutyUserRoleAlias(sysRoleLoader.getSysRoleNameById(dto
					.getDutyUserRole()));

			dto.setDutyUserIdAlias(sysUserLoader.getSysUserNameById(dto
					.getDutyUserId()));

			dto.setDutyDeptIdAlias(sysDeptLoader.getSysDeptNameBySysDeptId(
					dto.getDutyDeptId(),
					SessionHelper.getCurrentLanguageCode(request)));
			request.setAttribute("instituteCensorManageDTO", dto);
		} else {
			request.setAttribute("pid", id);
		}
		mav.setViewName("avicit/discussion_manage/majormanage/"
				+ "InstituteCensorManage" + type);
		return mav;
	}

	/**
	 * 保存数据
	 * 
	 * @param id
	 *            主键id
	 * @param instituteCensorManageDTO
	 *            保存的对象
	 * @return
	 */
	@RequestMapping(value = "/operation/sub/save", method = RequestMethod.POST)
	public ModelAndView toSaveInstituteCensorManageDTO(
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String jsonData = ServletRequestUtils.getStringParameter(request,
				"data", "");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			InstituteCensorManageDTO instituteCensorManageDTO = JsonHelper
					.getInstance().readValue(jsonData, dateFormat,
							new TypeReference<InstituteCensorManageDTO>() {
							});
			if ("".equals(instituteCensorManageDTO.getId())) {// 新增
				serviceSub
						.insertInstituteCensorManage(instituteCensorManageDTO);
			} else {// 修改
				serviceSub
						.updateInstituteCensorManageSensitive(instituteCensorManageDTO);
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
	public ModelAndView toDeleteInstituteCensorManageDTO(
			@RequestBody String[] ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			serviceSub.deleteInstituteCensorManageByIds(ids);
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
	 * 创区模板导出数据
	 * @param typeId 机型
	 * @throws Exception
	 */
	@RequestMapping(value = "/operation/majorExport")
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
		param.setScene("601");//601所
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
			
			list.add(0, "零件编号");
			int lastIndex = list.size();
			list.add(lastIndex, "是否是VCI");
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
