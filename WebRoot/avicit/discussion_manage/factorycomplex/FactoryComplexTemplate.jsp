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
<!-- ControllerPath = "discussion_manage/factorycomplex/FactoryComplexController/FactoryComplexTemplate" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
<script type="text/javascript" src="static/js/platform/component/layer-v3.0.3/layer/layer.js"></script>
<link href="static/js/platform/component/layer-v3.0.3/layer/skin/default/layer.css">
<script type="text/javascript">
	var factoryComplex;
	var factoryCensorManage;
	$(function() {
		factoryComplex = new FactoryComplex('dgFactoryComplex', '${url}',
				'searchDialog', 'factoryComplex','');

		factoryComplex.setOnLoadSuccess(function() {
			factoryCensorManage = new FactoryCensorManage(
					'dgfactoryCensorManage', '${surl}');
		});
		factoryComplex.setOnSelectRow(function(rowIndex, rowData, id) {
			factoryCensorManage.loadByPid(id);
		});

		factoryComplex.init();
		var workshopIdDeptCommonSelector = new CommonSelector("dept",
				"deptSelectCommonDialog", "workshopId", "workshopIdAlias");
		workshopIdDeptCommonSelector.init();

		var array = [];

		var searchObject = {
			name : '装配结构ID',
			field : 'STR_ID',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '车间ID',
			field : 'WORKSHOP_ID',
			type : 1,
			dataType : 'dept'
		};
		array.push(searchObject);

		var searchObject = {
			name : '状态',
			field : 'STATUS',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '路线类型',
			field : 'ROUTE_TYPE',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);
		///

		selfDefQury.init(array);
		selfDefQury.setQuery(function(param) {
			factoryComplex.searchDataBySfn(param);
		});

	});
	function formateDate(value, row, index) {
		return factoryComplex.formate(value);
	}
	function formateDateTime(value, row, index) {
		return factoryComplex.formateDateTime(value);
	}
	//factoryComplex.detail(\''+row.id+'\')
	function formateHref(value, row, inde) {
		return '<a href="javascript:void(0);" onclick="factoryComplex.detail(\''
				+ row.id + '\');">' + value + '</a>';
	}
	function factoryimport(id){
		var e = 'importFactoryExcel';
		var imp = new CommonDialog(
				"factoryimport",
				"700",
				"400",
				'avicit/discussion_manage/factorycomplex/FactoryExcelimport.jsp',
				"Excel数据导入", false, true, false);
		imp.show();
	}
	function closeImportData() {
		alert(1);
		$("#factoryimport").dialog('close');
	};
	function factoryexport(id){
		var _self = this;
		$.messager.confirm('确认', '是否确认导出Excel文件?', function(r) {
			if (r) { // 封装参数

				var columnFieldsOptions = getGridColumnFieldsOptions('dgDemoEmployee');
				var dataGridFields = JSON.stringify(columnFieldsOptions[0]);
				// 获得datagrid中的数据，getChecked：导出选中的数据；getRows:导出所有行数据
				var rows = $('#dgDemoEmployee').datagrid('getChecked');
				var datas = JSON.stringify(rows);
				var myParams = {
					dataGridFields : dataGridFields,// 表头信息集合
					datas : datas,// 需要导出的数据
					hasRowNum : true,// 默认为true:代表第一列为序号
					sheetName : 'sheet1',// 如果该参数为空，默认为导出的Excel文件名
					unContainFields : '',// 不需要导出的列，使用','分隔即可
					fileName : '平台用户导出数据' + new Date().format("yyyyMMddhhmmss")// 导出的Excel文件名
				};
				var url = _self.getUrl() + "sysuser/exportClient";// 导出请求地址
				var ep = new exportData("xlsExport", "xlsExport", myParams, url);
				ep.excuteExport();
			}
		});
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'west'"
		style="background: #ffffff; height: 0;width: 500px;">
		 <div id="toolbarFactoryComplex" class="datagrid-toolbar">
			<table>
				<tr>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_factoryComplex_button_add"
						permissionDes="主表添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="factoryComplex.insert();"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_factoryComplex_button_edit"
						permissionDes="主表编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-edit"
							plain="true" onclick="factoryComplex.modify();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_factoryComplex_button_delete"
						permissionDes="主表删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="factoryComplex.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_factoryComplex_button_query"
						permissionDes="主表查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="factoryComplex.openSearchForm();"
							href="javascript:void(0);">查询</a></td>
					</sec:accesscontrollist>
					<%-- <sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_factoryComplex_button_seniorquery"
						permissionDes="高级查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="selfDefQury.show();"
							href="javascript:void(0);">高级查询</a></td>
					</sec:accesscontrollist> --%>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_structureManage_button_seniorquery"
						permissionDes="厂区人员导入">
						<td><a class="easyui-linkbutton" iconCls="icon-import"
							plain="true" onclick="factoryimport();"
							href="javascript:void(0);">厂区人员导入</a></td>
					</sec:accesscontrollist>
				</tr>
			</table>
		</div> 
		<table id="dgFactoryComplex"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarFactoryComplex',
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
	<!-- 				<th data-options="field:'strId', halign:'center',hidden:true" width="220"></th>
					<th data-options="field:'strName', halign:'center'" width="220">装配结构</th> -->

					<th data-options="field:'workshopIdAlias',align:'center'"
						width="220">车间</th>

					<!-- <th data-options="field:'status', halign:'center'" width="220">状态</th>

					<th data-options="field:'routeType', halign:'center'" width="220">路线类型</th> -->

				</tr>
			</thead>
		</table>
	</div>
	<div data-options="region:'center'"
		style="width: 550px; background: #f5fafe;">

		 <div id="toolbarfactoryCensorManage" class="datagrid-toolbar">
			<table>
				<tr>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_factoryCensorManage_button_add"
						permissionDes="子表添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true"
							onclick="factoryCensorManage.insert(factoryComplex.getSelectID());"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_factoryCensorManage_button_edit"
						permissionDes="子表编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-edit"
							plain="true" onclick="factoryCensorManage.modify();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_factoryCensorManage_button_delete"
						permissionDes="子表删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="factoryCensorManage.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist>
				</tr>
			</table>
		</div> 
		<table id='dgfactoryCensorManage' class="easyui-datagrid"
			data-options="
    		fit: true,
			border: false,
			rownumbers: true,
			animate: true,
			collapsible: false,
			fitColumns: true,
			autoRowHeight: false,
			idField :'id',
			toolbar:'#toolbarfactoryCensorManage',
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
						width="220">ID</th>
	<!-- 				<th data-options="field:'strId', halign:'center',hidden:true" width="220"></th>
					<th data-options="field:'subName', halign:'center'" width="220">装配结构</th> -->

					<!-- <th data-options="field:'fcId', halign:'center'" width="220">厂区车间</th>
 -->
					<!-- <th data-options="field:'status', halign:'center'" width="220">状态(审查确认)</th>

					<th data-options="field:'dutyUserRoleAlias',align:'center'"
						width="220">角色</th> -->


					<th data-options="field:'dutyUserIdAlias',align:'center'"
						width="220">审查人员</th>
<!-- 
					<th data-options="field:'dutyDeptIdAlias',align:'center'"
						width="220">审查人员所在部门</th> -->

				</tr>
			</thead>
		</table>
	</div>
	<!--*****************************搜索*********************************  -->
	<div id="searchDialog"
		data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
		style="width: 904px; height: 340px; display: none;">
		<form id="factoryComplex">
			<table class="form_commonTable">
				<tr>
					<th width="10%">装配结构ID:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="strId" /></td>
					<th width="10%">车间ID:</th>
					<td><input title="车间ID" class="inputbox" type="hidden"
						name="workshopId" id="workshopId" />
						<div class="">
							<input class="easyui-validatebox" name="workshopIdAlias"
								id="workshopIdAlias" readOnly="readOnly"></input>
						</div></td>
				</tr>
				<tr>
					<th width="10%">状态:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="status" /></td>
					<th width="10%">路线类型:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="routeType" /></td>
				</tr>
				<tr>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td align="right"><a class="easyui-linkbutton primary-btn"
						iconCls="" plain="false" onclick="factoryComplex.searchData();"
						href="javascript:void(0);">查询</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="factoryComplex.clearData();"
						href="javascript:void(0);">清空</a> <a class="easyui-linkbutton"
						iconCls="" plain="false"
						onclick="factoryComplex.hideSearchForm();"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
	<script
		src=" avicit/discussion_manage/factorycomplex/js/FactoryComplex.js"
		type="text/javascript"></script>
	<script
		src=" avicit/discussion_manage/factorycomplex/js/FactoryCensorManage.js"
		type="text/javascript"></script>
	<script type="text/javascript">
		
	</script>
</body>
</html>