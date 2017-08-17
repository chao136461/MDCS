<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/processassignment/ProcessAssignmentController/operation/Edit/id" -->
<title>修改</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js"
	type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false">
		<form id='form'>
			<input type="hidden" name="version"
				value='${processAssignmentDTO.version}' /> <input type="hidden"
				name="id" value='${processAssignmentDTO.id}' />
				<input
				title="装配结构ID" class="easyui-validatebox"
				data-options="required:true" style="width: 180px;" type="hidden"
				name="strId" id="strId" value='${processAssignmentDTO.strId}' />
			<table class="form_commonTable">
				<tr>
					<th width="10%">零件编号:</th>
					<td width="40%"><input title="零件编号" class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[32]'"
						style="width: 180px;" type="text" name="strCode" id="strCode"
						readonly="readonly"
						value='<c:out value='${processAssignmentDTO.strCode}'/>' /></td>
					<th width="10%">任务类型:</th>
					<td width="40%"><%-- <input title="任务类型"
						class="inputbox easyui-validatebox"
						data-options="validType:'maxLength[32]'" style="width: 180px;"
						type="text" name="taskType" id="taskType" readonly="readonly"
						value='<c:out value='${processAssignmentDTO.taskType}'/>' /> --%>
						<pt6:syslookup name="taskType"
							lookupCode="PRO_STATUS" isNull="true"
							defaultValue='${processAssignmentDTO.taskType}'
							dataOptions="width:180,editable:false,panelHeight:'auto',readonly:true">
						</pt6:syslookup>
						</td>
				</tr>
				<tr>
					<th width="10%">发出时间:</th>
					<td width="40%"><input title="发出时间" class="easyui-datebox"
						style="width: 182px;" type="text" name="publicationTime"
						id="publicationTime"
						value='${processAssignmentDTO.publicationTime}' /></td>
					<th width="10%">状态:</th>
					<td width="40%"><pt6:syslookup name="status"
							lookupCode="PRO_STATUS" isNull="true"
							defaultValue='${processAssignmentDTO.status}'
							dataOptions="width:180,editable:false,panelHeight:'auto',readonly:true">
						</pt6:syslookup></td>
				</tr>
				<tr>
				</tr>
			</table>
		</form>

	</div>
	<div data-options="region:'south',border:false" style="height: 40px;">
		<div id="toolbar"
			class="datagrid-toolbar datagrid-toolbar-extend foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td width="50%" align="right"><a title="保存" id="saveButton"
						class="easyui-linkbutton primary-btn" onclick="saveForm();"
						href="javascript:void(0);">保存</a> <a title="返回" id="returnButton"
						class="easyui-linkbutton" onclick="closeForm();"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$.extend($.fn.validatebox.defaults.rules, {
			maxLength : {
				validator : function(value, param) {
					return param[0] >= value.length;

				},
				message : '请输入最多 {0} 字符.'
			}
		});
		$(function() {
			if (!"${processAssignmentDTO.publicationTime}" == "") {
				$('#publicationTime').datebox(
						'setValue',
						parserColumnTime(
								"${processAssignmentDTO.publicationTime}")
								.format("yyyy-MM-dd"));
			}
		})
		function closeForm() {
			parent.processAssignment.closeDialog("#edit");
		}
		function saveForm() {
			if ($('#form').form('validate') == false) {
				return;
			}
			$('#saveButton').linkbutton('disable').unbind("click");
			parent.processAssignment.save(serializeObject($('#form')), "#edit");
		}
	</script>
</body>
</html>