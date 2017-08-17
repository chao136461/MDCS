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
<!-- ControllerPath = "discussion_manage/discussionmanage/DiscussionManageController/DiscussionManageInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var discussionManage;
	$(function() {
		var userCommonSelector = new CommonSelector("user","userSelectCommonDialog","designerId","designerName");
		userCommonSelector.init();
		
		discussionManage = new DiscussionManage('dgDiscussionManage', '${url}',
				'searchDialog', 'discussionManage');
		
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
			name : '讨论区状态',
			field : 'STATUS',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '讨论区编号',
			field : 'TNM_CODE',
			type : 1,
			dataType : 'string'
		};
		array.push(searchObject);

		var searchObject = {
			name : '讨论区创建时间',
			field : 'CREATE_DATE',
			type : 1,
			dataType : 'date'
		};
		array.push(searchObject);
		///

		selfDefQury.init(array);
		selfDefQury.setQuery(function(param) {
			discussionManage.searchDataBySfn(param);
		});
	});
	function formateDate(value, row, index) {
		return discussionManage.formate(value);
	}
	function formateDateTime(value, row, index) {
		return discussionManage.formateDateTime(value);
	}
	//discussionManage.detail(\''+row.id+'\')
	function formateHref(value, row, inde) {
		return '<a href="javascript:void(0);" onclick="discussionManage.detail(\''
				+ row.id + '\');">' + value + '</a>';
	}
	function formateDetail(value, row, index){
		return '<a herf="javascript:void(0);" onclick="discussionManage.usersDetail(\''+row.strId+'\');">'+'详细'+'</a>';
	}
	function enterTalk(value, row, index){
		return '<a herf="javascript:void(0);" onclick="discussionManage.enterTalks(\''+row.strId+'\',\''+row.classCode+'\');">'+'进入讨论区'+'</a>'
	}
	//成熟度通用代码
	function formateStatus(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				discussionManage.status);
	}
	//讨论区状态通用代码
	function formatetalkStatus(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				discussionManage.talkStatus);
	}
</script>
</head>
<body class="easyui-layout" fit="true">
	<div id="searchDialog" data-options="region:'north'" style="height:60px; display: none;">
		<form id="discussionManage">
			<table class="form_commonTable">
				<tr>
					<th width="8%">装配结构:</th>
					<input class="easyui-validatebox"  type="hidden" name="strId" />
					<td width="25%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="className" /></td>
					<th width="8%">所有者:</th>
					<input class="easyui-validatebox" type="hidden" name="designerId" id="designerId"/>
					<td width="25%"><input class="easyui-validatebox"
					style="width: 151px;" type="text" name="designerName" id="designerName" /></td>
					
					<td align="center"><a class="easyui-linkbutton primary-btn"
						iconCls="" plain="false" onclick="discussionManage.searchData();"
						href="javascript:void(0);">查询</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="discussionManage.clearData();"
						href="javascript:void(0);">清空</a> <!-- <a class="easyui-linkbutton"
						iconCls="" plain="false"
						onclick="discussionManage.hideSearchForm();"
						href="javascript:void(0);">返回</a></td> -->
				</tr>
				<tr>
					
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center'"
		style="background: #ffffff; height: 0px; padding: 0px; overflow: hidden;">
		<div id="toolbarDiscussionManage" class="datagrid-toolbar">
			<table>
				<tr>

					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_discussionManage_table_${param.standName}"
						permissionDes="添加">
						<td><a class="easyui-linkbutton" iconCls="icon-add"
							plain="true" onclick="discussionManage.insert();"
							href="javascript:void(0);">添加</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_discussionManage_button_edit"
						permissionDes="编辑">
						<td><a class="easyui-linkbutton" iconCls="icon-edit"
							plain="true" onclick="discussionManage.modify();"
							href="javascript:void(0);">编辑</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_discussionManage_button_delete"
						permissionDes="删除">
						<td><a class="easyui-linkbutton" iconCls="icon-remove"
							plain="true" onclick="discussionManage.del();"
							href="javascript:void(0);">删除</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_discussionManage_button_query"
						permissionDes="查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="discussionManage.openSearchForm();"
							href="javascript:void(0);">查询</a></td>
					</sec:accesscontrollist>
					<sec:accesscontrollist hasPermission="3"
						domainObject="formdialog_discussionManage_button_seniorquery"
						permissionDes="高级查询">
						<td><a class="easyui-linkbutton" iconCls="icon-search"
							plain="true" onclick="selfDefQury.show();"
							href="javascript:void(0);">高级查询</a></td>
					</sec:accesscontrollist>

				</tr>
			</table>
		</div>
		<table id="dgDiscussionManage"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarDiscussionManage',
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
					<th data-options="field:'id', halign:'center',checkbox:true" width="220">讨论区ID</th>
					<th data-options="field:'strId', halign:'center',hidden:true" width="220">装配结构ID</th>
					<th data-options="field:'status', halign:'center',hidden:true" width="220">讨论区状态</th>
					<th data-options="field:'tnmCode', halign:'center'" width="220">讨论区编号</th>
					<th data-options="field:'classCode', halign:'center'" width="220">关联零部件</th>
					<th data-options="field:'edition', halign:'center'" width="220">版本</th>
					<th data-options="field:'className', halign:'center'" width="220">零件名称</th>
					<th data-options="field:'strStatus', halign:'center',formatter:formateStatus" width="220">成熟度</th>
					<th data-options="field:'createDate', halign:'center',formatter:formateDate" width="220">讨论区创建时间</th>
					<th data-options="field:'operation', align:'center',formatter:formateDetail" width="220" >操作1</th>
					<th data-options="field:'enterTalk', align:'center',formatter:enterTalk" width="220" >操作2</th>
				</tr>
			</thead>
		</table>
	</div>
	<!--*****************************搜索*********************************  -->
	<!-- <div id="searchDialog"
		data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
		style="width: 904px; height: 340px; display: none;">
		<form id="discussionManage">
			<table class="form_commonTable">
				<tr>
					<th width="10%">装配结构ID:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="strId" /></td>
					<th width="10%">讨论区状态:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="status" /></td>
				</tr>
				<tr>
					<th width="10%">讨论区编号:</th>
					<td width="40%"><input class="easyui-validatebox"
						style="width: 151px;" type="text" name="tnmCode" /></td>
				</tr>
				<tr>
					<th width="10%">讨论区创建时间从:</th>
					<td width="40%"><input name="createDateBegin"
						id="createDateBegin" class="easyui-datebox" editable="false" />
					<th width="10%">讨论区创建时间(至):</th>
					<td><input name="createDateEnd" id="createDateEnd"
						class="easyui-datebox" editable="false" /></td>
				</tr>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td align="right"><a class="easyui-linkbutton primary-btn"
						iconCls="" plain="false" onclick="discussionManage.searchData();"
						href="javascript:void(0);">查询</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="discussionManage.clearData();"
						href="javascript:void(0);">清空</a> <a class="easyui-linkbutton"
						iconCls="" plain="false"
						onclick="discussionManage.hideSearchForm();"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div> -->
	<script
		src=" avicit/discussion_manage/discussionmanage/js/DiscussionManage.js"
		type="text/javascript"></script>

</body>
</html>