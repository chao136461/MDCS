package avicit.discussion_manage.modelInterveneCheck.util;

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

import avicit.discussion_manage.modelInterveneCheck.dto.InterveneDTO;
import avicit.discussion_manage.structureDataSynchronize.dto.WebserviceXmlStructureDTO;
import avicit.platform6.commons.utils.ComUtil;

public class ResolveInterveneXml {

	/**
     * 解析webservice传输的干涉XML格式字符串中的信息 BY HEPS
     * 2017-08-03
     */
    public  static List<InterveneDTO> getInterveneDataByWs(String data) throws Exception {
    	List<InterveneDTO> InterveneBeanList = new ArrayList<InterveneDTO>();
    	//构建解析器
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        InputStream inputStream = new ByteArrayInputStream(data.getBytes());
        //得到xml Document文档
        Document doc = builder.parse(inputStream);
        //获取根元素
        Element root = doc.getDocumentElement();
        //解析机型数据
		NodeList infos = root.getElementsByTagName("jixing");
		String jixing = infos.item(0).getTextContent();
		
		
		NodeList parts = root.getElementsByTagName("part");
		
		for (int i = 0; i < parts.getLength(); i++) {
			
			Element struct = (Element)parts.item(i);
			
			InterveneDTO dto = getInterveneBean(struct);
			
			dto.setJixing(jixing);
			
			InterveneBeanList.add(dto);
		}
	
		return InterveneBeanList;
    }
    
    /**
     * 解析该零部件信息
     * @param Element（零部件元素）
     * @return InterveneDTO(零部件对象)
     */
    private static InterveneDTO getInterveneBean(Element struct){
	
    	InterveneDTO xmlStrBean =  new InterveneDTO();
       //分类编号
       NodeList partnumbers = struct.getElementsByTagName("partnumber");
  	   String   partnumber = partnumbers.item(0).getTextContent();
  	   xmlStrBean.setClassCode(partnumber);
  	   //干涉时间
  	   NodeList checkTimes = struct.getElementsByTagName("checktime");
  	   String   checkTime = checkTimes.item(0).getTextContent();
  	   xmlStrBean.setCheckTime(checkTime);
  	   //实例编号
  	   NodeList instancenumbers = struct.getElementsByTagName("instancenumber");
  	   String   instancenumber = instancenumbers.item(0).getTextContent();
  	   xmlStrBean.setInstanceNumber(instancenumber);
  	   //干涉结果
  	   NodeList results = struct.getElementsByTagName("result");
  	   String   result = results.item(0).getTextContent();
  	   xmlStrBean.setResult(result);
  	   //干涉内容
  	   NodeList descriptions = struct.getElementsByTagName("description");
  	   String   description = descriptions.item(0).getTextContent();
  	   xmlStrBean.setDescription(description);
    	
       return xmlStrBean;
    }
}
