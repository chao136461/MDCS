package avicit.discussion_manage.profconfiguration.rest;

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

import avicit.discussion_manage.profconfiguration.dto.ProfConfigurationDTO;
import avicit.discussion_manage.profconfiguration.service.ProfConfigurationService;

@RestEasyController
@Path("/api/discussion_manage/profconfiguration/ProfConfigurationRest")
public class ProfConfigurationRest{
    private static final Logger logger =  LoggerFactory.getLogger(ProfConfigurationRest.class);
    
	@Autowired
	private ProfConfigurationService profConfigurationService;
	
	
	
	
	@GET
	@Path("/get/v1/{id}")
	@Produces("application/json;charset=UTF-8")
	public ResponseMsg<ProfConfigurationDTO> get(@PathParam("id") String id) throws Exception {
		ResponseMsg<ProfConfigurationDTO> responseMsg = new ResponseMsg<ProfConfigurationDTO>();
		ProfConfigurationDTO dto = profConfigurationService.queryProfConfigurationByPrimaryKey(id);
		responseMsg.setResponseBody(dto);
		return responseMsg;
	}
	
	
	/**
	 * 插入一条记录
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/save/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<String> save(ProfConfigurationDTO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = profConfigurationService.insertProfConfiguration(dto);
		responseMsg.setResponseBody(id);
		return responseMsg;
	}
	
	/**
	 * 修改一条记录，按照匹配字段修改
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/updateSensitive/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> updateSensitive(ProfConfigurationDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = profConfigurationService.updateProfConfigurationSensitive(dto);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	/**
	 * 修改一条记录的全部字段
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/updateAll/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> updateAll(ProfConfigurationDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = profConfigurationService.updateProfConfiguration(dto);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	/**
	 * 按照ID删除一条记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/deleteById/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> deleteById(String id) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = profConfigurationService.deleteProfConfigurationById(id);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	
	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/searchByPage/v1")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<QueryRespBean<ProfConfigurationDTO>> searchByPage(QueryReqBean<ProfConfigurationDTO> queryReqBean) throws Exception {
		ResponseMsg<QueryRespBean<ProfConfigurationDTO>> responseMsg = new ResponseMsg<QueryRespBean<ProfConfigurationDTO>>();
		QueryRespBean<ProfConfigurationDTO> queryRespBean = profConfigurationService.searchProfConfigurationByPage(queryReqBean,"");
		responseMsg.setResponseBody(queryRespBean);
		return responseMsg;
	}
	/**
	 * 按条件不分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/search/v1/")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<List<ProfConfigurationDTO>> search(QueryReqBean<ProfConfigurationDTO> queryReqBean) throws Exception {
		ResponseMsg<List<ProfConfigurationDTO>> responseMsg = new ResponseMsg<List<ProfConfigurationDTO>>();
		List<ProfConfigurationDTO> queryRespBean = profConfigurationService.searchProfConfiguration(queryReqBean);
		responseMsg.setResponseBody(queryRespBean);
		return responseMsg;
	}
	
}
