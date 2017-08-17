<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/proposalmanage/ProposalManageController/operation/Detail/id" -->
<title>详情</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js" type="text/javascript"></script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false" style="overflow:hidden;padding-bottom:35px;">
		<form id='form'>
									<input type="hidden" name="id" value='${proposalManageDTO.id}'/>
												<input type="hidden" name="id" value='${proposalManageDTO.id}'/>
																																																																																																																																																													<table width="100%" style="padding-top: 10px;">
		<tr>
																																<th align="right">
											<span style="color:red;">*</span>
										装配结构ID:</th>
					<td>
																		<input title="装配结构ID" class="easyui-validatebox" data-options="required:true" style="width: 180px;" type="text" name="strId" id="strId" readonly="readonly" value='${proposalManageDTO.strId}'/>
																</td>
																										<th align="right">
											<span style="color:red;">*</span>
										父类建议ID:</th>
					<td>
																		<input title="父类建议ID" class="easyui-validatebox" data-options="required:true" style="width: 180px;" type="text" name="parentId" id="parentId" readonly="readonly" value='${proposalManageDTO.parentId}'/>
																</td>
											</tr>
						<tr>
																										<th align="right">
											<span style="color:red;">*</span>
										建议编号:</th>
					<td>
																		<input title="建议编号" class="easyui-validatebox" data-options="required:true" style="width: 180px;" type="text" name="proposalCode" id="proposalCode" readonly="readonly" value='${proposalManageDTO.proposalCode}'/>
																</td>
																										<th align="right">
											<span style="color:red;">*</span>
										建议标题:</th>
					<td>
																		<input title="建议标题" class="easyui-validatebox" data-options="required:true" style="width: 180px;" type="text" name="proposalName" id="proposalName" readonly="readonly" value='${proposalManageDTO.proposalName}'/>
																</td>
											</tr>
						<tr>
																										<th align="right">
											<span style="color:red;">*</span>
										建议正文:</th>
					<td>
																		<input title="建议正文" class="easyui-validatebox" data-options="required:true" style="width: 180px;" type="text" name="proposalMain" id="proposalMain" readonly="readonly" value='${proposalManageDTO.proposalMain}'/>
																</td>
																										<th align="right">
															状态:</th>
					<td>
																		<input title="状态" class="inputbox" style="width: 180px;" type="text" name="status" id="status" readonly="readonly" value='${proposalManageDTO.status}'/>
																</td>
											</tr>
						<tr>
																										<th align="right">
															建议发表时间      :</th>
					<td>
																  		<input title="建议发表时间      " class="easyui-datebox" style="width: 180px;" type="text" name="publicationTime" id="publicationTime" readonly="readonly" value='${proposalManageDTO.publicationTime}'/>
																</td>
																										<th align="right">
															建议提出人:</th>
					<td>
											<input title="{param.field.comment}" class="inputbox"  style="width: 180px;" type="hidden" name="proposalUserId" id="proposalUserId"/>
						<div class=""><input class="easyui-validatebox"  name="proposalUserIdAlias"   id="proposalUserIdAlias"  readOnly="readOnly" style="width:179px;" ></input></div>
										</td>
											</tr>
						<tr>
																										<th align="right">
															建议提出人部门（专业）:</th>
					<td>
											<input title="{param.field.comment}" class="inputbox"  style="width: 180px;" type="hidden" name="proposalUserDept" id="proposalUserDept"/>
						<div class=""><input class="easyui-validatebox"  name="proposalUserDeptAlias"   id="proposalUserDeptAlias"  readOnly="readOnly" style="width:179px;" ></input></div>
										</td>
																										<th align="right">
															是否有争议:</th>
					<td>
																		<input title="是否有争议" class="inputbox" style="width: 180px;" type="text" name="isDispute" id="isDispute" readonly="readonly" value='${proposalManageDTO.isDispute}'/>
																</td>
											</tr>
						<tr>
																										<th align="right">
															320厂是否批准建议（320厂默认否）:</th>
					<td>
																		<input title="320厂是否批准建议（320厂默认否）" class="inputbox" style="width: 180px;" type="text" name="isApproval" id="isApproval" readonly="readonly" value='${proposalManageDTO.isApproval}'/>
																</td>
																																																																																																										<th align="right">
															回复人ID:</th>
					<td>
											<input title="{param.field.comment}" class="inputbox"  style="width: 180px;" type="hidden" name="replierUserId" id="replierUserId"/>
						<div class=""><input class="easyui-validatebox"  name="replierUserIdAlias"   id="replierUserIdAlias"  readOnly="readOnly" style="width:179px;" ></input></div>
										</td>
											</tr>
						<tr>
																										<th align="right">
															建议状态:</th>
					<td>
																		<input title="建议状态" class="inputbox" style="width: 180px;" type="text" name="proposedType" id="proposedType" readonly="readonly" value='${proposalManageDTO.proposedType}'/>
																</td>
																										<th align="right">
															图片:</th>
					<td>
																		<input title="图片" class="inputbox" style="width: 180px;" type="text" name="picture" id="picture" readonly="readonly" value='${proposalManageDTO.picture}'/>
																</td>
											</tr>
						<tr>
																</tr>
		</table>
		</form>
	</div>
	<script type="text/javascript">
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
																																										});
	</script>
</body>
</html>