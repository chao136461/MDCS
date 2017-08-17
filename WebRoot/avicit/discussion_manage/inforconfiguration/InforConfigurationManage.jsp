<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/inforconfiguration/InforConfigurationController/InforConfigurationInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js" type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
<div data-options="region:'center',split:true,border:false" style="overflow: auto;">
	<div id="riskTabs" class="easyui-tabs" data-options="fit:true,plain:true,tabPosition:'top',border:false,toolPosition:'right'">
			<div title="基本配置" style="margin: 0px; padding: 0px; border: 0px">
					<div data-options="region:'center',split:true,border:false" style="overflow: auto; padding-bottom: 30px;">
						<form id='form'>
							<input type="hidden" name="id" id="id" />
							<table class="form_commonTable" style="padding-top: 70px;font-size: 15px;" border="0px"  >
								<tr>
									<th width="5%"><span class="remind">*</span>型&nbsp;&nbsp;号:</th>
									<td width="2%">
									<select title="型号" class="easyui-combobox"  style="width:130px" required="required"  name="typeId" id="typeId" data-options="">
									 </select>
									</td>
									
									<tr><td>&nbsp;</td></tr>
								</tr>
								
								<tr>
										<th width="5%">审查确认:</th>
										<td>
												<input title="审查确认"  type="checkbox" name="isConfirm" id="isConfirm"  />
										</td>
										<th width="5%">模型检查:</th>
										<td width="5%">	
									  			<input title="模型检查" type="checkbox" name="modelCheck" id="modelCheck"    />
										</td>
	
										<th width="5%">干涉检查:</th>
										<td width="10%">
												<input title="干涉检查" type="checkbox" name="interfereCheck" id="interfereCheck"  />
												
										</td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
										<th width="13%">建议审批:</th>
										<td width="5%">
												<input title="审批状态" type="checkbox" name="approveState" id="approveState"  />
												
										</td>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
										<th width="13%">降级流程:</th>
										<td width="5%">
												<input title="指标定义" type="checkbox" name="isDegrade" id="isDegrade"  />
												
										</td>
										</tr>
								</tr>
								<tr><td>&nbsp;</td></tr>
								<tr>
								
									<th width="13%">是否开启同步:</th>
										<td width="5%">
												<input title="是否开启同步" type="checkbox" name="isOpenTime" id="isOpenTime" onclick="change();" />
												
										</td>
										
									<th width="5%">同步时间:</th>
									<td width="2%">
									<%-- <select title="同步时间" class="easyui-combobox"  style="width:130px"  name="syncTime" id="syncTime" data-options="panelHeight:'auto'">
										<c:forEach items="${list}" var="li">
											<option value="${li.lookupCode}">${li.lookupName}</option>
										</c:forEach>
									 </select>  --%>
										 <pt6:syslookup name="syncTime" isNull="true" lookupCode="SYNC_TIME" dataOptions="width:151,editable:false,panelHeight:'100'">
		                         		 </pt6:syslookup> 
									</td>
								
								
								</tr>
								
	
							</table>
						</form>
						<div id="toolbar" class="datagrid-toolbar datagrid-toolbar-extend foot-formopera">
							<table class="tableForm" border="0" cellspacing="1" width='100%'>
								<tr>
									<td width="50%" align="right"><a title="保存" id="saveButton"
										class="easyui-linkbutton primary-btn" onclick="saveForm();"
										href="javascript:void(0);">保存</a> <a title="返回" id="returnButton"
										class="easyui-linkbutton" onclick="closeForm();"
										href="javascript:void(0);">返回</a></td>
								</tr>
							</table>
						</div>
					</div>
			</div>
			<div title="320厂专业配置" style="padding: 0px;" id="RiskMuB">
				<iframe id="tabRiskMuB" src='' frameBorder=0 style="height: 99%; width: 100%"></iframe>
			</div>
			<div title="601所专业配置" style="padding: 0px;" id="RiskMuC">
				<iframe id="tabRiskMuC" src='' frameBorder=0 style="height: 99%; width: 100%"></iframe>
			</div>
			<div title="区间配置" style="padding: 0px;" id="RiskMuD">
				<iframe id="tabRiskMuD" src='' frameBorder=0 style="height: 99%; width: 100%"></iframe>
			</div>
	</div>
