package avicit.discussion_manage.discussionmanage.dto;

import javax.persistence.Id;
//import javax.persistence.Transient;

import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;

/**
 * bean DiscussionManage Title: 表DISCUSSION_MANAGE的PoJo类，也就是数据库映射类 Description:
 * 讨论区管理 Copyriht: Copyright (c) 2012 Company: AVICIT Co., Ltd
 * 
 * @author AVICIT DEV
 * @version 1.0 Date: 2017-07-10 17:34
 * 
 */
@PojoRemark(table = "discussion_manage", object = "DiscussionManageDTO", name = "讨论区管理")
public class DiscussionManageDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;
	//关联查询零件表所需字段
	private java.lang.String className;
	private java.lang.String classCode;
	private java.lang.String strStatus;
	private java.lang.String edition;
	private java.lang.String designerId;
	//查询部门人员所需字段
	private java.lang.String workshopId;
	private java.lang.String dutyUserIds;
	
	public java.lang.String getWorkshopId() {
		return workshopId;
	}

	public void setWorkshopId(java.lang.String workshopId) {
		this.workshopId = workshopId;
	}

	public java.lang.String getDutyUserIds() {
		return dutyUserIds;
	}

	public void setDutyUserIds(java.lang.String dutyUserIds) {
		this.dutyUserIds = dutyUserIds;
	}

	public java.lang.String getClassName() {
		return className;
	}

	public void setClassName(java.lang.String className) {
		this.className = className;
	}

	public java.lang.String getClassCode() {
		return classCode;
	}

	public void setClassCode(java.lang.String classCode) {
		this.classCode = classCode;
	}

	public java.lang.String getStrStatus() {
		return strStatus;
	}

	public void setStrStatus(java.lang.String strStatus) {
		this.strStatus = strStatus;
	}

	public java.lang.String getEdition() {
		return edition;
	}

	public void setEdition(java.lang.String edition) {
		this.edition = edition;
	}

	public java.lang.String getDesignerId() {
		return designerId;
	}

	public void setDesignerId(java.lang.String designerId) {
		this.designerId = designerId;
	}

	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "讨论区ID")
	private java.lang.String id;
	@LogField
	@FieldRemark(column = "str_id", field = "strId", name = "装配结构ID")
	private java.lang.String strId;
	@LogField
	@FieldRemark(column = "status", field = "status", name = "讨论区状态")
	private java.lang.String status;
	@LogField
	@FieldRemark(column = "tnm_code", field = "tnmCode", name = "讨论区编号")
	private java.lang.String tnmCode;

	@FieldRemark(column = "attribute_01", field = "attribute01", name = "扩展字段01")
	private java.lang.String attribute01;

	@FieldRemark(column = "attribute_02", field = "attribute02", name = "扩展字段02")
	private java.lang.String attribute02;

	@FieldRemark(column = "attribute_03", field = "attribute03", name = "扩展字段03")
	private java.lang.String attribute03;

	@FieldRemark(column = "attribute_04", field = "attribute04", name = "扩展字段04")
	private java.lang.String attribute04;

	@FieldRemark(column = "attribute_05", field = "attribute05", name = "扩展字段05")
	private java.lang.String attribute05;

	@FieldRemark(column = "attribute_06", field = "attribute06", name = "扩展字段06")
	private java.lang.String attribute06;

	@FieldRemark(column = "attribute_07", field = "attribute07", name = "扩展字段07")
	private java.util.Date attribute07;

	private java.util.Date attribute07Begin;

	private java.util.Date attribute07End;

	@FieldRemark(column = "attribute_08", field = "attribute08", name = "扩展字段08")
	private java.util.Date attribute08;

	private java.util.Date attribute08Begin;

	private java.util.Date attribute08End;

	@FieldRemark(column = "attribute_09", field = "attribute09", name = "扩展字段09")
	private Long attribute09;

	@FieldRemark(column = "attribute_10", field = "attribute10", name = "扩展字段10")
	private Long attribute10;
	@LogField
	@FieldRemark(column = "create_date", field = "createDate", name = "讨论区创建时间")
	private java.util.Date createDate;

	private java.util.Date createDateBegin;

	private java.util.Date createDateEnd;

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

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getTnmCode() {
		return tnmCode;
	}

	public void setTnmCode(java.lang.String tnmCode) {
		this.tnmCode = tnmCode;
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

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getCreateDateBegin() {
		return createDateBegin;
	}

	public void setCreateDateBegin(java.util.Date createDateBegin) {
		this.createDateBegin = createDateBegin;
	}

	public java.util.Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(java.util.Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getLogFormName() {
		if (super.logFormName == null || super.logFormName.equals("")) {
			return "讨论区管理";
		} else {
			return super.logFormName;
		}
	}

	public String getLogTitle() {
		if (super.logTitle == null || super.logTitle.equals("")) {
			return "讨论区管理";
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