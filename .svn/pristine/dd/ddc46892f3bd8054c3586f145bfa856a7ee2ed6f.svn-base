package avicit.discussion_manage.structureDataSynchronize.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import avicit.discussion_manage.structureDataSynchronize.dto.WebserviceXmlStructureDTO;

public class XmlStrBeanVectorWeberviceDataUtil{
	/**
     * 解析webservice传输的零部件XML格式字符串中的信息 BY HEPS
     * 2017-08-03
     */
    public  static Vector<WebserviceXmlStructureDTO> getXmlStrBeanVectorByWeberviceData(String xmlStr) throws Exception {
    	Vector<WebserviceXmlStructureDTO>	strVector = new Vector<WebserviceXmlStructureDTO>();
    	//构建解析器
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        InputStream inputStream = new ByteArrayInputStream(xmlStr.getBytes());
        //得到xml Document文档
        Document doc = builder.parse(inputStream);
        //获取根元素
        Element root = doc.getDocumentElement();
        //解析机型数据
		NodeList infos = root.getElementsByTagName("jixing");
		String jixing = infos.item(0).getTextContent();
		
		NodeList structs = root.getElementsByTagName("Struct");
		
		WebserviceXmlStructureDTO xmlStrBean=null;
		
       for (int i = 0; i < structs.getLength(); i++) {
            // 解析每一个零件信息
    	   Element struct = (Element)structs.item(i);
    	  
    	   xmlStrBean = getXmlStrBean(struct);
    	   
    	   xmlStrBean.setJixing(jixing);
    	   
    	   String classType = xmlStrBean.getClassType();
    	   
    	   List<WebserviceXmlStructureDTO> subpartList =new  ArrayList<WebserviceXmlStructureDTO>();
    	   
    	   //如果clasType不等于3（不是零件），解析该装配件所有子节点信息 
    	   if(null != classType && !"".equals(classType) &&  !"3".equals(classType)){
    		   
    		   NodeList  subpartes = struct.getElementsByTagName("subparts");
    		   
    		   Element   subpart1 = (Element)subpartes.item(0);
    			   
    		   NodeList  subpartss = subpart1.getElementsByTagName("subpart");
        		   
        		   for (int j = 0; j < subpartss.getLength(); j++) {
    				
        			   Element subpart = (Element)subpartss.item(j);
        			   
        			   WebserviceXmlStructureDTO subXmlStrBean=new WebserviceXmlStructureDTO();
        			   
        			   subXmlStrBean = getXmlStrBean(subpart);
        			   
        			   subXmlStrBean.setJixing(jixing);
        			   
        			   subpartList.add(subXmlStrBean);
        	    	   
        		   } 
    	   }
    	   xmlStrBean.setSubparts(subpartList);
    	   
        }
      
        strVector.add(xmlStrBean);
       
		return strVector;
    }
    
    /**
     * 解析该零部件信息
     * @param Element（零部件元素）
     * @return WebserviceXmlStructureDTO(零部件对象)
     */
    private static WebserviceXmlStructureDTO getXmlStrBean(Element struct){
	
       WebserviceXmlStructureDTO xmlStrBean =  new WebserviceXmlStructureDTO();
       //分类编号
       NodeList partnumbers = struct.getElementsByTagName("partnumber");
  	   String   partnumber = partnumbers.item(0).getTextContent();
  	   xmlStrBean.setClassCode(partnumber);
  	   //分类类型
  	   NodeList types = struct.getElementsByTagName("type");
  	   String   type = types.item(0).getTextContent();
  	   xmlStrBean.setClassType(type);
  	   //实例编号
  	   NodeList instancenumbers = struct.getElementsByTagName("instancenumber");
  	   String   instancenumber = instancenumbers.item(0).getTextContent();
  	   xmlStrBean.setInstancenumber(instancenumber);
  	   //分类名称
  	   NodeList partnames = struct.getElementsByTagName("partname");
  	   String   partname = partnames.item(0).getTextContent();
  	   xmlStrBean.setClassName(partname);
  	   //设计者
  	   NodeList owners = struct.getElementsByTagName("owner");
  	   String   owner = owners.item(0).getTextContent();
  	   xmlStrBean.setDesignerId(owner);
  	   //成熟度
  	   NodeList maturitys = struct.getElementsByTagName("maturity");
  	   String   maturity = maturitys.item(0).getTextContent();
  	   xmlStrBean.setStatus(maturity);
  	   //版本
  	   NodeList versions = struct.getElementsByTagName("version");
  	   String   version = versions.item(0).getTextContent();
  	   xmlStrBean.setEdition(version);
  	   //创建时间
  	   NodeList createtimes = struct.getElementsByTagName("createtime");
  	   String   createtime = createtimes.item(0).getTextContent();
  	   xmlStrBean.setCreatetime(createtime);
  	   //修改时间
  	   NodeList modifytimes = struct.getElementsByTagName("modifytime");
  	   String   modifytime = modifytimes.item(0).getTextContent();
  	   xmlStrBean.setModifytime(modifytime);
    	
       return xmlStrBean;
    }
}  