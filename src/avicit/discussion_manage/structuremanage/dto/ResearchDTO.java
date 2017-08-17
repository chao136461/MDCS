package avicit.discussion_manage.structuremanage.dto;

import javax.persistence.Id;
//import javax.persistence.Transient;

import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;

/**
 * bean StructureManage Title: 表STRUCTURE_MANAGE的PoJo类，也就是数据库映射类 Description:
 * 装配件结构管理 Copyriht: Copyright (c) 2012 Company: AVICIT Co., Ltd
 * 
 * @author AVICIT DEV
 * @version 1.0 Date: 2017-06-16 15:36
 * 
 */
public class ResearchDTO extends BeanDTO {
	private static final long serialVersionUID = 1L;
	private java.lang.String itemCode;//零件编号
	private java.lang.String intensity;//强度
	private java.lang.String corrector;//校对
	private java.lang.String quality;//质审
	private java.lang.String reliable;//可靠性
	private java.lang.String standard;//标审
	private java.lang.String material;//材料
	private java.lang.String normalized;//标准件
	private java.lang.String weight;//重量
	private java.lang.String isvci;//是否是vci
	public java.lang.String getItemCode() {
		return itemCode;
	}

	public void setItemCode(java.lang.String itemCode) {
		this.itemCode = itemCode;
	}

	public java.lang.String getIntensity() {
		return intensity;
	}

	public void setIntensity(java.lang.String intensity) {
		this.intensity = intensity;
	}

	public java.lang.String getCorrector() {
		return corrector;
	}

	public void setCorrector(java.lang.String corrector) {
		this.corrector = corrector;
	}

	public java.lang.String getQuality() {
		return quality;
	}

	public void setQuality(java.lang.String quality) {
		this.quality = quality;
	}

	public java.lang.String getReliable() {
		return reliable;
	}

	public void setReliable(java.lang.String reliable) {
		this.reliable = reliable;
	}

	public java.lang.String getStandard() {
		return standard;
	}

	public void setStandard(java.lang.String standard) {
		this.standard = standard;
	}

	public java.lang.String getMaterial() {
		return material;
	}

	public void setMaterial(java.lang.String material) {
		this.material = material;
	}

	public java.lang.String getNormalized() {
		return normalized;
	}

	public void setNormalized(java.lang.String normalized) {
		this.normalized = normalized;
	}

	public java.lang.String getWeight() {
		return weight;
	}

	public void setWeight(java.lang.String weight) {
		this.weight = weight;
	}
	
	public java.lang.String getIsvci() {
		return isvci;
	}

	public void setIsvci(java.lang.String isvci) {
		this.isvci = isvci;
	}

	public String getLogFormName() {
		if (super.logFormName == null || super.logFormName.equals("")) {
			return "装配件结构管理";
		} else {
			return super.logFormName;
		}
	}

	public String getLogTitle() {
		if (super.logTitle == null || super.logTitle.equals("")) {
			return "装配件结构管理";
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