package avicit.discussion_manage.processrepresentative.dto;

import javax.persistence.Id;
//import javax.persistence.Transient;

import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;

/**
 * bean ProcessRepresentative Title: 表PROCESS_REPRESENTATIVE的PoJo类，也就是数据库映射类
 * Description: 工艺代表任务表 Copyriht: Copyright (c) 2012 Company: AVICIT Co., Ltd
 * 
 * @author AVICIT DEV
 * @version 1.0 Date: 2017-06-16 17:41
 * 
 */
@PojoRemark(table = "process_representative", object = "ProcessRepresentativeDTO", name = "工艺代表任务表")
public class ProcessRepresentativeDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;

	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "ID     ")
	private java.lang.String id;
	@LogField
	@FieldRemark(column = "str_id", field = "strId", name = "装配结构ID")
	private java.lang.String strId;
	private java.lang.String strCode;
	@LogField
	@FieldRemark(column = "task_type", field = "taskType", name = "任务类型")
	private java.lang.String taskType;
	@LogField
	@FieldRemark(column = "duty_user_id", field = "dutyUserId", name = "责任人")
	private java.lang.String dutyUserId;
	
	private java.lang.String dutyUserName;
	
	
	@LogField
	@FieldRemark(column = "process_user_id", field = "processUserId", name = "工艺分工人员")
	private java.lang.String processUserId;
	private java.lang.String processUserIdAlias;
	@LogField
	@FieldRemark(column = "publication_time", field = "publicationTime", name = "发出时间")
	private java.util.Date publicationTime;

	private java.util.Date publicationTimeBegin;

	private java.util.Date publicationTimeEnd;
	@LogField
	@FieldRemark(column = "status", field = "status", name = "状态（是否已分配分工人员） ")
	private java.lang.String status;

	@FieldRemark(column = "attribute_01", field = "attribute01", name = "预留字段1")
	private java.lang.String attribute01;

	@FieldRemark(column = "attribute_02", field = "attribute02", name = "预留字段2")
	private java.lang.String attribute02;

	@FieldRemark(column = "attribute_03", field = "attribute03", name = "预留字段3")
	private java.lang.String attribute03;

	@FieldRemark(column = "attribute_04", field = "attribute04", name = "预留字段4")
	private java.lang.String attribute04;

	@FieldRemark(column = "attribute_05", field = "attribute05", name = "预留字段5")
	private java.lang.String attribute05;

	@FieldRemark(column = "attribute_06", field = "attribute06", name = "预留字段6")
	private java.lang.String attribute06;

	@FieldRemark(column = "attribute_07", field = "attribute07", name = "预留字段7")
	private java.util.Date attribute07;

	private java.util.Date attribute07Begin;

	private java.util.Date attribute07End;

	@FieldRemark(column = "attribute_08", field = "attribute08", name = "预留字段8")
	private java.util.Date attribute08;

	private java.util.Date attribute08Begin;

	private java.util.Date attribute08End;

	@FieldRemark(column = "attribute_09", field = "attribute09", name = "预留字段9")
	private Long attribute09;

	@FieldRemark(column = "attribute_10", field = "attribute10", name = "预留字段10")
	private Long attribute10;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getStrId() {
		return strId;
	}

	public void setStrId(java.lang.String strId) {
		this.strId = strId;
	}
	public java.lang.String getStrCode() {
		return strCode;
	}

	public void setStrCode(java.lang.String strCode) {
		this.strCode = strCode;
	}

	public java.lang.String getTaskType() {
		return taskType;
	}

	public void setTaskType(java.lang.String taskType) {
		this.taskType = taskType;
	}

	public java.lang.String getProcessUserId() {
		return processUserId;
	}

	public void setProcessUserId(java.lang.String processUserId) {
		this.processUserId = processUserId;
	}

	public java.lang.String getProcessUserIdAlias() {
		return processUserIdAlias;
	}

	public void setProcessUserIdAlias(java.lang.String processUserIdAlias) {
		this.processUserIdAlias = processUserIdAlias;
	}

	public java.util.Date getPublicationTime() {
		return publicationTime;
	}

	public void setPublicationTime(java.util.Date publicationTime) {
		this.publicationTime = publicationTime;
	}

	public java.util.Date getPublicationTimeBegin() {
		return publicationTimeBegin;
	}

	public void setPublicationTimeBegin(java.util.Date publicationTimeBegin) {
		this.publicationTimeBegin = publicationTimeBegin;
	}

	public java.util.Date getPublicationTimeEnd() {
		return publicationTimeEnd;
	}

	public void setPublicationTimeEnd(java.util.Date publicationTimeEnd) {
		this.publicationTimeEnd = publicationTimeEnd;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getAttribute01() {
		return attribute01;
	}

	public void setAttribute01(java.lang.String attribute01) {
		this.attribute01 = attribute01;
	}

	public java.lang.String getAttribute02() {
		return attribute02;
	}

	public void setAttribute02(java.lang.String attribute02) {
		this.attribute02 = attribute02;
	}

	public java.lang.String getAttribute03() {
		return attribute03;
	}

	public void setAttribute03(java.lang.String attribute03) {
		this.attribute03 = attribute03;
	}

	public java.lang.String getAttribute04() {
		return attribute04;
	}

	public void setAttribute04(java.lang.String attribute04) {
		this.attribute04 = attribute04;
	}

	public java.lang.String getAttribute05() {
		return attribute05;
	}

	public void setAttribute05(java.lang.String attribute05) {
		this.attribute05 = attribute05;
	}

	public java.lang.String getAttribute06() {
		return attribute06;
	}

	public void setAttribute06(java.lang.String attribute06) {
		this.attribute06 = attribute06;
	}

	public java.util.Date getAttribute07() {
		return attribute07;
	}

	public void setAttribute07(java.util.Date attribute07) {
		this.attribute07 = attribute07;
	}

	public java.util.Date getAttribute07Begin() {
		return attribute07Begin;
	}

	public void setAttribute07Begin(java.util.Date attribute07Begin) {
		this.attribute07Begin = attribute07Begin;
	}

	public java.util.Date getAttribute07End() {
		return attribute07End;
	}

	public void setAttribute07End(java.util.Date attribute07End) {
		this.attribute07End = attribute07End;
	}

	public java.util.Date getAttribute08() {
		return attribute08;
	}

	public void setAttribute08(java.util.Date attribute08) {
		this.attribute08 = attribute08;
	}

	public java.util.Date getAttribute08Begin() {
		return attribute08Begin;
	}

	public void setAttribute08Begin(java.util.Date attribute08Begin) {
		this.attribute08Begin = attribute08Begin;
	}

	public java.util.Date getAttribute08End() {
		return attribute08End;
	}

	public void setAttribute08End(java.util.Date attribute08End) {
		this.attribute08End = attribute08End;
	}

	public Long getAttribute09() {
		return attribute09;
	}

	public void setAttribute09(Long attribute09) {
		this.attribute09 = attribute09;
	}

	public Long getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(Long attribute10) {
		this.attribute10 = attribute10;
	}
	public java.lang.String getDutyUserId() {
		return dutyUserId;
	}

	public void setDutyUserId(java.lang.String dutyUserId) {
		this.dutyUserId = dutyUserId;
	}
	public java.lang.String getDutyUserName() {
		return dutyUserName;
	}

	public void setDutyUserName(java.lang.String dutyUserName) {
		this.dutyUserName = dutyUserName;
	}

	public String getLogFormName() {
		if (super.logFormName == null || super.logFormName.equals("")) {
			return "工艺代表任务表";
		} else {
			return super.logFormName;
		}
	}

	public String getLogTitle() {
		if (super.logTitle == null || super.logTitle.equals("")) {
			return "工艺代表任务表";
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