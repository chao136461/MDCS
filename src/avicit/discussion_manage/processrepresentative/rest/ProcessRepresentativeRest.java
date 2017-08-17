package avicit.discussion_manage.processrepresentative.rest;

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

import avicit.discussion_manage.processrepresentative.dto.ProcessRepresentativeDTO;
import avicit.discussion_manage.processrepresentative.service.ProcessRepresentativeService;

@RestEasyController
@Path("/api/discussion_manage/processrepresentative/ProcessRepresentativeRest")
public class ProcessRepresentativeRest{
    private static final Logger logger =  LoggerFactory.getLogger(ProcessRepresentativeRest.class);
    
	@Autowired
	private ProcessRepresentativeService processRepresentativeService;
	
	
	
	
	@GET
	@Path("/get/v1/{id}")
	@Produces("application/json;charset=UTF-8")
	public ResponseMsg<ProcessRepresentativeDTO> get(@PathParam("id") String id) throws Exception {
		ResponseMsg<ProcessRepresentativeDTO> responseMsg = new ResponseMsg<ProcessRepresentativeDTO>();
		ProcessRepresentativeDTO dto = processRepresentativeService.queryProcessRepresentativeByPrimaryKey(id);
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
	public ResponseMsg<String> save(ProcessRepresentativeDTO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = processRepresentativeService.insertProcessRepresentative(dto);
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
	public ResponseMsg<Integer> updateSensitive(ProcessRepresentativeDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = processRepresentativeService.updateProcessRepresentativeSensitive(dto);
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
	public ResponseMsg<Integer> updateAll(ProcessRepresentativeDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = processRepresentativeService.updateProcessRepresentative(dto);
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
		int count = processRepresentativeService.deleteProcessRepresentativeById(id);
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
	public ResponseMsg<QueryRespBean<ProcessRepresentativeDTO>> searchByPage(QueryReqBean<ProcessRepresentativeDTO> queryReqBean) throws Exception {
		ResponseMsg<QueryRespBean<ProcessRepresentativeDTO>> responseMsg = new ResponseMsg<QueryRespBean<ProcessRepresentativeDTO>>();
		QueryRespBean<ProcessRepresentativeDTO> queryRespBean = processRepresentativeService.searchProcessRepresentativeByPage(queryReqBean,"");
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
	public ResponseMsg<List<ProcessRepresentativeDTO>> search(QueryReqBean<ProcessRepresentativeDTO> queryReqBean) throws Exception {
		ResponseMsg<List<ProcessRepresentativeDTO>> responseMsg = new ResponseMsg<List<ProcessRepresentativeDTO>>();
		List<ProcessRepresentativeDTO> queryRespBean = processRepresentativeService.searchProcessRepresentative(queryReqBean);
		responseMsg.setResponseBody(queryRespBean);
		return responseMsg;
	}
	
}
