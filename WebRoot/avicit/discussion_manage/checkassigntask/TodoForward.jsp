<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跳转到审查分工待办信息</title>
<%
	String userId = (String)session.getAttribute("userId");
	String url = "/platform/discussion_manage/checkassigntask/CheckAssignTaskController/toCheckAssignTaskManager?userId=" + userId;
%>
<script type="text/javascript">
	var baseurl = '<%=request.getContextPath()%>';
</script>
<jsp:forward page='<%=url%>'/> 