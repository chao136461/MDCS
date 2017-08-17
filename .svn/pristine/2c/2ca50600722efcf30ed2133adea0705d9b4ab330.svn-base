package avicit.discussion_manage.discussionmanage.dto;


import java.util.Date;

import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;


public class DiscussionInfoDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String keShiZhuanYe;
	
	private String keShiZhuanYeAlias;
	
	private String title;
	
	private String status;
	
	private String faBuPerson;
	
	private Date   date;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKeShiZhuanYe() {
		return keShiZhuanYe;
	}

	public void setKeShiZhuanYe(String keShiZhuanYe) {
		this.keShiZhuanYe = keShiZhuanYe;
	}

	public String getKeShiZhuanYeAlias() {
		return keShiZhuanYeAlias;
	}

	public void setKeShiZhuanYeAlias(String keShiZhuanYeAlias) {
		this.keShiZhuanYeAlias = keShiZhuanYeAlias;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFaBuPerson() {
		return faBuPerson;
	}

	public void setFaBuPerson(String faBuPerson) {
		this.faBuPerson = faBuPerson;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getLogFormName() {
		if (super.logFormName == null || super.logFormName.equals("")) {
			return "讨论区信息";
		} else {
			return super.logFormName;
		}
	}

	public String getLogTitle() {
		if (super.logTitle == null || super.logTitle.equals("")) {
			return "讨论区信息";
		} else {
			return super.logTitle;
		}
	}

	public LogType getLogType() {
		if (super.logType == null || super.logType.equals("")) {
			return LogType.module_operate;
		} else {
			return super.logType;
		}
	}

}