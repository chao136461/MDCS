/**
 * 
 */
function ProposalManage(datagrid,url,searchD,form,str_id,applyDeptId){
	if(!datagrid || typeof(datagrid)!=='string'&&datagrid.trim()!==''){
		throw new Error("datagrid不能为空！");
	}
	var _selectId='';
    var	_url=url;
    this.getUrl = function(){
    	return _url;
    }
	this._datagridId="#"+datagrid;
	this._doc = document;
	this._formId="#"+form;
	this._strid = str_id;
	this._applyDeptId = applyDeptId;
	this._searchDiaId ="#"+searchD;
	this.init.call(this);
};
//初始化操作
ProposalManage.prototype.init=function(){
	var _self = this;
	this.searchDialog =$(this._searchDiaId).css('display','block').dialog({
		title:'查询'
	});
	this.searchForm = $(this._formId).form();
	this.searchForm.find('input').on('keyup',function(e){
		if(e.keyCode == 13){
			_self.searchData();
		}
	});
	this.searchDialog.dialog('close',true);
	this._datagrid=$(this._datagridId).datagrid({
		url:this.getUrl()+"getProposalManagesByPage.json",
		queryParams:{
			param: JSON.stringify({strId:_self._strid,proposalUserDept:_self._applyDeptId})
		}
		});
};
//添加页面
ProposalManage.prototype.insert=function(strId,classCode,instancenumber){
	this.nData = new CommonDialog("insert","790","500",this.getUrl()+'Add/null?strId='+strId+'&classCode='+encodeURI(encodeURI(classCode))+'&instancenumber='+instancenumber,"添加",false,true,false);
	this.nData.show();
};
//快速发表建议
ProposalManage.prototype.ksinsert=function(strId){
	var _self = this;
	$.ajax({
		 url:_self.getUrl()+"saveDto",
		 data : {strId :strId},
		 type : 'post',
		 dataType : 'json',
		 success : function(r){
			 if (r.flag == "success"){
				 _self.init().call(this);
				$.messager.show({
					 title : '提示',
					 msg : '添加成功！',
					 timeout:5000

				 });
			 }else{
				 $.messager.show({
					 title : '提示',
					 msg : r.error
				});
			 } 
		 }
	 });
};
//编辑页面
ProposalManage.prototype.modify=function(){
	var rows = this._datagrid.datagrid('getChecked');
	if(rows.length !== 1){
		$.messager.alert('提示','请选择要编辑的记录！','warning');
		return false;
	}
	this.nData = new CommonDialog("edit","790","500",this.getUrl()+'Edit/'+rows[0].id,"编辑",false,true,false);
	this.nData.show();
};

//详细页
ProposalManage.prototype.detail=function(id){
	this.nData = new CommonDialog("edit","790","500",this.getUrl()+'Detail/'+id,"详情",false,true,false);
	this.nData.show();
};
//保存功能
ProposalManage.prototype.save=function(form,id){
	var _self = this;
	$.ajax({
		 url:_self.getUrl()+"save",
		 data : {data :JSON.stringify(form)},
		 type : 'post',
		 dataType : 'json',
		 success : function(r){
			 if (r.flag == "success"){
				 _self._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
				 $(id).dialog('close');
				 _self.init().call(this);
				$.messager.show({
					 title : '提示',
					 msg : '保存成功！'
				 });
			 }else{
				 $.messager.show({
					 title : '提示',
					 msg : r.error
				});
			 } 
		 }
	 });
};
//删除
ProposalManage.prototype.del=function(){
	var rows = this._datagrid.datagrid('getChecked');
	var _self = this;
	var ids = [];
	var l =rows.length;
	if(l > 0){
	  $.messager.confirm('请确认','您确定要删除当前所选的数据？',function(b){
		 if(b){
			 for(;l--;){
				 ids.push(rows[l].id);
			 }
			 $.ajax({
				 url:_self.getUrl()+'delete',
				 data:	JSON.stringify(ids),
				 contentType : 'application/json',
				 type : 'post',
				 dataType : 'json',
				 success : function(r){
					 if (r.flag == "success") {
						 _self.reLoad();
						 _self._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
						 $.messager.show({
							 title : '提示',
							 msg : '删除成功！'
						});
					}else{
						$.messager.show({
							 title : '提示',
							 msg : r.error
						});
					}
				 }
			 });
		 } 
	  });
	}else{
	  $.messager.alert('提示','请选择要删除的记录！','warning');
	}
};
//重载数据
ProposalManage.prototype.reLoad=function(){
	this._datagrid.datagrid('load',{ param : JSON.stringify(serializeObject(this.searchForm))});
};
//关闭对话框
ProposalManage.prototype.closeDialog=function(id){
	$(id).dialog('close');
};

//打开查询框
ProposalManage.prototype.openSearchForm =function(){
	this.searchDialog.dialog('open',true);
};
//去后台查询
ProposalManage.prototype.searchData =function(){
	this._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	this._datagrid.datagrid('load',{ param : JSON.stringify(serializeObject(this.searchForm))});
};
//隐藏查询框
ProposalManage.prototype.hideSearchForm =function(){
	this.searchDialog.dialog('close',true);
};
/*清空查询条件*/
ProposalManage.prototype.clearData =function(){
	this.searchForm.find('input').val('');
	this.searchData();
};
ProposalManage.prototype.formate=function(value){
	if(value){
		return new Date(value).format("yyyy-MM-dd");
	}
	return '';
};
ProposalManage.prototype.formateDateTime=function(value){
	if(value){
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
	return '';
};
ProposalManage.prototype.searchDataBySfn =function(conditions){
    this._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
    this._datagrid.datagrid('load',conditions);
};
                            ProposalManage.prototype.status=[];
Platform6.fn.lookup.getLookupType('PROPOSED_STATUS',function(r){r&&( ProposalManage.prototype.status=r);});  
                   ProposalManage.prototype.isDispute=[];
Platform6.fn.lookup.getLookupType('PROPOSED_IS_DISPUTE',function(r){r&&( ProposalManage.prototype.isDispute=r);});  
                                                                               ProposalManage.prototype.proposedType=[];
Platform6.fn.lookup.getLookupType('PROPOSED_TYPE',function(r){r&&( ProposalManage.prototype.proposedType=r);});  
        