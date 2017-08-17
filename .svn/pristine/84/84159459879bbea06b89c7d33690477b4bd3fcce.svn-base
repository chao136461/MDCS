package avicit.discussion_manage.inforconfiguration.rest;

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

import avicit.discussion_manage.inforconfiguration.dto.InforConfigurationDTO;
import avicit.discussion_manage.inforconfiguration.service.InforConfigurationService;

@RestEasyController
@Path("/api/discussion_manage/inforconfiguration/InforConfigurationRest")
public class InforConfigurationRest{
    private static final Logger logger =  LoggerFactory.getLogger(InforConfigurationRest.class);
    
	@Autowired
	private InforConfigurationService inforConfigurationService;
	/**
	 * 通过ID获取单条记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/get/v1/{id}")
	@Produces("application/json;charset=UTF-8")
	public ResponseMsg<InforConfigurationDTO> get(@PathParam("id") String id) throws Exception {
		ResponseMsg<InforConfigurationDTO> responseMsg = new ResponseMsg<InforConfigurationDTO>();
		InforConfigurationDTO dto = inforConfigurationService.queryInforConfigurationByPrimaryKey(id);
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
	@Path("/save/v1/")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<String> save(InforConfigurationDTO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = inforConfigurationService.insertInforConfiguration(dto);
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
	@Path("/updateSensitive/v1/")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> updateSensitive(InforConfigurationDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = inforConfigurationService.updateInforConfigurationSensitive(dto);
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
	@Path("/updateAll/v1/")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> updateAll(InforConfigurationDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = inforConfigurationService.updateInforConfiguration(dto);
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
	@Path("/deleteById/v1/")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<Integer> deleteById(String id) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = inforConfigurationService.deleteInforConfigurationById(id);
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
	@Path("/searchByPage/v1/")
	@Produces("application/json;charset=UTF-8")
	@Consumes("application/json;charset=UTF-8")
	public ResponseMsg<QueryRespBean<InforConfigurationDTO>> searchByPage(QueryReqBean<InforConfigurationDTO> queryReqBean) throws Exception {
		ResponseMsg<QueryRespBean<InforConfigurationDTO>> responseMsg = new ResponseMsg<QueryRespBean<InforConfigurationDTO>>();
		QueryRespBean<InforConfigurationDTO> queryRespBean = inforConfigurationService.searchInforConfigurationByPage(queryReqBean);
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
	public ResponseMsg<List<InforConfigurationDTO>> search(QueryReqBean<InforConfigurationDTO> queryReqBean) throws Exception {
		ResponseMsg<List<InforConfigurationDTO>> responseMsg = new ResponseMsg<List<InforConfigurationDTO>>();
		List<InforConfigurationDTO> queryRespBean = inforConfigurationService.searchInforConfiguration(queryReqBean);
		responseMsg.setResponseBody(queryRespBean);
		return responseMsg;
	}
	
}
