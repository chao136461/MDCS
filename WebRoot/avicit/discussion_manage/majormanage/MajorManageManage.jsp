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
<!-- ControllerPath = "discussion_manage/majormanage/MajorManageController/MajorManageInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
<script src="static/js/platform/component/layer-v3.0.3/layer/layer.js" type="text/javascript"></script>
<link href="static/js/platform/component/layer-v3.0.3/layer/mobile/need/layer.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
	var majorManage;
	var instituteCensorManage;
	$(function() {
		var strId = '${id}';
		majorManage = new MajorManage('dgMajorManage', '${url}',
				'searchDialog', 'majorManage',strId);

		majorManage.setOnLoadSuccess(function() {
			instituteCensorManage = new InstituteCensorManage(
					'dginstituteCensorManage', '${surl}');
		});
		majorManage.setOnSelectRow(function(rowIndex, rowData, id) {
			instituteCensorManage.loadByPid(id);
		});

		majorManage.init();

		var array = [];

		var searchObject = {
			name : '装配结构ID',
			field : 'STR_ID',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '专业ID',
			field : 'STD_ID',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);
		///

		selfDefQury.init(array);
		selfDefQury.setQuery(function(param) {
			majorManage.searchDataBySfn(param);
		});

	});
	function formateDate(value, row, index) {
		return majorManage.formate(value);
	}
	function formateDateTime(value, row, index) {
		return majorManage.formateDateTime(value);
	}
	//majorManage.detail(\''+row.id+'\')
	function formateHref(value, row, inde) {
		return '<a href="javascript:void(0);" onclick="majorManage.detail(\''
				+ row.id + '\');">' + value + '</a>';
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'"
		style="background: #ffffff; height: 0;">
		<div id="toolbarMajorManage" class="datagrid-toolbar">
			<table>
				<tr>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_majorManage_button_add"
						permissionDes="主表添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="majorManage.insert();"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_majorManage_button_edit"
						permissionDes="主表编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-edit"
							plain="true" onclick="majorManage.modify();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_majorManage_button_delete"
						permissionDes="主表删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="majorManage.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_majorManage_button_query"
						permissionDes="主表查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="majorManage.openSearchForm();"
							href="javascript:void(0);">查询</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_majorManage_button_seniorquery"
						permissionDes="高级查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="selfDefQury.show();"
							href="javascript:void(0);">高级查询</a></td>
					</sec:accesscontrollist>

				</tr>
			</table>
		</div> 
		<table id="dgMajorManage"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarMajorManage',
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
						width="220">专业楼层ID</th>
					<th data-options="field:'strId', halign:'center',hidden:true" width="220"></th>
					<th data-options="field:'strName', halign:'center'" width="220">装配结构</th>
					<th data-options="field:'stdId', halign:'center',hidden:true" width="220"></th>
					<th data-options="field:'stdName', halign:'center'" width="220">专业</th>

				</tr>
			</thead>
		</table>
	</div>
	<div data-options="region:'east'"
		style="width: 800px; background: #f5fafe;">
		<div id="toolbarinstituteCensorManage" class="datagrid-toolbar">
			<table>
				<tr>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_instituteCensorManage_button_add"
						permissionDes="子表添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true"
							onclick="instituteCensorManage.insert(majorManage.getSelectID());"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_instituteCensorManage_button_edit"
						permissionDes="子表编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-edit"
							plain="true" onclick="instituteCensorManage.modify();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_instituteCensorManage_button_delete"
						permissionDes="子表删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="instituteCensorManage.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist>
				</tr>
			</table>
		</div> 
		<table id='dginstituteCensorManage' class="easyui-datagrid"
			data-options="
    		fit: true,
			border: false,
			rownumbers: true,
			animate: true,
			collapsible: false,
			fitColumns: true,
			autoRowHeight: false,
			idField :'id',
			toolbar:'#toolbarinstituteCensorManage',
			singleSelect: true,
			checkOnSelect: true,
			selectOnCheck: false,
			method:'get',
			pagination:true,
            pageSize:dataOptions.pageSize,
            pageList:dataOptions.pageList,
			striped:true">
			<thead>
				<tr>
					<th data-options="field:'id', halign:'center',checkbox:true"
						width="220">主键ID</th>
					<th data-options="field:'strId', halign:'center',hidden:true" width="220"></th>
					<th data-options="field:'strName', halign:'center'" width="220">装配结构</th>

					<th data-options="field:'majId', halign:'center',hidden:true" width="220">所区专业表ID</th>

					<th data-options="field:'dutyUserRoleAlias',align:'center'"
						width="220">角色</th>
					<th data-options="field:'dutyUserId',align:'center',hidden:true"
						width="220">审查人员</th>
					<th data-options="field:'dutyUserIdAlias',align:'center'"
						width="220">审查人员</th>
					<th data-options="field:'status', halign:'center'" width="220">状态(审查确认)</th>
					

					<th data-options="field:'dutyDeptIdAlias',align:'center'"
						width="220">审查人员所在部门</th>

				</tr>
			</thead>
		</table>
	</div>
	<!--*****************************搜索*********************************  -->
	<div id="searchDialog"
		data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
		style="width: 904px; height: 340px; display: none;">
		<form id="majorManage">
			<table class="form_commonTable">
				<tr>
					<th width="10%">装配结构ID:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="strId" /></td>
					<th width="10%">专业ID:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="stdId" /></td>
				</tr>
				<tr>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td align="right"><a class="easyui-linkbutton primary-btn"
						iconCls="" plain="false" onclick="majorManage.searchData();"
						href="javascript:void(0);">查询</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="majorManage.clearData();"
						href="javascript:void(0);">清空</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="majorManage.hideSearchForm();"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
	<script src=" avicit/discussion_manage/majormanage/js/MajorManage.js"
		type="text/javascript"></script>
	<script
		src=" avicit/discussion_manage/majormanage/js/InstituteCensorManage.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>