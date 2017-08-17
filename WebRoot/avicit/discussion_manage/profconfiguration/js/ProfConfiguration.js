/**
 * 
 */
function ProfConfiguration(datagrid,url,searchD,form){
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
	this._searchDiaId ="#"+searchD;
	this.init.call(this);
};
//初始化操作
ProfConfiguration.prototype.init=function(){
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
		url:this.getUrl()+"getProfConfigurationsByPage.json",
		queryParams:{
			param: pid == '320'?JSON.stringify({scene:pid}):JSON.stringify({scene:pid})
		},
		});
};
//添加页面
ProfConfiguration.prototype.insert=function(){
	this.nData = new CommonDialog("insert","600","400",this.getUrl()+'Add/null?pid='+pid,"添加",false,true,false);
	this.nData.show();
};
//编辑页面
ProfConfiguration.prototype.modify=function(){
	var rows = this._datagrid.datagrid('getChecked');
	if(rows.length !== 1){
		$.messager.alert('提示','请选择要编辑的记录！','warning');
		return false;
	}
	this.nData = new CommonDialog("edit","600","400",this.getUrl()+'Edit/'+rows[0].id+'?pid='+pid,"编辑",false,true,false);
	this.nData.show();
};

//详细页
ProfConfiguration.prototype.detail=function(id){
	this.nData = new CommonDialog("edit","790","500",this.getUrl()+'Detail/'+id,"详情",false,true,false);
	this.nData.show();
};
//保存功能
ProfConfiguration.prototype.save=function(form,id,pid){
	var _self = this;
	$.ajax({
		 url:_self.getUrl()+"save",
		 data : {
			 data :JSON.stringify(form),
			 pid : pid	
			 	},
		 type : 'post',
		 
		 dataType : 'json',
		 success : function(r){
			 if (r.flag == "success"){
				 _self.reLoad();
				 _self._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
				 $(id).dialog('close');
				$.messager.show({
					 title : '提示',
					 msg : '保存成功！'
				 });
			 }
			 if(r.flag2 == "flag2"){
				 $.messager.show({
					 title : '提示',
					 msg : '已存在相同数据'
				});
			 }
		 }
	 });
};
//删除
ProfConfiguration.prototype.del=function(){
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
ProfConfiguration.prototype.reLoad=function(){
	this._datagrid.datagrid('load',{ param: pid == '320'?JSON.stringify({scene:pid}):JSON.stringify({scene:pid})});
};
//关闭对话框
ProfConfiguration.prototype.closeDialog=function(id){
	$(id).dialog('close');
};

//打开查询框
ProfConfiguration.prototype.openSearchForm =function(){
	this.searchDialog.dialog('open',true);
};
//去后台查询
ProfConfiguration.prototype.searchData =function(){
	this._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	this._datagrid.datagrid('load',{ param : JSON.stringify(serializeObject(this.searchForm))});
};
//隐藏查询框
ProfConfiguration.prototype.hideSearchForm =function(){
	this.searchDialog.dialog('close',true);
};
/*清空查询条件*/
ProfConfiguration.prototype.clearData =function(){
	this.searchForm.find('input').val('');
	this._datagrid.datagrid({
		url:this.getUrl()+"getProfConfigurationsByPage.json",
		queryParams:{
			param: pid == '320'?JSON.stringify({scene:pid}):JSON.stringify({scene:pid})
		},
		});
};
ProfConfiguration.prototype.formate=function(value){
	if(value){
		return new Date(value).format("yyyy-MM-dd");
	}
	return '';
};
ProfConfiguration.prototype.formateDateTime=function(value){
	if(value){
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
	return '';
};
ProfConfiguration.prototype.searchDataBySfn =function(conditions){
    this._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
    this._datagrid.datagrid('load',conditions);
};
            ProfConfiguration.prototype.category=[];
Platform6.fn.lookup.getLookupType('PRO_CATEGORY',function(r){r&&( ProfConfiguration.prototype.category=r);});  
                                                                        