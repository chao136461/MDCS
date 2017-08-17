package avicit.discussion_manage.majormanage.dto;

import javax.persistence.Id;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;

/**
 * bean InstituteCensorManage Title: 表INSTITUTE_CENSOR_MANAGE的PoJo类，也就是数据库映射类
 * Description: 所区审查人员管理 Copyriht: Copyright (c) 2012 Company: AVICIT Co., Ltd
 * 
 * @author AVICIT DEV
 * @version 1.0 Date: 2017-06-15 14:17
 * 
 */
@PojoRemark(table = "institute_censor_manage", object = "InstituteCensorManageDTO", name = "所区审查人员管理")
public class InstituteCensorManageDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;
	private java.lang.String strName;
	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "主键ID         ")
	private java.lang.String id;
	@LogField
	@FieldRemark(column = "str_id", field = "strId", name = "装配结构ID")
	private java.lang.String strId;
	@LogField
	@FieldRemark(column = "maj_id", field = "majId", name = "所区专业表ID")
	private java.lang.String majId;
	@LogField
	@FieldRemark(column = "status", field = "status", name = "状态(审查确认)")
	private java.lang.String status;

	@FieldRemark(column = "duty_user_role", field = "dutyUserRole", name = "角色")
	private java.lang.String dutyUserRole;
	private java.lang.String dutyUserRoleAlias;

	@FieldRemark(column = "duty_user_id", field = "dutyUserId", name = "审查人员")
	private java.lang.String dutyUserId;
	private java.lang.String dutyUserIdAlias;

	@FieldRemark(column = "duty_dept_id", field = "duty DeptId", name = "审查人员所在部门")
	private java.lang.String dutyDeptId;
	private java.lang.String dutyDeptIdAlias;

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

	public java.lang.String getMajId() {
		return majId;
	}

	public void setMajId(java.lang.String majId) {
		this.majId = majId;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getDutyUserRole() {
		return dutyUserRole;
	}

	public void setDutyUserRole(java.lang.String dutyUserRole) {
		this.dutyUserRole = dutyUserRole;
	}

	public java.lang.String getDutyUserRoleAlias() {
		return dutyUserRoleAlias;
	}

	public void setDutyUserRoleAlias(java.lang.String dutyUserRoleAlias) {
		this.dutyUserRoleAlias = dutyUserRoleAlias;
	}

	public java.lang.String getDutyUserId() {
		return dutyUserId;
	}

	public void setDutyUserId(java.lang.String dutyUserId) {
		this.dutyUserId = dutyUserId;
	}

	public java.lang.String getDutyUserIdAlias() {
		return dutyUserIdAlias;
	}

	public void setDutyUserIdAlias(java.lang.String dutyUserIdAlias) {
		this.dutyUserIdAlias = dutyUserIdAlias;
	}

	public java.lang.String getDutyDeptId() {
		return dutyDeptId;
	}

	public void setDutyDeptId(java.lang.String dutyDeptId) {
		this.dutyDeptId = dutyDeptId;
	}

	public java.lang.String getDutyDeptIdAlias() {
		return dutyDeptIdAlias;
	}

	public void setDutyDeptIdAlias(java.lang.String dutyDeptIdAlias) {
		this.dutyDeptIdAlias = dutyDeptIdAlias;
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
	
	
	public java.lang.String getStrName() {
		return strName;
	}

	public void setStrName(java.lang.String strName) {
		this.strName = strName;
	}

	public String getLogFormName() {
		if (super.logFormName == null || super.logFormName.equals("")) {
			return "所区审查人员管理";
		} else {
			return super.logFormName;
		}
	}

	public String getLogTitle() {
		if (super.logTitle == null || super.logTitle.equals("")) {
			return "所区审查人员管理";
		} else {
			return super.logTitle;
		}
	}

	// @Transient
	public LogType getLogType() {
		if (super.logType == null || super.logType.equals("")) {
			return LogType.module_operate;
		} else {
			return super.logType;
		}
	}

}