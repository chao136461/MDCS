package avicit.discussion_manage.structuremanage.controller;

import org.springframework.stereotype.Component;

import avicit.platform6.core.excel.imp.inf.IExcelImportUrl;
@Component(value="importFactoryExcel")
public class ImportFactoryExcel implements IExcelImportUrl{

	@Override
	public String getUrl() {
		return "platform/discussion_manage/factorycomplex/FactoryComplexController/operation/factoryUser/import";
	}
	
}
