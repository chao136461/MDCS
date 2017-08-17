<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "demo/develop/ExportExcel/ExportExcelListController/ExportExcelInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exportData.js"
	type="text/javascript"></script>

<script src="static/js/platform/component/common/exteasyui.js"
	type="text/javascript"></script>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'"
		style="background: #ffffff; height: 0;">
		<div id="toolbarExportExcel" class="datagrid-toolbar">
			<table>
				<tr>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_ExportExcel_button_add"
						permissionDes="添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="ExportExcel.insert();"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<%-- <sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_ExportExcel_button_edit"
						permissionDes="保存">
						<td><a class="easyui-linkbutton" iconCls="icon-save"
							plain="true" onclick="ExportExcel.save();"
							href="javascript:void(0);">保存</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_ExportExcel_button_delete"
						permissionDes="删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="ExportExcel.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist> --%>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_ExportExcel_button_import_export"
						permissionDes="导出">
						<td width="120px;"><a href="javascript:void(0);"
							id="client_import" name="client_import" class='easyui-linkbutton'
							plain="true" iconCls="icon-export"
							onclick="ExportExcel.exportClientData()">导出</a></td>
						<th>厂所:</th>
						<td><pt6:syslookup name="factory" id="factory" isNull="true"
								lookupCode="CHANGSUO"
								dataOptions="width:151,editable:false,panelHeight:'100'">
							</pt6:syslookup></td>
						<th>型号:</th>
						<td><select class="easyui-combobox" style="width: 150px"
							required="required" name="typeId" id="typeId"
							data-options="panelHeight:'auto'">
						</select></td>
					</sec:accesscontrollist>
				</tr>
			</table>
		</div>
		<table id="dgExportExcel"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarExportExcel',
				idField :'id',
				singleSelect: true,
				checkOnSelect: true,
				selectOnCheck: false,
				pagination:true,
				method:'get',
				pageSize:dataOptions.pageSize,
				pageList:dataOptions.pageList,
				striped:true">
		</table>
	</div>

	<script
		src="avicit/discussion_manage/inforconfiguration/js/ExportExcel.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		var ExportExcel;
		$(function() {
			ExportExcel = new ExportExcel('dgExportExcel', 'searchDialog',
					'${url}', 'ExportExcel');

			var userCommonSelector = new CommonSelector("user",
					"userSelectCommonDialog", "designerId", "designerName");
			userCommonSelector.init();
			//根据型号加载所选相关配置
			$('#typeId').combobox({
								valueField : 'StrId',
								textField : 'ClassCode',
								url : 'platform/discussion_manage/structuralrelationship/StructuralRelationshipController/findStructuralType',
								onLoadSuccess : function() {
									$('#typeId').combobox('setValue', '--请选择--');
								}
							});
		});
	</script>
</body>
</html>