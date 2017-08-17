package avicit.discussion_manage.majormanage.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import avicit.discussion_manage.majormanage.dto.InstituteCensorManageDTO;
import avicit.discussion_manage.majormanage.dto.MajorManageDTO;
import avicit.discussion_manage.majormanage.service.InstituteCensorManageService;
import avicit.discussion_manage.majormanage.service.MajorManageService;
import avicit.discussion_manage.structuralrelationship.service.StructuralRelationshipService;
import avicit.discussion_manage.structuremanage.service.StructureManageService;
import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.api.sysuser.dto.SysDept;
import avicit.platform6.api.sysuser.dto.SysRole;
import avicit.platform6.api.sysuser.dto.SysUser;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.modules.system.excelimport.orguser.AbstractExcelImport;

/**
 * @classname: FactoryComplexController
 * @description: 定义 厂区车间表 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/majormanage/majorUtil")
public class majorUtil extends AbstractExcelImport implements LoaderConstant {
	@Autowired
	private StructureManageService service;
	@Autowired
	private MajorManageService majorService;
	@Autowired
	private InstituteCensorManageService instituteService;
	@Autowired
	private  StructuralRelationshipService relationshipService;
	/**
	 * 描述：通过传统方式form表单提交方式导入excel文件
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value="/importMajor")
	public  void  uploadExcel(HttpServletRequest request) throws Exception {
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
 		int rowsnum = sheet.getLastRowNum()+1;
 		Row rowHeard =sheet.getRow(0);//获取表头信息
 		for (int i = 1; i < rowsnum; i++) {//从1开始，读取部门对应的人员
 			Row row =sheet.getRow(i);
 			if (rowsnum > 1) {//有对应人员数据
 				int colsnum = row.getLastCellNum();//列数
 				String isVci = row.getCell(colsnum-1).toString();//获取最后一列的值是不是vci
 				String strCode = row.getCell(0).toString(); //获取数据列零件编号
 				if (!"是".equals(isVci)) {//不是vci
 						String strId = service.findByCode(strCode).getId();
 						this.insertData(strId,rowHeard,row);//插入数据
				}else{//是vci
					//1、根据strCode查询vci下所有零部件
					List<String> StrIdList = relationshipService.getStrIdByCode(strCode);
					for (int j = 0; j < StrIdList.size(); j++) {
						String strId = StrIdList.get(j);
						this.insertData(strId,rowHeard,row);
					}
				}
 				
			}
		}
 		
 		JOptionPane.showMessageDialog(null, "导入成功!");
	}

	public void insertData(String strId, Row rowHeard, Row row) throws Exception{
		int colsnum = row.getLastCellNum();// 列数
		for (int j = 1; j < colsnum-1; j++) {// 从1开始跳过零件编号列
			MajorManageDTO manageDTO = new MajorManageDTO();
			
			// 插入主表数据
			String id = ComUtil.getId();
			// 插入零件id
			//String strId = service.findByCode(strCode).getId();
			// 插入部门rowHeard rowHeard.getCell(j);
			String deptName =rowHeard.getCell(j).toString();
			SysDept sysdept = sysDeptLoader.getSysDeptBySysDeptName(deptName,SessionHelper.getCurrentLanguageCode());
			String dutyDeptId = sysdept.getId();
			manageDTO.setId(id);
			manageDTO.setStrId(strId);
			manageDTO.setStdId(dutyDeptId);
			majorService.insertMajorManage(manageDTO);
			// 插入人员 row.getCell(j);
			String users = row.getCell(j).toString();
			InstituteCensorManageDTO instituteCensorManageDTO = new InstituteCensorManageDTO();
			SysUser excelUser = null;
			if (users.contains(",")) {
				String[] excelUsers = users.split(",");
				for (int k = 0; k < excelUsers.length; k++) {
					
					instituteCensorManageDTO.setId(ComUtil.getId());
					instituteCensorManageDTO.setStrId(strId);
					instituteCensorManageDTO.setMajId(id);
					instituteCensorManageDTO.setStatus("1");
					String excelUse = excelUsers[k];
					excelUser = sysUserLoader.getSysUserByLoginName(excelUse);
					String deptid = sysUserDeptPositionLoader.getChiefDeptIdBySysUserId(excelUser.getId());
					List<SysRole> roses = sysRoleLoader.getRolesByUserId(excelUser.getId(), "1");
					instituteCensorManageDTO.setDutyUserId(excelUser.getId());
					instituteCensorManageDTO.setDutyDeptId(deptid);
					for (SysRole sysrole : roses) {
						instituteCensorManageDTO.setDutyUserRole(sysrole.getId());
					}
					instituteService.insertInstituteCensorManage(instituteCensorManageDTO);// 插入人员 row.getCell(j);
				}
			} else {
				instituteCensorManageDTO.setId(ComUtil.getId());
				instituteCensorManageDTO.setStrId(strId);
				instituteCensorManageDTO.setMajId(id);
				instituteCensorManageDTO.setStatus("1");
				excelUser = sysUserLoader.getSysUserByLoginName(users);
				String deptid = sysUserDeptPositionLoader
						.getChiefDeptIdBySysUserId(excelUser.getId());
				List<SysRole> roses = sysRoleLoader.getRolesByUserId(
						excelUser.getId(), "1");
				instituteCensorManageDTO.setDutyUserId(excelUser.getId());
				instituteCensorManageDTO.setDutyDeptId(deptid);
				for (SysRole sysrole : roses) {
					instituteCensorManageDTO.setDutyUserRole(sysrole.getId());
				}
				instituteService.insertInstituteCensorManage(instituteCensorManageDTO);// 插入人员 row.getCell(j);
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
