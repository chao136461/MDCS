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
<!-- ControllerPath = "discussion_manage/informationstatistics/InformationStatisticsController/AuditOpinionInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/BpmJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include> 
<script src="static/js/platform/component/dialog/UserSelectDialog.js" type="text/javascript"></script>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js" type="text/javascript"></script>
<script src="static/js/platform/component/fusionchar/js/FusionCharts.js" type="text/javascript"></script>
</head>

 <body class="easyui-layout" fit="true">
	<div data-options="region:'center',title:''" >
		<div id="modelQueryChart1" ></div>
	</div> 
   <div data-options="region:'north',title:''" style="height:300px;"></div> 
    
  
 
<script type="text/javascript"> 
   $(function() {
	   $.ajax({
			 url:"platform/discussion_manage/informationstatistics/InformationStatisticsController/AuditOpinionInfo1",
			 data : {},
			 type : 'post',
			 timeout:1000,
			 dataType : 'json',
			 success : function(r){
				 if (r.flag == "success"){
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_Pie2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr);
					 chart.render("modelQueryChart1"); 
				 }else{
					 $.messager.show({
						 title : '提示',
						 msg : r.error
					 });
				 } 
			 }
		 });
	   
	 });
  </script> 
  	
</body>


</html>










 