</div>
	<script src=" avicit/discussion_manage/inforconfiguration/js/InforConfiguration.js" type="text/javascript"></script>
	<script type="text/javascript">
		var inforConfiguration;
		var typeId = $('#typeId').val();
		$(function(){
			
 			inforConfiguration= new InforConfiguration('dgInforConfiguration','${url}','searchDialog','inforConfiguration');

 			//根据型号加载所选相关配置
 			$('#typeId').combobox({
 				panelWidth:'136',
 				panelHeight:'auto',
 				valueField: 'StrId',    
 		        textField: 'ClassCode',    
 		        url: 'platform/discussion_manage/structuralrelationship/StructuralRelationshipController/findStructuralType', 
 		       onLoadSuccess:function(){
  		    	 // $('#typeId').combobox('select',"--请选择--");
 		    	 $('#typeId').combobox('setValue', '--请选择--');
 		    	 
 		    	 // 判断同步时间是否可选  
 		    	 change();

 		       },
 				onSelect: function(param){
 					$("input[name='isConfirm']").attr('checked',false);
 					$("input[name='modelCheck']").attr('checked',false);
 					$("input[name='interfereCheck']").attr('checked',false);
 					$("input[name='approveState']").attr('checked',false);
 					$("input[name='isDegrade']").attr('checked',false);
 					$("input[name='isOpenTime']").attr('checked',false);
 					//根据所选型号查找数据
 					$.ajax({
 						url:"platform/discussion_manage/inforconfiguration/InforConfigurationController/operation/findDataByType",
 				        type: 'post',
 				        dataType: 'json',
 				        data: {
 				        	typeId: param.StrId
 				        },
 				        async: false,
 				        success: function(r) {
 				        	if(r != null){
 				        		$('#id').val(r.id);
	 				        	if(r.isConfirm=='on'){	
									 $("input[name='isConfirm']").attr('checked','true');
								}
	 				        	if(r.modelCheck=='on'){	
									 $("input[name='modelCheck']").attr('checked','true');
								}
	 				        	if(r.interfereCheck=='on'){	
									 $("input[name='interfereCheck']").attr('checked','true');
								}
	 				        	if(r.approveState=='on'){	
									 $("input[name='approveState']").attr('checked','true');
								}
	 				        	if(r.isDegrade=='on'){	
									 $("input[name='isDegrade']").attr('checked','true');
								}
	 				        	if(r.isOpenTime=='on'){	
									 $("input[name='isOpenTime']").attr('checked','true');
									 $('#syncTime').combobox({disabled:false});
									 $('#syncTime').combobox('setValue',r.syncTime);
									 
								}else{
									$('#syncTime').combobox('setValue',"");
									$('#syncTime').combobox({disabled:true});
								}
 				        	}else{
 				        		$('#id').val('');
 				        	}
 				        }
 					});      
 				}
 			});
 			
 			//加载页签数据
			loadTabs();
 			
		});
		
		//加载页签数据 
		function loadTabs(){
			$('#riskTabs').tabs({    
			    border:false,    
			    onSelect:function(title,index){    
			    	var tab = $('#riskTabs').tabs('getSelected');
			    	
			    	if(tab.attr('id')=='RiskMuB'){//320厂专业配置
			    		var pid = "320";
			    		var urljsp = "platform/discussion_manage/profconfiguration/ProfConfigurationController/ProfConfigurationInfo?pid=" + pid;
			    		$('#tabRiskMuB').attr('src',urljsp);
			    	}else if(tab.attr('id') == "RiskMuC") {//601所专业配置
			    		var pid = "601";
			    		var urljsp = "platform/discussion_manage/profconfiguration/ProfConfigurationController/ProfConfigurationInfo?pid=" + pid;
			    		$('#tabRiskMuC').attr('src',urljsp);
			    	}else if(tab.attr('id') == "RiskMuD"){
			    		var urljsp = "platform/discussion_manage/regionconfiguration/RegionConfigurationController/RegionConfigurationInfo";
			    		$('#tabRiskMuD').attr('src',urljsp);
			    	}
			    }    
			}); 
		}
		
		//当选中是否开启同步时间时，同步时间框可以选择
		function change(){
			if($("input[name='isOpenTime']").is(':checked')){
				
				$('#syncTime').combobox({disabled:false});
			}else{

				$('#syncTime').combobox({disabled:true});
				$('#syncTime').combobox('setValue',"");
			}
		}
		
		
		
		
		//保存数据
		function saveForm(){	
			inforConfiguration.save(serializeObject($('#form')));
			
		}
		
		

		

		
	</script>
</body>
</html>