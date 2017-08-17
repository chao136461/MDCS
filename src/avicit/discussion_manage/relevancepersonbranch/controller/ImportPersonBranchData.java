package avicit.discussion_manage.relevancepersonbranch.controller;

import org.springframework.stereotype.Component;

import avicit.platform6.core.excel.imp.inf.IExcelImportUrl;

@Component(value = "importPersonBranchData")
public class ImportPersonBranchData implements IExcelImportUrl {

	@Override
	public String getUrl() {
		return "platform/discussion_manage/relevancepersonbranch/RelevancePersonBranchController/operation/import";
	}

}
