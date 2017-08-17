package avicit.discussion_manage.relevanceperson.controller;

import java.io.UnsupportedEncodingException;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.api.sysuser.SysDeptAPI;
import avicit.platform6.api.sysuser.SysUserAPI;
import avicit.platform6.api.sysuser.SysUserDeptPositionAPI;
import avicit.platform6.api.sysuser.dto.SysUser;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.excel.export.CilentExcelExport;
import avicit.platform6.core.excel.export.ServerExcelExport;
import avicit.platform6.core.excel.export.datasource.TypeReferenceDataSource;
import avicit.platform6.core.excel.export.entity.DataGridHeader;
import avicit.platform6.core.excel.export.inf.IFormatField;
import avicit.platform6.core.excel.imp.ExcelImport;
import avicit.platform6.core.excel.imp.inf.ImportOpt;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.web.AvicitResponseBody;
import avicit.platform6.modules.system.excelimport.orguser.AbstractExcelImport;

import avicit.discussion_manage.relevanceperson.dto.RelevancePersonDTO;
import avicit.discussion_manage.relevanceperson.service.RelevancePersonService;

import avicit.discussion_manage.structureDataSynchronize.util.EcodeChangeUtil;

import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @classname: RelevancePersonController
 * @description: 定义  关联人员表 控制层
 * @author:   AVICIT DEV 
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/relevanceperson/RelevancePersonController")
public class RelevancePersonController extends AbstractExcelImport implements LoaderConstant{
    private static final Logger logger =  LoggerFactory.getLogger(RelevancePersonController.class);
    
    @Autowired
    private  RelevancePersonService service;
    
    
    @Autowired
    private SysUserAPI sysUserAPI;
    
    
    @Autowired
    private SysDeptAPI sysDeptAPI;
    
    @Autowired
    private  SysUserDeptPositionAPI  sysUserDeptPositionAPI;
    /**
     * 跳转到管理页面
     * @param request 请求
     * @param reponse 响应
     * @return
     */
    @RequestMapping(value="RelevancePersonInfo")
	public ModelAndView toRelevancePerson(HttpServletRequest request,HttpServletResponse reponse){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("avicit/discussion_manage/relevanceperson/RelevancePersonManage");
		request.setAttribute("url", "platform/discussion_manage/relevanceperson/RelevancePersonController/operation/");
		return mav;
	}
    
