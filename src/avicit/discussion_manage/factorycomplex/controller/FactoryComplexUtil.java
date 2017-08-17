package avicit.discussion_manage.factorycomplex.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.http.HttpStatus;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import avicit.discussion_manage.factorycomplex.dto.FactoryCensorManageDTO;
import avicit.discussion_manage.factorycomplex.dto.FactoryComplexDTO;
import avicit.discussion_manage.factorycomplex.service.FactoryCensorManageService;
import avicit.discussion_manage.factorycomplex.service.FactoryComplexService;
import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.api.sysuser.dto.SysDept;
import avicit.platform6.api.sysuser.dto.SysUser;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.modules.system.excelimport.orguser.AbstractExcelImport;
import avicit.platform6.modules.system.sysorguser.sysuser.service.SysUserLoader;

/**
 * @classname: FactoryComplexController
 * @description: 定义 厂区车间表 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/factorycomplex/FactoryComplexUtil")
public class FactoryComplexUtil extends AbstractExcelImport implements LoaderConstant {
	@Autowired
	private FactoryComplexService factoryService;
	@Autowired
	private FactoryCensorManageService serviceSub;
	/**
	 * 导入excel文件
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value="/importFactory")
	public  Map<String, String>  uploadExcel(HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>(2);
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
		InputStream in =null;
		MultipartFile file = multipartRequest.getFile("impExcelFile");
		if(file.isEmpty()){
			throw new Exception("文件不存在！");
		}
		String name=file.getOriginalFilename();
 		in = file.getInputStream();
 		Workbook workbook = createWorkBook(in,name);
 		Sheet sheet = workbook.getSheetAt(0);
		Row row =sheet.getRow(0);
		if (row == null) {
			map.put("msg", "模板不能为空");
			return map;
		}
		FactoryComplexDTO factoryComplexDTO = new FactoryComplexDTO();
		String id ="";
		for (int j = 0; j < row.getLastCellNum(); j++) {
			if(row.getCell(j)!=null){
				SysDept sysDept = sysDeptLoader.getSysDeptBySysDeptName(row.getCell(j).toString(), SessionHelper.getCurrentLanguageCode(request));
				String factoryid = factoryService.findByDeptId(sysDept.getId(),"");
				if(factoryid!=null){//对应车间模板存在则删除
					factoryService.deleteFactoryComplexById(factoryid);
				}
				id = ComUtil.getId();
				factoryComplexDTO.setId(id);//车间表主键id
				factoryComplexDTO.setWorkshopId(sysDept.getId());//车间id
				factoryComplexDTO.setTemplate("Y");//模板状态
				factoryService.insertFactoryComplex(factoryComplexDTO);
				Row rows =sheet.getRow(1);
				if(rows.getCell(j)!=null){
					String data = rows.getCell(j).toString();
					this.factoryCensorUtil(id,data);
				}
			}
		}
		map.put("flag", "success");
		return map;
	}
	/**
	 * 仅供import导入调用，执行添加操作
	 * @param id 为主表factoryComplexDTO的主键ID
	 * @param data 用户
	 * @throws Exception
	 */
	private void factoryCensorUtil(String id,String data) throws Exception{
		FactoryCensorManageDTO factoryCensorManageDTO = new FactoryCensorManageDTO();
		if(data.contains(",")){//如果导入的用户为多个
			String [] sysid=data.split(",");//按“,”分割
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
	 * 判断是xls文件还是xlsx文件
	 */
	private Workbook createWorkBook(InputStream is,String name) throws Exception {
		if (name.toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(is);
		} else if (name.toLowerCase().endsWith("xlsx")) {
			return new XSSFWorkbook(is);
		} else {
			throw new RuntimeException("上传文档格式错误！");
		}
	}
}
