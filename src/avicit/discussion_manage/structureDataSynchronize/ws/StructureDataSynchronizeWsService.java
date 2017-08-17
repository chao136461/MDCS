package avicit.discussion_manage.structureDataSynchronize.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
public interface StructureDataSynchronizeWsService {

	
	@WebMethod(operationName="getStructureData")
	@WebResult(name="result")
	public  String  getStructureData(@WebParam(name="data")String data)throws Exception;
	
	/**
	 * 获取干涉数据信息
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	@WebMethod(operationName="getInterveneData")
	@WebResult(name="result")
	public  String  getInterveneData(@WebParam(name="data")String data)throws Exception;

	/**
	 * 获取质量检查数据信息
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	@WebMethod(operationName="getQualityData")
	@WebResult(name="result")
	public  String  getQualityData(@WebParam(name="data")String data)throws Exception;
	/**
	 * 根据用户名查找用户审查任务
	 * @param String
	 * @return String
	 * @throws Exception
	 */
	@WebMethod(operationName="CheckMission")
	@WebResult(name="result")
	public  String  CheckMission(@WebParam(name="userName")String userName)throws Exception;

	/**
	 * 保存建议发表截图信息
	 * @param String proposalId
	 * @param String saveAddress
	 * @param String pictureOption
	 */
	@WebMethod(operationName="saveProposalPictureData")
	@WebResult(name="result")
	public  String  saveProposalPictureData(@WebParam(name="proposalId")String proposalId,@WebParam(name="saveAddress")String saveAddress,@WebParam(name="pictureOption")String pictureOption,@WebParam(name="pictureInfo")byte[] pictureInfo);
	
	/**
	 * 根据VPM登录名获取本系统登录名
	 * @param 
	 * @param 
	 * @param 
	 */
	@WebMethod(operationName="getSysUserName")
	@WebResult(name="result")
	public  String  getSysUserName(@WebParam(name="vpmLoginName")String vpmLoginName)throws Exception;

	/**
	 * 根据建议id返回截图矩阵信息
	 * @param String
	 * author by heps
	 */
	@WebMethod(operationName="getPicturePosition")
	@WebResult(name="result")
	public  String  getPicturePosition(@WebParam(name="id")String id);


}
