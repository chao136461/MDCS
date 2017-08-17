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
<!-- ControllerPath = "discussion_manage/disputememo/DisputeMemoController/DisputeMemoInfo" -->
<title></title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include page="/avicit/platform6/modules/system/commonpopup/commonSelectionHead.jsp"></jsp:include>
<script src="static/js/platform/component/sfn/SelfDefiningQuery.js" type="text/javascript"></script>
<script type="text/javascript">
		var disputeMemo;
		$(function(){
			disputeMemo= new DisputeMemo('dgDisputeMemo','${url}','searchDialog','disputeMemo');
																																																																																																																			var sendToUserCommonSelector = new CommonSelector("user","userSelectCommonDialog","sendTo","sendToAlias");
							sendToUserCommonSelector.init(); 
																																																																																																																																					/////
			var array=[];
    			                                                                                                                                                                                                             
                              var searchObject = 
                                {
                                    name:'建议ID',
                                    field:'PRO_ID',
                                    type:1,
                                    dataType:'string'};
                                     array.push(searchObject);
                                                                                                                                                                                             
                              var searchObject = 
                                {
                                    name:'备忘内容',
                                    field:'DIS_TEXT',
                                    type:1,
                                    dataType:'string'};
                                     array.push(searchObject);
                                                                                                                                                                                            
                            var searchObject = 
                                {
                                    name:'通知接收目标人',
                                    field:'SEND_TO',
                                    type:1,
                                    dataType:'user'};
                                    
                                    array.push(searchObject);
                               
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ///
              
            
            selfDefQury.init(array);
            selfDefQury.setQuery(function(param){
                disputeMemo.searchDataBySfn(param);
            });
		});
		function formateDate(value,row,index){
			return disputeMemo.formate(value);
		}
		function formateDateTime(value,row,index){
			return disputeMemo.formateDateTime(value);
		}
		//disputeMemo.detail(\''+row.id+'\')
		function formateHref(value,row,inde){
			return '<a href="javascript:void(0);" onclick="disputeMemo.detail(\''+row.id+'\');">'+value+'</a>';
		}
		                                                                                                                                                                                                                                                                                                                                                                                                                                            	</script>
</head>
<body class="easyui-layout" fit="true">
	<div data-options="region:'center'" style="background:#ffffff;height:0px;padding:0px;overflow:hidden;">
		<div id="toolbarDisputeMemo" class="datagrid-toolbar">
		 	<table>
		 		<tr>
		 		
		 				 		<sec:accesscontrollist hasPermission="3" domainObject="formdialog_disputeMemo_table_${param.standName}" permissionDes="添加">
					<td><a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="disputeMemo.insert();" href="javascript:void(0);">添加</a></td>
				</sec:accesscontrollist>
				<sec:accesscontrollist hasPermission="3" domainObject="formdialog_disputeMemo_button_edit" permissionDes="编辑">
					<td><a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="disputeMemo.modify();" href="javascript:void(0);">编辑</a></td>
				</sec:accesscontrollist>
				<sec:accesscontrollist hasPermission="3" domainObject="formdialog_disputeMemo_button_delete" permissionDes="删除">
					<td><a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="disputeMemo.del();" href="javascript:void(0);">删除</a></td>
				</sec:accesscontrollist>
								<sec:accesscontrollist hasPermission="3" domainObject="formdialog_disputeMemo_button_query" permissionDes="查询">	
					<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="disputeMemo.openSearchForm();" href="javascript:void(0);">查询</a></td>
				</sec:accesscontrollist>	
				<sec:accesscontrollist hasPermission="3" domainObject="formdialog_disputeMemo_button_seniorquery" permissionDes="高级查询">	
					<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="selfDefQury.show();" href="javascript:void(0);">高级查询</a></td>	
				</sec:accesscontrollist>	
				
				</tr>
		 	</table>
	 	</div>
	 	<table id="dgDisputeMemo"
			data-options="
				fit: true,
				border: false,
				rownumbers: true,
				animate: true,
				collapsible: false,
				fitColumns: true,
				autoRowHeight: false,
				toolbar:'#toolbarDisputeMemo',
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
																																																											<th data-options="field:'proId', halign:'center'" width="220">建议ID</th>	
																	
																																																											<th data-options="field:'disText', halign:'center'" width="220">备忘内容</th>	
																	
																																																									<th data-options="field:'sendToAlias',align:'center'" width="220">通知接收目标人</th>
																																																																																																																																																																																		</tr>
			</thead>
		</table>
	</div>
	<!--*****************************搜索*********************************  -->
	<div id="searchDialog" data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'" style="width: 904px;height:340px;display:none;">
		<form id="disputeMemo">
    		<table class="form_commonTable">
					<tr>
																																																														<th width="10%">建议ID:</th>
										    								 <td width="40%"><input class="easyui-validatebox"  style="width: 151px;" type="text" name="proId"/></td>
																																																								<th width="10%">备忘内容:</th>
										    								 <td width="40%"><input class="easyui-validatebox"  style="width: 151px;" type="text" name="disText"/></td>
																										</tr>
									<tr>
																																															<th width="10%">通知接收目标人:</th>
																			 <td width="40%">
										<input title="通知接收目标人" class="inputbox"   type="hidden" name="sendTo" id="sendTo"/>
										<div class=""><input class="easyui-validatebox"  name="sendToAlias"   id="sendToAlias"  readOnly="readOnly"  ></input></div>
										</td>
																																																																																																																																																																																																																								</tr>
    				</table>
    				</form>
    	 <div id="searchBtns" class="datagrid-toolbar foot-formopera">
            <table class="tableForm" border="0" cellspacing="1" width='100%'>
                <tr>
                    <td align="right">
                        <a class="easyui-linkbutton primary-btn" iconCls="" plain="false" onclick="disputeMemo.searchData();" href="javascript:void(0);">查询</a>
                        <a class="easyui-linkbutton" iconCls="" plain="false" onclick="disputeMemo.clearData();" href="javascript:void(0);">清空</a>
                        <a class="easyui-linkbutton" iconCls="" plain="false" onclick="disputeMemo.hideSearchForm();" href="javascript:void(0);">返回</a>
                    </td>
                </tr>
            </table>
        </div>
  </div>
	<script src=" avicit/discussion_manage/disputememo/js/DisputeMemo.js" type="text/javascript"></script>
	
</body>
</html>