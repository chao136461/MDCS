﻿<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!-- ControllerPath = "discussion_manage/relevanceperson/RelevancePersonController/RelevancePersonInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include
	page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include
	page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js"
	type="text/javascript"></script>

<script
	src=" avicit/discussion_manage/relevanceperson/js/RelevancePerson.js"
	type="text/javascript"></script>
	<script src="static/js/platform/component/common/exportData.js"
		type="text/javascript"></script>

<script type="text/javascript">
	$.extend($.fn.validatebox.defaults.rules, {
		maxLength : {
			validator : function(value, param) {
				return param[0] >= value.length;

			},
			message : '请输入最多 {0} 字符.'
		}
	});
	var relevancePerson;
	var deptCode = "320";
	$(function() {
		relevancePerson = new RelevancePerson('dgRelevancePerson', 'searchDialog', '${url}', 'relevancePerson');
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
		    		var urljsp = "platform/discussion_manage/relevancepersonbranch/RelevancePersonBranchController/RelevancePersonBranchInfo";
		    		$('#tabRiskMuB').attr('src',urljsp);
		    	}
		    }    
		}); 
	}
	function formateDate(value, row, index) {
		return relevancePerson.formate(value);
	};
	function formateDateTime(value, row, index) {
		return relevancePerson.formateDateTime(value);
	};
	/**通用代码格式化
	function formatvalidFlag(value){
	return Platform6.fn.lookup.formatLookupType(value,relevancePerson.validFlag);
	};
	 **/
</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center',split:true,border:false"
		style="overflow: auto;">
		<div id="riskTabs" class="easyui-tabs"
			data-options="fit:true,plain:true,tabPosition:'top',border:false,toolPosition:'right'">
			<div title="320厂人员配置" style="margin: 0px; padding: 0px; border: 0px">
				<div data-options="region:'center',split:true,border:false"
					style="overflow: auto; padding-bottom: 30px; height: 470px;">
					<div id="toolbarRelevancePerson" class="datagrid-toolbar">
						<table>
							<tr>

								<%-- <sec:accesscontrollist hasPermission="3"
									domainObject="formdialog_relevancePerson_button_add"
									permissionDes="添加">
									<td><a class="easyui-linkbutton" iconCls="icon-add"
										plain="true" onclick="relevancePerson.insert();"
										href="javascript:void(0);">添加</a></td>
								</sec:accesscontrollist> --%>
								<sec:accesscontrollist hasPermission="3" domainObject="formdialog_relevancePerson_button_edit" permissionDes="保存">
									<td><a class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="relevancePerson.save();" href="javascript:void(0);">保存</a></td>
								</sec:accesscontrollist>
								<sec:accesscontrollist hasPermission="3" domainObject="formdialog_relevancePerson_button_delete" permissionDes="删除">
									<td><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="relevancePerson.del();" href="javascript:void(0);">删除</a></td>
								</sec:accesscontrollist>
								<sec:accesscontrollist hasPermission="3" domainObject="formdialog_relevancePerson_button_query" permissionDes="查询">
									<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="relevancePerson.openSearchForm();" href="javascript:void(0);">查询</a></td>
								</sec:accesscontrollist>
								<sec:accesscontrollist hasPermission="3" domainObject="formdialog_examBookInfoLf_button_in" permissionDes="导入">
									<td><a class="easyui-linkbutton" iconCls="icon-import" plain="true" onclick="relevancePerson.importData();" href="javascript:void(0);">导入</a></td>
								</sec:accesscontrollist>
								<sec:accesscontrollist hasPermission="3" domainObject="formdialog_examBookInfoLf_button_out" permissionDes="导出">
									<td><a class="easyui-linkbutton" iconCls="icon-export" plain="true" onclick="relevancePerson.exportClientData();" href="javascript:void(0);">导出</a></td>
								</sec:accesscontrollist>	

							</tr>
						</table>
					</div>
					<table id="dgRelevancePerson"
						data-options="
								fit: true,
								border: false,
								rownumbers: true,
								animate: true,
								collapsible: false,
								fitColumns: true,
								autoRowHeight: false,
								toolbar:'#toolbarRelevancePerson',
								idField :'id',
								singleSelect: true,
								checkOnSelect: true,
								selectOnCheck: false,
								pagination:true,
								method:'get',
								pageSize:dataOptions.pageSize,
								pageList:dataOptions.pageList,
								striped:true">
						<thead>
							<tr>

								<th data-options="field:'id', halign:'center',checkbox:true" width="220">ID</th>

								<th data-options="field:'name', halign:'center'" width="220">姓名</th>

								<th data-options="field:'loginName', halign:'center'" width="220">登录名</th>

								<th data-options="field:'vpmLoginName', halign:'center'" editor="{type:'validatebox',options:{validType:'maxLength[32]'}}" width="220">VPM登录名</th>

							</tr>
						</thead>
					</table>

				</div>
				<!--*****************************搜索*********************************  -->
				<div id="searchDialog"
					data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
					style="width: 400px; height: 200px;">
					<form id="relevancePerson">
						<table class="form_commonTable">
							<tr>
								<th width="10%">姓名:</th>
								<td width="40%"><input class="easyui-validatebox"   type="text" name="name"/></td>		
					        </tr>

						</table>
					</form>
					<div id="searchBtns" class="datagrid-toolbar foot-formopera">
						<table class="tableForm" border="0" cellspacing="1" width='100%'>
							<tr>
								<td align="right">
								    <a class="easyui-linkbutton primary-btn" iconCls="" plain="false" onclick="relevancePerson.searchData();" href="javascript:void(0);">查询</a> 
									<a class="easyui-linkbutton" iconCls="" plain="false" onclick="relevancePerson.clearData();" href="javascript:void(0);">清空</a>
									<a class="easyui-linkbutton" iconCls="" plain="false" onclick="relevancePerson.hideSearchForm();" href="javascript:void(0);">返回</a></td>
							</tr>
						</table>
					</div>

				</div>
			</div>
			<div title="601所人员配置" style="padding: 0px;" id="RiskMuB">
				<iframe id="tabRiskMuB" src='' frameBorder=0  style="height: 99%; width: 100%"></iframe>
			</div>
		</div>
	</div>

</body>
</html>