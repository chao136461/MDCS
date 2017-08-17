<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<%@ page import="avicit.platform6.api.session.SessionHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/processassignment/ProcessAssignmentController/ProcessAssignmentInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<%
	String applyUserId = SessionHelper.getLoginSysUser(request).getId();
	/* String applyUserName = SessionHelper.getLoginSysUser(request).getName();
	String applyDeptId = SessionHelper.getCurrentDeptId(request); */
	//String applyDeptName = SessionHelper.getCurrentDeptTl(request).getDeptName();

%>
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
	
<script type="text/javascript">
	var processAssignment;
	$(function() {
		var taskType='2';
		var currUserId = '<%=applyUserId%>';
		processAssignment = new ProcessAssignment('dgProcessAssignment','${url}', 'searchDialog', 'processAssignment',taskType,currUserId);
	});
	
	function formateDate(value, row, index) {
		return processAssignment.formate(value);
	}
	function formateDateTime(value, row, index) {
		return processAssignment.formateDateTime(value);
	}
	//processAssignment.detail(\''+row.id+'\')
	function formateHref(value, row, inde) {
		return '<a href="javascript:void(0);" onclick="processAssignment.detail(\''
				+ row.id + '\');">' + value + '</a>';
	}
	function formatestatus(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				processAssignment.status);
	}
	function formatetasktype(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				processAssignment.taskType);
	}
	function enterTalk(value, row, index){
		return '<a herf="javascript:void(0);" onclick="enterTalks(\''+row.strId+'\',\''+row.strCode+'\');">'+'进入讨论区'+'</a>'
	}
	function enterTalks(id,classCode){
		
		var url="avicit/discussion_manage/discussionmanage/ProposalManage.jsp?id="+id+'&classCode='+classCode;
		if(typeof(top.addTab) != 'undefined'){
			top.addTab("讨论区", url);
		}else{
			window.open(url);
		}
	};
</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'"
		style="background: #ffffff; height: 0px; padding: 0px; overflow: hidden;">
		<%-- <div id="toolbarProcessAssignment" class="datagrid-toolbar">
			<table>
				<tr>

					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_processAssignment_table_${param.standName}"
						permissionDes="添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="processAssignment.insert();"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_processAssignment_button_edit"
						permissionDes="编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-edit"
							plain="true" onclick="processAssignment.modify();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_processAssignment_button_delete"
						permissionDes="删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="processAssignment.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_processAssignment_button_query"
						permissionDes="查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="processAssignment.openSearchForm();"
							href="javascript:void(0);">查询</a></td>
					</sec:accesscontrollist>

				</tr>
			</table>
		</div> --%>
		<table id="dgProcessAssignment"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarProcessAssignment',
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
					<th data-options="field:'id', halign:'center',checkbox:true"
						width="220">表主键</th>
						<th data-options="field:'strId', halign:'center', align:'center',hidden:true"
						width="220">零件id</th>
					<th data-options="field:'strCode', halign:'center', align:'center'"
						width="220">零件编号</th>
					<th data-options="field:'taskType', halign:'center',align:'center',formatter:formatetasktype"
						width="220">任务类型</th>
					<th
						data-options="field:'publicationTime',align:'center', halign:'center',formatter:formateDate"
						width="220">发出时间</th>
					<th data-options="field:'dutyUserName', halign:'center',align:'center'"
						width="220">责任人</th>
					<th data-options="field:'dutyDeptName', halign:'center',align:'center'"
						width="220">责任部门</th>
					<th
						data-options="field:'status', halign:'center',align:'center'"
						width="220">建议发表状态</th>
					<th
						data-options="field:'taskStatus', halign:'center',align:'center'"
						width="220">建议审查状态</th>	
					<th data-options="field:'designerhandle', align:'center',formatter:enterTalk" width="220">操作</th>
				</tr>
			</thead>
		</table>
	</div>
	<!--*****************************搜索*********************************  -->
	<div id="searchDialog"
		data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
		style="width: 904px; height: 340px; display: none;">
		<form id="processAssignment">
			<table class="form_commonTable">
				<tr>
					<th width="10%">装配结构ID:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="strId" /></td>
					<th width="10%">任务类型:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="taskType" /></td>
				</tr>
				<tr>
				<tr>
					<th width="10%">发出时间从:</th>
					<td width="40%"><input name="publicationTimeBegin"
						id="publicationTimeBegin" class="easyui-datebox" editable="false" />
					<th width="10%">发出时间(至):</th>
					<td width="40%"><input name="publicationTimeEnd"
						id="publicationTimeEnd" class="easyui-datebox" editable="false" />
					</td>
				</tr>
				<th width="10%">状态:</th>
				<td width="40%"><pt6:syslookup name="status" isNull="true"
						lookupCode="PRO_STATUS"
						dataOptions="width:151,editable:false,panelHeight:'auto'">
					</pt6:syslookup></td>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td align="right"><a class="easyui-linkbutton primary-btn"
						iconCls="" plain="false" onclick="processAssignment.searchData();"
						href="javascript:void(0);">查询</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="processAssignment.clearData();"
						href="javascript:void(0);">清空</a> <a class="easyui-linkbutton"
						iconCls="" plain="false"
						onclick="processAssignment.hideSearchForm();"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
	<script
		src=" avicit/discussion_manage/processassignment/js/ProcessAssignment.js"
		type="text/javascript"></script>
<div style="display: none;">
	
</div>
</body>
</html>