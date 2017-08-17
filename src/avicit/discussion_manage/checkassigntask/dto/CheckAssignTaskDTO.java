package avicit.discussion_manage.checkassigntask.dto;

import javax.persistence.Id;
//import javax.persistence.Transient;

import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;

/**
 * bean CheckAssignTask Title: 表CHECK_ASSIGN_TASK的PoJo类，也就是数据库映射类 Description:
 * 审查分工待办表 Copyriht: Copyright (c) 2012 Company: AVICIT Co., Ltd
 * 
 * @author AVICIT DEV
 * @version 1.0 Date: 2017-07-28 11:26
 * 
 */
@PojoRemark(table = "check_assign_task", object = "CheckAssignTaskDTO", name = "审查分工待办表")
public class CheckAssignTaskDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;

	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "ID")
	private java.lang.String id;

	@FieldRemark(column = "task_code", field = "taskCode", name = "任务编号")
	private java.lang.String taskCode;

	@FieldRemark(column = "task_title", field = "taskTitle", name = "任务标题")
	private java.lang.String taskTitle;

	@FieldRemark(column = "task_type", field = "taskType", name = "任务类型()")
	private java.lang.String taskType;

	@FieldRemark(column = "structure_id", field = "structureId", name = "零部件ID")
	private java.lang.String structureId;
	
	private java.lang.String classCode;

	@FieldRemark(column = "task_table", field = "taskTable", name = "任务表名或任务表编号")
	private java.lang.String taskTable;

	@FieldRemark(column = "form_id", field = "formId", name = "业务表ID(主业务表+副业务表)")
	private java.lang.String formId;

	@FieldRemark(column = "sponsor_user_id", field = "sponsorUserId", name = "待办发起人ID")
	private java.lang.String sponsorUserId;

	@FieldRemark(column = "sponsor_user_name", field = "sponsorUserName", name = "待办发起人姓名")
	private java.lang.String sponsorUserName;

	@FieldRemark(column = "task_user_id", field = "taskUserId", name = "待办人ID")
	private java.lang.String taskUserId;

	@FieldRemark(column = "task_user_name", field = "taskUserName", name = "待办人姓名")
	private java.lang.String taskUserName;

	@FieldRemark(column = "task_url", field = "taskUrl", name = "待办URL")
	private java.lang.String taskUrl;

	@FieldRemark(column = "task_isok", field = "taskIsok", name = "是否办理(0: 待办理 1:已办理)")
	private java.lang.String taskIsok;

	@FieldRemark(column = "valid_flag", field = "validFlag", name = "有效性 通用代码PLATFORM_VALID_FLAG")
	private java.lang.String validFlag;
	
	private java.lang.String sendDate;

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

	public java.lang.String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(java.lang.String taskCode) {
		this.taskCode = taskCode;
	}

	public java.lang.String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(java.lang.String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public java.lang.String getTaskType() {
		return taskType;
	}

	public void setTaskType(java.lang.String taskType) {
		this.taskType = taskType;
	}

	public java.lang.String getStructureId() {
		return structureId;
	}

	public void setStructureId(java.lang.String structureId) {
		this.structureId = structureId;
	}

	public java.lang.String getClassCode() {
		return classCode;
	}

	public void setClassCode(java.lang.String classCode) {
		this.classCode = classCode;
	}

	public java.lang.String getTaskTable() {
		return taskTable;
	}

	public void setTaskTable(java.lang.String taskTable) {
		this.taskTable = taskTable;
	}

	public java.lang.String getFormId() {
		return formId;
	}

	public void setFormId(java.lang.String formId) {
		this.formId = formId;
	}

	public java.lang.String getSponsorUserId() {
		return sponsorUserId;
	}

	public void setSponsorUserId(java.lang.String sponsorUserId) {
		this.sponsorUserId = sponsorUserId;
	}

	public java.lang.String getSponsorUserName() {
		return sponsorUserName;
	}

	public void setSponsorUserName(java.lang.String sponsorUserName) {
		this.sponsorUserName = sponsorUserName;
	}

	public java.lang.String getTaskUserId() {
		return taskUserId;
	}

	public void setTaskUserId(java.lang.String taskUserId) {
		this.taskUserId = taskUserId;
	}

	public java.lang.String getTaskUserName() {
		return taskUserName;
	}

	public void setTaskUserName(java.lang.String taskUserName) {
		this.taskUserName = taskUserName;
	}

	public java.lang.String getTaskUrl() {
		return taskUrl;
	}

	public void setTaskUrl(java.lang.String taskUrl) {
		this.taskUrl = taskUrl;
	}

	public java.lang.String getTaskIsok() {
		return taskIsok;
	}

	public void setTaskIsok(java.lang.String taskIsok) {
		this.taskIsok = taskIsok;
	}

	public java.lang.String getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(java.lang.String validFlag) {
		this.validFlag = validFlag;
	}
	
	public java.lang.String getSendDate() {
		return sendDate;
	}

	public void setSendDate(java.lang.String sendDate) {
		this.sendDate = sendDate;
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

	public String getLogFormName() {
		if (super.logFormName == null || super.logFormName.equals("")) {
			return "审查分工待办表";
		} else {
			return super.logFormName;
		}
	}

	public String getLogTitle() {
		if (super.logTitle == null || super.logTitle.equals("")) {
			return "审查分工待办表";
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