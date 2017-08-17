<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/proposalmanage/ProposalManageController/operation/Edit/id" -->
<title>修改</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js" type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false">
		<form id='form'>
						<input type="hidden" name="version" value='${proposalManageDTO.version}' />
												<input type="hidden" name="id" value='${proposalManageDTO.id}'/>
								
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
											
							 <table class="form_commonTable">
					<tr>
																																																														<th width="10%">
																	<span class="remind">*</span>
																装配结构ID:</th>
								<td width="40%">
																											<input title="装配结构ID" class="easyui-validatebox" data-options="required:true,validType:'maxLength[32]'" style="width: 180px;" type="text" name="strId" id="strId" value='<c:out value='${proposalManageDTO.strId}'/>'/>
																									</td>
																																															<th width="10%">
																	<span class="remind">*</span>
																父类建议ID:</th>
								<td width="40%">
																											<input title="父类建议ID" class="easyui-validatebox" data-options="required:true,validType:'maxLength[32]'" style="width: 180px;" type="text" name="parentId" id="parentId" value='<c:out value='${proposalManageDTO.parentId}'/>'/>
																									</td>
																	</tr>
									<tr>
																																															<th width="10%">
																	<span class="remind">*</span>
																建议编号:</th>
								<td width="40%">
																											<input title="建议编号" class="easyui-validatebox" data-options="required:true,validType:'maxLength[50]'" style="width: 180px;" type="text" name="proposalCode" id="proposalCode" value='<c:out value='${proposalManageDTO.proposalCode}'/>'/>
																									</td>
																																															<th width="10%">
																	<span class="remind">*</span>
																建议标题:</th>
								<td width="40%">
																											<input title="建议标题" class="easyui-validatebox" data-options="required:true,validType:'maxLength[50]'" style="width: 180px;" type="text" name="proposalName" id="proposalName" value='<c:out value='${proposalManageDTO.proposalName}'/>'/>
																									</td>
																	</tr>
									<tr>
																																															<th width="10%">
																	<span class="remind">*</span>
																建议正文:</th>
								<td width="40%">
																											<input title="建议正文" class="easyui-validatebox" data-options="required:true,validType:'maxLength[500]'" style="width: 180px;" type="text" name="proposalMain" id="proposalMain" value='<c:out value='${proposalManageDTO.proposalMain}'/>'/>
																									</td>
																																															<th width="10%">
																								状态:</th>
								<td width="40%">
								                                     <pt6:syslookup name="status" lookupCode="PROPOSED_STATUS" isNull="true" defaultValue='${proposalManageDTO.status}' dataOptions="width:180,editable:false,panelHeight:'auto'">
                                     </pt6:syslookup> 
																</td>
																	</tr>
									<tr>
																																															<th width="10%">
																								建议发表时间      :</th>
								<td width="40%">
																									  		<input title="建议发表时间      " class="easyui-datebox"  style="width: 182px;" type="text" name="publicationTime" id="publicationTime" value='${proposalManageDTO.publicationTime}'/>
																									</td>
																																															<th width="10%">
																								建议提出人:</th>
								<td width="40%">
																	 <input title="建议提出人" class="inputbox"  style="width: 182px;" type="hidden" name="proposalUserId" id="proposalUserId" value='${proposalManageDTO.proposalUserId}'/>
						 			 <div class=""><input class="easyui-validatebox"  name="proposalUserIdAlias"   id="proposalUserIdAlias"  readOnly="readOnly" value='<c:out  value='${proposalManageDTO.proposalUserIdAlias}'/>' style="width:179px;" ></input></div>
																</td>
																	</tr>
									<tr>
																																															<th width="10%">
																								建议提出人部门（专业）:</th>
								<td width="40%">
																	 <input title="建议提出人部门（专业）" class="inputbox"  style="width: 182px;" type="hidden" name="proposalUserDept" id="proposalUserDept" value='${proposalManageDTO.proposalUserDept}'/>
						 			 <div class=""><input class="easyui-validatebox"  name="proposalUserDeptAlias"   id="proposalUserDeptAlias"  readOnly="readOnly" value='<c:out  value='${proposalManageDTO.proposalUserDeptAlias}'/>' style="width:179px;" ></input></div>
																</td>
																																															<th width="10%">
																								是否有争议:</th>
								<td width="40%">
								                                     <pt6:syslookup name="isDispute" lookupCode="PROPOSED_IS_DISPUTE" isNull="true" defaultValue='${proposalManageDTO.isDispute}' dataOptions="width:180,editable:false,panelHeight:'auto'">
                                     </pt6:syslookup> 
																</td>
																	</tr>
									<tr>
																																															<th width="10%">
																								320厂是否批准建议（320厂默认否）:</th>
								<td width="40%">
																											<input title="320厂是否批准建议（320厂默认否）" class="inputbox easyui-validatebox" data-options="validType:'maxLength[4]'" style="width: 180px;" type="text" name="isApproval" id="isApproval" value='<c:out value='${proposalManageDTO.isApproval}'/>'/>
																									</td>
																																																																																																																																																																																																																															<th width="10%">
																								回复人ID:</th>
								<td width="40%">
																	 <input title="回复人ID" class="inputbox"  style="width: 182px;" type="hidden" name="replierUserId" id="replierUserId" value='${proposalManageDTO.replierUserId}'/>
						 			 <div class=""><input class="easyui-validatebox"  name="replierUserIdAlias"   id="replierUserIdAlias"  readOnly="readOnly" value='<c:out  value='${proposalManageDTO.replierUserIdAlias}'/>' style="width:179px;" ></input></div>
																</td>
																	</tr>
									<tr>
																																															<th width="10%">
																								建议状态:</th>
								<td width="40%">
								                                     <pt6:syslookup name="proposedType" lookupCode="PROPOSED_TYPE" isNull="true" defaultValue='${proposalManageDTO.proposedType}' dataOptions="width:180,editable:false,panelHeight:'auto'">
                                     </pt6:syslookup> 
																</td>
																																															<th width="10%">
																								图片:</th>
								<td width="40%">
																											<input title="图片" class="inputbox easyui-validatebox" data-options="validType:'maxLength[32]'" style="width: 180px;" type="text" name="picture" id="picture" value='<c:out value='${proposalManageDTO.picture}'/>'/>
																									</td>
																	</tr>
									<tr>
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
																																														if(!"${proposalManageDTO.publicationTime}"==""){
					$('#publicationTime').datebox('setValue', parserColumnTime("${proposalManageDTO.publicationTime}").format("yyyy-MM-dd"));
				}
																																																																																																																																																																																																																																																																						var proposalUserIdUserCommonSelector = new CommonSelector("user","userSelectCommonDialog","proposalUserId","proposalUserIdAlias");
					proposalUserIdUserCommonSelector.init(); 
																					var proposalUserDeptDeptCommonSelector = new CommonSelector("dept","deptSelectCommonDialog","proposalUserDept","proposalUserDeptAlias");
					proposalUserDeptDeptCommonSelector.init();
																																																																																																																																					var replierUserIdUserCommonSelector = new CommonSelector("user","userSelectCommonDialog","replierUserId","replierUserIdAlias");
					replierUserIdUserCommonSelector.init(); 
																																										})
	function closeForm(){
		parent.proposalManage.closeDialog("#edit");
	}
	function saveForm(){
	    if ($('#form').form('validate') == false) {
            return;
        }
	$('#saveButton').linkbutton('disable').unbind("click");
		parent.proposalManage.save(serializeObject($('#form')),"#edit");
	}
	</script>
</body>
</html>