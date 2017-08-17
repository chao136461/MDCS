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
<!-- ControllerPath = "discussion_manage/discussionmanage/DiscussionManageController/DiscussionManageInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<% String strid = request.getParameter("id")==null?"":request.getParameter("id"); %>
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>
<script type="text/javascript">
	
	$(function() {
		var strid ='<%=strid%>';
		$("#dgDiscussion").datagrid({
			url:'platform/discussion_manage/discussionmanage/DiscussionManageController/operation/getDeptUserDetail/'+ strid
		}); 
	});
</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'"
		style="background: #ffffff; height: 0px; padding: 0px; overflow: hidden;">
		<table id="dgDiscussion"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarDiscussionManage',
				idField :'id',
				singleSelect: true,
				checkOnSelect: true,
				selectOnCheck: false,
				pagination:true,
				pageSize:dataOptions.pageSize,
				pageList:dataOptions.pageList,
				striped:true">
			<thead>
				<tr>
					<th data-options="field:'workshopId', halign:'center'" width="220">单位</th>
					<th data-options="field:'dutyUserIds', halign:'center'" width="220">审查人员</th>
				</tr>
			</thead>
		</table>
	</div>
	<script
		src=" avicit/discussion_manage/discussionmanage/js/DiscussionManage.js"
		type="text/javascript"></script>

</body>
</html>