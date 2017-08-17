package avicit.discussion_manage.regionconfiguration.rest;

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

import avicit.discussion_manage.regionconfiguration.dto.RegionConfigurationDTO;
import avicit.discussion_manage.regionconfiguration.service.RegionConfigurationService;

@RestEasyController
@Path("/api/discussion_manage/regionconfiguration/RegionConfigurationRest")
public class RegionConfigurationRest{
    private static final Logger logger =  LoggerFactory.getLogger(RegionConfigurationRest.class);
    
	@Autowired
	private RegionConfigurationService regionConfigurationService;
	
	
	
	
	@GET
	@Path("/get/v1/{id}")
	@Produces("application/json;charset=UTF-8")
	public ResponseMsg<RegionConfigurationDTO> get(@PathParam("id") String id) throws Exception {
		ResponseMsg<RegionConfigurationDTO> responseMsg = new ResponseMsg<RegionConfigurationDTO>();
		RegionConfigurationDTO dto = regionConfigurationService.queryRegionConfigurationByPrimaryKey(id);
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
	public ResponseMsg<String> save(RegionConfigurationDTO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id = regionConfigurationService.insertRegionConfiguration(dto);
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
	public ResponseMsg<Integer> updateSensitive(RegionConfigurationDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = regionConfigurationService.updateRegionConfigurationSensitive(dto);
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
	public ResponseMsg<Integer> updateAll(RegionConfigurationDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = regionConfigurationService.updateRegionConfiguration(dto);
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
		int count = regionConfigurationService.deleteRegionConfigurationById(id);
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
	public ResponseMsg<QueryRespBean<RegionConfigurationDTO>> searchByPage(QueryReqBean<RegionConfigurationDTO> queryReqBean) throws Exception {
		ResponseMsg<QueryRespBean<RegionConfigurationDTO>> responseMsg = new ResponseMsg<QueryRespBean<RegionConfigurationDTO>>();
		QueryRespBean<RegionConfigurationDTO> queryRespBean = regionConfigurationService.searchRegionConfigurationByPage(queryReqBean,"");
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
	public ResponseMsg<List<RegionConfigurationDTO>> search(QueryReqBean<RegionConfigurationDTO> queryReqBean) throws Exception {
		ResponseMsg<List<RegionConfigurationDTO>> responseMsg = new ResponseMsg<List<RegionConfigurationDTO>>();
		List<RegionConfigurationDTO> queryRespBean = regionConfigurationService.searchRegionConfiguration(queryReqBean);
		responseMsg.setResponseBody(queryRespBean);
		return responseMsg;
	}
	
}
