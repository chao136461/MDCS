<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@ page import="aviavicit.platform6.commons.utils.ViewUtil
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/proposalmanage/ProposalManageController/ProposalManageInfo" -->
<title></title>
<base href="<%=VieViewUtil.getRequestPath(request)>
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var proposalManage;
	$(function() {
		proposalManage = new ProposalManage('dgProposalManage', '${url}',
				'searchDialog', 'proposalManage');
		var proposalUserIdUserCommonSelector = new CommonSelector("user",
				"userSelectCommonDialog", "proposalUserId",
				"proposalUserIdAlias");
		proposalUserIdUserCommonSelector.init();
		var proposalUserDeptDeptCommonSelector = new CommonSelector("dept",
				"deptSelectCommonDialog", "proposalUserDept",
				"proposalUserDeptAlias");
		proposalUserDeptDeptCommonSelector.init();
		var replierUserIdUserCommonSelector = new CommonSelector("user",
				"userSelectCommonDialog", "replierUserId", "replierUserIdAlias");
		replierUserIdUserCommonSelector.init();
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
			name : '父类建议ID',
			field : 'PARENT_ID',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '建议编号',
			field : 'PROPOSAL_CODE',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '建议标题',
			field : 'PROPOSAL_NAME',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '建议正文',
			field : 'PROPOSAL_MAIN',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '状态',
			field : 'STATUS',
			type : 1,
			dictCode : 'PROPOSED_STATUS',
			dataType : 'dict'
		};
		array.push(searchObject);

		var searchObject = {
			name : '建议发表时间      ',
			field : 'PUBLICATION_TIME',
			type : 1,
			dataType : 'date'
		};
		array.push(searchObject);

		var searchObject = {
			name : '建议提出人',
			field : 'PROPOSAL_USER_ID',
			type : 1,
			dataType : 'user'
		};

		array.push(searchObject);

		var searchObject = {
			name : '建议提出人部门（专业）',
			field : 'PROPOSAL_USER_DEPT',
			type : 1,
			dataType : 'dept'
		};
		array.push(searchObject);

		var searchObject = {
			name : '是否有争议',
			field : 'IS_DISPUTE',
			type : 1,
			dictCode : 'PROPOSED_IS_DISPUTE',
			dataType : 'dict'
		};
		array.push(searchObject);

		var searchObject = {
			name : '320厂是否批准建议（320厂默认否）',
			field : 'IS_APPROVAL',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '回复人ID',
			field : 'REPLIER_USER_ID',
			type : 1,
			dataType : 'user'
		};

		array.push(searchObject);

		var searchObject = {
			name : '建议状态',
			field : 'PROPOSED_TYPE',
			type : 1,
			dictCode : 'PROPOSED_TYPE',
			dataType : 'dict'
		};
		array.push(searchObject);

		var searchObject = {
			name : '图片',
			field : 'PICTURE',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);
		///

		selfDefQury.init(array);
		selfDefQury.setQuery(function(param) {
			proposalManage.searchDataBySfn(param);
		});
	});
	function formateDate(value, row, index) {
		return proposalManage.formate(value);
	}
	function formateDateTime(value, row, index) {
		return proposalManage.formateDateTime(value);
	}
	//proposalManage.detail(\''+row.id+'\')
	function formateHref(value, row, inde) {
		return '<a href="javascript:void(0);" onclick="proposalManage.detail(\''
				+ row.id + '\');">' + value + '</a>';
	}
	function formatestatus(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				proposalManage.status);
	}
	function formateisDispute(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				proposalManage.isDispute);
	}
	function formateproposedType(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				proposalManage.proposedType);
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'"
		style="background: #ffffff; height: 0px; padding: 0px; overflow: hidden;">
		<div id="toolbarProposalManage" class="datagrid-toolbar">
			<table>
				<tr>

					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_proposalManage_table_${param.standName}"
						permissionDes="添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="proposalManage.insert();"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_proposalManage_button_edit"
						permissionDes="编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-edit"
							plain="true" onclick="proposalManage.modify();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_proposalManage_button_delete"
						permissionDes="删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="proposalManage.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_proposalManage_button_query"
						permissionDes="查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="proposalManage.openSearchForm();"
							href="javascript:void(0);">查询</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_proposalManage_button_seniorquery"
						permissionDes="高级查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="selfDefQury.show();"
							href="javascript:void(0);">高级查询</a></td>
					</sec:accesscontrollist>

				</tr>
			</table>
		</div>
		<table id="dgProposalManage"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarProposalManage',
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
					<th data-options="field:'strId', halign:'center'" width="220">装配结构ID</th>

					<th data-options="field:'parentId', halign:'center'" width="220">父类建议ID</th>

					<th data-options="field:'proposalCode', halign:'center'"
						width="220">建议编号</th>

					<th data-options="field:'proposalName', halign:'center'"
						width="220">建议标题</th>

					<th data-options="field:'proposalMain', halign:'center'"
						width="220">建议正文</th>

					<th
						data-options="field:'status', halign:'center',formatter:formatestatus"
						width="220">状态</th>
					<th
						data-options="field:'publicationTime', halign:'center',formatter:formateDate"
						width="220">建议发表时间</th>
					<th data-options="field:'proposalUserIdAlias',align:'center'"
						width="220">建议提出人</th>
					<th data-options="field:'proposalUserDeptAlias',align:'center'"
						width="220">建议提出人部门（专业）</th>

					<th
						data-options="field:'isDispute', halign:'center',formatter:formateisDispute"
						width="220">是否有争议</th>
					<th data-options="field:'isApproval', halign:'center'" width="220">320厂是否批准建议（320厂默认否）</th>

					<th data-options="field:'replierUserIdAlias',align:'center'"
						width="220">回复人ID</th>
					<th
						data-options="field:'proposedType', halign:'center',formatter:formateproposedType"
						width="220">建议状态</th>
					<th data-options="field:'picture', halign:'center'" width="220">图片</th>

				</tr>
			</thead>
		</table>
	</div>
	<!--*****************************搜索*********************************  -->
	<div id="searchDialog"
		data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
		style="width: 904px; height: 340px; display: none;">
		<form id="proposalManage">
			<table class="form_commonTable">
				<tr>
					<th width="10%">装配结构ID:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="strId" /></td>
					<th width="10%">父类建议ID:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="parentId" /></td>
				</tr>
				<tr>
					<th width="10%">建议编号:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="proposalCode" /></td>
					<th width="10%">建议标题:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="proposalName" /></td>
				</tr>
				<tr>
					<th width="10%">建议正文:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="proposalMain" /></td>
					<th width="10%">状态:</th>
					<td width="40%"><pt6:syslookup name="status" isNull="true"
							lookupCode="PROPOSED_STATUS"
							dataOptions="width:151,editable:false,panelHeight:'auto'">
						</pt6:syslookup></td>
				</tr>
				<tr>
				<tr>
					<th width="10%">建议发表时间 从:</th>
					<td width="40%"><input name="publicationTimeBegin"
						id="publicationTimeBegin" class="easyui-datebox" editable="false" />
					<th width="10%">建议发表时间 (至):</th>
					<td width="40%"><input name="publicationTimeEnd"
						id="publicationTimeEnd" class="easyui-datebox" editable="false" />
					</td>
				</tr>
				<th width="10%">建议提出人:</th>
				<td width="40%"><input title="建议提出人" class="inputbox"
					type="hidden" name="proposalUserId" id="proposalUserId" />
					<div class="">
						<input class="easyui-validatebox" name="proposalUserIdAlias"
							id="proposalUserIdAlias" readOnly="readOnly"></input>
					</div></td>
				<th width="10%">建议提出人部门（专业）:</th>
				<td width="40%"><input title="建议提出人部门（专业）" class="inputbox"
					type="hidden" name="proposalUserDept" id="proposalUserDept" />
					<div class="">
						<input class="easyui-validatebox" name="proposalUserDeptAlias"
							id="proposalUserDeptAlias" readOnly="readOnly"></input>
					</div></td>
				</tr>
				<tr>
					<th width="10%">是否有争议:</th>
					<td width="40%"><pt6:syslookup name="isDispute" isNull="true"
							lookupCode="PROPOSED_IS_DISPUTE"
							dataOptions="width:151,editable:false,panelHeight:'auto'">
						</pt6:syslookup></td>
					<th width="10%">320厂是否批准建议（320厂默认否）:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="isApproval" /></td>
				</tr>
				<tr>
					<th width="10%">回复人ID:</th>
					<td width="40%"><input title="回复人ID" class="inputbox"
						type="hidden" name="replierUserId" id="replierUserId" />
						<div class="">
							<input class="easyui-validatebox" name="replierUserIdAlias"
								id="replierUserIdAlias" readOnly="readOnly"></input>
						</div></td>
					<th width="10%">建议状态:</th>
					<td width="40%"><pt6:syslookup name="proposedType"
							isNull="true" lookupCode="PROPOSED_TYPE"
							dataOptions="width:151,editable:false,panelHeight:'auto'">
						</pt6:syslookup></td>
				</tr>
				<tr>
					<th width="10%">图片:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="picture" /></td>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td align="right"><a class="easyui-linkbutton primary-btn"
						iconCls="" plain="false" onclick="proposalManage.searchData();"
						href="javascript:void(0);">查询</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="proposalManage.clearData();"
						href="javascript:void(0);">清空</a> <a class="easyui-linkbutton"
						iconCls="" plain="false"
						onclick="proposalManage.hideSearchForm();"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
	<script
		src=" avicit/discussion_manage/proposalmanage/js/ProposalManage.js"
		type="text/javascript"></script>

</body>
</html>