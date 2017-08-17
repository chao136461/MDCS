package avicit.discussion_manage.majormanage.rest;

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

import avicit.discussion_manage.majormanage.dto.MajorManageDTO;
import avicit.discussion_manage.majormanage.dto.InstituteCensorManageDTO;
import avicit.discussion_manage.majormanage.service.MajorManageService;
import avicit.discussion_manage.majormanage.service.InstituteCensorManageService;
//import avicit.discussion_manage.majormanage.service.Service;


@RestEasyController
@Path("/api/discussion_manage/majormanage/MajorManageRest")
public class MajorManageRest{
    private static final Logger logger =  LoggerFactory.getLogger(MajorManageRest.class);
    
	@Autowired
	private MajorManageService majorManageService;
	
	@Autowired
	private InstituteCensorManageService instituteCensorManageService;
	
	/**
	 * 通过主表id获得主表数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/getMain/v1/{id}")
	@Produces("application/json;charset=UTF-8")
	public ResponseMsg<MajorManageDTO> getMainById(@PathParam("id") String id) throws Exception {
		ResponseMsg<MajorManageDTO> responseMsg = new ResponseMsg<MajorManageDTO>();
		MajorManageDTO dto = majorManageService.queryMajorManageByPrimaryKey(id);
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
	public ResponseMsg<String> saveMain(MajorManageDTO dto) throws Exception {
		ResponseMsg<String> responseMsg = new ResponseMsg<String>();
		String id =  majorManageService.insertMajorManage(dto);
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
	public ResponseMsg<Integer> updateMainSensitive(MajorManageDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = majorManageService.updateMajorManageSensitive(dto);
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
	public ResponseMsg<Integer> updateMainAll(MajorManageDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = majorManageService.updateMajorManage(dto);
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
	public ResponseMsg<QueryRespBean<MajorManageDTO>> searchMainByPage(QueryReqBean<MajorManageDTO> queryReqBean,String sfnConditions) throws Exception {
		ResponseMsg<QueryRespBean<MajorManageDTO>> responseMsg = new ResponseMsg<QueryRespBean<MajorManageDTO>>();
		QueryRespBean<MajorManageDTO> queryRespBean = majorManageService.searchMajorManageByPage(queryReqBean,sfnConditions);
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
	public ResponseMsg<List<MajorManageDTO>> searchMain(QueryReqBean<MajorManageDTO> queryReqBean) throws Exception {
		ResponseMsg<List<MajorManageDTO>> responseMsg = new ResponseMsg<List<MajorManageDTO>>();
		List<MajorManageDTO> queryRespBean = majorManageService.searchMajorManage(queryReqBean);
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
		int count = majorManageService.deleteMajorManageById(id);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
	
	//*********************************子表操作*********************************************
	//按照父id获得子表数据
	@GET
	@Path("/getSubByPid/v1/{pid}")
	@Produces("application/json;charset=UTF-8")
	public ResponseMsg<List<InstituteCensorManageDTO>> getSubByPid(@PathParam("pid") String pid) throws Exception {
		ResponseMsg<List<InstituteCensorManageDTO>> responseMsg = new ResponseMsg<List<InstituteCensorManageDTO>>();
		List<InstituteCensorManageDTO> dto = instituteCensorManageService.queryInstituteCensorManageByPid(pid);
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
	public ResponseMsg<InstituteCensorManageDTO> getSubById(@PathParam("id") String id) throws Exception {
		ResponseMsg<InstituteCensorManageDTO> responseMsg = new ResponseMsg<InstituteCensorManageDTO>();
		InstituteCensorManageDTO dto = instituteCensorManageService.queryInstituteCensorManageByPrimaryKey(id);
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
	public ResponseMsg<Integer> updateSubSensitive(InstituteCensorManageDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = instituteCensorManageService.updateInstituteCensorManage(dto);
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
	public ResponseMsg<Integer> updateSubAll(InstituteCensorManageDTO dto) throws Exception {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<Integer>();
		int count = instituteCensorManageService.updateInstituteCensorManageSensitive(dto);
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
		int count = instituteCensorManageService.deleteInstituteCensorManageById(id);
		responseMsg.setResponseBody(count);
		return responseMsg;
	}
}
