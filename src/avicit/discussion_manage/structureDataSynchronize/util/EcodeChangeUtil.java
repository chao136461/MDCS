package avicit.discussion_manage.structureDataSynchronize.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import avicit.discussion_manage.structuralrelationship.service.StructuralRelationshipService;

public class EcodeChangeUtil {
	private static final Logger logger =  LoggerFactory.getLogger(StructuralRelationshipService.class);

	//中文字符处理
    public static String charSetConvert(String xmlRequest){
        String charSet = getEncoding(xmlRequest);
        try {
            byte[] b = xmlRequest.getBytes(charSet);
            xmlRequest = new String(b, "GBK");
        } catch (Exception e) {
            logger.error("输入的内容不属于常见的编码格式,请再仔细核实", e);
        }
        return xmlRequest;
        
    }
    public static String charSetConvertUT(String xmlRequest){
        String charSet = getEncoding(xmlRequest);
        try {
            byte[] b = xmlRequest.getBytes(charSet);
            xmlRequest = new String(b, "UTF-8");
        } catch (Exception e) {
            logger.error("输入的内容不属于常见的编码格式,请再仔细核实", e);
        }
        return xmlRequest;
        
    }
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GB2312
                String s = encode;
                return s; // 是的话，返回GB2312，以下代码同理
            }
        } catch (Exception e) {
            logger.error("getEncoding异常---GB2312", e);
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是ISO-8859-1
                String s1 = encode;
                return s1;
            }
        } catch (Exception e) {
            logger.error("getEncoding异常---ISO-8859-1", e);
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是UTF-8编码
                String s2 = encode;
                return s2;
            }
        } catch (Exception e) {
            logger.error("getEncoding异常---UTF-8", e);
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) { // 判断是不是GBK
                String s3 = encode;
                return s3;
            }
        } catch (Exception e) {
            logger.error("getEncoding异常---GBK", e);
        }
        return ""; // 到这一步，你就应该检查是不是其他编码啦  
        
        }
}