<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/majormanage/MajorManageController/operation/sub/Add/null" -->
<title>娣诲姞</title>
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
			<input type="hidden" name="id" /> <input type="hidden" name="majId"
				id="majId" value='${pid}' />
			<table class="form_commonTable">
				<tr>
					<th width="10%"><span style="color: red;">*</span> 装配结构ID:</th>
					<td width="40%"><input title="装配结构ID"
						class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[32]'"
						style="width: 180px;" type="text" name="strId" id="strId" /></td>
					<th width="10%"><span style="color: red;">*</span> 状态(审查确认):</th>
					<td width="40%"><input title="状态(审查确认)"
						class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[32]'"
						style="width: 180px;" type="text" name="status" id="status" /></td>
				</tr>
				<tr>
					<th width="10%">角色:</th>
					<td width="40%"><input title="角色" class="inputbox"
						style="width: 182px;" type="hidden" name="dutyUserRole"
						id="dutyUserRole" />
						<div class="">
							<input class="easyui-validatebox" name="dutyUserRoleAlias"
								id="dutyUserRoleAlias" readOnly="readOnly" style="width: 179px;"></input>
						</div></td>
					<th width="10%">审查人员:</th>
					<td width="40%"><input title="审查人员" class="inputbox"
						style="width: 182px;" type="hidden" name="dutyUserId"
						id="dutyUserId" />
						<div class="">
							<input class="easyui-validatebox" name="dutyUserIdAlias"
								id="dutyUserIdAlias" readOnly="readOnly" style="width: 179px;"></input>
						</div></td>
				</tr>
				<tr>
					<th width="10%">审查人员所在部门:</th>
					<td width="40%"><input title="审查人员所在部门" class="inputbox"
						style="width: 182px;" type="hidden" name="duty DeptId"
						id="duty DeptId" />
						<div class="">
							<input class="easyui-validatebox" name="dutyDeptIdAlias"
								id="dutyDeptIdAlias" readOnly="readOnly" style="width: 179px;"></input>
						</div></td>
				</tr>
			</table>
		</form>

	</div>
	<div data-options="region:'south',border:false" style="height: 40px;">
		<div id="toolbar"
			class="datagrid-toolbar datagrid-toolbar-extend foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td width="50%" align="right"><a title="淇濆瓨" id="saveButton"
						class="easyui-linkbutton primary-btn" onclick="saveForm();"
						href="javascript:void(0);">保存</a> <a title="杩斿洖" id="returnButton"
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
				message : '璇疯緭鍏ユ渶澶�{0} 瀛楃.'
			}
		});
		$(function() {
			var dutyUserRoleRoleCommonSelector = new CommonSelector("role",
					"roleSelectCommonDialog", "dutyUserRole",
					"dutyUserRoleAlias", null, null, null);
			dutyUserRoleRoleCommonSelector.init();
			var dutyUserIdUserCommonSelector = new CommonSelector("user",
					"userSelectCommonDialog", "dutyUserId", "dutyUserIdAlias");
			dutyUserIdUserCommonSelector.init();
			var dutyDeptIdDeptCommonSelector = new CommonSelector("dept",
					"deptSelectCommonDialog", "dutyDeptId", "dutyDeptIdAlias");
			dutyDeptIdDeptCommonSelector.init();
		});
		function closeForm() {
			parent.instituteCensorManage.closeDialog("#insert");
		}
		function saveForm() {
			if ($('#form').form('validate') == false) {
				return;
			}
			$('#saveButton').linkbutton('disable').unbind("click");
			parent.instituteCensorManage.save(serializeObject($('#form')),
					"#insert");
		}
	</script>
</body>
</html>