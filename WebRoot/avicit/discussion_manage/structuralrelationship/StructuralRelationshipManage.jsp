<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sec" uri="/WEB-INF/tags/shiro.tld"%>
<%@taglib prefix="pt6" uri="/WEB-INF/tags/platform6.tld"%>
<%@ page import="avicit.platform6.commons.utils.ViewUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"  content="text/html; charset=utf-8">
<meta http-equiv="Pragma" 	     content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache"> 

<!-- ControllerPath = "discussion_manage/structuralrelationship/StructuralRelationshipController/StructuralRelationshipInfo" -->
<title>tree</title>
<base href="<%=ViewUtil.getRequestPath(request)%>">
</head>
<jsp:include page="/avicit/platform6/component/common/EasyUIJsInclude.jsp"></jsp:include>
<jsp:include page="/avicit/discussion_manage/structuralrelationship/ztreeLayuiInclude.jsp"></jsp:include>
<script type="text/javascript">
	var setting = {
		async: {
			autoParam:["id","pId"],
			otherParam:[],
			dataType:"json",
			enable: true,
			type:"post",
			url:"platform/discussion_manage/structuralrelationship/StructuralRelationshipController/gettreeZtree/3"
		},
		callback: {
			onClick: zTreeOnClick,
			beforeDrag: beforeDrag,
			beforeDrop: beforeDrop
		},
		edit: {
			enable: true,
			showRemoveBtn: false,
			showRenameBtn: false
		},
		view: {
			fontCss: getFontCss,
			nameIsHTML: true
		}
	};

	function getFont(treeId, node) {
		return node.font ? node.font : {};
	}
	
	//初始化树
	$(document).ready(function(){
		$.fn.zTree.init($("#tree"), setting);
	});
	
	//初始化选择根节点
	window.onload = function(){
		var zTree = $.fn.zTree.getZTreeObj("tree");
		var pn = zTree.getNodeByParam("pId", "-100");
		zTree.selectNode(pn);
		//绑定按下enter键定位节点
		$("#searchWord").on('keyup',function(e){
				if(e.keyCode == 13){
					var value = $(this).val();
					searchNodes(value);
				}
			});
		//键定位节点前刷新树去除之前定位
		$("#searchWord").on('click',function(){
				refreshZtree();
				});
	};
	
	var structuralRelationship;
	$(function() {
			structuralRelationship = new StructuralRelationship('tree','${url}', 'searchWord','searchDialog','structuralRelationshipForm');
			structuralRelationship.init();
	});
	
	//获取选择节点数据
	var selectNode=null;
	function zTreeOnClick(event, treeId, treeNode) {
		getDgStructuralRelationship(treeNode.id);
		selectNode = treeNode;
	};
	
	//通过零件查询单条记录
	function getDgStructuralRelationship(id){
		$("#dgStructuralRelationship").datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$("#dgStructuralRelationship").datagrid("load",{"id":id});
	};
	
	//根节点不允许拖拽
	function beforeDrag(treeId, treeNodes) {
		for (var i=0,l=treeNodes.length; i<l; i++) {
			if (treeNodes[i].pId === '-1') {
				return false;
			}
		}
		return true;
	}
	
	function getFontCss(treeId, treeNode) {
		return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
	}
	//保存拖拽后的数据结构
	function beforeDrop(treeId, treeNodes, targetNode, moveType) {
		var targetNodePid = targetNode.id;//目标节点id
		var tabId = treeNodes[0].tabId;//当前节点id
		var ids = [];
		ids.push(targetNodePid);
		ids.push(tabId);
		$.ajax({
			 url : "platform/discussion_manage/structuralrelationship/StructuralRelationshipController/saveDragNode",
			 data : JSON.stringify(ids),
			 type : 'post',
			 contentType : 'application/json',
			 dataType : 'json',
			 success : function(r){
					 if (r.flag == "success") {
						window.location.reload();//刷新当前页面.
					}else{
						$.messager.show({
							 title : '提示',
							 msg : r.msg
						});
				 	};
			 	}
			 });
		return targetNode ? targetNode.drop !== false : true;
	}
	
	function formateclassType(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				structuralRelationship.classType);
	}
	function formatestatus(value) {
		return Platform6.fn.lookup.formatLookupType(value,
				structuralRelationship.status);
	}
	//定位节点
	function searchNodes(value){
		var zTree = $.fn.zTree.getZTreeObj("tree");
 		if(value != null && value != ""){
 			var nodeList = zTree.getNodesByParamFuzzy("name", value);
 			updateAllNode(true,nodeList,zTree);
    	}
	};
	
	//定位节点
	function onClickSearchButton(){
		var value = $("#searchWord").val();
		searchNodes(value);
	};
