<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/processrepresentative/ProcessRepresentativeController/operation/Detail/id" -->
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
			<input type="hidden" name="id" value='${processRepresentativeDTO.id}' />
			<input type="hidden" name="id" value='${processRepresentativeDTO.id}' />
			<table width="100%" style="padding-top: 10px;">
				<tr>
					<th align="right"><span style="color: red;">*</span> 装配结构ID:</th>
					<td><input title="装配结构ID" class="easyui-validatebox"
						data-options="required:true" style="width: 180px;" type="text"
						name="strId" id="strId" readonly="readonly"
						value='${processRepresentativeDTO.strId}' /></td>
					<th align="right"><span style="color: red;">*</span> 任务类型:</th>
					<td><input title="任务类型" class="easyui-validatebox"
						data-options="required:true" style="width: 180px;" type="text"
						name="taskType" id="taskType" readonly="readonly"
						value='${processRepresentativeDTO.taskType}' /></td>
				</tr>
				<tr>
					<th align="right"><span style="color: red;">*</span> 工艺分工人员:</th>
					<td><input title="{param.field.comment}" class="inputbox"
						style="width: 180px;" type="hidden" name="processUserId"
						id="processUserId" />
						<div class="">
							<input class="easyui-validatebox" name="processUserIdAlias"
								id="processUserIdAlias" readOnly="readOnly"
								style="width: 179px;"></input>
						</div></td>
					<th align="right"><span style="color: red;">*</span> 发出时间:</th>
					<td><input title="发出时间"
						class="easyui-datebox easyui-validatebox"
						data-options="required:true" style="width: 180px;" type="text"
						readonly="readonly" name="publicationTime" id="publicationTime"
						value='${processRepresentativeDTO.publicationTime}' /></td>
				</tr>
				<tr>
					<th align="right"><span style="color: red;">*</span>
						状态（是否已分配分工人员） :</th>
					<td><input title="状态（是否已分配分工人员） " class="easyui-validatebox"
						data-options="required:true" style="width: 180px;" type="text"
						name="status" id="status" readonly="readonly"
						value='${processRepresentativeDTO.status}' /></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(function() {
			if (!"${processRepresentativeDTO.publicationTime}" == "") {
				$('#publicationTime').datebox(
						'setValue',
						parserColumnTime(
								"${processRepresentativeDTO.publicationTime}")
								.format("yyyy-MM-dd"));
			}
			var processUserIdUserCommonSelector = new CommonSelector("user",
					"userSelectCommonDialog", "processUserId",
					"processUserIdAlias");
			processUserIdUserCommonSelector.init();
		});
	</script>
</body>
</html>