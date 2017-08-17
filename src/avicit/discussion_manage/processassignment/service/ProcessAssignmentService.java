package avicit.discussion_manage.processassignment.service;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.discussion_manage.discussionmanage.service.DiscussionManageService;
import avicit.discussion_manage.factorycomplex.dao.FactoryCensorManageDao;
import avicit.discussion_manage.factorycomplex.dao.FactoryComplexDao;
import avicit.discussion_manage.factorycomplex.dto.FactoryCensorManageDTO;
import avicit.discussion_manage.factorycomplex.dto.FactoryComplexDTO;
import avicit.discussion_manage.majormanage.service.MajorManageService;
import avicit.discussion_manage.processassignment.dao.ProcessAssignmentDao;
import avicit.discussion_manage.processassignment.dao.ProcessRouteDao;
import avicit.discussion_manage.processassignment.dto.ProcessAssignmentDTO;
import avicit.discussion_manage.processassignment.dto.ProcessRouteDTO;
import avicit.discussion_manage.structuremanage.service.StructureManageService;
import avicit.platform6.api.syslookup.SysLookupAPI;
import avicit.platform6.api.sysuser.SysDeptAPI;
import avicit.platform6.api.sysuser.SysRoleAPI;
import avicit.platform6.api.sysuser.dto.SysRole;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.sfn.intercept.SelfDefinedQuery;
import avicit.platform6.core.spring.SpringFactory;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname: ProcessAssignmentService
 * @description: 定义 工艺分工人员任务表实现类
 * @author: AVICIT DEV
 */
