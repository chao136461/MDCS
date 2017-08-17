package avicit.discussion_manage.proposalmanage.dto;

import javax.persistence.Id;
//import javax.persistence.Transient;

import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;

/**
 * bean ProposalManage Title: 表PROPOSAL_MANAGE的PoJo类，也就是数据库映射类 Description: 建议管理
 * Copyriht: Copyright (c) 2012 Company: AVICIT Co., Ltd
 * 
 * @author AVICIT DEV
 * @version 1.0 Date: 2017-07-18 13:48
 * 
 */
@PojoRemark(table = "proposal_manage", object = "ProposalManageDTO", name = "建议管理")
public class ProposalManageDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;
	private java.lang.Integer  nums;
	

	@Id
	@LogField
	@FieldRemark(column = "id", field = "id", name = "ID       ")
	private java.lang.String id;
	@LogField
	@FieldRemark(column = "str_id", field = "strId", name = "装配结构ID")
	private java.lang.String strId;
	@LogField
	@FieldRemark(column = "parent_id", field = "parentId", name = "父类建议ID")
	private java.lang.String parentId;
	@LogField
	@FieldRemark(column = "proposal_code", field = "proposalCode", name = "建议编号")
	private java.lang.String proposalCode;
	@LogField
	@FieldRemark(column = "proposal_name", field = "proposalName", name = "建议标题")
	private java.lang.String proposalName;
	@LogField
	@FieldRemark(column = "proposal_main", field = "proposalMain", name = "建议正文")
	private java.lang.String proposalMain;

	@FieldRemark(column = "status", field = "status", name = "状态")
	private java.lang.String status;
	private java.lang.String strCode;
	@FieldRemark(column = "publication_time", field = "publicationTime", name = "建议发表时间      ")
	private java.util.Date publicationTime;

	private java.util.Date publicationTimeBegin;

	private java.util.Date publicationTimeEnd;

	@FieldRemark(column = "proposal_user_id", field = "proposalUserId", name = "建议提出人")
	private java.lang.String proposalUserId;
	private java.lang.String proposalUserIdAlias;

	@FieldRemark(column = "proposal_user_dept", field = "proposalUserDept", name = "建议提出人部门（专业）")
	private java.lang.String proposalUserDept;
	private java.lang.String proposalUserDeptAlias;
	private java.lang.String deptName;
	@FieldRemark(column = "is_dispute", field = "isDispute", name = "是否有争议")
	private java.lang.String isDispute;

	@FieldRemark(column = "is_approval", field = "isApproval", name = "320厂是否批准建议（320厂默认否）")
	private java.lang.String isApproval;

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

	@FieldRemark(column = "replier_user_id", field = "replierUserId", name = "回复人ID")
	private java.lang.String replierUserId;
	private java.lang.String replierUserIdAlias;

	@FieldRemark(column = "proposed_type", field = "proposedType", name = "建议状态")
	private java.lang.String proposedType;

	@FieldRemark(column = "picture", field = "picture", name = "图片")
	private java.lang.String picture;
	@FieldRemark(column = "praise", field = "praise", name = "赞")
	private java.lang.String praise;
	@FieldRemark(column = "complex", field = "complex", name = "厂所")
	private java.lang.String complex;
	@FieldRemark(column = "mobile", field = "mobile", name = "手机")
	private java.lang.String mobile;
	@FieldRemark(column = "officeTel", field = "officeTel", name = "座机")
	private java.lang.String officeTel;
	@FieldRemark(column = "deptNameIds", field = "deptNameIds", name = "部门名称串")
	private java.lang.String deptNameIds;
	private java.lang.String serdeptNames;
	private java.lang.String proposalColor;
	
	public java.lang.String getStrCode() {
		return strCode;
	}

	public void setStrCode(java.lang.String strCode) {
		this.strCode = strCode;
	}

	public java.lang.String getProposalColor() {
		return proposalColor;
	}

	public void setProposalColor(java.lang.String proposalColor) {
		this.proposalColor = proposalColor;
	}

	public java.lang.String getSerdeptNames() {
		return serdeptNames;
	}

	public void setSerdeptNames(java.lang.String serdeptNames) {
		this.serdeptNames = serdeptNames;
	}

	
	public java.lang.String getComplex() {
		return complex;
	}

	public void setComplex(java.lang.String complex) {
		this.complex = complex;
	}

	public java.lang.String getDeptNameIds() {
		return deptNameIds;
	}

	public void setDeptNameIds(java.lang.String deptNameIds) {
		this.deptNameIds = deptNameIds;
	}

	public java.lang.String getPraise() {
		return praise;
	}

	public void setPraise(java.lang.String praise) {
		this.praise = praise;
	}

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

	public java.lang.String getParentId() {
		return parentId;
	}

	public void setParentId(java.lang.String parentId) {
		this.parentId = parentId;
	}

	public java.lang.String getProposalCode() {
		return proposalCode;
	}

	public void setProposalCode(java.lang.String proposalCode) {
		this.proposalCode = proposalCode;
	}

	public java.lang.String getProposalName() {
		return proposalName;
	}

	public void setProposalName(java.lang.String proposalName) {
		this.proposalName = proposalName;
	}

	public java.lang.String getProposalMain() {
		return proposalMain;
	}

	public void setProposalMain(java.lang.String proposalMain) {
		this.proposalMain = proposalMain;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
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

	public java.lang.String getProposalUserId() {
		return proposalUserId;
	}

	public void setProposalUserId(java.lang.String proposalUserId) {
		this.proposalUserId = proposalUserId;
	}

	public java.lang.String getProposalUserIdAlias() {
		return proposalUserIdAlias;
	}

	public void setProposalUserIdAlias(java.lang.String proposalUserIdAlias) {
		this.proposalUserIdAlias = proposalUserIdAlias;
	}

	public java.lang.String getProposalUserDept() {
		return proposalUserDept;
	}

	public void setProposalUserDept(java.lang.String proposalUserDept) {
		this.proposalUserDept = proposalUserDept;
	}

	public java.lang.String getProposalUserDeptAlias() {
		return proposalUserDeptAlias;
	}

	public void setProposalUserDeptAlias(java.lang.String proposalUserDeptAlias) {
		this.proposalUserDeptAlias = proposalUserDeptAlias;
	}

	public java.lang.String getIsDispute() {
		return isDispute;
	}

	public void setIsDispute(java.lang.String isDispute) {
		this.isDispute = isDispute;
	}

	public java.lang.String getIsApproval() {
		return isApproval;
	}

	public void setIsApproval(java.lang.String isApproval) {
		this.isApproval = isApproval;
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

	public java.lang.String getReplierUserId() {
		return replierUserId;
	}

	public void setReplierUserId(java.lang.String replierUserId) {
		this.replierUserId = replierUserId;
	}

	public java.lang.String getReplierUserIdAlias() {
		return replierUserIdAlias;
	}

	public void setReplierUserIdAlias(java.lang.String replierUserIdAlias) {
		this.replierUserIdAlias = replierUserIdAlias;
	}

	public java.lang.String getProposedType() {
		return proposedType;
	}

	public void setProposedType(java.lang.String proposedType) {
		this.proposedType = proposedType;
	}

	public java.lang.String getPicture() {
		return picture;
	}

	public void setPicture(java.lang.String picture) {
		this.picture = picture;
	}
	
	

	public java.lang.Integer getNums() {
		return nums;
	}

	public void setNums(java.lang.Integer nums) {
		this.nums = nums;
	}

	public String getLogFormName() {
		if (super.logFormName == null || super.logFormName.equals("")) {
			return "建议管理";
		} else {
			return super.logFormName;
		}
	}

	public String getLogTitle() {
		if (super.logTitle == null || super.logTitle.equals("")) {
			return "建议管理";
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

	public java.lang.String getDeptName() {
		return deptName;
	}

	public void setDeptName(java.lang.String deptName) {
		this.deptName = deptName;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(java.lang.String officeTel) {
		this.officeTel = officeTel;
	}
	
}