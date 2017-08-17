<%@page import="avicit.platform6.commons.utils.ComUtil"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<%@ page import="avicit.platform6.api.session.SessionHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/proposalmanage/ProposalManageController/operation/Add/null" -->
<title>添加</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<%
	String applyUserId = SessionHelper.getLoginSysUser(request).getId();
	String applyUserName = SessionHelper.getLoginSysUser(request).getName();
	String applyDeptId = SessionHelper.getCurrentDeptId(request);
	String applyDeptName = SessionHelper.getCurrentDeptTl(request).getDeptName();
	String uuid = ComUtil.getId();
%>
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/common/exteasyui.js"
	type="text/javascript"></script>
</head>

<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false">
		<form id='form'>
			<input type="hidden" id="id" name="id" />
			<table class="form_commonTable">
				<tr>
				<th width="10%"><span class="remind">*</span> 编号:</th>
					<td width="30%"><input title="建议编号" class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[50]'"
						style="width: 150px;" type="text" name="proposalCode" readonly
						id="proposalCode" value="${str}"/></td>
				<th width="10%"><span class="remind">*</span> 类型:</th>
					<td width="20%"><pt6:syslookup name="proposedType" 
							lookupCode="PROPOSED_TYPE" isNull = "true"
							dataOptions="onShowPanel:comboboxOnShowPanel,editable:false,panelHeight:'auto'">
						</pt6:syslookup>	
						</td>		
				<td width="20%" rowspan="5"><img id="imgs" style="border:1.5px dotted #ADADAD;" src="avicit/discussion_manage/discussionmanage/js/images/botton.png" align="right" width="160" height="160"  onclick="magnify();"/>
					<div align="center"><a title="查看" class="easyui-linkbutton" onclick="findPicture('<%=uuid %>');" plain="true"
						href="javascript:void(0);" data-options="iconCls:'icon-search'">查看</a>
					<a title="截图" class="easyui-linkbutton" onclick="Run('<%=uuid %>');" plain="true"
						href="javascript:void(0);" data-options="iconCls:'icon-cut'">截图</a><div></td>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
				<th width="12%"><span class="remind">*</span> 零部件:</th>
					<input title="装配结构ID" type="hidden" name="strId" id="strId"  value="${strId}"/>
				<td width="38%"><input title="关联零部件"
						class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[32]'" readonly
						style="width: 180px;" type="text" name="strcode" id="strcode" value="${classCode}" /></td>
				<th width="12%">提出人:</th>
					<td width="40%"><input title="建议提出人" class="inputbox" 
						style="width: 182px;" type="hidden" name="proposalUserId"
						id="proposalUserId" value="<%=applyUserId %>" />
						<div class="">
							<input class="easyui-validatebox" name="proposalUserIdAlias"
								id="proposalUserIdAlias" readOnly="readOnly"
								style="width: 179px;" value="<%=applyUserName %>"></input>
						</div></td>
				</tr>
				<tr>
				<th width="14%"><span class="remind">*</span> 标题:</th>
					<td width="36%" colspan="3"><input title="建议标题" class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[50]'"
						style="width: 180px;" type="text" name="proposalName"
						id="proposalName" /></td>
				</tr>
				<tr>
					
					<th width="10%"><span class="remind">*</span> 正文:</th>
					<td width="40%" colspan="4"><textarea title="建议正文" class="easyui-validatebox"
						data-options="required:true,validType:'maxLength[500]'"
						style="width: 180px;height: 150px!important" type="text" name="proposalMain"
						id="proposalMain" ></textarea></td>

					
				</tr>
				<tr>
					
					<input title="建议提出人部门（专业）" class="inputbox"
						style="width: 182px;" type="hidden" name="proposalUserDept"
						id="proposalUserDept"  value="<%=applyDeptId%>"/>
						
					<input class="easyui-validatebox" name="proposalUserDeptAlias"
								id="proposalUserDeptAlias" readOnly="readOnly" type="hidden"
								style="width: 179px;" value="<%=applyDeptName %>"></input>
						
				</tr>
				<tr>
				
				<input title="父类建议ID"
						style="width: 180px;" type="hidden" name="parentId" id="parentId"  value="-1"/>
				<input title="建议发表时间      "
						type="hidden" name="publicationTime" id="publicationTime" value="${currdate }"/>
				<input title="状态" type="hidden" name="status" id="status" value="0"/>	
				</tr>
				
			</table>
		</form>

	</div>
	<div data-options="region:'south',border:false" style="height: 40px;">
		<div id="toolbar"
			class="datagrid-toolbar datagrid-toolbar-extend foot-formopera">
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
	<script type="text/javascript">
		$.extend($.fn.validatebox.defaults.rules, {
			maxLength : {
				validator : function(value, param) {
					return param[0] >= value.length;

				},
				message : '请输入最多 {0} 字符.'
			}
		});
		var uuid = '<%=uuid%>';
		var strcode = '${classCode}';
		var instancenumber = '${instancenumber}';
		$(function() {
			$('#id').val(uuid);
		});
		function closeForm() {
			parent.proposalManage.closeDialog("#insert");
		}
		function saveForm() {
			if ($('#form').form('validate') == false) {
				return;
			}
			$('#saveButton').linkbutton('disable').unbind("click");
			parent.proposalManage.save(serializeObject($('#form')), "#insert");
		}
		//调用本地bat
		function Run(id) { 
		  var objShell = new ActiveXObject("WScript.Shell"); 
		  objShell.Run("D:\\1.bat "+id+" "+strcode+" 0 "+instancenumber+""); 
		  objShell = null; 
		}
		//图片放大
		function magnify(){
			var wid = $("#imgs").attr("width");
			if(wid=="160"){
				$("#imgs").attr("width","200").attr("height","200");
			}else{
				$("#imgs").attr("width","160").attr("height","160");	
			}
		}
		//查看图片
		function findPicture(id){
  			$("#imgs").attr("src",'platform/discussion_manage/mdcsproposalpicture/MdcsProposalPictureController/operation/picture.json?id='+id);	
		}
	</script>
</body>
</html>