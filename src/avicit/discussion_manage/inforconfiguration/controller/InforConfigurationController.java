package avicit.discussion_manage.inforconfiguration.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import avicit.discussion_manage.inforconfiguration.dto.InforConfigurationDTO;
import avicit.discussion_manage.inforconfiguration.service.InforConfigurationService;
import avicit.platform6.api.syslookup.SysLookupAPI;
import avicit.platform6.api.syslookup.dto.SysLookupSimpleVo;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.core.excel.export.CilentExcelExport;
import avicit.platform6.core.excel.export.entity.DataGridHeader;
import avicit.platform6.core.excel.export.inf.IFormatField;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * @classname: InforConfigurationController
 * @description: 定义 信息配置表 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/inforconfiguration/InforConfigurationController")
public class InforConfigurationController implements LoaderConstant {
	private static final Logger logger = LoggerFactory
			.getLogger(InforConfigurationController.class);

	@Autowired
	private InforConfigurationService service;

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
	@RequestMapping(value = "InforConfigurationInfo/{temp}")
	public ModelAndView toInforConfiguration(@PathVariable String temp,
			HttpServletRequest request, HttpServletResponse reponse) {
		ModelAndView mav = new ModelAndView();
		if ("8".equals(temp)) {
			mav.setViewName("avicit/discussion_manage/inforconfiguration/ExportExcelManage");
		} else {
			mav.setViewName("avicit/discussion_manage/inforconfiguration/InforConfigurationManage");
		}
		Collection<SysLookupSimpleVo> list = sysLookupAPI
				.getLookUpListByType("SYNC_TIME");
		request.setAttribute("list", list);
		request.setAttribute(
				"url",
				"platform/discussion_manage/inforconfiguration/InforConfigurationController/operation/");
		return mav;
	}

