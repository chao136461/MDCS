package avicit.discussion_manage.structuremanage.controller;

import avicit.platform6.core.excel.imp.entity.ExcelHeader;
import avicit.platform6.core.excel.imp.executor.AbstractExcutor;

public class FactoryComplexExcutor extends AbstractExcutor {
	private String name;

	public FactoryComplexExcutor(String name) {
		this.name = name;
		this.initHeader();
	}

	private void initHeader() {
		headers.add(new ExcelHeader("part", "零件"));
		headers.add(new ExcelHeader("assembly", "装配"));
		headers.add(new ExcelHeader("material", "材料"));
		headers.add(new ExcelHeader("metallurgy", "冶金"));
		headers.add(new ExcelHeader("tooling", "工装"));
		headers.add(new ExcelHeader("lofting", "模线"));
		headers.add(new ExcelHeader("standardization", "标准化"));
		headers.add(new ExcelHeader("procurement", "采购"));
		headers.add(new ExcelHeader("verification", "检验"));
	}

	public void addHeader(ExcelHeader header) {
		headers.add(header);
	}

	@Override
	public String getName() {

		return name;
	}

}
