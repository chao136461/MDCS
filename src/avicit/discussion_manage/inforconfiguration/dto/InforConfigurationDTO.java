package avicit.discussion_manage.inforconfiguration.dto;

import javax.persistence.Id;
//import javax.persistence.Transient;

import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;
/**
 * bean InforConfiguration
 * Title: 表INFOR_CONFIGURATION的PoJo类，也就是数据库映射类
 * Description: 信息配置表
 * Copyriht: Copyright (c) 2012
 * Company: AVICIT Co., Ltd
 * @author AVICIT DEV
 * @version 1.0 Date: 2017-07-20 18:43
 *
 */
 @PojoRemark(table="infor_configuration",object="InforConfigurationDTO",name="信息配置表")
 public class InforConfigurationDTO extends BeanDTO{
    private static final long serialVersionUID = 1L;
    @Id
    @LogField
    @FieldRemark(column="id",field="id",name="ID")
    private java.lang.String id;
    @FieldRemark(column="is_confirm",field="isConfirm",name="审查确认")
    private java.lang.String isConfirm;
    @FieldRemark(column="model_check",field="modelCheck",name="模型检查")
    private java.lang.String modelCheck;
    @FieldRemark(column="interfere_check",field="interfereCheck",name="干涉检查")
    private java.lang.String interfereCheck;
    @FieldRemark(column="approve_state",field="approveState",name="审批状态")
    private java.lang.String approveState;
    @FieldRemark(column="is_degrade",field="isDegrade",name="是否降级")
    private java.lang.String isDegrade;
    @FieldRemark(column="attribute_01",field="attribute01",name="扩展字段01")
    private java.lang.String attribute01;
    @FieldRemark(column="attribute_02",field="attribute02",name="扩展字段02")
    private java.lang.String attribute02;
    @FieldRemark(column="attribute_03",field="attribute03",name="扩展字段03")
    private java.lang.String attribute03;
    @FieldRemark(column="attribute_04",field="attribute04",name="扩展字段04")
    private java.lang.String attribute04;
    @FieldRemark(column="attribute_05",field="attribute05",name="扩展字段05")
    private java.lang.String attribute05;
    @FieldRemark(column="attribute_06",field="attribute06",name="扩展字段06")
    private java.lang.String attribute06;
    @FieldRemark(column="attribute_07",field="attribute07",name="扩展字段07")
    private java.util.Date attribute07;
    @FieldRemark(column="attribute_08",field="attribute08",name="扩展字段08")
    private java.util.Date attribute08;
    @FieldRemark(column="attribute_09",field="attribute09",name="扩展字段09")
    private long attribute09;
    @FieldRemark(column="attribute_10",field="attribute10",name="扩展字段10")
    private long attribute10;
    @FieldRemark(column="type_id",field="typeId",name="类型ID")   
    private java.lang.String typeId;
    @FieldRemark(column="sync_time",field="syncTime",name="同步时间")
    private java.lang.String syncTime;
    @FieldRemark(column="is_opentime",field="isOpenTime",name="是否打开同步时间")
    private java.lang.String isOpenTime;
    
    
    
   
   

	

	public java.lang.String getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(java.lang.String syncTime) {
		this.syncTime = syncTime;
	}

	public java.lang.String getIsOpenTime() {
		return isOpenTime;
	}

	public void setIsOpenTime(java.lang.String isOpenTime) {
		this.isOpenTime = isOpenTime;
	}

	public java.lang.String getId(){
	  return id;
	}
		
	public void setId(java.lang.String id){
	  this.id = id;
	}
 	    	  
    public java.lang.String getIsConfirm(){
	  return isConfirm;
	}
		
	public void setIsConfirm(java.lang.String isConfirm){
	  this.isConfirm = isConfirm;
	}
 	    	  
    public java.lang.String getModelCheck(){
	  return modelCheck;
	}
		
	public void setModelCheck(java.lang.String modelCheck){
	  this.modelCheck = modelCheck;
	}
 	    	  
    public java.lang.String getInterfereCheck(){
	  return interfereCheck;
	}
		
	public void setInterfereCheck(java.lang.String interfereCheck){
	  this.interfereCheck = interfereCheck;
	}
 	    	  
    public java.lang.String getApproveState(){
	  return approveState;
	}
		
	public void setApproveState(java.lang.String approveState){
	  this.approveState = approveState;
	}
 	    	  
    public java.lang.String getIsDegrade(){
	  return isDegrade;
	}
		
	public void setIsDegrade(java.lang.String isDegrade){
	  this.isDegrade = isDegrade;
	}
 	    	    	    	    	    	    	    	  
    public java.lang.String getAttribute01(){
	  return attribute01;
	}
		
	public void setAttribute01(java.lang.String attribute01){
	  this.attribute01 = attribute01;
	}
 	    	  
    public java.lang.String getAttribute02(){
	  return attribute02;
	}
		
	public void setAttribute02(java.lang.String attribute02){
	  this.attribute02 = attribute02;
	}
 	    	  
    public java.lang.String getAttribute03(){
	  return attribute03;
	}
		
	public void setAttribute03(java.lang.String attribute03){
	  this.attribute03 = attribute03;
	}
 	    	  
    public java.lang.String getAttribute04(){
	  return attribute04;
	}
		
	public void setAttribute04(java.lang.String attribute04){
	  this.attribute04 = attribute04;
	}
 	    	  
    public java.lang.String getAttribute05(){
	  return attribute05;
	}
		
	public void setAttribute05(java.lang.String attribute05){
	  this.attribute05 = attribute05;
	}
 	    	  
    public java.lang.String getAttribute06(){
	  return attribute06;
	}
		
	public void setAttribute06(java.lang.String attribute06){
	  this.attribute06 = attribute06;
	}
 	    	  
    public java.util.Date getAttribute07(){
	  return attribute07;
	}
		
	public void setAttribute07(java.util.Date attribute07){
	  this.attribute07 = attribute07;
	}
 	    	  
    public java.util.Date getAttribute08(){
	  return attribute08;
	}
		
	public void setAttribute08(java.util.Date attribute08){
	  this.attribute08 = attribute08;
	}
 	    	  
    public long getAttribute09(){
	  return attribute09;
	}
		
	public void setAttribute09(long attribute09){
	  this.attribute09 = attribute09;
	}
 	    	  
    public long getAttribute10(){
	  return attribute10;
	}
		
	public void setAttribute10(long attribute10){
	  this.attribute10 = attribute10;
	}
 	    	  
    public java.lang.String getTypeId(){
	  return typeId;
	}
		
	public void setTypeId(java.lang.String typeId){
	  this.typeId = typeId;
	}
 	  
       
 
	public String getLogFormName() {
		if(super.logFormName==null||super.logFormName.equals("")){
			return "信息配置表";
		}else{
			return super.logFormName;
		}
	}

	public String getLogTitle() {
		if(super.logTitle==null||super.logTitle.equals("")){
			return "信息配置表";
		}else{
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