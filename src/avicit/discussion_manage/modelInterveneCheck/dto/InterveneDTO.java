package avicit.discussion_manage.modelInterveneCheck.dto;

import avicit.platform6.core.domain.BeanDTO;

public class InterveneDTO extends BeanDTO{

	private static final long serialVersionUID = 1L;

	private  String  id;
	
	private  String  strId;
	
	private  String  parentId;
	
	private  String  jixing;
	
	private  String  classCode;
	
	private  String  instanceNumber;
	
	private  String  checkTime;
	
	private  String  result;
	
	private  String  description;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStrId() {
		return strId;
	}

	public void setStrId(String strId) {
		this.strId = strId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getJixing() {
		return jixing;
	}

	public void setJixing(String jixing) {
		this.jixing = jixing;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getInstanceNumber() {
		return instanceNumber;
	}

	public void setInstanceNumber(String instanceNumber) {
		this.instanceNumber = instanceNumber;
	}

	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getLogFormName() {
		return "干涉表";
	}

	@Override
	public String getLogTitle() {
		return "干涉表";
	}
	
	
}
