<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>treedemo</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
</head>

<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false">
		<form id='form' class='form'>
			<input type="hidden" name='id'       id='id'		value='${addformDataDTO.id}'/> 
			<input type="hidden" name="version"  id="version"   value='${addformDataDTO.version}'/>
			<input type="hidden" name="addType"  id="addType" 	value='2'/>
			<table class="form_commonTable">
				<tr>
					<th width="10%"><span class="remind">*</span> 分类名称:</th>
					<td width="40%"><input title="分类名称:" class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[50]'"
						style="width: 180px;" type="text" name="className" id="className"
						value='<c:out value='${addformDataDTO.className}'/>' /></td>
					<th width="10%"><span class="remind">*</span>分类编号:</th>
					<td width="40%"><input title="分类编号:" class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[32]'"
						style="width: 180px;" type="text" name="classCode"
						id="classCode"
						value='<c:out value='${addformDataDTO.classCode}'/>' />
					</td>
				</tr>
				<tr>
					<th width="10%">分类类型:</th>
					<td width="40%"><pt6:syslookup name="classType"
							lookupCode="CLASS_TYPE" isNull="true"
							defaultValue='${addformDataDTO.classType}'
							dataOptions="width:180,editable:false,panelHeight:'auto'">
						</pt6:syslookup></td>
					<th width="10%"><span class="remind">*</span>版本:</th>
					<td width="40%"><input title="版本:" class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[32]'"
						style="width: 180px;" type="text" name="edition"
						id="edition"
						value='<c:out value='${addformDataDTO.edition}'/>' />
					</td>
				</tr>
				<tr>
					<th width="10%">成熟度:</th>
					<td width="40%"><pt6:syslookup name="status"
							lookupCode="STR_STATUS" isNull="true"
							defaultValue='${addformDataDTO.status}'
							dataOptions="width:180,editable:false,panelHeight:'auto'">
						</pt6:syslookup></td>
				</tr>
			</table>
		</form>
		<div id="toolbar"
			class="datagrid-toolbar datagrid-toolbar-extend foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td width="60%" align="right"><a title="保存" id="saveButton"
						class="easyui-linkbutton primary-btn" plain="false"
						onclick="saveForm();" href="javascript:void(0);">保存</a> <a
						title="返回" id="returnButton" class="easyui-linkbutton"
						plain="false" onclick="closeForm();" href="javascript:void(0);">返回</a>
					</td>
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
		function closeForm() {
			parent.structuralRelationship.hideSearchForm("#modify");
		}
		function saveForm() {
			if ($('#form').form('validate') == false) {
				return;
			}
			parent.structuralRelationship.save(serializeObject($('#form')),'${url}', '#modify', '${id}');
		}
	</script>
</body>
</html>