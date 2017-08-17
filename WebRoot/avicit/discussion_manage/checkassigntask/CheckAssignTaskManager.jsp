<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>审查分工待办信息</title>
<base href="<%=ViewUtil.getRequestPath(request) %>">
<%
	String userId = (String)session.getAttribute("userId");
%>
<jsp:include page="/avicit/platform6/bpmclient/bpm/ProcessCommonJsInclude.jsp"></jsp:include>
<script src="static/js/platform/bpm/client/js/ToolBar.js" type="text/javascript"></script>
<link type="text/css" rel="stylesheet" href="static/css/platform/todo/todo.css" >
</head>
<script type="text/javascript">
	var page = '1';
	//跳转到待办业务处理页面
	function executeTask(structureId, taskUrl,taskType){
		if (taskType == '1') {
			top.addTab("审查任务","platform/discussion_manage/processassignment/ProcessAssignmentController/ProcessTeskInfo");
		}else if(taskType == '2'){
			
			top.addTab("审查确认","avicit/discussion_manage/discussionmanage/ProposalManage.jsp?id="+structureId+"&classCode="+"${todo.classCode }");
		}else if(taskType == '3'){
			
			top.addTab("测试","");
		}else if(taskType == '4'){
			
			top.addTab("工艺分工","platform/discussion_manage/processrepresentative/ProcessRepresentativeController/ProcessRepresentativeInfo");
		}else if(taskType == '5'){
			
			top.addTab("工艺分工任务","platform/discussion_manage/processassignment/ProcessAssignmentController/ProcessAssignmentInfo");
		}
	}
	//把待办任务标记完成
	function finishTask(id, entryId) {
		if (confirm("是否标识完成?")) {
			ajaxRequest("POST", "dbid=" + id + "&entryId=" + entryId,
					"platform/bpm/clientbpmdisplayaction/finishtodo", "json",
					"backFinished");
		}
	}
	//把待办任务标记完成后回调方法，刷新待办
	function backFinished(obj) {
		if (obj != null && obj.mes == true) {
			window.location.reload();
		}
	}
	function finishTaskNew(id, entryId) {
		if (id != null) {
			ajaxRequest("POST", "dbid=" + id + "&entryId=" + entryId,
					"platform/bpm/clientbpmdisplayaction/finishtodo", "json",
					"backFinished");
		}
	}
	function trackBpm(processInstanceId) {
		var url = getPath2() + "/avicit/platform6/bpmclient/bpm/ProcessTrack.jsp";
		if(myUtils.IS_IE6){
			url = getPath2()+"/avicit/platform6/bpmclient/bpm/ProcessTrack_bak.jsp";
		}
		url += "?processInstanceId=" + processInstanceId;
		top.addTab("流程图", encodeURI(url), "static/images/platform/index/images/icons.gif", "taskTodo", " -0px -120px");
	}
	function go(num) {
		if (num <= 0) {
			alert('当前已是第一页');
			return;
		}
		if (num > '${maxPage}') {
			alert('当前已是最后一页');
			return;
		}
		var url = window.location;
		var pos = String(url).indexOf("page"); //查看是否存在pageNum页数参数 
		if (pos == "-1") {
			window.location.replace(url + '?page=' + num); //不存在则添加,值为所点击的页数 
		} else {
			var ui = String(url).substring(0, pos);
			window.location.replace(ui + 'page=' + num); //存在,则刷新pageNum参数值 
		}
	}
	/**
	 * 刷新当前页
	 */
	function bpm_operator_refresh() {
		window.go('${pageNo}');
	}
</script>
 <style type="text/css">  
.mytable {  
    table-layout: fixed;  
}  

.mytable tr td {  
    text-overflow: ellipsis; /* for IE */  
    -moz-text-overflow: ellipsis; /* for Firefox,mozilla */  
    overflow: hidden;  
    white-space: nowrap;  
}  
</style>  
<body>
<c:if test="${total == 0 }">
	<div class="spanMain">暂无待办任务</div>
</c:if>
<c:if test="${total > 0 }">
	<div class="lmain">
		<table cellpadding="0" cellspacing="0" border="0" width="100%" class="mytable">
			<tbody>
				<c:forEach items="${rows}" var="todo" varStatus="vs">
					<tr>
						<td class="w15"><img src='static/css/platform/todo/images/todo_arrow.gif'/>&nbsp;关联零部件</td>
						<td _id="rmie6" class="alignl w15" title="${todo.classCode }" style="width:20%;" ><c:if test="${todo.classCode != null && todo.classCode != ''}">【${todo.classCode }】</c:if><c:if test="${todo.classCode == null || todo.classCode == ''}">通知</c:if></td>
						<td class="alignl" title="${todo.taskTitle }" ><a href="javascript:void(0);" onclick="window.executeTask('${todo.structureId }','${todo.taskUrl }','${todo.taskType}')">${todo.taskTitle }</a>
						</td>
						<td style="width:10%;">
							<c:if test="${todo.taskType==2}">
			        		<img src='static/css/platform/todo/images/jj.gif'/>
				        	</c:if>
							<c:if test="${todo.taskType==1}">
				        		<img src='static/css/platform/todo/images/j.gif'/>
				        	</c:if>
				        	<c:if test="${todo.taskType==0 || todo.taskType==null || todo.taskType==''}">
				        	</c:if>
						</td>
						<td class="w80 gray" title="${todo.sponsorUserName }">${todo.sponsorUserName }</td>
						<td class="w60 gray">${todo.sendDate}</td>
						<%--
						<td class="w60">
						<c:if test="${todo.taskType == '1'}">
							<a href="javascript:void(0);" title="把待办任务标记完成" onclick="window.finishTask('${todo.dbid }','${todo.processInstance }')">标记完成</a>
						</c:if>
						</td>
						 --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="rmain">
		<a href="javascript:void(0);" onclick="window.go('${pageNo-1}')" class="fjpages"></a>
		<a href="javascript:void(0);" onclick="window.go('${pageNo+1}')" class="fjpagex"></a>
	</div>
</c:if>
<script type="text/javascript">
	var myUtils = {
		IS_IE6: navigator.userAgent.indexOf('MSIE 6') >= 0
	};
	$(function(){
		if(myUtils.IS_IE6){
			$("td[_id='rmie6']").removeClass("w15");
		}
	});
</script>
</body>
</html>
