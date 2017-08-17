<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/profconfiguration/ProfConfigurationController/ProfConfigurationInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<%String pid=request.getParameter("pid");%>
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
<script
		src=" avicit/discussion_manage/profconfiguration/js/ProfConfiguration.js"
		type="text/javascript"></script>

<script type="text/javascript">
	var profConfiguration;
	var pid = <%=pid %>;//厂所
	$(function() {
		$('#scene').val(pid);
		profConfiguration = new ProfConfiguration('dgProfConfiguration',
				'platform/discussion_manage/profconfiguration/ProfConfigurationController/operation/', 'searchDialog', 'profConfiguration');
		var professionDeptCommonSelector = new CommonSelector("dept",
				"deptSelectCommonDialog", "profession", "professionAlias");
		professionDeptCommonSelector.init();
		
		//601所专业配置有类别,320无类别
		
		if(pid=="320"){
			$('#dgProfConfiguration').datagrid("hideColumn","category");
		}else{
			$('#dgProfConfiguration').datagrid("showColumn","category");
		}
		
		//加载型号
		$('#typeId').combobox({
				valueField: 'StrId',    
		        textField: 'ClassCode',    
		        url: 'platform/discussion_manage/structuralrelationship/StructuralRelationshipController/findStructuralType',
		       	onLoadSuccess:function(){
		    	  $('#typeId').combobox('select',"");
		    	 /*  var data = $('#typeId').combobox('getData');
		       	  $('#typeId').combobox('select',data[0].ClassCode); */
		       }
		})
		
		
	});
	//打开添加页面
	function insert(){
		nData = new CommonDialog("insert","600","400",'platform/discussion_manage/profconfiguration/ProfConfigurationController/operation/Add/null?pid='+pid,"添加",false,true,false);
		nData.show();
	}
	function formateDate(value, row, index) {
		return profConfiguration.formate(value);
	}
	function formateDateTime(value, row, index) {
		return profConfiguration.formateDateTime(value);
	}
	//profConfiguration.detail(\''+row.id+'\')
	function formateHref(value, row, inde) {
		return '<a href="javascript:void(0);" onclick="profConfiguration.detail(\''
				+ row.id + '\');">' + value + '</a>';
	}
	function formatecategory(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				profConfiguration.category);
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'"
		style="background: #ffffff; height: 0px; padding: 0px; overflow: hidden;">
		<div id="toolbarProfConfiguration" class="datagrid-toolbar">
			<table>
				<tr>

					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_profConfiguration_table_${param.standName}"
						permissionDes="添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="insert();"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_profConfiguration_button_edit"
						permissionDes="编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-edit"
							plain="true" onclick="profConfiguration.modify();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_profConfiguration_button_delete"
						permissionDes="删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="profConfiguration.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_profConfiguration_button_query"
						permissionDes="查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="profConfiguration.openSearchForm();"
							href="javascript:void(0);">查询</a></td>
					</sec:accesscontrollist>
					

				</tr>
			</table>
		</div>
		<table id="dgProfConfiguration"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarProfConfiguration',
				idField :'id',
				singleSelect: true,
				checkOnSelect: true,
				selectOnCheck: false,
				pagination:true,
				pageSize:dataOptions.pageSize,
				pageList:dataOptions.pageList,
				striped:true">
			<thead>
				<tr>
					<th data-options="field:'id', halign:'center',checkbox:true" width="220">ID</th>
					<th data-options="field:'classCode', halign:'center',align:'center'" width="220">型号</th>
					<th data-options="field:'category', halign:'center',align:'center',formatter:formatecategory" width="220">类别</th>
					<th data-options="field:'professionAlias',align:'center'" width="220">专业</th>

				</tr>
			</thead>
		</table>
	</div>
	<!--*****************************搜索*********************************  -->
	<div id="searchDialog"
		data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
		style="width: 600px; height: 400px; display: none;">
		<form id="profConfiguration">
		<input type="hidden" name="scene" id="scene"/>  
			<table class="form_commonTable" style="padding-top: 50px;padding-right: 50px;">
				<tr>
					<th width="10%">型号:</th>
					<td width="40%"><select title="型号" class="easyui-combobox"  style="width:130px"   name="typeId" id="typeId" data-options="panelHeight:'auto'">
					</select></td>
					</tr>
					<tr>
					<th width="10%">类别:</th>
					<td width="40%"><pt6:syslookup name="category" isNull="true"
							lookupCode="PRO_CATEGORY"
							dataOptions="width:151,editable:false,panelHeight:'auto'">
						</pt6:syslookup></td>
				</tr>
				<tr>
					<th width="10%">专业:</th>
					<td width="40%"><input title="专业" class="inputbox"
						type="hidden" name="profession" id="profession" />
						<div class="">
							<input class="easyui-validatebox" name="professionAlias"
								id="professionAlias" readOnly="readOnly"></input>
						</div></td>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td align="right">
					<a class="easyui-linkbutton primary-btn" iconCls="" plain="false" onclick="profConfiguration.searchData();" href="javascript:void(0);">查询</a> 
					<a class="easyui-linkbutton" iconCls="" plain="false" onclick="profConfiguration.clearData();" href="javascript:void(0);">清空</a>
					<a class="easyui-linkbutton" iconCls="" plain="false" onclick="profConfiguration.hideSearchForm();" href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
	
</body>
</html>