﻿/**
 * 
 */
function ProcessRoute(treegrid, url, form, strId) {
	if (!treegrid || typeof (treegrid) !== 'string' && treegrid.trim() !== '') {
		throw new Error("treegrid不能为空！");
	}

	this._treegridId = "#" + treegrid;
	this._doc = document;
	this._url = url;
	this._strId = strId;
	this.comboData = {};
	// 正在编辑的行
	this._indexEditing = undefined;
	// 是否可以结束编辑
	this._isEndEdit = true;
	this.init.call(this);
};
// 初始化操作
// 通用代码 待定
/**
 * DemoSingleForm.prototype.validFlag=[];
 * Platform6.fn.lookup.getLookupType('PLATFORM_VALID_FLAG',function(r){r&&(DemoSingleForm.prototype.validFlag=r);});
 */
ProcessRoute.prototype.init = function() {
	var _self = this;
	this._treegrid = $(this._treegridId).treegrid({
		url : this._url + 'getTreeGridByPage/' + this._strId,
		onClickCell : function(field, node) {
			if (!_self.endEdit()) {
				alert('请确保上一条数据填写完整');
				return;
			}
			var opts = $(this).treegrid('getColumnOption', field);
			if (opts && opts.editor) {
				_self._indexEditing = node.id;
				$(this).treegrid('beginEdit', node.id);
			}
		}
	});
	var dg = this._treegrid;
};

// 保存功能
ProcessRoute.prototype.save = function() {
	if (!this.endEdit()) {
		$.messager.alert('提示', '不能保存，请确保上一条数据填写完整', 'warning');
		return false;
	}
	var rows = this._treegrid.treegrid('getChanges');
	var editCells = [ "routeId","strId", "thermalUnit", "manufacturingUnit", "useUnit" ];
	var newRows = [];
	for (var i = 0; i < rows.length; i++) {
		var newRow = {};
		for (var j = 0; j < editCells.length; j++) {
			newRow[editCells[j]] = rows[i][editCells[j]];
		}
		newRows.push(newRow);
	}
	
	var data = JSON.stringify(newRows);
	var _self = this;
	if (newRows.length > 0) {
		_self._indexEditing = undefined;
		$.ajax({
			url : this._url + '/proSave.json',
			data : {
				datas : data,
				strId : this._strId,
				dutyUserId : dutyUserId
			},
			type : 'post',
			dataType : 'json',
			success : function(r) {
				if (r.flag == "success") {
					_self._treegrid.treegrid('reload', {});// 刷新当前页
					_self._treegrid.treegrid('uncheckAll').treegrid(
							'unselectAll').treegrid('clearSelections');
					_self._treegrid.treegrid('acceptChanges');
					$.messager.show({
						title : '提示',
						msg : '保存成功！'
					});
				} else {
					$.messager.show({
						title : '提示',
						msg : r.e,
						width:'300px',
						height:'150px'
					});
				}
			}
		});
	} else {
		$.messager.show({
			title : '提示',
			msg : '没有要提交的数据！'
		});
	}

};

// 编辑
ProcessRoute.prototype.edit = function() {
	var temp = this._treegrid;
	var row = temp.treegrid('getSelected');
	index = row.id;
	// 编辑正在编辑的数据
	// if (this._indexEditing === index)
	// return true;
	if (!this.endEdit()) {
		$.messager.alert('提示', '不能编辑，请确保上一条数据填写完整', 'warning');
		return false;
	}
	if (row == undefined) {
		$.messager.alert('提示', '请选择一条数据！', 'warning');
		return false;
	}
	temp.treegrid('beginEdit', index);
	this._indexEditing = index;
};

// 删除
ProcessRoute.prototype.del = function() {
	var rows = this._treegrid.treegrid('getChecked');
	var _self = this;
	var ids = [];
	alert("delete");
	var l = rows.length;
	if (l > 0) {

		$.messager.confirm('请确认', '您确定要删除当前所选的数据？', function(b) {
			if (b) {
				_self._indexEditing = undefined;
				for (; l--;) {
					ids.push(rows[l].id);
				}
				if (ids.length == 0)
					return;
				$.ajax({
					url : _self._url + '/delete.json',
					data : {
						ids : ids.join(',')
					},
					type : 'post',
					dataType : 'json',
					success : function(r) {
						if (r.flag == "success") {
							_self._treegrid.treegrid('reload', {});// 刷新当前页
							_self._treegrid.treegrid('uncheckAll').treegrid(
									'unselectAll').treegrid('clearSelections');
							$.messager.show({
								title : '提示',
								msg : '删除成功！'
							});
						} else {
							$.messager.show({
								title : '提示',
								msg : r.e
							});
						}
					}
				});
			}
		});
	} else {
		$.messager.alert('提示', '请选择要删除的记录！', 'warning');
	}
};

// 结束当前编辑行
ProcessRoute.prototype.endEdit = function() {

	if (this._indexEditing != undefined) {
		this._treegrid.treegrid('endEdit', this._indexEditing);
		this._indexEditing = undefined;
	}

	return true;
};

ProcessRoute.prototype.status = [];
Platform6.fn.lookup.getLookupType('STR_STATUS', function(r) {
	r && (ProcessRoute.prototype.status = r);
});
