package avicit.discussion_manage.relevancepersonbranch.rest;

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

import avicit.discussion_manage.relevancepersonbranch.dto.RelevancePersonBranchDTO;
import avicit.discussion_manage.relevancepersonbranch.service.RelevancePersonBranchService;

@RestEasyController
@Path("/api/discussion_manage/relevancepersonbranch/RelevancePersonBranchRest")
public class RelevancePersonBranchRest{
    private static final Logger logger =  LoggerFactory.getLogger(RelevancePersonBranchRest.class);
    
	@Autowired
	private RelevancePersonBranchService relevancePersonBranchService;
	
	
	
	
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
	public ResponseMsg<Integer> updateAll(RelevancePersonBranchDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = relevancePersonBranchService.updateRelevancePersonBranch(dto);
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
		int count = relevancePersonBranchService.deleteRelevancePersonBranchById(id);
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
	public ResponseMsg<QueryRespBean<RelevancePersonBranchDTO>> searchByPage(QueryReqBean<RelevancePersonBranchDTO> queryReqBean) throws Exception {
		ResponseMsg<QueryRespBean<RelevancePersonBranchDTO>> responseMsg = new ResponseMsg<QueryRespBean<RelevancePersonBranchDTO>>();
		QueryRespBean<RelevancePersonBranchDTO> queryRespBean = relevancePersonBranchService.searchRelevancePersonBranchByPage(queryReqBean,null);
		responseMsg.setResponseBody(queryRespBean);
		return responseMsg;
	}
	
	
}