    @RequestMapping(value="/operation/getRelevancePersonsByPage",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> togetRelevancePersonByPage(PageParameter pageParameter,HttpServletRequest request){
		QueryReqBean<RelevancePersonDTO> queryReqBean = new QueryReqBean<RelevancePersonDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String,Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param", "");
		String sfnConditions = ServletRequestUtils.getStringParameter(request, "sdfConditons", "");//自定义查询条件
		RelevancePersonDTO param = null;
		QueryRespBean<RelevancePersonDTO> result =null;
		if(json!= null && !"".equals(json)){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			param = JsonHelper.getInstance().readValue(json,dateFormat, new TypeReference<RelevancePersonDTO>(){});
			queryReqBean.setSearchParams(param);
		}
		try {
			 result = service.searchRelevancePersonByPage(queryReqBean,sfnConditions);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return map;  
		}
		
		for(RelevancePersonDTO dto :result.getResult()){

	    				    		
		}
		
		map.put("total",result.getPageParameter().getTotalCount());
		map.put("rows", result.getResult());
		logger.debug("成功获取RelevancePerson分页数据");
		return map;
	}
    
       	/**
  	 * 保存数据
  	 * @param id 主键id
  	 * @param demoBusinessTripDTO 保存的对象
  	 * @return
  	 */
  	@RequestMapping(value="/operation/save",method=RequestMethod.POST)
	@ResponseBody
	public AvicitResponseBody saveOrUpdateRelevancePerson(HttpServletRequest request){
		String datas = ServletRequestUtils.getStringParameter(request, "datas", "");
		if(datas == ""){
			return new AvicitResponseBody(OpResult.success);
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			List<RelevancePersonDTO> list = JsonHelper.getInstance().readValue(datas,dateFormat, new TypeReference<List<RelevancePersonDTO>>(){});
			service.insertOrUpdateRelevancePerson(list);
			return new AvicitResponseBody(OpResult.success);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			return new AvicitResponseBody(OpResult.failure,ex.getMessage());
		}
	}
  	/**
  	 * 按照id批量删除数据
  	 * @param ids id数组
  	 * @return
  	 */
  	@RequestMapping(value="/operation/delete",method=RequestMethod.POST)
	@ResponseBody
	public AvicitResponseBody deleteRelevancePerson(HttpServletRequest request){
    	String ids = ServletRequestUtils.getStringParameter(request, "ids", "");
		try {
			service.deleteRelevancePersonByIds(ids.split(","));
			return new AvicitResponseBody(OpResult.success);
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
			return new AvicitResponseBody(OpResult.failure,e.getMessage());
		}
	}
  	
  //根据部门code查询部门下的用户
    @RequestMapping(value="/operation/getSysUserByPage" )    
	@ResponseBody
	public Map<String,Object> toSearchSysUserByPage(HttpServletRequest request) throws UnsupportedEncodingException {
    	HashMap<String,Object> map = new HashMap<String, Object>();
    	String deptCode = ServletRequestUtils.getStringParameter(request, "deptCode", "");
    	String data= ServletRequestUtils.getStringParameter(request, "param", "");
    	String encode = EcodeChangeUtil.getEncoding(data);
		String json = new String(data.getBytes(encode),"UTF-8");
		RelevancePersonDTO param = null;
		if(json!= null && !"".equals(json)){
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			param = JsonHelper.getInstance().readValue(json,dateFormat, new TypeReference<RelevancePersonDTO>(){});
			
		}
    	String deptId = sysDeptAPI.getSysDeptIdByDeptCode(deptCode);
    	List<RelevancePersonDTO>  list1 =new ArrayList<RelevancePersonDTO>();
    	RelevancePersonDTO dto1 = null;
    	List<SysUser>  list = sysUserDeptPositionAPI.getSysUserListBySysDeptId(deptId,true);
    	RelevancePersonDTO dto=null;
    	
			try {
				for (SysUser sysUser : list) {	
					dto = service.findRelevancePersonByPid(sysUser.getId());
					if(param!=null){
						if(!"".equals(param.getName())){
							if(!sysUser.getName().equals(param.getName())){
								continue;
							}
						}
					}
					if(dto!=null){
						dto1 = new RelevancePersonDTO();
						String  VpmLoginName = dto.getVpmLoginName();
						dto1.setId(dto.getPid());
			    		dto1.setLoginName(sysUser.getLoginName());
			    		dto1.setName(sysUser.getName());
			    	    dto1.setVpmLoginName(VpmLoginName);
			    		list1.add(dto1);
					}else{
						dto1 = new RelevancePersonDTO();
						dto1.setId(sysUser.getId());
			    		dto1.setLoginName(sysUser.getLoginName());
			    		dto1.setName(sysUser.getName());
			    	    dto1.setVpmLoginName("");
			    		list1.add(dto1);
					}
					
			   }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	map.put("total",list1.size());
    	map.put("rows", list1);
		return map;
	} 
    
    /**
	 * Excel导入
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operation/import")
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
		PersonDataExcutor supplierExecutor = new PersonDataExcutor("用户导入");
		excelImport.setExcutor(supplierExecutor);
		  
		// 验证器
		/*
		 * Validate v = new Validate() {
		 * 
		 * @Override public boolean validate(Object arg0) { return false; }
		 * 
		 * @Override public String getField() { // TODO Auto-generated method
		 * stub return null; }
		 * 
		 * @Override public String getErrorMag() { // TODO Auto-generated method
		 * stub return null; } }; // 把验证器添加到执行器中
		 * supplierExecutor.addValidate(v);
		 */

		// 为执行器添加持久化方法
		supplierExecutor.setImportOpt(new ImportOpt() {

			@Override
			public void import2Db(Map<String, Object> data) throws Exception {
				// 把map对象转换为json字符串
				String str = JsonHelper.getInstance().writeValueAsString(data);
				// json对象转换为对象
				RelevancePersonDTO relevancePersonDTO = JsonHelper.getInstance()
						.readValue(str, RelevancePersonDTO.class);
				SysUser sysUser = sysUserAPI.getSysUserByLoginName(relevancePersonDTO.getLoginName());
				relevancePersonDTO.setPid(sysUser.getId());
				RelevancePersonDTO dto = service.findRelevancePersonByPid(sysUser.getId());
				//判断如果已经存在相同数据，则不再导入
				if(dto==null){
					service.insertRelevancePerson(relevancePersonDTO);

				}else{
					service.updateRelevancePerson(relevancePersonDTO);
				}
				
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
	 * excel客户端导出
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/operation/exportClient")
	public String exportClient(HttpServletRequest request, ModelMap map) {
		// 解析前台参数
		String fileName = ServletRequestUtils.getStringParameter(request,
				"fileName", "导出excel");
		String dataGridheaders = ServletRequestUtils.getStringParameter(
				request, "dataGridFields", "");
		boolean hasRowNum = ServletRequestUtils.getBooleanParameter(request,
				"hasRowNum", true);
		String unContainFields = ServletRequestUtils.getStringParameter(
				request, "unContainFields", "");
		String datas = ServletRequestUtils.getStringParameter(request, "datas",
				"");
		String sheetName = ServletRequestUtils.getStringParameter(request,
				"sheetName", "sheet1");

		CilentExcelExport clientExp = new CilentExcelExport();
		clientExp.setFileName(fileName);// 设置导出文件名称
		clientExp.setSheetName(sheetName);// 设置sheet名字
		clientExp.setHasRowNum(hasRowNum);// 是否有行号
		clientExp.setUnexportColumn(unContainFields);// 设置不需要导出的列
		clientExp.setDataGridHeaders(dataGridheaders);// 设置导出的表头，一般为前台传递过来的json格式数据

		clientExp.setFormatField(new IFormatField() {

			@Override
			public Object formatField(DataGridHeader header,
					Map<String, Object> data, String field) {
				// 参数header 为表头的配置属性
				// 参数data，为当前的数据
				// 参数field代表的当前表头列，通过data.get(field).toString()可以获得通用代码的code

				return data.get(field).toString();
			}
		});
		clientExp.setData(datas);// 设置需要导出的数据，一般为前台传递的数据
		map.addAttribute("export", clientExp);// 必须的代码，属性名字不能修改。
		return "excel.down";// 必须的代码，不需要修改
	}
}
