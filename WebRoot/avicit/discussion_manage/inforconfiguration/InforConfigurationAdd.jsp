<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/inforconfiguration/InforConfigurationController/operation/Add/null" -->
<title>添加</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js" type="text/javascript"></script>
<style type="text/css">
.required-icon {
	maring:0px;
	padding:0px;
	width: 10px;
	height: 23px;
	overflow: hidden;
	display: inline-block;
	vertical-align:-4px;
	/* opacity: 0.6; */
/* 	filter: alpha(opacity=60); */
	background: url('avicit/platform6/modules/system/sysuser/view/required.gif') no-repeat center center;	
}
.inputbox{
	background-color: #fff;
	border: 1px solid #95b8e7;
	color: #000;
	height: 18px;
}
</style>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false" style="overflow:hidden;padding-bottom:35px;">
		<form id='form'>
			<input type="hidden" name="id" />
				<table width="100%" style="padding-top: 10px;">
					<tr>
							<th align="right">审查确认:</th>
							<td>
								  <input title="审查确认" class="inputbox" style="width: 180px;" type="text" name="isConfirm" id="isConfirm"/>
							</td>
													
							<th align="right">模型检查:</th>
							<td>
								  <input title="模型检查" class="inputbox" style="width: 180px;" type="text" name="modelCheck" id="modelCheck"/>
							</td>
													
					</tr>
					<tr>
							<th align="right">干涉检查:</th>
							<td>
								  <input title="干涉检查" class="inputbox" style="width: 180px;" type="text" name="interfereCheck" id="interfereCheck"/>
							</td>
													
							<th align="right">审批状态:</th>
							<td>
								  <input title="审批状态" class="inputbox" style="width: 180px;" type="text" name="approveState" id="approveState"/>
							</td>
													
					</tr>
					<tr>
							<th align="right">是否降级:</th>
							<td>
								  <input title="是否降级" class="inputbox" style="width: 180px;" type="text" name="isDegrade" id="isDegrade"/>
							</td>
													
							<th align="right">类型ID:</th>
							<td>
								  <input title="类型ID" class="inputbox" style="width: 180px;" type="text" name="typeId" id="typeId"/>
							</td>
													
					</tr>
					<tr>
					</tr>
				</table>
		</form>
		<div id="toolbar" class="datagrid-toolbar datagrid-toolbar-extend">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>	
					<td width="50%" align="right">
						<a title="保存" id="saveButton" class="easyui-linkbutton" onclick="saveForm();" href="javascript:void(0);">保存</a>
						<a title="返回" id="returnButton" class="easyui-linkbutton" onclick="closeForm();" href="javascript:void(0);">返回</a>
					</td>	
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	function closeForm(){
		parent.inforConfiguration.closeDialog("#insert");
	}
	function saveForm(){
		parent.inforConfiguration.save(serializeObject($('#form')),"#insert");
	}
	</script>
</body>
</html>