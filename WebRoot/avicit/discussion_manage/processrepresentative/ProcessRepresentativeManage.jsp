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
<!-- ControllerPath = "discussion_manage/processrepresentative/ProcessRepresentativeController/ProcessRepresentativeInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var processRepresentative;
	$(function() {
		processRepresentative = new ProcessRepresentative(
				'dgProcessRepresentative', '${url}', 'searchDialog',
				'processRepresentative');
		var processUserIdUserCommonSelector = new CommonSelector("user",
				"userSelectCommonDialog", "processUserId", "processUserIdAlias");
		processUserIdUserCommonSelector.init();
		/////
		var array = [];

		var searchObject = {
			name : '装配结构ID',
			field : 'STR_ID',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '任务类型',
			field : 'TASK_TYPE',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '工艺分工人员',
			field : 'PROCESS_USER_ID',
			type : 1,
			dataType : 'user'
		};

		array.push(searchObject);

		var searchObject = {
			name : '发出时间',
			field : 'PUBLICATION_TIME',
			type : 1,
			dataType : 'date'
		};
		array.push(searchObject);

		var searchObject = {
			name : '状态（是否已分配分工人员） ',
			field : 'STATUS',
			type : 1,
			dictCode : 'PRO_STATUS',
			dataType : 'dict'
		};
		array.push(searchObject);
		///

		selfDefQury.init(array);
		selfDefQury.setQuery(function(param) {
			processRepresentative.searchDataBySfn(param);
		});
	});
	function formateDate(value, row, index) {
		return processRepresentative.formate(value);
	}
	function formateDateTime(value, row, index) {
		return processRepresentative.formateDateTime(value);
	}
	//processRepresentative.detail(\''+row.id+'\')
	function formateHref(value, row, inde) {
		return '<a href="javascript:void(0);" onclick="processRepresentative.detail(\''
				+ row.id + '\');">' + value + '</a>';
	}
	function formatestatus(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				processRepresentative.status);
	}
	function formatetasktype(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				processRepresentative.taskType);
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'"
		style="background: #ffffff; height: 0px; padding: 0px; overflow: hidden;">
		<div id="toolbarProcessRepresentative" class="datagrid-toolbar">
			<table>
				<tr>

					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_processRepresentative_table_${param.standName}"
						permissionDes="添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="processRepresentative.insert();"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_processRepresentative_button_edit"
						permissionDes="编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-edit"
							plain="true" onclick="processRepresentative.modify();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_processRepresentative_button_delete"
						permissionDes="删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="processRepresentative.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_processRepresentative_button_query"
						permissionDes="查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="processRepresentative.openSearchForm();"
							href="javascript:void(0);">查询</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_processRepresentative_button_seniorquery"
						permissionDes="高级查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="selfDefQury.show();"
							href="javascript:void(0);">高级查询</a></td>
					</sec:accesscontrollist>

				</tr>
			</table>
		</div>
		<table id="dgProcessRepresentative"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarProcessRepresentative',
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
						width="220">ID</th>
					<th data-options="field:'strCode', halign:'center', align:'center'"
						width="220">零件编号</th>
					<th data-options="field:'taskType', halign:'center',align:'center',formatter:formatetasktype"
						width="220">任务类型</th>
					<th data-options="field:'processUserIdAlias',align:'center'"
						width="220">工艺分工人员</th>
					<th
						data-options="field:'publicationTime', halign:'center',formatter:formateDate,align:'center'"
						width="220">发出时间</th>
					<th
						data-options="field:'dutyUserName', halign:'center',align:'center'"
						width="220">责任人</th>
					<th
						data-options="field:'status', halign:'center',align:'center',formatter:formatestatus"
						width="220">状态（是否已分配分工人员）</th>
				</tr>
			</thead>
		</table>
	</div>
	<!--*****************************搜索*********************************  -->
	<div id="searchDialog"
		data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
		style="width: 904px; height: 340px; display: none;">
		<form id="processRepresentative">
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
					<th width="10%">工艺分工人员:</th>
					<td width="40%"><input title="工艺分工人员" class="inputbox"
						type="hidden" name="processUserId" id="processUserId" />
						<div class="">
							<input class="easyui-validatebox" name="processUserIdAlias"
								id="processUserIdAlias" readOnly="readOnly"></input>
						</div></td>
				</tr>
				<tr>
					<th width="10%">发出时间从:</th>
					<td width="40%"><input name="publicationTimeBegin"
						id="publicationTimeBegin" class="easyui-datebox" editable="false" />
					<th width="10%">发出时间(至):</th>
					<td><input name="publicationTimeEnd" id="publicationTimeEnd"
						class="easyui-datebox" editable="false" /></td>
				</tr>
				<th width="10%">状态（是否已分配分工人员） :</th>
				<td width="40%"><pt6:syslookup name="status" isNull="true"
						lookupCode="PRO_STATUS"
						dataOptions="width:151,editable:false,panelHeight:'auto'">
					</pt6:syslookup></td>
				</tr>
				<tr>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td align="right"><a class="easyui-linkbutton primary-btn"
						iconCls="" plain="false"
						onclick="processRepresentative.searchData();"
						href="javascript:void(0);">查询</a> <a class="easyui-linkbutton"
						iconCls="" plain="false"
						onclick="processRepresentative.clearData();"
						href="javascript:void(0);">清空</a> <a class="easyui-linkbutton"
						iconCls="" plain="false"
						onclick="processRepresentative.hideSearchForm();"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
	<script
		src=" avicit/discussion_manage/processrepresentative/js/ProcessRepresentative.js"
		type="text/javascript"></script>

</body>
</html>