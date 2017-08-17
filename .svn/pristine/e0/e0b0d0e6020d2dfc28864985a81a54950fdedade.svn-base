package avicit.discussion_manage.factorycomplex.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.core.rest.resteasy.RestEasyController;

import avicit.discussion_manage.factorycomplex.dto.FactoryComplexDTO;
import avicit.discussion_manage.factorycomplex.dto.FactoryCensorManageDTO;
import avicit.discussion_manage.factorycomplex.service.FactoryComplexService;
import avicit.discussion_manage.factorycomplex.service.FactoryCensorManageService;
//import avicit.discussion_manage.factorycomplex.service.Service;


@RestEasyController
@Path("/api/discussion_manage/factorycomplex/FactoryComplexRest")
public class FactoryComplexRest{
    private static final Logger logger =  LoggerFactory.getLogger(FactoryComplexRest.class);
    
	@Autowired
	private FactoryComplexService factoryComplexService;
	
	@Autowired
	private FactoryCensorManageService factoryCensorManageService;
	
	/**
	 * 通过主表id获得主表数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/getMain/v1/{id}")
	@Produces("application/json;charset=UTF-8")
	public ResponseMsg<FactoryComplexDTO> getMainById(@PathParam("id") String id) throws Exception {
		ResponseMsg<FactoryComplexDTO> responseMsg = new ResponseMsg<FactoryComplexDTO>();
		FactoryComplexDTO dto = factoryComplexService.queryFactoryComplexByPrimaryKey(id);
		responseMsg.setResponseBody(dto);
		return responseMsg;
	}
	/**
	 * 保存一条主表数据
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/saveMain/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<String> saveMain(FactoryComplexDTO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id =  factoryComplexService.insertFactoryComplex(dto);
		responseMsg.setResponseBody(id);
		return responseMsg;
	}
	/**
	 * 更新主表数据
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/updateMain/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> updateMainSensitive(FactoryComplexDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = factoryComplexService.updateFactoryComplexSensitive(dto);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	/**
	 * 修改主表一条记录的全部字段
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/updateMainAll/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> updateMainAll(FactoryComplexDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = factoryComplexService.updateFactoryComplex(dto);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	/**
	 * 按条件分页查询主表数据
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/searchMainByPage/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<QueryRespBean<FactoryComplexDTO>> searchMainByPage(QueryReqBean<FactoryComplexDTO> queryReqBean,String sfnConditions) throws Exception {
		ResponseMsg<QueryRespBean<FactoryComplexDTO>> responseMsg = new ResponseMsg<QueryRespBean<FactoryComplexDTO>>();
		QueryRespBean<FactoryComplexDTO> queryRespBean = factoryComplexService.searchFactoryComplexByPage(queryReqBean,sfnConditions);
		responseMsg.setResponseBody(queryRespBean);
		return responseMsg;
	}
	
	/**
	 * 按条件不分页查询主表数据
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/searchMain/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<List<FactoryComplexDTO>> searchMain(QueryReqBean<FactoryComplexDTO> queryReqBean) throws Exception {
		ResponseMsg<List<FactoryComplexDTO>> responseMsg = new ResponseMsg<List<FactoryComplexDTO>>();
		List<FactoryComplexDTO> queryRespBean = factoryComplexService.searchFactoryComplex(queryReqBean);
		responseMsg.setResponseBody(queryRespBean);
		return responseMsg;
	}
	
	/**
	 * 按照ID删除一条主表记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/deleteMainById/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> deleteMainById(String id) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = factoryComplexService.deleteFactoryComplexById(id);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	
	//*********************************子表操作*********************************************
	//按照父id获得子表数据
	@GET
	@Path("/getSubByPid/v1/{pid}")
	@Produces("application/json;charset=UTF-8")
	public ResponseMsg<List<FactoryCensorManageDTO>> getSubByPid(@PathParam("pid") String pid) throws Exception {
		ResponseMsg<List<FactoryCensorManageDTO>> responseMsg = new ResponseMsg<List<FactoryCensorManageDTO>>();
		List<FactoryCensorManageDTO> dto = factoryCensorManageService.queryFactoryCensorManageByPid(pid);
		responseMsg.setResponseBody(dto);
		return responseMsg;
	}
	
	/**
	 * 通过子表id获得数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/getSub/v1/{id}")
	@Produces("application/json;charset=UTF-8")
	public ResponseMsg<FactoryCensorManageDTO> getSubById(@PathParam("id") String id) throws Exception {
		ResponseMsg<FactoryCensorManageDTO> responseMsg = new ResponseMsg<FactoryCensorManageDTO>();
		FactoryCensorManageDTO dto = factoryCensorManageService.queryFactoryCensorManageByPrimaryKey(id);
		responseMsg.setResponseBody(dto);
		return responseMsg;
	}
	/**
	 * 更新子表数据
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/updateSub/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> updateSubSensitive(FactoryCensorManageDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = factoryCensorManageService.updateFactoryCensorManage(dto);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	/**
	 * 修改子表一条记录的全部字段
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/updateSubAll/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> updateSubAll(FactoryCensorManageDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = factoryCensorManageService.updateFactoryCensorManageSensitive(dto);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	/**
	 * 按照ID删除一条子表记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/deleteSubById/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> deleteSubById(String id) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = factoryCensorManageService.deleteFactoryCensorManageById(id);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
}
