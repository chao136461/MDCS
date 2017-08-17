<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/disputememo/DisputeMemoController/operation/Detail/id" -->
<title>详情</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js" type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false" style="overflow:hidden;padding-bottom:35px;">
		<form id='form'>
									<input type="hidden" name="id" value='${disputeMemoDTO.id}'/>
												<input type="hidden" name="id" value='${disputeMemoDTO.id}'/>
																																																																																																						<table width="100%" style="padding-top: 10px;">
		<tr>
																																<th align="right">
															建议ID:</th>
					<td>
																		<input title="建议ID" class="inputbox" style="width: 180px;" type="text" name="proId" id="proId" readonly="readonly" value='${disputeMemoDTO.proId}'/>
																</td>
																										<th align="right">
															备忘内容:</th>
					<td>
																		<input title="备忘内容" class="inputbox" style="width: 180px;" type="text" name="disText" id="disText" readonly="readonly" value='${disputeMemoDTO.disText}'/>
																</td>
											</tr>
						<tr>
																										<th align="right">
															通知接收目标人:</th>
					<td>
											<input title="{param.field.comment}" class="inputbox"  style="width: 180px;" type="hidden" name="sendTo" id="sendTo"/>
						<div class=""><input class="easyui-validatebox"  name="sendToAlias"   id="sendToAlias"  readOnly="readOnly" style="width:179px;" ></input></div>
										</td>
																																																																																																</tr>
		</table>
		</form>
	</div>
	<script type="text/javascript">
	$(function(){
																																																																																																																																																																						var sendToUserCommonSelector = new CommonSelector("user","userSelectCommonDialog","sendTo","sendToAlias");
					sendToUserCommonSelector.init(); 
																																																																																										});
	</script>
</body>
</html>