</script>
</head>
<body>
    <div class="easyui-layout" fit="true">
		<div data-options="region:'west',split:true,title:'产品型号树结构'"
			style="width: 250px; padding: 0px;">
			<table>
				<tr>
					<td> <input  name="searchWord" id="searchWord"  style="width: 160px;height: 20px;margin: 2px" lay-verify="title" 
				    	   autocomplete="off" placeholder="请输入树节点名称！" class="layui-input" type="text"/></td>
				    <td><button onclick="onClickSearchButton();" style="line-height: inherit;height: 20px" class="layui-btn layui-btn-mini"><i class="layui-icon">&#xe615;</i></button></td>
				</tr>
			</table>
			<ul id="tree" class="ztree"></ul>
		</div>
	    <div data-options="region:'center',split:true" style="padding: 0px; overflow: auto;">
	    		<div class="easyui-layout" fit="true">
	    
					<div data-options="region:'north',split:true,title:'产品型号结构操作'" style="padding: 0px;height: 67px;">
					  <div id="toolbarImportResult" class='datagrid-toolbar'>
							<table>
								<tr>
									<sec:accesscontrollist hasPermission="3"
										domainObject="formdialog_structuralRelationship_query"
										permissionDes="查询">
										<td><a class="easyui-linkbutton" iconCls="icon-search"
											plain="true" onclick="structuralRelationship.openSearchForm();"
											href="javascript:void(0);">查询</a></td>
									</sec:accesscontrollist>
									<sec:accesscontrollist hasPermission="3"
										domainObject="formdialog_structuralRelationship_insert_"
										permissionDes="添加平级节点">
										<td><a class="easyui-linkbutton" iconCls="icon-add"
											plain="true" onclick="structuralRelationship.insert();"
											href="javascript:void(0);">添加平级节点</a></td>
									</sec:accesscontrollist>
				
									<sec:accesscontrollist hasPermission="3"
										domainObject="formdialog_structuralRelationship_insertsub_"
										permissionDes="添加子节点">
										<td><a class="easyui-linkbutton" iconCls="icon-add_other"
											plain="true" onclick="structuralRelationship.insertsub();"
											href="javascript:void(0);">添加子节点</a></td>
									</sec:accesscontrollist>
									<sec:accesscontrollist hasPermission="3"
										domainObject="formdialog_structuralRelationship_modify_"
										permissionDes="编辑">
										<td><a class="easyui-linkbutton" iconCls="icon-edit"
											plain="true" onclick="structuralRelationship.modify();"
											href="javascript:void(0);">编辑</a></td>
									</sec:accesscontrollist>
									<sec:accesscontrollist hasPermission="3"
										domainObject="formdialog_structuralRelationship_del_"
										permissionDes="删除">
										<td><a class="easyui-linkbutton" iconCls="icon-remove"
											plain="true" onclick="structuralRelationship.del();"
											href="javascript:void(0);">删除</a></td>
									</sec:accesscontrollist>
								</tr>
							</table>
						</div>
					</div>
			  
	  				<div data-options="region:'center',title:'零件部信息'">
	  					<table id="dgStructuralRelationship"  class="easyui-datagrid"  
							data-options="
								fit: true,
								border: false,
								url:'platform/discussion_manage/structuralrelationship/StructuralRelationshipController/getDgStructuralRelationship.json',
								rownumbers: true,
								animate: true,
								collapsible: false,
								fitColumns: true,
								autoRowHeight: false,
								idField :'id',
								method:'get',
								singleSelect: true,
								checkOnSelect: true,
								selectOnCheck: false,
								pagination:true,
								pageSize:dataOptions.pageSize,
								pageList:dataOptions.pageList,
								striped:true">
							<thead>
								<tr>
									<th data-options="field:'id', align:'center',checkbox:true" width="200">表主键</th>
									<th data-options="field:'className', align:'center'" 	 width="150">分类名称</th>
									<th data-options="field:'classCode', align:'center'" 	 width="150">分类编号</th>
									<th data-options="field:'classType', align:'center',formatter:formateclassType" width="220">分类类型</th>
									<th data-options="field:'status', align:'center',formatter:formatestatus" width="220">成熟度</th>
									<th data-options="field:'edition',  align:'center'" 	 width="150">版本</th>
									<th data-options="field:'designerName', align:'center'"	 width="150">设计者</th>
								</tr>
							</thead>
						</table>
	  				</div>
	  		</div>
	  </div>
	</div>
	
	
	
	
	
	
	<!--*****************************搜索*********************************  -->
	<div id="searchDialog" 
		data-options="iconCls:'icon-search',resizable:true,modal:false,buttons:'#searchBtns'"
		style="width: 904px; height: 340px; display: block;">
		<form id="structuralRelationshipForm">
			<table class="form_commonTable">
				<tr>
					<th width="10%">分类编号:</th>
					<td width="40%"><input class="easyui-validatebox"
						 type="text" name="classCode" /></td>
					<th width="10%"></th>
					<td width="40%"></td>
				</tr>
			</table>
		</form>
		<div id="searchBtns" class="datagrid-toolbar foot-formopera">
			<table class="tableForm" border="0" cellspacing="1" width='100%'>
				<tr>
					<td align="right"><a class="easyui-linkbutton primary-btn"
						iconCls="" plain="false" onclick="structuralRelationship.searchData(serializeObject($('#structuralRelationshipForm')));"
						href="javascript:void(0);">查询</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="structuralRelationship.clearData();"
						href="javascript:void(0);">清空</a> <a class="easyui-linkbutton"
						iconCls="" plain="false" onclick="structuralRelationship.hideSearchForm();"
						href="javascript:void(0);">返回</a></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>