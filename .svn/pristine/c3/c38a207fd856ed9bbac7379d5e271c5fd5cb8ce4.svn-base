<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<%@ page import="avicit.platform6.api.session.SessionHelper"%>
<html>
<head>

<%
	String path = request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工作移交</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/bpmclient/bpm/ProcessCommonJsInclude.jsp"></jsp:include>

</head>

<script type="text/javascript">
var baseurl = '<%=ViewUtil.getRequestPath(request)%>';

	var workhandDialog = null;
	/**
	 * 工作移交前检查是否存在未注销的工作移交的回调函数
	 */
	function checkBeforeDoWorkhand(checkResult) {
		if (checkResult.checkResult == '0') {
			var usd = new UserSelectDialog("workhandDialog", "626", "395", "avicit/platform6/bpmclient/workhand/workhandAdd.jsp", "工作移交");
			workhandDialog = usd;
			var buttons = [ {
				text : '提交',
				id : 'processSubimt',
				//iconCls : 'icon-submit',
				handler : function() {
					var frmId = $('#workhandDialog iframe').attr('id');
					var frm = document.getElementById(frmId).contentWindow;

					var receptUserName = frm.$("#receptUserName").val();
					var handEffectiveDate = frm.$("#handEffectiveDate").datebox("getValue");
					var backDate = frm.$("#backDate").datebox("getValue");

					if (receptUserName == "" || handEffectiveDate == "" || backDate == "") {
						alert("接受人、生效日期、返回日期不能为空！");
						return;
					}
					if (handEffectiveDate > backDate) {
						alert("返回日期不能在生效日期之前！");
						return;
					}
					var dataVo = frm.$('#workhandForm').serializeArray();
					var dataJson = frm.convertToJson(dataVo);
					dataVo = JSON.stringify(dataJson);
					ajaxRequest("POST", "dataVo=" + dataVo, "platform/bpm/clientbpmWorkHandAction/doWorkHand", "json", "doWorkHandBack");
				}
			} ];
			usd.createButtonsInDialog(buttons);
			usd.show();
		} else {
			$.messager.alert("操作提示", "您还有未注销的工作移交，请注销后再进行移交操作！", "info");
		}
	}

	/**
	 * 工作移交完成后的回调函数
	 */
	function doWorkHandBack(result) {
		try {
			$.messager.show({
				title : '提示',
				msg : result.msg + ""
			});
			if (result.success) {
				searchFun();
				workhandDialog.close();
				workhandDialog = null;
			}
			//$.messager.alert("提示",result.msg+"","info");
		} catch (e) {
			$.messager.alert('提示', result + "");
		}
	}

	var cancelWorkhandDialog = null;
	/**
	 * 注销工作移交前检查是否存在待注销的工作的回调函数
	 */
	function checkBeforeCancelWorkhand(checkResult) {
		if (checkResult.checkResult == '1') {
			var usd = new UserSelectDialog("cancelWorkhandDialog", "700", "450", "avicit/platform6/bpmclient/workhand/processWorkHandTask.jsp", "工作移交注销");
			cancelWorkhandDialog = usd;
			var buttons = [ {
				text : '提交',
				id : 'processSubimt',
				handler : function() {
					var frmId = $('#cancelWorkhandDialog iframe').attr('id');
					var frm = document.getElementById(frmId).contentWindow;
					var rows = frm.$('#WorkHandTasklist').datagrid('getChecked');
					var deleteRowArr = [];
					if (rows.length > 0) {
						for (var i = 0; i < rows.length; i++) {
							deleteRowArr.push(rows[i].dbid + "@@" + rows[i].assignee + "@@" + rows[i].processInstanceId);
						}
					}
					var message = "您确认要注销选定的数据吗？";
					if (deleteRowArr.length == 0) {
						message = "没有选择数据，您确认要注销这条工作移交记录吗？";
					}
					$.messager.confirm("操作提示", message, function(data) {
						if (data) {
							ajaxRequest("POST", "deleteRows=" + deleteRowArr.join(','), "platform/bpm/clientbpmWorkHandAction/deleteSysWorkHandPass", "json", "cancelWorkhandBack");
						} else {
							usd.close();
						}
					});
				}
			} ];
			usd.createButtonsInDialog(buttons);
			usd.show();
		} else if (checkResult.checkResult == '2') {
			$.messager.confirm("操作提示", "确定注销这条工作移交记录吗？", function(data) {
				if (data) {
					ajaxRequest("POST", "deleteRows=", "platform/bpm/clientbpmWorkHandAction/deleteSysWorkHandPass", "json", "cancelWorkhandBack");
				}
			});
		} else {
			$.messager.alert("操作提示", "没有可以注销的工作移交！", "info");
		}
	}
	/**
	 * 工作注销回调函数
	 */
	function cancelWorkhandBack(retResult) {
		if (retResult.result == '1') {
			searchFun();
			$.messager.show({
				title : '提示',
				msg : "注销成功!"
			});
			if (cancelWorkhandDialog != null) {
				cancelWorkhandDialog.close();
				cancelWorkhandDialog = null;
			}
		} else {
			$.messager.alert("操作提示", "注销失败!", "info");
		}
	}
	/**
	 *选择人员及部门
	 */
	function selectUserDept() {
		var usd = new UserSelectDialog('innerAddressDialog', 800, 600, getPath2() + '/platform/user/bpmSelectUserAction/userSelectCommon?isMultiple=true', '选择人员部门');
		var buttons = [ {
			text : '确定',
			id : 'processSubimt',
			//iconCls : 'icon-submit',
			handler : function() {
				var frmId = $('#innerAddressDialog iframe').attr('id');
				var frm = document.getElementById(frmId).contentWindow;
				var resultDatas = frm.getSelectedResultDataJson();
				var frmResultId = $('#workhandDialog iframe').attr('id');
				var frmResult = document.getElementById(frmResultId).contentWindow;
				if (resultDatas.length > 1) {
					alert("只能选择一位接受人！");
					return;
				}
				for (var i = 0; i < resultDatas.length; i++) {
					var resultData = resultDatas[i];
					frmResult.$("#receptUserId").val(resultData.userId);
					frmResult.$("#receptUserName").val(resultData.userName);
					frmResult.$("#receptDeptId").val(resultData.deptId);
					frmResult.$("#receptDeptName").val(resultData.deptName);
				}
				usd.close();
			}
		} ];
		usd.createButtonsInDialog(buttons);
		usd.show();
	}
	//查询
	function searchFun() {
		var queryParams = {
			filter_EQ_validFlag : $('#filter_EQ_validFlag').combobox('getValue'),
			filter_LE_handEffectiveDate : $('#filter_LE_handEffectiveDate').datetimebox('getValue'),
			filter_GE_handEffectiveDate : $('#filter_GE_handEffectiveDate').datetimebox('getValue')
		};
		$("#workhandlist").datagrid("uncheckAll").datagrid("unselectAll").datagrid("clearSelections");
		$("#workhandlist").datagrid("load", queryParams);
	}
	function clearFun() {
		$('#searchForm input').val('');
		$('#searchForm select').val('');
	}
