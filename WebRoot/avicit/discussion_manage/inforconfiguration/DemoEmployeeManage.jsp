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
<!-- ControllerPath = "discussion_manage/inforconfiguration/InforConfigurationController/" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js"
	type="text/javascript"></script>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'"
		style="background: #ffffff; height: 0px; padding: 0px; overflow: hidden;">
		<div id="toolbarDemoEmployee" class="datagrid-toolbar">
			<table>
				<tr>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_demoEmployee_button_add"
						permissionDes="添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="demoEmployee.insert();"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_demoEmployee_button_edit"
						permissionDes="编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-edit"
							plain="true" onclick="demoEmployee.modify();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_demoEmployee_button_delete"
						permissionDes="删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="demoEmployee.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_demoEmployee_button_query"
						permissionDes="查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="demoEmployee.openSearchForm();"
							href="javascript:void(0);">查询</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_demoEmployee_button_import_export"
						permissionDes="导入/导出">
						<td width="120px;"><a href="javascript:void(0);" id="allMenu"
							name="bpm_all_menu" class='easyui-menubutton'
							data-options="menu:'#allmm',iconCls:'icon-all-file'">导出</a>
							<div id="allmm" style="width: 105px;">
								<div id='client_import' name="client_import"
									onclick="demoEmployee.exportClientData()">客户端导出</div>
								<div id='server_import' name="server_import"
									onclick="demoEmployee.exportServerData()">服务端导出</div></td>
					</sec:accesscontrollist>
				</tr>
			</table>
		</div>
		<table id="dgDemoEmployee"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarDemoEmployee',
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
						width="220">零件编号</th>
					<th data-options="field:'employeeCode', halign:'center'"
						width="220">强度</th>
					<th
						data-options="field:'employeeName', halign:'center',formatter:formateHref"
						width="220">校对</th>
					<th data-options="field:'deptAlias',align:'center'" width="220">质审</th>
					<th data-options="field:'genderAlias', halign:'center'" width="220">可靠性</th>
					<th data-options="field:'age', halign:'center'" width="220">标审</th>
					<th data-options="field:'email', halign:'center'" width="220">材料</th>
					<th data-options="field:'address', halign:'center'" width="220">标准件</th>
					<th data-options="field:'phoneNum', halign:'center'" width="220">重量</th>
				</tr>
			</thead>
		</table>
	</div>
	<!--*****************************搜索*********************************  -->
	<div id="searchDialog"
		data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
		style="width: 904px; height: 340px; display: none;">
		<form id="demoEmployee">
			<table class="form_commonTable">
				<tr>
					<th width="10%">编号:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="employeeCode" /></td>
					<th width="10%">名称:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="employeeName" /></td>
				</tr>
				<tr>
					<th width="10%">所属部门:</th>
					<td width="40%"><input title="部门名称" class="inputbox"
						type="hidden" name="dept" id="dept" />
						<div class="">
							<input class="easyui-validatebox" name="deptAlias" id="deptAlias"
								readOnly="readOnly"></input>
						</div></td>
					<th width="10%">性别:</th>
					<td width="40%"><pt6:syslookup name="gender"
							lookupCode="PLATFORM_SEX"></pt6:syslookup></td>
				</tr>
				<tr>
					<th width="10%">年龄:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="age" /></td>
					<th width="10%">邮箱:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="email" /></td>
				</tr>
				<tr>
					<th width="10%">家庭住址:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="address" /></td>
					<th width="10%">电话:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="phoneNum" /></td>
				</tr>
				<tr>
					<th width="10%">状态:</th>
					<td width="40%"><pt6:syslookup name="status"
							lookupCode="PLATFORM_VALID_FLAG" /></td>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td align="right"><a class="easyui-linkbutton primary-btn"
						iconCls="" plain="false" onclick="demoEmployee.searchData();"
						href="javascript:void(0);">查询</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="demoEmployee.clearData();"
						href="javascript:void(0);">清空</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="demoEmployee.hideSearchForm();"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
	<script src="avicit/demo/develop/excel/demoemployee/js/DemoEmployee.js"
		type="text/javascript"></script>
	<script src="static/js/platform/component/common/exportData.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		var demoEmployee;
		$(function() {
			demoEmployee = new DemoEmployee('dgDemoEmployee', '${url}',
					'searchDialog', 'demoEmployee');
			var deptDeptCommonSelector = new CommonSelector("dept",
					"deptSelectCommonDialog", "dept", "deptAlias");
			deptDeptCommonSelector.init();
			/////
			var array = [];

			var searchObject = {
				name : '编号',
				field : 'EMPLOYEE_CODE',
				type : 1,
				dataType : 'string'
			};
			array.push(searchObject);

			var searchObject = {
				name : '名称',
				field : 'EMPLOYEE_NAME',
				type : 1,
				dataType : 'string'
			};
			array.push(searchObject);

			var searchObject = {
				name : '所属部门',
				field : 'DEPT',
				type : 1,
				dataType : 'dept'
			};
			array.push(searchObject);

			var searchObject = {
				name : '性别',
				field : 'GENDER',
				type : 1,
				dataType : 'string'
			};
			array.push(searchObject);

			var searchObject = {
				name : '年龄',
				field : 'AGE',
				type : 1,
				dataType : 'number'
			};
			array.push(searchObject);

			var searchObject = {
				name : '邮箱',
				field : 'EMAIL',
				type : 1,
				dataType : 'string'
			};
			array.push(searchObject);

			var searchObject = {
				name : '家庭住址',
				field : 'ADDRESS',
				type : 1,
				dataType : 'string'
			};
			array.push(searchObject);

			var searchObject = {
				name : '电话',
				field : 'PHONE_NUM',
				type : 1,
				dataType : 'number'
			};
			array.push(searchObject);

			var searchObject = {
				name : '状态',
				field : 'STATUS',
				type : 1,
				dictCode : 'PLATFORM_VALID_FLAG',
				dataType : 'dict'
			};
			array.push(searchObject);
			///

			selfDefQury.init(array);
			selfDefQury.setQuery(function(param) {
				demoEmployee.searchDataBySfn(param);
			});
		});
		function formateDate(value, row, index) {
			return demoEmployee.formate(value);
		}
		function formateDateTime(value, row, index) {
			return demoEmployee.formateTime(value);
		}
		//demoEmployee.detail(\''+row.id+'\')
		function formateHref(value, row, inde) {
			return '<a href="javascript:void(0);" onclick="demoEmployee.detail(\''
					+ row.id + '\');">' + value + '</a>';
		}
	</script>
</body>
</html>