@Service
public class ProcessAssignmentService implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(ProcessAssignmentService.class);

	private static final long serialVersionUID = 1L;

	// @Autowired
	// private SysLogUtil sysLogUtil;

	@Autowired
	private ProcessAssignmentDao dao;
	@Autowired
	private ProcessRouteDao prodao;
	@Autowired
	private FactoryComplexDao factoryComdao;
	@Autowired
	private FactoryCensorManageDao factoryCendao;
	@Autowired
	private SysDeptAPI sysDeptAPI;
	@Autowired
	private SysLookupAPI sysLookupAPI;
	@Autowired
	private MajorManageService majorservice;
	@Autowired
	private DiscussionManageService discussionservice;
	@Autowired
	private StructureManageService structureManageService;
	/**
	 * 按条件分页查询
	 * 
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<ProcessAssignmentDTO> searchProcessAssignmentByPage(
			QueryReqBean<ProcessAssignmentDTO> queryReqBean, String sfnConditions) throws Exception {
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			ProcessAssignmentDTO searchParams = queryReqBean.getSearchParams();
			SelfDefinedQuery sdc = new SelfDefinedQuery(sfnConditions, "t1");
			Page<ProcessAssignmentDTO> dataList = dao.searchProcessAssignmentByPage(searchParams, sdc.parseSql());
			QueryRespBean<ProcessAssignmentDTO> queryRespBean = new QueryRespBean<ProcessAssignmentDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		} catch (DaoException e) {
			logger.error("searchProcessAssignmentByPaging出错：", e);
			throw e;
		}
	}

	/**
	 * 按条件查询，不分页
	 * 
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public List<ProcessAssignmentDTO> searchProcessAssignment(QueryReqBean<ProcessAssignmentDTO> queryReqBean)
			throws Exception {
		try {
			ProcessAssignmentDTO searchParams = queryReqBean.getSearchParams();
			List<ProcessAssignmentDTO> dataList = dao.searchProcessAssignment(searchParams);
			return dataList;
		} catch (Exception e) {
			logger.error("searchProcessAssignment出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 通过主键查询单条记录
	 * 
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public ProcessAssignmentDTO queryProcessAssignmentByPrimaryKey(String id) throws Exception {
		try {
			ProcessAssignmentDTO dto = dao.findProcessAssignmentById(id);
			// 记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		} catch (Exception e) {
			logger.error("searchProcessAssignment出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 新增对象
	 * 
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public String insertProcessAssignment(ProcessAssignmentDTO dto) throws Exception {
		try {
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertProcessAssignment(dto);
			// 记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		} catch (Exception e) {
			logger.error("insertProcessAssignment出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 修改对象全部字段
	 * 
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateProcessAssignment(ProcessAssignmentDTO dto) throws Exception {
		// 记录日志
		ProcessAssignmentDTO old = findById(dto.getId());
		SysLogUtil.log4Update(dto, old);
		PojoUtil.setSysProperties(dto, OpType.update);
		PojoUtil.copyProperties(old, dto, true);
		int ret = dao.updateProcessAssignmentAll(old);
		if (ret == 0) {
			throw new DaoException("数据失效，请重新更新");
		}
		return ret;

	}

	/**
	 * 修改对象部分字段
	 * 
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateProcessAssignmentSensitive(ProcessAssignmentDTO dto) throws Exception {
		try {
			// 记录日志
			ProcessAssignmentDTO old = findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto, true);
			int count = dao.updateProcessAssignmentSensitive(old);
			if (count == 0) {
				throw new DaoException("数据失效，请重新更新");
			}
			return count;
		} catch (Exception e) {
			logger.error("searchDemoBusinessTrip出错：", e);
			throw new DaoException(e.getMessage(), e);
		}

	}

	/**
	 * 按主键单条删除
	 * 
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int deleteProcessAssignmentById(String id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new Exception("删除失败！传入的参数主键为null");
		}
		try {
			// 记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteProcessAssignmentById(id);
		} catch (Exception e) {
			logger.error("deleteProcessAssignment出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 批量删除数据
	 * 
	 * @param ids
	 *            id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteProcessAssignmentByIds(String[] ids) throws Exception {
		int result = 0;
		for (String id : ids) {
			deleteProcessAssignmentById(id);
			result++;
		}
		return result;
	}

	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private ProcessAssignmentDTO findById(String id) throws Exception {
		try {
			ProcessAssignmentDTO dto = dao.findProcessAssignmentById(id);
			return dto;
		} catch (DaoException e) {
			logger.error("findProcessAssignment出错：", e);
			throw e;
		}
	}

	// 按照父id查询tree数据
	public List<Map<String, Object>> gettreeProcessassignmentByStrId(String parentId) {
		try {
			List<Map<String, Object>> tree = dao.gettreeProcessassignmentByStrId(parentId);

			for (Map<String, Object> node : tree) {
				String stid = (String) node.get("strId");
				List<Map<String, Object>> proute = dao.getprocessRoteByStrId(stid);
				if (proute != null && proute.size() > 0) {
					int i=0;
						Map<String, Object> nodes = proute.get(i);
						// 往同一个树形中重复赋值，是覆盖,他应该只有一条数据
						node.put("routeId", nodes.get("id") == null ? "" : nodes.get("id"));
						node.put("thermalUnit", nodes.get("thermalUnit") == null ? "" : nodes.get("thermalUnit"));
						node.put("manufacturingUnit",
								nodes.get("manufacturingUnit") == null ? "" : nodes.get("manufacturingUnit"));
						node.put("useUnit", nodes.get("useUnit") == null ? "" : nodes.get("useUnit"));
				}
				if(parentId.equals((String) node.get("strId"))){
					node.put("_parentId", "-1");
				}
			}
			return tree;
		} catch (DaoException e) {
			logger.error("gettreeProcessassignmentByStrId出错：", e);
			throw e;
		}
	}

	/**
	 * 新增对象
	 * 
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int insertProcessRoute(ProcessRouteDTO dto) throws Exception {
		try {
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			int ret = prodao.insertProcessRoute(dto);
			// 记录日志
			SysLogUtil.log4Insert(dto);
			return ret;
		} catch (Exception e) {
			logger.error("insertProcessRoute出错：", e);
			throw new DaoException("", e);
		}
	}

	/**
	 * 修改对象全部字段
	 * 
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateProcessRoute(ProcessRouteDTO dto) throws Exception {
		// 记录日志
		ProcessRouteDTO old = findByProSid(dto.getStrId());
		SysLogUtil.log4Update(dto, old);
		PojoUtil.setSysProperties(dto, OpType.update);
		PojoUtil.copyProperties(old, dto, true);
		int ret = prodao.updateProcessRouteSensitive(old);
		if (ret == 0) {
			throw new DaoException("数据失效，请重新更新");
		}
		return ret;

	}

	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private ProcessRouteDTO findByProSid(String id) throws Exception {
		try {
			ProcessRouteDTO dto = prodao.findByProSid(id);
			return dto;
		} catch (DaoException e) {
			logger.error("findProcessRoute出错：", e);
			throw e;
		}
	}

	/**
	 * //去除数组中重复的记录
	 */

	public String[] getrouteList(String[] thUnit) {
		List<String> list = new LinkedList<String>();
		for (int i = 0; i < thUnit.length; i++) {
			if (!list.contains(thUnit[i])) {
				list.add(thUnit[i]);
			}
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	/**
	 * 查找厂区模板对应记录
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map getworkGroup() {
		Map equTypeMap = new HashMap();// 设备类型
		List list = prodao.getFactoryMap();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = (Map<String, String>) list.get(i);
				String dept = map.get("dept");
				String user = map.get("user");
				equTypeMap.put(dept, user);
			}
		}
		return equTypeMap;
	}

	/**
	 * 插入路線之前 ，刪除該零件下的路線
	 * 
	 * @throws Exception
	 */
	public void deleteFectoryBySid(String id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new Exception("删除失败！传入的参数主键为null");
		}

		try {
			// 记录日志
			// SysLogUtil.log4Delete(findById(id));
			List<String> comId = dao.findFactoryComById(id);
			if (comId.size() > 0) {
				dao.deleteFactoryComById(id);
				for (int i = 0; i < comId.size(); i++) {
					dao.deleteFactoryCenById(id);
				}
			}
			/*int cenId = dao.findFactoryCenById(id);
			if (cenId > 0) {
				dao.deleteFactoryCenById(id);
			}*/
		} catch (Exception e) {
			logger.error("delete分工专业以及人员出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}
	
	public void insertFectory(FactoryComplexDTO dto, String thUnit) throws Exception {
		String id = ComUtil.getId();
		dto.setId(id);
		PojoUtil.setSysProperties(dto, OpType.insert);
		int ret = factoryComdao.insertFactoryComplex(dto);
		if (ret > 0) {
			String user = (String) this.getworkGroup().get(dto.getWorkshopId());
			if (user == null || "".equals(user)) {
				throw new DaoException("专业为:"+thUnit+"所对应的人员不存在");
			} else {
				String[] newuser = user.split(",");
				if (newuser.length > 0) {
					for (int i = 0; i < newuser.length; i++) {
						FactoryCensorManageDTO cendto = new FactoryCensorManageDTO();
						cendto.setStrId(dto.getStrId());
						cendto.setFcId(id);
						cendto.setDutyUserId(newuser[i]);
						String fid = ComUtil.getId();
						cendto.setId(fid);
						PojoUtil.setSysProperties(cendto, OpType.insert);
						factoryCendao.insertFactoryCensorManage(cendto);
						//审查任务添加
						ProcessAssignmentDTO processDto = new ProcessAssignmentDTO();
						//零件id
						processDto.setStrId(dto.getStrId());
						//2代表审查任务，1代表工艺分工
						processDto.setTaskType("2");
						//2代表分工的审查任务，1代表默认的审查任务
						processDto.setExamineStatu("2");
						//所指派的人员id
						processDto.setDutyUserId(newuser[i]);
						//分配时间
						processDto.setPublicationTime(new Date());
						this.insertProcessAssignment(processDto);
					}
				}
			}
		}
		// 记录日志
		SysLogUtil.log4Insert(dto);
	}

	public void insertFactoryDto(ProcessRouteDTO demo, String[] thUnit, String type) throws Exception {
		SysDeptAPI api = SpringFactory.getBean(SysDeptAPI.class);
		// 先刪除該零件對應的路線下的專業以及人員情況
		for (int i = 0; i < thUnit.length; i++) {
			String dept = api.getSysDeptIdByDeptCode(thUnit[i]);
			if (thUnit[i] != null && !"".equals(thUnit[i])) {
				if (dept != null && !"".equals(dept)) {
					FactoryComplexDTO dto = new FactoryComplexDTO();
					int count = factoryComdao.findByStrIdAndDeptId(demo.getStrId(), dept);
					if (count>0) {
						throw new DaoException("专业编号：" + thUnit[i] + "已存在默认专业中！");
					}
					dto.setStrId(demo.getStrId());
					dto.setWorkshopId(dept);
					dto.setRouteType(type);
					dto.setTemplate("N");
					SysLogUtil.log4Insert(dto);
					this.insertFectory(dto,thUnit[i]);
				} else {
					throw new DaoException("专业编号：" + thUnit[i] + "在系统中不存在！");
				}
			}
		}
	}
	/**
	 * 删除对应已分工零件操作
	 * @param demo 审查对象
	 * @throws Exception
	 */
	public void deleteFactoryDto(ProcessRouteDTO demo) throws Exception {
		ProcessAssignmentDTO processDto = new ProcessAssignmentDTO();
		//零件ID
		processDto.setStrId(demo.getStrId());
		//2代表分工的状态
		processDto.setExamineStatu("2");
		List<ProcessAssignmentDTO> process = dao.searchProcessAssignment(processDto);
		if(process.size()>0){//将要删除的零件id集合
			for (int j = 0; j < process.size(); j++) {
				if(process.get(j).getId()!=null&&!"".equals(process.get(j).getId())){
					//删除操作
					dao.deleteProcessAssignmentById(process.get(j).getId());
				}
			}
		}
	}
	//查看审查任务是否有重复
	public int serchAssingnmentBystrIdAndUserId(String strId,String userId) throws Exception{
		int count = dao.serchAssingnmentBystrIdAndUserId(strId, userId);
		return count;
		
	}
	/**
	 * 根据用户名查找用户审查任务
	 * @param String
	 * @return String
	 * @throws Exception
	 * xul
	 */

	public String getAssignmentByuserId(String userId) throws Exception{
		//查审查任务的str_id
		String str="";
		List<String> strIdlist= dao.getAssignmentByuserId(userId);
		if (strIdlist.size()==0) {
			return str;
		}else{
			for (int i = 0; i < strIdlist.size(); i++) {
				String strId = strIdlist.get(i);
				String strCode = structureManageService.getStrcodeByStrId(strId);
				String roseId = majorservice.getRolsByStrIdAndUserId(strId,userId);
				//String roseCode=sysDeptAPI.getSysDeptBySysDeptId(roseId).getDeptCode();
				
				String roseName=sysDeptAPI.getSysDeptNameBySysDeptId(roseId, "zh_CN");
				String statuscode= discussionservice.getStatusByStrId(strId);
				String status = sysLookupAPI.getNameByLooupTypeCodeAndLooupCodeByAppId("PLATFORM_TALK_STATUS", statuscode,"1");
				str+=strCode+"##"+roseName+"##"+status+"$$";
			}
			return str;
		}
		
	}
}
