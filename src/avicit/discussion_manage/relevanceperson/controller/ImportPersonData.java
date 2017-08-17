package avicit.discussion_manage.relevanceperson.controller;

import org.springframework.stereotype.Component;

import avicit.platform6.core.excel.imp.inf.IExcelImportUrl;

@Component(value = "importPersonData")
public class ImportPersonData implements IExcelImportUrl {

	@Override
	public String getUrl() {
		return "platform/discussion_manage/relevanceperson/RelevancePersonController/operation/import";
	}

}
