package avicit.discussion_manage.modelInterveneCheck.ws.impl;


import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;


import avicit.platform6.commons.utils.web.WsUtil;




public class T {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String data ="<?xml version='1.0' encoding='utf-8'?>"
+"<root>"
+"<infos>"
+"<jixing>MLXG</jixing>"
	+"</infos>"
	+"<Struct>"
		+"<part>"
			+"<partnumber>C919.HEAD.XXXX.0005.0002</partnumber>"
			+"<type>0</type>"
			+"<instancenumber>C919.HEAD.XXXX.0005.0002.1</instancenumber>"
			+"<partname>vci的名字3</partname>"
			+"<owner>EV5ADM</owner>"
			+"<maturity>M2</maturity>"
			+"<version>---</version>"
			+"<createtime>2014-4-2 13:34:29</createtime>"
			+"<modifytime>2014-4-2 13:34:32</modifytime>"
			+"<subparts>"
				+"<subpart>"
				+"<partnumber>C919.HEAD.XXXX.0005.0001.0003</partnumber>"
				+"<type>3</type>"
				+"<instancenumber>C919.HEAD.XXXX.0005.0001.0003.1</instancenumber>"
				+"<partname>零件名称</partname>"
				+"<owner>EV5ADM</owner>"
				+"<maturity>M2</maturity>"
				+"<version>---</version>"
				+"<createtime>2014-4-2 13:34:28</createtime>"
				+"<modifytime>2014-4-2 13:34:32</modifytime>"
			+"</subpart>"
			+"</subparts>"
		+"</part>"
	+"</Struct>"
+"</root>";
		
		String wsurl = "http://localhost:8080/MDCS/ws/structureDataSynchronizeWsService?wsdl";
		JaxWsDynamicClientFactory dcf =  JaxWsDynamicClientFactory.newInstance();
		org.apache.cxf.endpoint.Client client =  dcf.createClient(wsurl);
//		javax.xml.namespace.QName   qName = WsUtil.getOperationName(client.getEndpoint(), "getInterveneData");
		javax.xml.namespace.QName   qName = WsUtil.getOperationName(client.getEndpoint(), "getStructureData");
//		javax.xml.namespace.QName   qName = WsUtil.getOperationName(client.getEndpoint(), "CheckMission");
		//javax.xml.namespace.QName   qName = WsUtil.getOperationName(client.getEndpoint(), "getSysUserName");
		Object[] obj =  client.invoke(qName,data);
		System.out.println(obj[0].toString());
	}

}