</script>
<body class="easyui-layout" fit="true">
	<div data-options="region:'north',split:false,border:false"
		style="height:70px">
		<fieldset>
			<legend>查询条件</legend>
			<table class="tableForm" id="searchForm">
				<tr>
					<td>生效日期(起)</td>
					<td style="width:170px"><input name="filter_GE_handEffectiveDate" id="filter_GE_handEffectiveDate" class="easyui-datebox" editable="false" style="width: 150px;" /></td>
					<td>生效日期(止)</td>
					<td style="width:170px"><input name="filter_LE_handEffectiveDate" id="filter_LE_handEffectiveDate" class="easyui-datebox" editable="false" style="width: 150px;" /></td>
					<td>是否有效</td>
					<td style="width:170px">
						<select name="filter_EQ_validFlag" id="filter_EQ_validFlag" class="easyui-combobox" style="width: 156px;">
							<option value=""></option>
							<option value="1">有效</option>
							<option value="0">无效</option>
						</select>
					</td>
					<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchFun();" href="javascript:void(0);">查询</a><a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="clearFun();" href="javascript:void(0);">清空</a></td>
				</tr>
			</table>
		</fieldset>
	</div>
	<div data-options="region:'center',split:false,border:false">
		<table id="workhandlist" class="easyui-datagrid"
			data-options="fit: true,
						url:'platform/bpm/clientbpmWorkHandAction/getSysWorkHandListByPage.json',
						queryParams:{filter_EQ_workOwnerId : ''},
						sortName: 'handEffectiveDate',
						toolbar:'#myToolbar',
						sortOrder: 'desc',
						border: false,
						rownumbers: true,
						animate: true,
						fitColumns: true,
						autoRowHeight: false,
						idField :'id',
						singleSelect: true,
						pagination:true,
						pageSize:dataOptions.pageSize,
						pageList:dataOptions.pageList,
						striped:true">
			<thead>
				<tr>
					<th data-options="field:'id', hidden:true">id</th>
					<th data-options="field:'workOwnerName',align:'left',sortable:true" width="25">移交人</th>
					<th data-options="field:'workOwnerDeptName',align:'left',sortable:true" width="25">移交部门</th>
					<th data-options="field:'receptUserName',align:'left',sortable:true" width="25">接受人</th>
					<th data-options="field:'receptDeptName',align:'left',sortable:true" width="25">接受人部门</th>
					<th data-options="field:'handReason',align:'left',sortable:true" width="80">移交原因</th>
					<th data-options="field:'handEffectiveDate',align:'left',sortable:true,formatter:formatDate" width="25">生效日期</th>
					<th data-options="field:'backDate',align:'left',sortable:true,formatter:formatDate" width="25">预计返回日期</th>
					<th data-options="field:'handDate',align:'left',sortable:true,formatter:formatDate" width="25">登记日期</th>
					<th data-options="field:'validFlag',align:'left',sortable:true,formatter:formatValidFlag" width="15">是否有效</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="myToolbar" class="datagrid-toolbar">
		<table>
			<tr>
				<td><a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addWorkHand();" href="javascript:void(0);">工作移交</a></td>
				<td><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeWorkHand();" href="javascript:void(0);">注销移交</a></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		function formatDate(value, rec) {
			var newDate = new Date(value);
			return newDate.Format("yyyy-MM-dd");
		}
		function formatValidFlag(value, rec) {
			if (value == '1') {
				return "有效";
			} else {
				return "无效";
			}
		}
		function addWorkHand() {
			ajaxRequest("POST", "", "platform/bpm/clientbpmWorkHandAction/checkBeforeWorkHand", "json", "checkBeforeDoWorkhand");
		}
		function removeWorkHand() {
			ajaxRequest("POST", "", "platform/bpm/clientbpmWorkHandAction/checkBeforeWorkHand", "json", "checkBeforeCancelWorkhand");
		}
	</script>
</body>
</html>