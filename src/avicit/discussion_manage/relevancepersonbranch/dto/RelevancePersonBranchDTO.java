package avicit.discussion_manage.relevancepersonbranch.dto;

import javax.persistence.Id;
//import javax.persistence.Transient;

import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;

/**
 * bean RelevancePersonBranch Title: 表RELEVANCE_PERSON_BRANCH的PoJo类，也就是数据库映射类
 * Description: 关联人员表 Copyriht: Copyright (c) 2012 Company: AVICIT Co., Ltd
 * 
 * @author AVICIT DEV
 * @version 1.0 Date: 2017-08-07 09:18
 * 
 */
@PojoRemark(table = "relevance_person_branch", object = "RelevancePersonBranchDTO", name = "关联人员表")
public class RelevancePersonBranchDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;

	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "ID")
	private java.lang.String id;

	@FieldRemark(column = "pid", field = "pid", name = "关联ID")
	private java.lang.String pid;
	
	private java.lang.String name;


	@FieldRemark(column = "login_name", field = "loginName", name = "登录名")
	private java.lang.String loginName;

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

	@FieldRemark(column = "vpm_login_name", field = "vpmLoginName", name = "VPM登录名")
	private java.lang.String vpmLoginName;

	public java.lang.String getId() {
		return id;
	}

	public void setId(java.lang.String id) {
		this.id = id;
	}

	public java.lang.String getPid() {
		return pid;
	}

	public void setPid(java.lang.String pid) {
		this.pid = pid;
	}

	public java.lang.String getLoginName() {
		return loginName;
	}

	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
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
	

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getVpmLoginName() {
		return vpmLoginName;
	}

	public void setVpmLoginName(java.lang.String vpmLoginName) {
		this.vpmLoginName = vpmLoginName;
	}

	public String getLogFormName() {
		if (super.logFormName == null || super.logFormName.equals("")) {
			return "关联人员表";
		} else {
			return super.logFormName;
		}
	}

	public String getLogTitle() {
		if (super.logTitle == null || super.logTitle.equals("")) {
			return "关联人员表";
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