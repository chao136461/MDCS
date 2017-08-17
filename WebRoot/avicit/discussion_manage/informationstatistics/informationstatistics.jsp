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
<!-- ControllerPath = "discussion_manage/informationstatistics/InformationStatisticsController/StructureManageInfo" -->
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
	
 <div data-options="region:'west',title:''" style="width:650px; padding:0px;">
	<div id="structureManageChart"></div>
 </div> 
  
<div data-options="region:'center',title:''" >
		<div id="suggestionClosedChart" ></div>
	</div>  
<div data-options="region:'south',title:''" style="height:300px;">
  <div id="suggestionStatisticalChart" ></div>
 </div>


<div data-options="region:'north',title:''" style="height:40px;"> 
<button id="red"  style=" height:10px;background-color:#CCCC99"></button>
<button id="blue"  style=" height:10px; background-color:#CCFF66"></button>
<button id="gree" style=" height:10px;background-color:#FF6600"></button>

</div>  
</body> 
<!-- <script type="text/javascript">
   $("#red").click(function(){
	 
    $.ajax({
    	
			 url:"platform/discussion_manage/informationstatistics/InformationStatisticsController/StructureManageInfo",
			 data : {"one":"CCCC99","two":"CC3399","three":"FFCC00"},
			 type : 'post',
	      });
    
   });
</script>   -->

<!-- red -->
  <script type="text/javascript">
   
  $("#red").click(function() {
	   $.ajax({
			 url:"platform/discussion_manage/informationstatistics/InformationStatisticsController/StructureManageInfo",
			 data : {"one":"CCCC99","two":"CC3399","three":"FFCC00"},
			 type : 'post',
			 dataType : 'json',
			 success : function(r){
				
				 if (r.flag == "success"){
				/*  零件   装配件对应讨论区的数量统计	 */
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_Pie2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr1);
					 chart.render("structureManageChart"); 
			    /*  建议关闭状态的统计图形   已关闭  未关闭   争议性关闭   */	 
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_Pie2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr2);
					 chart.render("suggestionClosedChart"); 
				/*  各相关部门的建议统计  */
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_StackedColumn2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr4);
					 chart.render("suggestionStatisticalChart"); 
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
<!-- blue -->
  <script type="text/javascript">
  $("#blue").click(function() {
	   $.ajax({
			 url:"platform/discussion_manage/informationstatistics/InformationStatisticsController/StructureManageInfo",
			 data : {"one":"FFCC99","two":"66CCCC","three":"CCFF66"},
			 type : 'post',
			 dataType : 'json',
			 success : function(r){
				
				 if (r.flag == "success"){
				/*  零件   装配件对应讨论区的数量统计	 */
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_Pie2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr1);
					 chart.render("structureManageChart"); 
			    /*  建议关闭状态的统计图形   已关闭  未关闭   争议性关闭   */	 
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_Pie2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr2);
					 chart.render("suggestionClosedChart"); 
				/*  各相关部门的建议统计  */
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_StackedColumn2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr4);
					 chart.render("suggestionStatisticalChart"); 
				 }else{
					 $.messager.show({
						 title : '提示',
						 msg : r.error
					 });
				 } 
			 }
		 });
	  
	 });
  </script> <!-- blue -->
  <script type="text/javascript">
  $("#gree").click(function() {
	   $.ajax({
			 url:"platform/discussion_manage/informationstatistics/InformationStatisticsController/StructureManageInfo",
			 data : {"one":"FF6600","two":"99CC33","three":"CCCC99"},
			 type : 'post',
			 dataType : 'json',
			 success : function(r){
				
				 if (r.flag == "success"){
				/*  零件   装配件对应讨论区的数量统计	 */
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_Pie2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr1);
					 chart.render("structureManageChart"); 
			    /*  建议关闭状态的统计图形   已关闭  未关闭   争议性关闭   */	 
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_Pie2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr2);
					 chart.render("suggestionClosedChart"); 
				/*  各相关部门的建议统计  */
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_StackedColumn2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr4);
					 chart.render("suggestionStatisticalChart"); 
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
 <!--  默认颜色  -->
  <script type="text/javascript">
   $(function() {
	   $.ajax({
			 url:"platform/discussion_manage/informationstatistics/InformationStatisticsController/StructureManageInfo",
			 data : {},
			 type : 'post',
			 dataType : 'json',
			 success : function(r){
				
				 if (r.flag == "success"){
		/*  零件   装配件对应讨论区的数量统计	 */
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_Pie2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr1);
					 chart.render("structureManageChart"); 
		/*  建议关闭状态的统计图形   已关闭  未关闭   争议性关闭   */
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_Pie2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr2);
					 chart.render("suggestionClosedChart"); 
	    /*  各相关部门的建议统计  */
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_StackedColumn2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr4);
					 chart.render("suggestionStatisticalChart");
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
  
  
  
  <!-- 建议关闭状态的统计图形   已关闭  未关闭   争议性关闭 -->
<!--  <script type="text/javascript">
   
   $(function() {
	   $.ajax({
			 url:"platform/discussion_manage/informationstatistics/InformationStatisticsController/SuggestionClosedInfo",
			 data : {},
			 type : 'post',
			 dataType : 'json',
			 success : function(r){
				 if (r.flag == "success"){
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_Pie2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr);
					 chart.render("suggestionClosedChart"); 
				 }else{
					 $.messager.show({
						 title : '提示',
						 msg : r.error,
					 });
				 } 
			 }
		 });
	  
	 });
  </script>   -->  
 <!--  各相关部门的建议统计-->
  <!-- <script type="text/javascript">
   $(function() {
	   $.ajax({
			 url:"platform/discussion_manage/informationstatistics/InformationStatisticsController/SuggestionStatisticalInfo",
			 data : {},
			 type : 'post',
			 dataType : 'json',
			 success : function(r){
				 if (r.flag == "success"){
					 var chart = new FusionCharts("static/js/platform/component/fusionchar/charts/FCF_StackedColumn2D.swf?ChartNoDataText=暂无数据显示!", "ChartId", "100%", "275", "0", "0");
					 chart.setDataXML(r.htmlStr4);
					 chart.render("suggestionStatisticalChart"); 
				 }else{
					 $.messager.show({
						 title : '提示',
						 msg : r.error
					 });
				 } 
			 }
		 });
	 });
  </script> 		 -->
</html>









 

