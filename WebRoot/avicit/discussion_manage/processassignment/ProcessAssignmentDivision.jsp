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
<!-- ControllerPath = "discussion_manage/processassignment/ProcessAssignmentController/ProcessAssignmentDivision" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
	<% 
	String strid = request.getParameter("strId");
	%>
<script type="text/javascript">
	var processRoute;
	var strid = '<%=strid%>';
	var dutyUserId = '${param.dutyUserId}';
	$(function() {
		var url = 'platform/discussion_manage/processassignment/ProcessAssignmentController/operation/';
		processRoute = new ProcessRoute('treeProcessRoute', url,
				'ProcessRoute', strid);
	});
	function formatestatus(value) {
		return Platform6.fn.lookup.formatLookupType(value, processRoute.status);
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'"
		style="background: #ffffff; height: 0px; padding: 0px; overflow: hidden;">
		<div id="toolbarProcessRoute" class="datagrid-toolbar">
			<table>
				<tr>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_processRoute_button_add"
						permissionDes="编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="processRoute.edit();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_processRoute_button_edit"
						permissionDes="保存">
						<td><a class="easyui-linkbutton" iconCls="icon-save"
							plain="true" onclick="processRoute.save();"
							href="javascript:void(0);">保存</a></td>
					</sec:accesscontrollist>
				</tr>
			</table>
		</div>
		<table id="treeProcessRoute"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarProcessRoute',
				idField :'id',
				treeField:'classCode',
				singleSelect: true,
				checkOnSelect: true,
				pagination:true,
				striped:true">
			<thead>
				<tr>
					<th data-options="field:'id', halign:'center',checkbox:true"
						width="220">表主键</th>
					<th data-options="field:'routeId',width:100,hidden:true"
						class="easyui-validatebox">路线表ID：</th>
						<th data-options="field:'strId',width:100,hidden:true"
						class="easyui-validatebox">零件ID：</th>
					<th data-options="field:'classCode',width:100"
						class="easyui-validatebox">分类编号:</th>
					<th data-options="field:'name',width:60">分类名称:</th>
					<th data-options="field:'edition',width:60,align:'center'">版本:</th>
					<th
						data-options="field:'status',width:60,align:'center',formatter:formatestatus"
						>成熟度:</th>
					<th data-options="field:'thermalUnit',width:60,align:'center'"
						editor="{type:'validatebox',options:{validType:'maxLength[50]'}}">热表单位:</th>
					<th
						data-options="field:'manufacturingUnit',width:60,align:'center'"
						editor="{type:'validatebox',options:{validType:'maxLength[50]'}}">制造单位:</th>
					<th data-options="field:'useUnit',width:60,align:'center'"
						editor="{type:'validatebox',options:{validType:'maxLength[50]'}}">使用单位:</th>
				</tr>
			</thead>
		</table>
	</div>
	<script
		src=" avicit/discussion_manage/processassignment/js/ProcessRoute.js"
		type="text/javascript"></script>
</body>
</html>