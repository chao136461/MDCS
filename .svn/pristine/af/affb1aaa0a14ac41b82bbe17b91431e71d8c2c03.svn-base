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
<!-- ControllerPath = "discussion_manage/structuremanage/StructureManageController/operation/Add/null" -->
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
			<table class="form_commonTable">
					<tr>
											
																		     
																																						     
															<th width="10%">
																	<span class="remind">*</span>
																分类类型:</th>
								 <td width="40%">
																											<input title="分类类型" class="easyui-validatebox" data-options="required:true,validType:'maxLength[2]'" style="width: 180px;" type="text" name="classType" id="classType"/>
																									</td>								
																																							     
															<th width="10%">
																	<span class="remind">*</span>
																分类名称:</th>
								 <td width="40%">
																											<input title="分类名称" class="easyui-validatebox" data-options="required:true,validType:'maxLength[50]'" style="width: 180px;" type="text" name="className" id="className"/>
																									</td>								
																	</tr>
									<tr>
																																							     
															<th width="10%">
																								分类编号:</th>
								 <td width="40%">
																											<input title="分类编号" class="inputbox easyui-validatebox" data-options="validType:'maxLength[32]'" style="width: 180px;" type="text" name="classCode" id="classCode"/>
																									</td>								
																																							     
															<th width="10%">
																								成熟度:</th>
								 <td width="40%">
																											<input title="成熟度" class="inputbox easyui-validatebox" data-options="validType:'maxLength[32]'" style="width: 180px;" type="text" name="status" id="status"/>
																									</td>								
																	</tr>
									<tr>
																																							     
															<th width="10%">
																								版本:</th>
								 <td width="40%">
																											<input title="版本" class="inputbox easyui-validatebox" data-options="validType:'maxLength[32]'" style="width: 180px;" type="text" name="edition" id="edition"/>
																									</td>								
																																							     
															<th width="10%">
																								设计者:</th>
								 <td width="40%">
																											<input title="设计者" class="inputbox easyui-validatebox" data-options="validType:'maxLength[32]'" style="width: 180px;" type="text" name="designerId" id="designerId"/>
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
																																																																																																																																																																																																						/* var userCommonSelector = new CommonSelector("user","userSelectCommonDialog","userName","userNameName");
		userCommonSelector.init();  */
		//选择部门
		/* var deptCommonSelector = new CommonSelector("dept","deptSelectCommonDialog","userName","userNameName");
	    deptCommonSelector.init();  */
	    //选择角色
	    /* var roleCommonSelector = new CommonSelector("role","roleSelectCommonDialog","userName","userNameName",null,null,null);
	    roleCommonSelector.init();  */ 
	   /*  //选择群组
	    var groupCommonSelector = new CommonSelector("group","groupSelectCommonDialog","userName","userNameName",null,null,null);
	    groupCommonSelector.init(); 
	    //选择岗位
	    var positionCommonSelector = new CommonSelector("position","positionSelectCommonDialog","userName","userNameName");
	    positionCommonSelector.init();  */
	});
	function closeForm(){
		parent.structureManage.closeDialog("#insert");
	}
	function saveForm(){
	    if ($('#form').form('validate') == false) {
            return;
        }
	$('#saveButton').linkbutton('disable').unbind("click");
		parent.structureManage.save(serializeObject($('#form')),"#insert");
	}
	</script>
</body>
</html>