<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/regionconfiguration/RegionConfigurationController/operation/Detail/id" -->
<title>详情</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js" type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false" style="overflow:hidden;padding-bottom:35px;">
		<form id='form'>
			<input type="hidden" name="id" value='${regionConfigurationDTO.id}'/>
			
																																																																																																																<table width="100%" style="padding-top: 10px;">
		<tr>
			<th align="right">型号ID:</th>
			<td>
				<input title="型号ID" class="inputbox" style="width: 180px;" type="text" name="typeId" id="typeId" readonly="readonly" value='${regionConfigurationDTO.typeId}'/>
			</td>
			<th align="right">开始天数:</th>
			<td>
				<input title="开始天数" class="inputbox" style="width: 180px;" type="text" name="beginDay" id="beginDay" readonly="readonly" value='${regionConfigurationDTO.beginDay}'/>
			</td>
		</tr>
		<tr>
			<th align="right">结束天数:</th>
			<td>
				<input title="结束天数" class="inputbox" style="width: 180px;" type="text" name="endDay" id="endDay" readonly="readonly" value='${regionConfigurationDTO.endDay}'/>
			</td>
			<th align="right">状态:</th>
			<td>
				<input title="状态" class="inputbox" style="width: 180px;" type="text" name="state" id="state" readonly="readonly" value='${regionConfigurationDTO.state}'/>
			</td>
		</tr>
		<tr>
			<th align="right">级别:</th>
			<td>
				<input title="级别" class="inputbox" style="width: 180px;" type="text" name="regionLevel" id="regionLevel" readonly="readonly" value='${regionConfigurationDTO.regionLevel}'/>
			</td>
		</tr>
		</table>
		</form>
	</div>
	<script type="text/javascript">
	$(function(){
		});
	</script>
</body>
</html>