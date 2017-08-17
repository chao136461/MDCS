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
<!-- ControllerPath = "discussion_manage/regionconfiguration/RegionConfigurationController/operation/Add/null" -->
<title>添加</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js" type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false">
		<form id='form'>
			<input type="hidden" name="id" />
			<table class="form_commonTable" style="padding-top: 40px;padding-right: 40px;">
					<tr>
																																     
						<th width="10%" ><span class="remind">*</span>型号:</th>
						 <td width="40%">
							<input title="型号"   required="required"  name="typeId" id="typeId"/>
						</td>								
							
					</tr>
					<tr>
						<th width="10%"><span class="remind">*</span>级别:</th>
						 <td width="40%">
							<pt6:syslookup name="regionLevel"  isNull="true"  lookupCode="REGION_LEVEL" dataOptions="width:462,editable:false">
                                   </pt6:syslookup> 
<!-- 							<select title="级别" class="easyui-combobox"  name="regionLevel" id="regionLevel" select:""> -->
<%-- 							  	<c:forEach items="${list}" var="li"> --%>
<%-- 							  		<option value="${li.lookupCode}">${li.lookupName}</option> --%>
<%-- 							  	</c:forEach> --%>
<!-- 							</select> -->
						</td>
					</tr>
					<tr>	
						<th width="10%">状态:</th>
						 <td width="40%">
							<pt6:syslookup name="state" isNull="true" lookupCode="REGION_STATE" dataOptions="width:180,editable:false,panelHeight:'auto',disabled:true">
                            </pt6:syslookup> 
<!-- 							<select title="状态" class="easyui-combobox"  name="state" id="state" disabled="disabled"> -->
<%-- 							  	<c:forEach items="${state}" var="li"> --%>
<%-- 							  		<option value="${li.lookupCode}">${li.lookupName}</option> --%>
<%-- 							  	</c:forEach> --%>
<!-- 							</select> -->
<!-- 								<input name="state" id="state" type="text"> -->
						</td>																																		     
	
					</tr>
					<tr id="day1">
					<th width="10%">开始天数:</th>
						 <td width="40%">
							<input title="开始天数" class="inputbox easyui-validatebox" data-options="" d style="width: 180px;" type="text" name="beginDay" id="beginDay" readonly="readonly"/>
						</td>
					</tr>
					<tr id="day2">																																		     
						<th width="10%">结束天数:</th>
						<td width="40%">
							<input title="结束天数" class="inputbox easyui-validatebox" data-options="" style="width: 180px;" type="text" name="endDay" id="endDay"/>
						</td>	
					</tr>									
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
		
		//加载型号
		$('#typeId').combobox({
				valueField: 'StrId',    
		        textField: 'ClassCode',    
		        panelWidth:'465',
		        panelHeight:'auto',
		        url: 'platform/discussion_manage/structuralrelationship/StructuralRelationshipController/findStructuralType',
		       
		})
		
		//当选择一级时隐藏开始天数选择三级时隐藏结束天数
		$('#regionLevel').combobox({ 
			panelHeight:'auto',
			 onSelect: function(rec){    
		            var val =  rec.value;
	            	var typeVal= $('#typeId').combobox('getValue');


		            if(val == "1"){
		               $('#day2').show();
		               $('#day1').hide();
		               $('#endDay').val("");
		               //默认选中状态
		               selectData(val);
		            		
		            }else if(val == "2"){
		            	$('#day1').show();
		            	$('#day2').show();
		            	$('#endDay').val("");
		            	//默认选中状态
		            	selectData(val);
		            	
		            }else if(val == "3"){
		            	$('#endDay').val("1000");
		            	$('#day1').show();
		            	$('#day2').hide();
		            	//默认选中状态
		            	selectData(val);
		            }
		            
		            //判断是否有重复数据和是否按一二三级数据依次添加
		            $.ajax({
						url:"platform/discussion_manage/regionconfiguration/RegionConfigurationController/operation/fingData",
				        type: 'post',
				        dataType: 'json',
				        data: {
				        	regionLevel:val,
				        	typeId:typeVal
				        },
				        async: false,
				        success: function(r) {
				        	if(r.dto!=null){
				        		//将上级的结束时间赋值给下级的开始时间
					        	var endDay = parseInt(r.dto.endDay)+1;
				   
					        	$('#beginDay').val(endDay);
					        	//$('#beginDay').attr('disabled',true);
				        	}
				        	if(r.flag == "failure"){
				        		$('#regionLevel').combobox('setValue',"");


				        		$.messager.show({
									 title : '提示',
									 msg : '不能添加重复数据！'
								 });
				        	}
				        	if(r.flag == "flag2"){
				        		$('#regionLevel').combobox('setValue',"");
				        		$.messager.show({
									 title : '提示',
									 msg : '请先添加一级数据！'
								 });
				        	}
				        	if(r.flag == "flag3"){
				        		$('#regionLevel').combobox('setValue',"");
				        		$.messager.show({
									 title : '提示',
									 msg : '请先添加二级数据！'
								 });
				        	}
				        }
					});  
		           
		      }
		
			
		});
		
		//选中状态
		function selectData(val){
			if(val == "1"){
				$('#state').combobox('select','#28FF28');
			}else if(val == "2"){
				$('#state').combobox('select','#F9f900');
			}else{
				$('#state').combobox('select','#FFB6C1');
			}
		}
		
		
		
		
	});
	function closeForm(){
		parent.regionConfiguration.closeDialog("#insert");
	}
	function saveForm(){
	    if ($('#form').form('validate') == false) {
            return;
        }
	    
	    var typeIdValue =  $('#typeId').combobox('getValue');
	    if(typeIdValue == "" || typeIdValue =="--请选择--"){
	    	return;
	    }

	    var regionLevelValue=$("input[name='regionLevel']").val();
	    if(regionLevelValue == ""){
	    	$.messager.show({
				 title : '提示',
				 msg : '请选择级别！'
			 });
	    	$('#beginDay').val('');
	    	$('#endDay').val('');
	    	return;
	    }
	    
  		//结束天数大于等于开始天数
	    if(parseInt($('#endDay').val()) <= parseInt($('#beginDay').val())){  
	    	$.messager.show({
				 title : '提示',
				 msg : '结束时间必须大于等于开始时间！'
			 });
	    	
	    	return;
	    /* }else{
	    	$('#endDay').val().toString(); */
	    } 
	    


	    
		$('#saveButton').linkbutton('disable').unbind("click");
		parent.regionConfiguration.save(serializeObject($('#form')),"#insert");
	}
	</script>
</body>
</html>