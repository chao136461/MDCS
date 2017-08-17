package avicit.discussion_manage.structureDataSynchronize.ws.impl;


import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import avicit.discussion_manage.mdcsproposalpicture.service.MdcsProposalPictureService;
import avicit.discussion_manage.modelInterveneCheck.service.SynchronousInterveneDataervice;
import avicit.discussion_manage.modelQualityCheck.service.SynchronousQualityDataService;
import avicit.discussion_manage.processassignment.service.ProcessAssignmentService;
import avicit.discussion_manage.relevanceperson.service.RelevancePersonService;
import avicit.discussion_manage.structureDataSynchronize.service.StructureDataSynchronizeService;
import avicit.discussion_manage.structureDataSynchronize.util.EcodeChangeUtil;
import avicit.discussion_manage.structureDataSynchronize.ws.StructureDataSynchronizeWsService;
import avicit.platform6.core.exception.DaoException;

@WebService(endpointInterface="avicit.discussion_manage.structureDataSynchronize.ws.StructureDataSynchronizeWsService")
public class StructureDataSynchronizeWsServiceImpl implements StructureDataSynchronizeWsService{

	private static final Logger logger =  LoggerFactory.getLogger(StructureDataSynchronizeWsServiceImpl.class);

	@Autowired
	private StructureDataSynchronizeService service;
	@Autowired
	private SynchronousInterveneDataervice InterveneService;
	@Autowired
	private SynchronousQualityDataService QualityService;
	@Autowired
	private ProcessAssignmentService assignmentService;
	@Autowired
	private  RelevancePersonService personservice;
	@Autowired
	private  MdcsProposalPictureService mdcsProposalPictureService;
	
	/**
	 * 同步零件信息
	 * @param String
	 * @return String
	 * author by heps
	 * @throws Exception
	 */
	@Override
	public String getStructureData(String data) throws Exception{
		String result = "";
		if(null != data && !"".equals(data)){
			String encode = EcodeChangeUtil.getEncoding(data);
			String xmlData = new String(data.getBytes(encode),"gbk");
			if(xmlData!=null){
				 result = service.updateStructureDataSynchronize(xmlData);
			}
			return result;
		}else{
			
			return "no data";
		}
	}
	
	/**
	 * 获取干涉数据信息
	 * @param String
	 * @return String
	 * author by heps
	 * @throws Exception
	 */
	@Override
	public String getInterveneData(String data){
		try {
			String result = "no data";
			if(null == data || "".equals(data)){
				return result;
			}else{
				data = EcodeChangeUtil.charSetConvert(data);
				result = InterveneService.saveResolveInterveneXmlData(data);
				if("success".equals(result)){
					return result;
				}else{
					return result;
				}
			}
		} catch (Exception e) {
			logger.error("getInterveneData出错：",e);
			return "xml data error";
		}
	}
	
	/**
	 * 获取质量检查数据信息
	 * @param String
	 * @return String
	 * author by heps
	 * @throws Exception
	 */
	@Override
	public String getQualityData(String data) throws Exception {
		try {
			String result = "no data";
			if(null == data || "".equals(data)){
				return result;
			}else{
				data = EcodeChangeUtil.charSetConvert(data);
				if("success".equals(result)){
					return result;
				}
			}
			return "xml data error";
		} catch (Exception e) {
			logger.error("getInterveneData出错：",e);
			throw new DaoException("",e);
		}
	}
	/**
	 * 根据用户名查找用户审查任务
	 * @param String
	 * @return String(**##**##**$$格式)
	 * @throws Exception
	 */
	@Override
	public String CheckMission(String userName) throws Exception {
		
		String result="";
		if (null!=userName&&!"".equals(userName)) {
			String userId = personservice.getUserIdByUserName(userName);
			if (null!=userId&&!"".equals(userId)) {
				result = assignmentService.getAssignmentByuserId(userId);
			}else{
				result=userName+"not found";
			}
		}
		return result;
	}
	
	/**
	 * 保存建议发表截图信息
	 * @param String proposalId
	 * @param String saveAddress
	 * @param String pictureOption
	 * author by heps
	 */
	@Override
	public String saveProposalPictureData(String proposalId,String saveAddress,String pictureOption,byte[] pictureInfo){
		String result = "failure";
		try {
			if(null == proposalId || "".equals(proposalId)){
				return "proposalId is null";
			}
			if(null == saveAddress || "".equals(saveAddress)){
				return "saveAddress is null";
			}
			if(null == pictureInfo || "".equals(pictureInfo)){
				return "pictureInfo is null";
			}else{
				result = mdcsProposalPictureService.saveProposalPictureData(proposalId,saveAddress,pictureOption,pictureInfo);
				return result;
			}
		} catch (Exception e) {
			logger.error("saveProposalPictureData出错：",e);
			return result;
		}
	}

	
	/**
	 * 根据通过VPM登录名获取本系统登录名
	 */
	@Override
	public String getSysUserName(String vpmLoginName) throws Exception {
		String loginName=null;
		if(vpmLoginName!=null && !"".equals(vpmLoginName)){
			loginName = personservice.findRelevancePersonByVpmLoginName(vpmLoginName);
			 
		}else{
			return "no data";
		}
		
		return loginName;
	}
	

		/**
		 * 根据建议id返回截图矩阵信息
		 * @param String
		 * author by heps
		 */
		@Override
		public String getPicturePosition(String id){
			if(null == id || "".equals(id)){
				return "id is null";
			}
			try {
				String result = mdcsProposalPictureService.getPicturePosition(id);
				return result;
			} catch (Exception e) {
				return "not found";
			}
		}
}
