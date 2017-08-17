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
<!-- ControllerPath = "discussion_manage/regionconfiguration/RegionConfigurationController/RegionConfigurationInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js" type="text/javascript"></script>
<script type="text/javascript">
		var regionConfiguration;
		$(function(){
			regionConfiguration= new RegionConfiguration('dgRegionConfiguration','${url}','searchDialog','regionConfiguration');
			
			//加载型号
			$('#typeId').combobox({
					valueField: 'StrId',    
			        textField: 'ClassCode',    
			        url: 'platform/discussion_manage/structuralrelationship/StructuralRelationshipController/findStructuralType',
			       	onLoadSuccess:function(){
			    	  $('#typeId').combobox('select','');
			       }
			})
			
		});
		function formateDate(value,row,index){
			return regionConfiguration.formate(value);
		}
		function formateDateTime(value,row,index){
			return regionConfiguration.formateDateTime(value);
		}
		//regionConfiguration.detail(\''+row.id+'\')
		function formateHref(value,row,inde){
			return '<a href="javascript:void(0);" onclick="regionConfiguration.detail(\''+row.id+'\');">'+value+'</a>';
		}
		function formatestate(value){
             return Platform6.fn.lookup.formatLookupType(value, regionConfiguration.state);
        }
        function formateregionLevel(value){
             return Platform6.fn.lookup.formatLookupType(value, regionConfiguration.regionLevel);
        }
                                                                                                                                                                                                                                                                                                                                                                     	</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'" style="background:#ffffff;height:0px;padding:0px;overflow:hidden;">
		<div id="toolbarRegionConfiguration" class="datagrid-toolbar">
		 	<table>
		 		<tr>
		 		
		 		<sec:accesscontrollist hasPermission="3" domainObject="formdialog_regionConfiguration_table_${param.standName}" permissionDes="添加">
					<td><a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="regionConfiguration.insert();" href="javascript:void(0);">添加</a></td>
				</sec:accesscontrollist>
				<sec:accesscontrollist hasPermission="3" domainObject="formdialog_regionConfiguration_button_edit" permissionDes="编辑">
					<td><a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="regionConfiguration.modify();" href="javascript:void(0);">编辑</a></td>
				</sec:accesscontrollist>
				<sec:accesscontrollist hasPermission="3" domainObject="formdialog_regionConfiguration_button_delete" permissionDes="删除">
					<td><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="regionConfiguration.del();" href="javascript:void(0);">删除</a></td>
				</sec:accesscontrollist>
								<sec:accesscontrollist hasPermission="3" domainObject="formdialog_regionConfiguration_button_query" permissionDes="查询">	
					<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="regionConfiguration.openSearchForm();" href="javascript:void(0);">查询</a></td>
				</sec:accesscontrollist>	
			
				</tr>
		 	</table>
	 	</div>
	 	<table id="dgRegionConfiguration"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarRegionConfiguration',
				idField :'id',
				singleSelect: true,
				checkOnSelect: true,
				selectOnCheck: false,
				pagination:true,
				pageSize:dataOptions.pageSize,
				pageList:dataOptions.pageList,
				striped:true">
			<thead>
				<tr>
					<th data-options="field:'id', halign:'center',checkbox:true" width="220">ID</th>
					<th data-options="field:'classCode', halign:'center'" width="220">型号</th>	
	
					<th data-options="field:'beginDay', halign:'center'" width="220">开始天数</th>	
	
					<th data-options="field:'endDay', halign:'center'" width="220">结束天数</th>	
					<th data-options="field:'state', halign:'center',formatter:formatestate" width="220">状态</th>
					<th data-options="field:'regionLevel', halign:'center',formatter:formateregionLevel" width="220">级别</th>
					</tr>
			</thead>
		</table>
	</div>
	<!--*****************************搜索*********************************  -->
	<div id="searchDialog" data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'" style="width: 750px;height:340px;display:none;">
		<form id="regionConfiguration">
    		<table class="form_commonTable">
					<tr>
						<th width="10%">型号:</th>
						 <td width="40%"><select title="型号" class="easyui-combobox"  style="width:130px" required="required"  name="typeId" id="typeId" data-options="panelHeight:'auto'">
							</select>
						 <th width="10%">开始天数:</th>
						 <td width="40%"><input class="easyui-validatebox"  style="width: 151px;" type="text" name="beginDay"/></td>
																										</tr>
					<tr>
						<th width="10%">结束天数:</th>
						<td width="40%"><input class="easyui-validatebox"  style="width: 151px;" type="text" name="endDay"/></td>
						<th width="10%">状态:</th>
						<td width="40%">
	                         <pt6:syslookup name="state" isNull="true" lookupCode="REGION_STATE" dataOptions="width:151,editable:false,panelHeight:'auto'">
	                         </pt6:syslookup> 
                        </td>
					
					<tr>
						 <th width="10%">级别:</th>
						 <td width="40%">
                              <pt6:syslookup name="regionLevel" isNull="true" lookupCode="REGION_LEVEL" dataOptions="width:151,editable:false,panelHeight:'auto'">
                              </pt6:syslookup> 
                         </td>
					</tr>
    				</table>
    				</form>
    	 <div id="searchBtns" class="datagrid-toolbar foot-formopera">
            <table class="tableForm" border="0" cellspacing="1" width='100%'>
                <tr>
                    <td align="right">
                        <a class="easyui-linkbutton primary-btn" iconCls="" plain="false" onclick="regionConfiguration.searchData();" href="javascript:void(0);">查询</a>
                        <a class="easyui-linkbutton" iconCls="" plain="false" onclick="regionConfiguration.clearData();" href="javascript:void(0);">清空</a>
                        <a class="easyui-linkbutton" iconCls="" plain="false" onclick="regionConfiguration.hideSearchForm();" href="javascript:void(0);">返回</a>
                    </td>
                </tr>
            </table>
        </div>
  </div>
	<script src=" avicit/discussion_manage/regionconfiguration/js/RegionConfiguration.js" type="text/javascript"></script>
	
</body>
</html>