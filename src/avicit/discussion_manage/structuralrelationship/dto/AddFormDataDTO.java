package avicit.discussion_manage.structuralrelationship.dto;

import avicit.platform6.core.domain.BeanDTO;

public class AddFormDataDTO extends BeanDTO {

	private static final long serialVersionUID = 1L;

	private String tabId;
	
	private String pId;

	private String id;

	private String addType;

	private String className;

	private String classCode;

	private String designerId;
	
	private String designerName;

	private String classType;
	
	private Long   version;
	
	private String   status;
	
	private String   edition;
	
	private String returnInt;
	
	private String returnString;

	
	public String getTabId() {
		return tabId;
	}

	public void setTabId(String tabId) {
		this.tabId = tabId;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddType() {
		return addType;
	}

	public void setAddType(String addType) {
		this.addType = addType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	public String getDesignerId() {
		return designerId;
	}

	public void setDesignerId(String designerId) {
		this.designerId = designerId;
	}
	
	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getReturnInt() {
		return returnInt;
	}

	public void setReturnInt(String returnInt) {
		this.returnInt = returnInt;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}

	@Override
	public String getLogFormName() {
		return "零件表添加数据";
	}

	@Override
	public String getLogTitle() {
		return "数据表单";
	}

}
