package avicit.discussion_manage.structuremanage.controller;

import avicit.platform6.core.excel.imp.entity.ExcelHeader;
import avicit.platform6.core.excel.imp.executor.AbstractExcutor;

public class majorExcutor extends AbstractExcutor {
	private String name;
	
	public majorExcutor(String name) {
		this.name = name;
		this.initHeader();
	}

	private void initHeader() {
		
		headers.add(new ExcelHeader("itemCode", "零件编号"));
		headers.add(new ExcelHeader("intensity", "强度"));
		headers.add(new ExcelHeader("corrector", "校对"));
		headers.add(new ExcelHeader("quality", "质审"));
		headers.add(new ExcelHeader("reliable", "可靠性"));
		headers.add(new ExcelHeader("standard", "标审"));
		headers.add(new ExcelHeader("material", "材料(所区)"));
		headers.add(new ExcelHeader("normalized", "标准件"));
		headers.add(new ExcelHeader("weight", "重量"));
		headers.add(new ExcelHeader("isvci", "是否VCI"));
	}

	public void addHeader(ExcelHeader header) {
		headers.add(header);
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
