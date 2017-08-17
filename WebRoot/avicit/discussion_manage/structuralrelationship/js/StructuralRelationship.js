/**
 * 
 */
function StructuralRelationship(tree,url,searchId,searchDialogId,form){
	if(!tree || typeof(tree)!=='string'&&tree.trim()!==''){
		throw new Error("tree不能为空！");
	}
    var	_url=url;
    this.level={};//级别
    this.getUrl = function(){
    	return _url;
    };
    this._formId="#"+form;
    this._searchId = "#"+searchId;
    this._searchDialogId = "#"+searchDialogId;
	this._treeId="#"+tree;
	this._tree={};
	this._doc = document;
	this._rootId='';
};
//初始化操作
StructuralRelationship.prototype.init=function(){
	this.searchDialog =$(this._searchDialogId).css('display','block').dialog({
		title:'查询',
		modal: true
	});
	this.searchDialog.dialog('close',true);
	this.searchForm = $(this._formId).form();
};



//查询vci树
StructuralRelationship.prototype.searchData=function(searchDate){
	setting.async.otherParam=searchDate;
	setting.async.url="platform/discussion_manage/structuralrelationship/StructuralRelationshipController/searchZtree/4";
	$.fn.zTree.init($("#tree"), setting);
	getDgStructuralRelationship();
	this.hideSearchForm();
};

//添加平级菜单
StructuralRelationship.prototype.insert=function(){
	if(selectNode === null){
		alert("请先选择节点！");
		return;
	}
	if(selectNode.pId === '-100'){
		this._iDg = new CommonDialog("insert","700","400",this.getUrl()+'/operation/Add/3',"添加平级节点",false,true,false);
		this._iDg.show();
		return;
	}
	this._iDg = new CommonDialog("insert","700","400",this.getUrl()+'/operation/Add/'+ selectNode.pId +","+ selectNode.id +","+ "0" +","+ selectNode.tabId,"添加平级节点",false,true,false);
	this._iDg.show();
};
//添加子菜单
StructuralRelationship.prototype.insertsub=function(){
	if(selectNode === null){
		alert("请先选择节点！");
		return;
	}
	this._iDg = new CommonDialog("insert","700","400",this.getUrl()+'/operation/Add/'+ selectNode.pId +","+ selectNode.id +","+ "1"+","+ selectNode.tabId,"添加子节点",false,true,false);
	this._iDg.show();
};

//编辑子菜单
StructuralRelationship.prototype.modify=function(){
	if(selectNode === null){
		alert("请先选择节点！");
		return;
	}
	this._eDg=new CommonDialog("modify","700","400",this.getUrl()+'/operation/Edit/'+ selectNode.id,"编辑",false,true,false);
	this._eDg.show();
};

//保存数据
StructuralRelationship.prototype.save=function(form,url,dialog,id){
	$.ajax({
		 url : url,
		 data : JSON.stringify(form),
		 type : 'post',
		 contentType : 'application/json',
		 dataType : 'json',
		 success : function(r){
			 if (r.flag == "success"){
				 if(r.type =='0'){//新增平级
					 window.location.reload();//刷新当前页面.
					 $(dialog).dialog('close');
				 }else if(r.type =='1'){//新增子节点
					 window.location.reload();
					 $(dialog).dialog('close');
				 }else if(r.type =='2'){
					 $(dialog).dialog('close');
					 $("#dgStructuralRelationship").datagrid('reload', {"id":id});
				 }else if(r.type =='3'){//新增根节点
					 window.location.reload();
					 $(dialog).dialog('close');
				 }
				 $.messager.show({
					 title : '提示',
					 msg : r.returnString
				 });
				
			 }else{
				 $.messager.show({
					 title : '提示',
					 msg : r.returnString
				});
			 } 
		 }
	 });
};

//删除数据
StructuralRelationship.prototype.del=function(){
	if(selectNode == null){
		alert("请先选择节点！");
		return;
	}
	if(selectNode.isParent){
		 $.messager.show({
			 title : '提示',
			 msg : '当前选中菜单含有子菜单项，请先删除子菜单！'
		});
		return;
	}
	var id = selectNode.id;
	var pId = selectNode.pId;
	var ids = [];
	ids.push(id);
	ids.push(pId);
	  $.messager.confirm('请确认','您确定要删除当前节点？',function(b){
		 if(b){
			 $.ajax({
				 url:'platform/discussion_manage/structuralrelationship/StructuralRelationshipController/delete',
				 data:	JSON.stringify(ids),
				 contentType : 'application/json',
				 type : 'post',
				 dataType : 'json',
				 success : function(r){
					 if (r.flag == "success") {
						 window.location.reload();//刷新当前页面.
						 $.messager.show({
							 title : '提示',
							 msg : r.msg
						 });
					}else{
						$.messager.show({
							 title : '提示',
							 msg : r.msg
						});
					}
				 }
			 });
		 }; 
	  });
};

//定位节点
function updateAllNode(highlight,nodeList,zTree) {
	for( var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = highlight;
		zTree.updateNode(nodeList[i]);
	}
}
//定位节点
function refreshZtree(){
	var zTree = $.fn.zTree.getZTreeObj("tree");
	zTree.reAsyncChildNodes(null, "refresh");
}
//打开查询框
StructuralRelationship.prototype.openSearchForm =function(){
	this.searchDialog.dialog('open',true);;
};
//关闭对话框
StructuralRelationship.prototype.hideSearchForm=function(id){
	if(id == null || id == "" || id == "undefined"){
		this.searchDialog.find("input").val("");
		this.searchDialog.dialog('close',true);
	}else{
		$(id).dialog('close',true);
	}
};

/*清空查询条件*/
StructuralRelationship.prototype.clearData =function(){
	this.searchForm.find('input').val('');
	this.searchData();
};
StructuralRelationship.prototype.classType=[];
Platform6.fn.lookup.getLookupType('CLASS_TYPE',function(r){r&&( StructuralRelationship.prototype.classType=r);});  
StructuralRelationship.prototype.status=[];
Platform6.fn.lookup.getLookupType('STR_STATUS',function(r){r&&( StructuralRelationship.prototype.status=r);});  