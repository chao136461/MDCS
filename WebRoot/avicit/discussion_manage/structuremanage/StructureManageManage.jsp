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
<!-- ControllerPath = "discussion_manage/structuremanage/StructureManageController/StructureManageInfo" -->
<title>所区专业人员管理</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var structureManage;
	$(function() {
		structureManage = new StructureManage('dgStructureManage', '${url}',
				'searchDialog', 'structureManage');
		/////
		var array = [];

		var searchObject = {
			name : '分类类型',
			field : 'CLASS_TYPE',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '分类名称',
			field : 'CLASS_NAME',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '分类编号',
			field : 'CLASS_CODE',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '成熟度',
			field : 'STATUS',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '版本',
			field : 'EDITION',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '设计者',
			field : 'DESIGNER_ID',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);
		///

		selfDefQury.init(array);
		selfDefQury.setQuery(function(param) {
			structureManage.searchDataBySfn(param);
		});
	});
	function formateDate(value, row, index) {
		return structureManage.formate(value);
	}
	function formateDateTime(value, row, index) {
		return structureManage.formateDateTime(value);
	}
	//structureManage.detail(\''+row.id+'\')
	function formateHref(value, row, inde) {
		return '<a href="javascript:void(0);" onclick="structureManage.detail(\''
				+ row.id + '\');">' + value + '</a>';
	}
	function formateDefaults(value, row, inde) {
		return '<a href="javascript:void(0);" onclick="structureManage.majorDefault(\''
				+ row.id + '\');">' + "查看详细" + '</a>';
	}
	function formateClassType(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				structureManage.classType);
	}
	function formateStatus(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				structureManage.status);
	}
	function factoryimport(id){
		var e = 'importMajorExcel';
		var imp = new CommonDialog(
				"MajorImport",
				"700",
				"400",
				'avicit/discussion_manage/majormanage/MajorExcelimport.jsp',
				"Excel数据导入", false, true, false);
		imp.show();
	}
	function aa(){
		url='${url}'+'exportData';
		window.location.href= url;
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'"
		style="background: #ffffff; height: 0px; padding: 0px; overflow: hidden;">
		<div id="toolbarStructureManage" class="datagrid-toolbar">
			<table>
				<tr>

					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_structureManage_table_${param.standName}"
						permissionDes="添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="structureManage.insert();"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_structureManage_button_edit"
						permissionDes="编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-edit"
							plain="true" onclick="structureManage.modify();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_structureManage_button_delete"
						permissionDes="删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="structureManage.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_structureManage_button_query"
						permissionDes="查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="structureManage.openSearchForm();"
							href="javascript:void(0);">查询</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_structureManage_button_seniorquery"
						permissionDes="高级查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="aa();"
							href="javascript:void(0);">高级查询</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_structureManage_button_import"
						permissionDes="高级查询">
						<td><a class="easyui-linkbutton" iconCls="icon-import"
							plain="true" onclick="factoryimport();"
							href="javascript:void(0);">导入</a></td>
					</sec:accesscontrollist>
					

				</tr>
			</table>
		</div>
		<table id="dgStructureManage"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarStructureManage',
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
						width="220">分类节点ID</th>
					<th data-options="field:'classCode', align:'center'" width="220">分类编号</th>
					<th data-options="field:'className', align:'center'" width="220">分类名称</th>
					<th data-options="field:'classType', align:'center',formatter:formateClassType" width="220">分类类型</th>



					<th data-options="field:'status', align:'center',formatter:formateStatus" width="220">成熟度</th>

					<th data-options="field:'edition', align:'center'" width="220">版本</th>

					<th data-options="field:'designerId', align:'center'" width="220">设计者</th>
					
					<th data-options="field:'detail', align:'center',formatter:formateDefaults" width="220">操作</th>

				</tr>
			</thead>
		</table>
	</div>
	<!--*****************************搜索*********************************  -->
	<div id="searchDialog"
		data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
		style="width: 904px; height: 340px; display: none;">
		<form id="structureManage">
			<table class="form_commonTable">
				<tr>
					<th width="10%">分类类型:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="classType" /></td>
					<th width="10%">分类名称:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="className" /></td>
				</tr>
				<tr>
					<th width="10%">分类编号:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="classCode" /></td>
					<th width="10%">成熟度:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="status" /></td>
				</tr>
				<tr>
					<th width="10%">版本:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="edition" /></td>
					<th width="10%">设计者:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="designerId" /></td>
				</tr>
				<tr>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td align="right"><a class="easyui-linkbutton primary-btn"
						iconCls="" plain="false" onclick="structureManage.searchData();"
						href="javascript:void(0);">查询</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="structureManage.clearData();"
						href="javascript:void(0);">清空</a> <a class="easyui-linkbutton"
						iconCls="" plain="false"
						onclick="structureManage.hideSearchForm();"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
	<script
		src=" avicit/discussion_manage/structuremanage/js/StructureManage.js"
		type="text/javascript"></script>

</body>
</html>