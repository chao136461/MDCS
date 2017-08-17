<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/factorycomplex/FactoryComplexController/operation/Detail/id" -->
<title>详情</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js"
	type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false"
		style="overflow: hidden; padding-bottom: 35px;">
		<form id='form'>
			<input type="hidden" name="id" value='${factoryComplexDTO.id}' /> <input
				type="hidden" name="id" value='${factoryComplexDTO.id}' />
			<table width="100%" style="padding-top: 10px;">
				<tr>
					<th align="right"><span style="color: red;">*</span> 装配结构ID:</th>
					<td><input title="装配结构ID" class="easyui-validatebox"
						data-options="required:true" style="width: 180px;" type="text"
						name="strId" id="strId" readonly="readonly"
						value='${factoryComplexDTO.strId}' /></td>
					<th align="right">车间:</th>
					<td><input title="{param.field.comment}" class="inputbox"
						style="width: 180px;" type="hidden" name="workshopId"
						id="workshopId" />
						<div class="">
							<input class="easyui-validatebox" name="workshopIdAlias"
								id="workshopIdAlias" readOnly="readOnly" style="width: 179px;"></input>
						</div></td>
				</tr>
				<tr>
					<th align="right">状态:</th>
					<td><input title="状态" class="inputbox" style="width: 180px;"
						type="text" name="status" id="status" readonly="readonly"
						value='${factoryComplexDTO.status}' /></td>
					<th align="right">路线类型:</th>
					<td><input title="路线类型" class="inputbox" style="width: 180px;"
						type="text" name="routeType" id="routeType" readonly="readonly"
						value='${factoryComplexDTO.routeType}' /></td>
				</tr>
				<tr>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			var workshopIdDeptCommonSelector = new CommonSelector("dept",
					"deptSelectCommonDialog", "workshopId", "workshopIdAlias");
			workshopIdDeptCommonSelector.init();
		});
	</script>
</body>
</html>