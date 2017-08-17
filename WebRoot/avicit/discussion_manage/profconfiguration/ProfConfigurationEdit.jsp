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
<!-- ControllerPath = "discussion_manage/profconfiguration/ProfConfigurationController/operation/Edit/id" -->
<title>修改</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<%String pid=request.getParameter("pid");%>
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
			<input type="hidden" name="version" value='${profConfigurationDTO.version}' /> 
			<input type="hidden" name="id" value='${profConfigurationDTO.id}' />
			<input type="hidden" name="typeId" value='${profConfigurationDTO.typeId}' />
				
			<table class="form_commonTable" style="padding-top: 80px;padding-right: 50px;">
				<tr>
					<th width="10%">型号:</th>
					<td width="40%"><input title="型号ID"
						class="inputbox easyui-validatebox"
						data-options="validType:'maxLength[32]'" disabled="disabled" style="width: 180px;"
						type="text" name="classCode" id="classCode"
						value='<c:out value='${profConfigurationDTO.classCode}'/>' /></td>
				<tr/>
				<tr id="categ">
					<th width="10%">类别:</th>
					<td width="40%"><pt6:syslookup name="category"
							lookupCode="PRO_CATEGORY" isNull="true"
							defaultValue='${profConfigurationDTO.category}' 
							dataOptions="width:180,editable:false,panelHeight:'auto',disabled:true">
						</pt6:syslookup></td>
				</tr>
				<tr>
					<th width="10%">专业:</th>
					<td width="40%"><input title="专业" class="inputbox"
						style="width: 182px;" type="hidden" name="profession"
						id="profession" value='${profConfigurationDTO.profession}' />
						<div class="">
							<input class="easyui-validatebox" name="professionAlias"
								id="professionAlias" readOnly="readOnly"
								value='<c:out  value='${profConfigurationDTO.professionAlias}'/>'
								style="width: 179px;"></input>
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
		var pid = <%=pid %>;
		$(function() {
			var professionDeptCommonSelector = new CommonSelector("dept", "deptSelectCommonDialog", "profession", "professionAlias","","","",false);
			professionDeptCommonSelector.init();
		})
		
		//601所专业配置有类别,320无类别
			
			if(pid=="601"){
				$('#categ').show();
			}else{
				$('#categ').hide();
			}
		
		//加载型号
		$('#typeId').combobox({
				valueField: 'CLASS_NAME',    
		        textField: 'CLASS_NAME',    
		        url: 'platform/discussion_manage/structuralrelationship/StructuralRelationshipController/findStructuralType',
		       
		})
		function closeForm() {
			parent.profConfiguration.closeDialog("#edit");
		}
		function saveForm() {
			if ($('#form').form('validate') == false) {
				return;
			}
			$('#saveButton').linkbutton('disable').unbind("click");
			parent.profConfiguration.save(serializeObject($('#form')), "#edit");
		}
	</script>
</body>
</html>