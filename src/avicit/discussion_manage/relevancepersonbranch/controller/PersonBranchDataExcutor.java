package avicit.discussion_manage.relevancepersonbranch.controller;

import avicit.platform6.core.excel.imp.entity.ExcelHeader;
import avicit.platform6.core.excel.imp.executor.AbstractExcutor;

public class PersonBranchDataExcutor extends AbstractExcutor {
	private String name;

	public PersonBranchDataExcutor(String name) {
		this.name = name;
		this.initHeader();
	}

	private void initHeader() {
		
		headers.add(new ExcelHeader("name", "姓名"));
		headers.add(new ExcelHeader("loginName", "登录名"));
		headers.add(new ExcelHeader("vpmLoginName", "VPM登录名"));
		
	}

	public void addHeader(ExcelHeader header) {
		headers.add(header);
	}

	@Override
	public String getName() {

		return name;
	}

}
