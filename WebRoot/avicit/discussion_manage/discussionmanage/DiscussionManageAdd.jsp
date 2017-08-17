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
<!-- ControllerPath = "discussion_manage/discussionmanage/DiscussionManageController/operation/Add/null" -->
<title>添加</title>
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
			<input type="hidden" name="id" />
			<table class="form_commonTable">
				<tr>



					<th width="10%"><span class="remind">*</span> 装配结构:</th>
					
					<td width="40%"><input title="装配结构ID"
						class="easyui-validatebox"
						data-options="validType:'maxLength[50]'"
						style="width: 180px;" type="text" name="strId" id="strId" /></td>

					<th width="10%"><span class="remind">*</span> 讨论区状态:</th>
					<td width="40%"><!-- <input title="讨论区状态"
						class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[32]'"
						style="width: 180px;" type="text" name="status" id="status" /> -->
						<pt6:syslookup name="status" isNull="false"
							lookupCode="PLATFORM_TALK_STATUS"
							dataOptions="width:180,editable:false,panelHeight:'auto'">
						</pt6:syslookup>
						
						
						</td>
				</tr>
				<tr>

					<th width="10%"><span class="remind">*</span> 讨论区编号:</th>
					<td width="40%"><input title="讨论区编号"
						class="easyui-validatebox" readonly
						data-options="required:true,validType:'maxLength[20]'"
						style="width: 180px;" type="text" name="tnmCode" id="tnmCode" value="${tlCode}"/></td>

					<th width="10%"><span class="remind">*</span> 讨论区创建时间:</th>
					<td width="40%"><input title="讨论区创建时间" class="easyui-datebox "
						editable="false" data-options="required:true"
						style="width: 182px;" type="text" name="createDate"
						id="createDate" /></td>
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
			$('#strId').combobox({    
			    url:'platform/discussion_manage/structuremanage/StructureManageController/operation/getStructureManagesCombox.json',    
			    valueField:'id',    
			    textField:'classCode',
			    panelWidth:'300'
			});
		});
		function closeForm() {
			parent.discussionManage.closeDialog("#insert");
		}
		function saveForm() {
			console.log(serializeObject($('#form')));
			if ($('#form').form('validate') == false) {
				return;
			}
			$('#saveButton').linkbutton('disable').unbind("click");
			parent.discussionManage.save(serializeObject($('#form')), "#insert");
		}
	</script>
</body>
</html>