package avicit.discussion_manage.disputememo.dto;

import javax.persistence.Id;
//import javax.persistence.Transient;

import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant.LogType;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;
/**
 * bean DisputeMemo
 * Title: 表DISPUTE_MEMO的PoJo类，也就是数据库映射类
 * Description: 争议备忘
 * Copyriht: Copyright (c) 2012
 * Company: AVICIT Co., Ltd
 * @author AVICIT DEV
 * @version 1.0 Date: 2017-07-28 10:35
 *
 */
 @PojoRemark(table="dispute_memo",object="DisputeMemoDTO",name="争议备忘")
 public class DisputeMemoDTO extends BeanDTO{
    private static final long serialVersionUID = 1L;
	
							    			@Id
						    			@LogField
			    	
    		
    		    		 	@FieldRemark(column="id",field="id",name="ID")
    		 	private java.lang.String id;
    																    	
    		
    		    		 	@FieldRemark(column="pro_id",field="proId",name="建议ID")
    		 	private java.lang.String proId;
    																    	
    		
    		    		 	@FieldRemark(column="dis_text",field="disText",name="备忘内容")
    		 	private java.lang.String disText;
    																    	
    		
    		    			@FieldRemark(column="send_to",field="sendTo",name="通知接收目标人")
    		 	private java.lang.String sendTo;
    		 	private java.lang.String sendToAlias;
    																																		    	
    		
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
    		 	
    		 	private java.util.Date attribute07Begin;
    		 	
    		 	private java.util.Date attribute07End;
    																    	
    		
    		    			@FieldRemark(column="attribute_08",field="attribute08",name="扩展字段08")
    		 	private java.util.Date attribute08;
    		 	
    		 	private java.util.Date attribute08Begin;
    		 	
    		 	private java.util.Date attribute08End;
    																    	
    		
    		    		 	@FieldRemark(column="attribute_09",field="attribute09",name="扩展字段09")
    		 	private Long attribute09;
    																    	
    		
    		    		 	@FieldRemark(column="attribute_10",field="attribute10",name="扩展字段10")
    		 	private Long attribute10;
    					
	  	  		    		
    		    			public java.lang.String getId(){
		  			return id;
				}
			
				public void setId(java.lang.String id){
		  			this.id = id;
				}
    		    		
 		  	  		    		
    		    			public java.lang.String getProId(){
		  			return proId;
				}
			
				public void setProId(java.lang.String proId){
		  			this.proId = proId;
				}
    		    		
 		  	  		    		
    		    			public java.lang.String getDisText(){
		  			return disText;
				}
			
				public void setDisText(java.lang.String disText){
		  			this.disText = disText;
				}
    		    		
 		  	  		    		
    			    		public java.lang.String getSendTo(){
		  			return sendTo;
				}
			
				public void setSendTo(java.lang.String sendTo){
		  			this.sendTo = sendTo;
				}
				
				public java.lang.String getSendToAlias(){
		  			return sendToAlias;
				}
			
				public void setSendToAlias(java.lang.String sendToAlias){
		  			this.sendToAlias = sendToAlias;
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
				
				public java.util.Date getAttribute07Begin(){
		  			return attribute07Begin;
				}
			
				public void setAttribute07Begin(java.util.Date attribute07Begin){
		  			this.attribute07Begin = attribute07Begin;
				}
				
				public java.util.Date getAttribute07End(){
		  			return attribute07End;
				}
			
				public void setAttribute07End(java.util.Date attribute07End){
		  			this.attribute07End = attribute07End;
				}
    		    		
 		  	  		    		
    						public java.util.Date getAttribute08(){
		  			return attribute08;
				}
			
				public void setAttribute08(java.util.Date attribute08){
		  			this.attribute08 = attribute08;
				}
				
				public java.util.Date getAttribute08Begin(){
		  			return attribute08Begin;
				}
			
				public void setAttribute08Begin(java.util.Date attribute08Begin){
		  			this.attribute08Begin = attribute08Begin;
				}
				
				public java.util.Date getAttribute08End(){
		  			return attribute08End;
				}
			
				public void setAttribute08End(java.util.Date attribute08End){
		  			this.attribute08End = attribute08End;
				}
    		    		
 		  	  		    		
    		    			public Long getAttribute09(){
		  			return attribute09;
				}
			
				public void setAttribute09(Long attribute09){
		  			this.attribute09 = attribute09;
				}
    		    		
 		  	  		    		
    		    			public Long getAttribute10(){
		  			return attribute10;
				}
			
				public void setAttribute10(Long attribute10){
		  			this.attribute10 = attribute10;
				}
    		    		
 		  	
       
 
	public String getLogFormName() {
		if(super.logFormName==null||super.logFormName.equals("")){
			return "争议备忘";
		}else{
			return super.logFormName;
		}
	}

	public String getLogTitle() {
		if(super.logTitle==null||super.logTitle.equals("")){
			return "争议备忘";
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