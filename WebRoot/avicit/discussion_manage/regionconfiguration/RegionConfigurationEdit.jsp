<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/regionconfiguration/RegionConfigurationController/operation/Edit/id" -->
<title>修改</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js" type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false">
		<form id='form'>
			<input type="hidden" name="version" value='${regionConfigurationDTO.version}' />
			<input type="hidden" name="id" value='${regionConfigurationDTO.id}'/>

			<table class="form_commonTable" style="padding-top: 40px;">
				<tr>
					<th width="10%">型号:</th>
					<td width="40%" colspan="3">
						<input title="型号" class="inputbox easyui-validatebox" data-options="validType:'maxLength[32]'" style="width: 180px;" type="text" name="typeId" id="typeId" value='<c:out value='${regionConfigurationDTO.classCode}'/>'/>
					</td>
					
				</tr>
				<tr>
					<th width="10%">开始天数:</th>
					<td width="40%">
						<input title="开始天数" class="inputbox easyui-validatebox" data-options="validType:'maxLength[32]'" style="width: 180px;" type="text" name="beginDay" id="beginDay" value='<c:out value='${regionConfigurationDTO.beginDay}'/>'/>
					</td>
					<th width="10%">结束天数:</th>
					<td width="40%">
						<input title="结束天数" class="inputbox easyui-validatebox" data-options="validType:'maxLength[32]'" style="width: 180px;" type="text" name="endDay" id="endDay" value='<c:out value='${regionConfigurationDTO.endDay}'/>'/>
					</td>
					
				</tr>
				<tr>
					<th width="10%">状态:</th>
					<td width="40%">
							<pt6:syslookup name="state" lookupCode="REGION_STATE" isNull="true" defaultValue='${regionConfigurationDTO.state}' dataOptions="width:180,editable:false,panelHeight:'auto'">
                            </pt6:syslookup> 
					</td>
					<th width="10%">级别:</th>
					<td width="40%">
							<pt6:syslookup name="regionLevel" lookupCode="REGION_LEVEL" isNull="true" defaultValue='${regionConfigurationDTO.regionLevel}' dataOptions="width:180,editable:false,panelHeight:'auto'">
                            </pt6:syslookup> 
					</td>
																																																																																																																																																																																																															</tr>
			</table>
		</form>
	 
	</div>  
	<div data-options="region:'south',border:false" style="height:40px;">
    		 <div id="toolbar" class="datagrid-toolbar datagrid-toolbar-extend foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>	
					<td width="50%" align="right">
						<a title="保存" id="saveButton" class="easyui-linkbutton primary-btn" onclick="saveForm();" href="javascript:void(0);">保存</a>
						<a title="返回" id="returnButton" class="easyui-linkbutton" onclick="closeForm();" href="javascript:void(0);">返回</a>
					</td>	
				</tr>
			</table>
		</div>
	</div>
	<script type="text/javascript">
		$.extend($.fn.validatebox.defaults.rules, {    
        maxLength: {    
            validator: function(value, param){    
                return param[0] >= value.length;
                
            },    
            message: '请输入最多 {0} 字符.'   
        }    
    });  
	$(function(){
		
	})
	function closeForm(){
		parent.regionConfiguration.closeDialog("#edit");
	}
	function saveForm(){
	    if ($('#form').form('validate') == false) {
            return;
        }
	$('#saveButton').linkbutton('disable').unbind("click");
		parent.regionConfiguration.save(serializeObject($('#form')),"#edit");
	}
	</script>
</body>
</html>