	@RequestMapping(value = "/operation/getInforConfigurationsByPage")
	@ResponseBody
	public Map<String, Object> togetInforConfigurationByPage(
			PageParameter pageParameter, HttpServletRequest request) {
		QueryReqBean<InforConfigurationDTO> queryReqBean = new QueryReqBean<InforConfigurationDTO>();
		queryReqBean.setPageParameter(pageParameter);
		HashMap<String, Object> map = new HashMap<String, Object>();
		String json = ServletRequestUtils.getStringParameter(request, "param",
				"");
		InforConfigurationDTO param = null;
		QueryRespBean<InforConfigurationDTO> result = null;
		if (json != null && !"".equals(json)) {
			param = JsonHelper.getInstance().readValue(json,
					new TypeReference<InforConfigurationDTO>() {
					});
			queryReqBean.setSearchParams(param);
		}
		try {
			result = service.searchInforConfigurationByPage(queryReqBean);
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			return map;
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
			// LogBase logBase = LogBaseFactory.getLogBase( request);
			InforConfigurationDTO inforConfigurationDTO = service
					.queryInforConfigurationByPrimaryKey(id);
			request.setAttribute("inforConfigurationDTO", inforConfigurationDTO);
		}
		mav.setViewName("avicit/discussion_manage/inforconfiguration/"
				+ "InforConfiguration" + type);
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
	public ModelAndView toSaveInforConfigurationDTO(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String id = null;
		String jsonData = ServletRequestUtils.getStringParameter(request,
				"data", "");
		/*
		 * try { InforConfigurationDTO inforConfigurationDTO = JsonHelper
		 * .getInstance().readValue(jsonData, InforConfigurationDTO.class);
		 */
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			InforConfigurationDTO inforConfigurationDTO = JsonHelper
					.getInstance().readValue(jsonData, dateFormat,
							new TypeReference<InforConfigurationDTO>() {
							});
			if ("".equals(inforConfigurationDTO.getId())) {// 新增

				InforConfigurationDTO dto = service
						.findDataByType(inforConfigurationDTO.getTypeId());

				if (dto == null || "".equals(dto)) {

					id = service
							.insertInforConfiguration(inforConfigurationDTO);

				} else {
					service.updateInforConfigurationSensitive(inforConfigurationDTO);
				}

			} else {// 修改
				if (inforConfigurationDTO.getIsConfirm() == null) {
					inforConfigurationDTO.setIsConfirm("n");
				}
				if (inforConfigurationDTO.getModelCheck() == null) {
					inforConfigurationDTO.setModelCheck("n");
				}
				if (inforConfigurationDTO.getInterfereCheck() == null) {
					inforConfigurationDTO.setInterfereCheck("n");
				}
				if (inforConfigurationDTO.getApproveState() == null) {
					inforConfigurationDTO.setApproveState("n");
				}
				if (inforConfigurationDTO.getIsDegrade() == null) {
					inforConfigurationDTO.setIsDegrade("n");
				}
				if (inforConfigurationDTO.getIsOpenTime() == null) {
					inforConfigurationDTO.setIsOpenTime("n");
				}
				service.updateInforConfigurationSensitive(inforConfigurationDTO);
			}
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
	 * 按照id批量删除数据
	 * 
	 * @param ids
	 *            id数组
	 * @return
	 */
	@RequestMapping(value = "/operation/delete", method = RequestMethod.POST)
	public ModelAndView toDeleteInforConfigurationDTO(
			@RequestBody String[] ids, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			// LogBase logBase = LogBaseFactory.getLogBase( request);
			service.deleteInforConfigurationByIds(ids);
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
	 * 根据type查询数据
	 */
	@RequestMapping(value = "/operation/findDataByType", method = RequestMethod.POST)
	@ResponseBody
	public InforConfigurationDTO findDataByType(HttpServletRequest request)
			throws Exception {
		InforConfigurationDTO dto = null;
		String typeId = request.getParameter("typeId");
		dto = service.findDataByType(typeId);
		if (dto != null && !"".equals(dto)) {
			request.setAttribute("InforConfigurationDTO", dto);
			request.setAttribute("id", dto.getId());
			return dto;
		} else {
			return null;
		}

	}

	/**
	 * excel客户端导出
	 * 
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/operation/exportClient", method = RequestMethod.POST)
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
		
		List<Object> list = JSON.parseArray(datas);
		Map<String , String> mp = new HashMap<String , String>();  
		for (int i = 0; i < list.size(); i++) {
			Object obj = list.get(i);
			Map<String,String> ma = (Map<String, String>) obj;
			String [] ids =ma.get("ID").split(";");
			Set<String> set= ma.keySet();
			int a  = 0 ;
			
			for (String str : set) {
				if (!str.equals("ID")) {
					if (null !=ids[a]&&!"".equals(ids[a])) {
						String loggerName = sysUserLoader.getSysUserById(
								ids[a]).getLoginName();
						mp.put(str, loggerName);
						a++;
					}else{
						mp.put(str, "");
					}
				}
			}
			datas = "["+JSON.toJSONString(mp)+"]";
			
			//System.out.println(JSON.toJSONString(datas));
		}

		
		
		CilentExcelExport clientExp = new CilentExcelExport();
		clientExp.setFileName(fileName);// 设置导出文件名称
		clientExp.setSheetName(sheetName);// 设置sheet名字
		clientExp.setHasRowNum(hasRowNum);// 是否有行号
		clientExp.setUnexportColumn(unContainFields);// 设置不需要导出的列
		clientExp.setDataGridHeaders(dataGridheaders);// 设置导出的表头，一般为前台传递过来的json格式数据

		//clientExp.setFormatField(new FormatField());

		clientExp.setData(datas);// 设置需要导出的数据，一般为前台传递的数据
		map.addAttribute("export", clientExp);// 必须的代码，属性名字不能修改。
		return "excel.down";// 必须的代码，不需要修改
	}
}

/*class FormatField implements IFormatField, LoaderConstant {
	@Override
	public Object formatField(DataGridHeader header, Map<String, Object> data,
			String field) {
		List<String> listName=new ArrayList<String>();
		if (field.equals("ID")) {
			String userId[] = ((String) data.get("ID")).split(";");
			for(int i=0;i<userId.length;i++){
				if (userId[i] != null && !"".equals(userId[i])) {
					String loggerName = sysUserLoader.getSysUserById(
							userId[i]).getLoginName();
					listName.add(loggerName);
				}
			}
			try {
				return listName;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data.get(field);
	}

}
*/