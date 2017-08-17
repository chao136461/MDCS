/**
 * 
 */
function DemoEmployee(datagrid, searchD, url, form) {
	if (!datagrid || typeof (datagrid) !== 'string' && datagrid.trim() !== '') {
		throw new Error("datagrid不能为空！");
	}
	if (!searchD || typeof (searchD) !== 'string' && searchD.trim() !== '') {
		throw new Error("查询id不能为空！");
	}
	this._datagridId = "#" + datagrid;
	this._formId = "#" + form;
	this._searchDiaId = "#" + searchD;
	this._doc = document;
	this._url = url;
	this.comboData = {};
	// 正在编辑的行
	this._indexEditing = -1;
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
DemoEmployee.prototype.init = function() {
	var _self = this;
	this._datagrid = $(this._datagridId).datagrid({
		url : this._url + 'getDemoEmployeesByPage.json',
		onAfterEdit : function(rowIndex, rowData, changes) {
			var rows = dg.datagrid('getRows');
			var l = rows.length;
			_self._isEndEdit = true;
		},
		onClickRow : function(rowIndex, rowData) {
			if (_self._indexEditing != rowIndex && _self.endEdit())
				_self._indexEditing = -1;
			_self.edit();
		}
	});
	var dg = this._datagrid;
	this.searchDialog = $(this._searchDiaId).css('display', 'block').dialog({
		title : '查询'
	});
	this.searchForm = $(this._formId).form();
	this.searchForm.find('input').on('keyup', function(e) {
		if (e.keyCode == 13) {
			_self.searchData();
		}
	});
	this.searchDialog.dialog('close', true);

};
// 添加
DemoEmployee.prototype.insert = function() {
	if (!this.endEdit()) {
		$.messager.alert('提示', '不能编辑，请确保上一条数据填写完整', 'warning');
		return false;
	}

	var dname = $('#designerName').val();
	var did = $('#designerId').val();
	var arrdname = dname.split(",");
	var arrdid = did.split(",");

	var temp = this._datagrid;
	temp.datagrid('insertRow', {
		index : 0,
		row : {strength : arrdname[0],
			strengthid : arrdid[0],
			check : arrdname[1],
			checkid:arrdid[1],
			biaoshen:arrdname[2],
			biaoshenid:arrdid[2],
			weight:arrdname[3],
			weightid:arrdid[3],
			reliablity:arrdname[4],
			reliablityid:arrdid[4],
			material:arrdname[5],
			materialid:arrdid[5],
			standardparts:arrdname[6],
			standardpartsid:arrdid[6],
			qualityaudit:arrdname[7],
			qualityauditid:arrdid[7]
			}
	});
	temp.datagrid('selectRow', 0).datagrid('beginEdit', 0);
	this._indexEditing = 0;

};
// 保存功能
DemoEmployee.prototype.save = function() {
	if (!this.endEdit()) {
		$.messager.alert('提示', '不能保存，请确保上一条数据填写完整', 'warning');
		return false;
	}
	var rows = this._datagrid.datagrid('getChanges');
	var data = JSON.stringify(rows);
	var _self = this;
	if (rows.length > 0) {
		_self._indexEditing = -1;
		$.ajax({
			url : this._url + '/save.json',
			data : {
				datas : data
			},
			type : 'post',
			dataType : 'json',
			success : function(r) {
				if (r.flag == "success") {
					_self._datagrid.datagrid('reload', {});// 刷新当前页
					_self._datagrid.datagrid('uncheckAll').datagrid(
							'unselectAll').datagrid('clearSelections');

					$.messager.show({
						title : '提示',
						msg : '保存成功！'
					});
				} else {
					$.messager.show({
						title : '提示',
						msg : r.error
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
DemoEmployee.prototype.edit = function() {
	var temp = this._datagrid;
	var rows = temp.datagrid('getChecked');
	var index = temp.datagrid('getRowIndex', rows[0]);
	// 编辑正在编辑的数据
	if (this._indexEditing === index)
		return true;

	if (!this.endEdit()) {
		$.messager.alert('提示', '不能编辑，请确保上一条数据填写完整', 'warning');
		return false;
	}
	var l = rows.length;
	if (l !== 1) {
		$.messager.alert('提示', '请选择一条数据！', 'warning');
		return false;
	}
	temp.datagrid('beginEdit', index);
	this._indexEditing = index;

	var ed = temp.datagrid('getEditor', {
		index : 0,
		field : 'deptAlias'
	});
	$(ed.target).attr('id', "receptdeptAlias" + 0);
	$(ed.target).attr('disabled', true);
	var ed = temp.datagrid('getEditor', {
		index : 0,
		field : 'dept'
	});
	$(ed.target).attr('id', "receptdept" + 0);


};

// 删除
DemoEmployee.prototype.del = function() {
	var rows = this._datagrid.datagrid('getChecked');
	var _self = this;
	var ids = [];
	var l = rows.length;
	if (l > 0) {

		$.messager.confirm('请确认', '您确定要删除当前所选的数据？', function(b) {
			if (b) {
				_self._indexEditing = -1;
				for (; l--;) {
					ids.push(rows[l].id);
				}
				if (ids.length == 0)
					return;
				// 删除选中的行
				_self.datagrid('deleteRow',rows);
			}
		});
	} else {
		$.messager.alert('提示', '请选择要删除的记录！', 'warning');
	}
};

// 结束当前编辑行
DemoEmployee.prototype.endEdit = function() {
	if (this._indexEditing === -1)
		return true;
	this._isEndEdit = false;
	this._datagrid.datagrid('endEdit', this._indexEditing);
	return this._isEndEdit;
};

// 打开查询框
DemoEmployee.prototype.openSearchForm = function() {
	this.searchDialog.dialog('open', true);
};
// 去后台查询
DemoEmployee.prototype.searchData = function() {
	this._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid(
			'clearSelections');
	this._datagrid.datagrid('load', {
		param : JSON.stringify(serializeObject(this.searchForm))
	});
};
// 隐藏查询框
DemoEmployee.prototype.hideSearchForm = function() {
	this.searchDialog.dialog('close', true);
};
// 清空查询条件
DemoEmployee.prototype.clearData = function() {
	this.searchForm.find('input').val('');
	this.searchData();
};

DemoEmployee.prototype.formate = function(value) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd");
	}
	return '';
};
DemoEmployee.prototype.formateDateTime = function(value) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
	return '';
};
DemoEmployee.prototype.searchDataBySfn = function(conditions) {
	this._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid(
			'clearSelections');
	this._datagrid.datagrid('load', conditions);
};
DemoEmployee.prototype.gender = [];
Platform6.fn.lookup.getLookupType('PLATFORM_SEX', function(r) {
	r && (DemoEmployee.prototype.gender = r);
});
DemoEmployee.prototype.status = [];
Platform6.fn.lookup.getLookupType('PLATFORM_VALID_FLAG', function(r) {
	r && (DemoEmployee.prototype.status = r);
});
// Excel客户端导出
DemoEmployee.prototype.exportClientData = function() {
	var _self = this;
	$.messager
			.confirm(
					'确认',
					'是否确认导出Excel文件?',
					function(r) {
						if (r) { // 封装参数
							var columnFieldsOptions = getGridColumnFieldsOptions('dgDemoEmployee');
							var dataGridFields = JSON
									.stringify(columnFieldsOptions[0]);
							// 获得datagrid中的数据，getChecked：导出选中的数据；getRows:导出所有行数据
							var rows = $('#dgDemoEmployee').datagrid(
									'getChecked');
							var datas = JSON.stringify(rows);
							var myParams = {
								dataGridFields : dataGridFields,// 表头信息集合
								datas : datas,// 需要导出的数据
								hasRowNum : true,// 默认为true:代表第一列为序号
								sheetName : 'sheet1',// 如果该参数为空，默认为导出的Excel文件名
								unContainFields : 'id,strength,check,biaoshen,weight,reliablity,material,standardparts,qualityaudit',// 不需要导出的列，使用','分隔即可
								fileName : '平台用户导出数据'
										+ new Date().format("yyyyMMddhhmmss")// 导出的Excel文件名
							};
							var url = "platform/discussion_manage/inforconfiguration/InforConfigurationController/operation/exportClient";// 导出请求地址
							var ep = new exportData("xlsExport", "xlsExport",
									myParams, url);
							ep.excuteExport();
						}
					});
};
