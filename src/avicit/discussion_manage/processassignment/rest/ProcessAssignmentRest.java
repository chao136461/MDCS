package avicit.discussion_manage.processassignment.rest;

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

import avicit.discussion_manage.processassignment.dto.ProcessAssignmentDTO;
import avicit.discussion_manage.processassignment.service.ProcessAssignmentService;

@RestEasyController
@Path("/api/discussion_manage/processassignment/ProcessAssignmentRest")
public class ProcessAssignmentRest{
    private static final Logger logger =  LoggerFactory.getLogger(ProcessAssignmentRest.class);
    
	@Autowired
	private ProcessAssignmentService processAssignmentService;
	
	
	
	
	@GET
	@Path("/get/v1/{id}")
	@Produces("application/json;charset=UTF-8")
	public ResponseMsg<ProcessAssignmentDTO> get(@PathParam("id") String id) throws Exception {
		ResponseMsg<ProcessAssignmentDTO> responseMsg = new ResponseMsg<ProcessAssignmentDTO>();
		ProcessAssignmentDTO dto = processAssignmentService.queryProcessAssignmentByPrimaryKey(id);
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
	public ResponseMsg<String> save(ProcessAssignmentDTO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = processAssignmentService.insertProcessAssignment(dto);
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
	public ResponseMsg<Integer> updateSensitive(ProcessAssignmentDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = processAssignmentService.updateProcessAssignmentSensitive(dto);
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
	public ResponseMsg<Integer> updateAll(ProcessAssignmentDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = processAssignmentService.updateProcessAssignment(dto);
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
		int count = processAssignmentService.deleteProcessAssignmentById(id);
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
	public ResponseMsg<QueryRespBean<ProcessAssignmentDTO>> searchByPage(QueryReqBean<ProcessAssignmentDTO> queryReqBean) throws Exception {
		ResponseMsg<QueryRespBean<ProcessAssignmentDTO>> responseMsg = new ResponseMsg<QueryRespBean<ProcessAssignmentDTO>>();
		QueryRespBean<ProcessAssignmentDTO> queryRespBean = processAssignmentService.searchProcessAssignmentByPage(queryReqBean,"");
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
	public ResponseMsg<List<ProcessAssignmentDTO>> search(QueryReqBean<ProcessAssignmentDTO> queryReqBean) throws Exception {
		ResponseMsg<List<ProcessAssignmentDTO>> responseMsg = new ResponseMsg<List<ProcessAssignmentDTO>>();
		List<ProcessAssignmentDTO> queryRespBean = processAssignmentService.searchProcessAssignment(queryReqBean);
		responseMsg.setResponseBody(queryRespBean);
		return responseMsg;
	}
	
}
