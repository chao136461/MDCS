/**
 * 
 */
function ExportExcel(datagrid, searchD, url, form) {
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
ExportExcel.prototype.init = function() {
	var _self = this;
	this._datagrid = $(this._datagridId).datagrid({
		url : this._url + 'getExportExcelByPage.json',
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
ExportExcel.prototype.insert = function() {
	var _self = this;
	var factory = $('#factory').combobox('getValue');
	var typeId = $('#typeId').combobox('getValue');
	$
			.ajax({
				url : 'platform/discussion_manage/profconfiguration/ProfConfigurationController/operation/getProfConfigurations',
				data : {
					factory : factory,
					typeId : typeId
				},
				type : 'post',
				dataType : 'json',
				success : function(r) {
					var nameList = r.nameList;
					nameList.push("ID");
					var temp = _self._datagrid;
					var columns = [];
					for ( var i = 0; i < nameList.length; i++) {
						var cell = {
							field : nameList[i],
							title : nameList[i],
							width : 100
						};
						if (nameList[i] == 'ID') {
							cell.editor = {
								type : 'text'
							};
							cell.hidden = true;
						} else if (i == 0) {
							cell.editor = {
								type : 'CommonSelector',
								options : {
									selectType : 'user',
									dataGridId : 'dgExportExcel',
									dialogShowField : nameList[i],
									width : 200,
									callBackFun : function(index, d) {
										var userId = d.userId;
										var userName = d.userName;
										setGridData(userId, userName, index,
												nameList);
									},
									selectCount : 10,
									splitChar : ';'
								}
							};
						} else {
							cell.editor = {
								type : 'text'
							};
						}
						columns.push(cell);

					}
					var r = {};
					temp.datagrid({
						url : 'datagrid_data.json',
						columns : [ columns ]
					});
					temp.datagrid('appendRow', r);

					temp.datagrid('selectRow', 0).datagrid('beginEdit', 0);
				}
			});

	this._indexEditing = 0;

};

function setGridData(userId, userName, index, nameList) {
	var grid = $('#dgExportExcel');
	var names = userName.split(';');
	for ( var i = 0; i < nameList.length; i++) {
		var ed = grid.datagrid('getEditor', {
			index : index,
			field : nameList[i]
		});
		if (nameList[i] == 'ID') {
			$(ed.target).val(userId);
		} else if (i < names.length) {
			$(ed.target).val(names[i]);
			$(ed.target).find('input').val(names[i]);
		}
	}
}
// 保存功能
ExportExcel.prototype.save = function() {
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
						msg : r.er
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
ExportExcel.prototype.edit = function() {
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

};

// 删除
ExportExcel.prototype.del = function() {
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
				_self.datagrid('deleteRow', rows);
			}
		});
	} else {
		$.messager.alert('提示', '请选择要删除的记录！', 'warning');
	}
};

// 结束当前编辑行
ExportExcel.prototype.endEdit = function() {
	if (this._indexEditing === -1)
		return true;
	this._isEndEdit = false;
	this._datagrid.datagrid('endEdit', this._indexEditing);
	return this._isEndEdit;
};

// 打开查询框
ExportExcel.prototype.openSearchForm = function() {
	this.searchDialog.dialog('open', true);
};
// 去后台查询
ExportExcel.prototype.searchData = function() {
	this._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid(
			'clearSelections');
	this._datagrid.datagrid('load', {
		param : JSON.stringify(serializeObject(this.searchForm))
	});
};
// 隐藏查询框
ExportExcel.prototype.hideSearchForm = function() {
	this.searchDialog.dialog('close', true);
};
// 清空查询条件
ExportExcel.prototype.clearData = function() {
	this.searchForm.find('input').val('');
	this.searchData();
};

ExportExcel.prototype.formate = function(value) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd");
	}
	return '';
};
ExportExcel.prototype.formateDateTime = function(value) {
	if (value) {
		return new Date(value).format("yyyy-MM-dd hh:mm:ss");
	}
	return '';
};
ExportExcel.prototype.searchDataBySfn = function(conditions) {
	this._datagrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid(
			'clearSelections');
	this._datagrid.datagrid('load', conditions);
};
/*
 * ExportExcel.prototype.gender = [];
 * Platform6.fn.lookup.getLookupType('PLATFORM_SEX', function(r) { r &&
 * (ExportExcel.prototype.gender = r); }); ExportExcel.prototype.status = [];
 * Platform6.fn.lookup.getLookupType('PLATFORM_VALID_FLAG', function(r) { r &&
 * (ExportExcel.prototype.status = r); });
 */
// Excel客户端导出
ExportExcel.prototype.exportClientData = function() {
	$('#dgExportExcel').datagrid('endEdit', 0);
	var userId = ($('#dgExportExcel').datagrid('getRows')[0].ID).split(';');
	console.info(userId);
	var _self = this;
	$.messager.confirm(
					'确认',
					'是否确认导出Excel文件?',
					function(r) {
						if (r) { // 封装参数
							var columnFieldsOptions = getGridColumnFieldsOptions('dgExportExcel');
							var dataGridFields = JSON
									.stringify(columnFieldsOptions[0]);
							// 获得datagrid中的数据，getChecked：导出选中的数据；getRows:导出所有行数据
							var rows = $('#dgExportExcel').datagrid(
									'getChecked');
							// alert(rows.ID);
							var datas = JSON.stringify(rows);
							var myParams = {
								dataGridFields : dataGridFields,// 表头信息集合
								datas : datas,// 需要导出的数据
								hasRowNum : true,// 默认为true:代表第一列为序号
								sheetName : 'sheet1',// 如果该参数为空，默认为导出的Excel文件名
								unContainFields : 'ID',// 不需要导出的列，使用','分隔即可
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
