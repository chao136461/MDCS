package avicit.discussion_manage.structuralrelationship.rest;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.core.rest.resteasy.RestEasyController;
import avicit.discussion_manage.structuralrelationship.dto.AddFormDataDTO;
import avicit.discussion_manage.structuralrelationship.service.StructuralRelationshipService;

@RestEasyController
@Path("/api/discussion_manage/structuralrelationship/StructuralRelationshipRest")
public class StructuralRelationshipRest{
    private static final Logger logger =  LoggerFactory.getLogger(StructuralRelationshipRest.class);
    
	@Autowired
	private StructuralRelationshipService structuralRelationshipService;
	/**
	 * 通过ID获取单条记录
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/get/v1/{id}")
	@Produces("application/json;charset=UTF-8")
	public ResponseMsg<AddFormDataDTO> getStructuralRelationshipDTOById(@PathParam("id") String id) throws Exception {
		ResponseMsg<AddFormDataDTO> responseMsg = new ResponseMsg<AddFormDataDTO>();
		AddFormDataDTO dto = structuralRelationshipService.getAddformDataDTOById(id);
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
	public ResponseMsg<String> save(AddFormDataDTO dto,HttpServletRequest request) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = structuralRelationshipService.insertStructuralRelationship(dto,request);
		responseMsg.setResponseBody(id);
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
	public ResponseMsg<Integer> updateAll(AddFormDataDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
	    structuralRelationshipService.updateStructureManage(dto);
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
	public ResponseMsg<Integer> deleteById(String[] id) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		 structuralRelationshipService.deleteStructuralRelationship(id);
		//responseMsg.setResponseBody(count);
		return responseMsg;
	}
	
	
}
