package avicit.discussion_manage.processrepresentative.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.platform6.bpmconsole.action.ProcessAccessAction;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.sfn.intercept.SelfDefinedQuery;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.discussion_manage.processassignment.dao.ProcessAssignmentDao;
import avicit.discussion_manage.processassignment.dto.ProcessAssignmentDTO;
import avicit.discussion_manage.processrepresentative.dao.ProcessRepresentativeDao;
import avicit.discussion_manage.processrepresentative.dto.ProcessRepresentativeDTO;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname: ProcessRepresentativeService
 * @description: 定义 工艺代表任务表实现类
 * @author: AVICIT DEV
 */
@Service
public class ProcessRepresentativeService implements Serializable {

	private static final Logger logger = LoggerFactory
			.getLogger(ProcessRepresentativeService.class);

	private static final long serialVersionUID = 1L;

	// @Autowired
	// private SysLogUtil sysLogUtil;

	@Autowired
	private ProcessRepresentativeDao dao;
	@Autowired
	private ProcessAssignmentDao Prodao;

	/**
	 * 按条件分页查询
	 * 
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<ProcessRepresentativeDTO> searchProcessRepresentativeByPage(
			QueryReqBean<ProcessRepresentativeDTO> queryReqBean,
			String sfnConditions) throws Exception {
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			ProcessRepresentativeDTO searchParams = queryReqBean
					.getSearchParams();
			SelfDefinedQuery sdc = new SelfDefinedQuery(sfnConditions, "t1");
			Page<ProcessRepresentativeDTO> dataList = dao
					.searchProcessRepresentativeByPage(searchParams,
							sdc.parseSql());
			QueryRespBean<ProcessRepresentativeDTO> queryRespBean = new QueryRespBean<ProcessRepresentativeDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		} catch (DaoException e) {
			logger.error("searchProcessRepresentativeByPaging出错：", e);
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
	public List<ProcessRepresentativeDTO> searchProcessRepresentative(
			QueryReqBean<ProcessRepresentativeDTO> queryReqBean)
			throws Exception {
		try {
			ProcessRepresentativeDTO searchParams = queryReqBean
					.getSearchParams();
			List<ProcessRepresentativeDTO> dataList = dao
					.searchProcessRepresentative(searchParams);
			return dataList;
		} catch (Exception e) {
			logger.error("searchProcessRepresentative出错：", e);
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
	public ProcessRepresentativeDTO queryProcessRepresentativeByPrimaryKey(
			String id) throws Exception {
		try {
			ProcessRepresentativeDTO dto = dao
					.findProcessRepresentativeById(id);
			// 记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		} catch (Exception e) {
			logger.error("searchProcessRepresentative出错：", e);
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
	public String insertProcessRepresentative(ProcessRepresentativeDTO dto)
			throws Exception {
		try {
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertProcessRepresentative(dto);
			// 记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		} catch (Exception e) {
			logger.error("insertProcessRepresentative出错：", e);
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
	public int updateProcessRepresentative(ProcessRepresentativeDTO dto)
			throws Exception {
		// 记录日志
		ProcessRepresentativeDTO old = findById(dto.getId());
		SysLogUtil.log4Update(dto, old);
		PojoUtil.setSysProperties(dto, OpType.update);
		PojoUtil.copyProperties(old, dto, true);
		int ret = dao.updateProcessRepresentativeAll(old);
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
	public int updateProcessRepresentativeSensitive(ProcessRepresentativeDTO dto)
			throws Exception {
		try {
			// 记录日志
			ProcessRepresentativeDTO old = findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			
			// 根据工艺分工人员信息确定是否已经分配人员
			if (dto.getProcessUserId().equals("")
					|| "" == dto.getProcessUserId()
					|| dto.getProcessUserId() == null) {
				ProcessAssignmentDTO assignmentDTO = findProById(old.getStrId());
				if (assignmentDTO != null) {
					insertorUpdatePro(assignmentDTO, dto, "delete");
				}
			} else {
				ProcessAssignmentDTO assignmentDTO = findProById(old.getStrId());
				// 根据是否存在该零件下的工艺分工人员任务情况，选择insert or update。
				if (assignmentDTO == null) {
					insertorUpdatePro(assignmentDTO, dto, "insert");
				} else {
					if (!dto.getProcessUserId().equals(old.getProcessUserId())) {
						insertorUpdatePro(assignmentDTO, dto, "update");
					}
				}
			}
			PojoUtil.copyProperties(old, dto, true);
			int count = dao.updateProcessRepresentativeSensitive(old);
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
	 * 根据工艺分工人员新增任务
	 * 
	 * @param assignmentDTO
	 * @param old
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public void insertorUpdatePro(ProcessAssignmentDTO assignmentDTO,
			ProcessRepresentativeDTO old, String type) throws Exception {
		try {
			if (type.equals("insert")) {
				// 由于根据零件ID查询到，工艺分工任务不存在，所以新增零件相关工艺分工任务
				assignmentDTO = new ProcessAssignmentDTO();
				assignmentDTO.setPublicationTime(new Date());
				assignmentDTO.setStatus("N");
				assignmentDTO.setTaskType("1");
				assignmentDTO.setStrId(old.getStrId());
				assignmentDTO.setDutyUserId(old.getProcessUserId());
				String id = ComUtil.getId();
				assignmentDTO.setId(id);
				SysLogUtil.log4Insert(assignmentDTO);
				PojoUtil.setSysProperties(assignmentDTO, OpType.insert);
				Prodao.insertProcessAssignment(assignmentDTO);
			} else if (type.equals("update")) {
				// 由于根据零件ID查询到，工艺分工任务已经存在，但是更换了工艺人员，所以更新工艺责任人
				assignmentDTO.setDutyUserId(old.getProcessUserId());
				assignmentDTO.setPublicationTime(new Date());
				PojoUtil.setSysProperties(assignmentDTO, OpType.update);
				Prodao.updateProcessAssignmentSensitive(assignmentDTO);
			} else if (type.equals("delete")) {
				// 由于根据工艺代表清空工艺分工人员，所以清除掉工艺分工人员任务
				PojoUtil.setSysProperties(assignmentDTO, OpType.delete);
				Prodao.deleteProcessAssignmentById(assignmentDTO.getId());
			}
		} catch (Exception e) {
			logger.error("updateProcessAssignmentDTO出错：", e);
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
	public int deleteProcessRepresentativeById(String id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new Exception("删除失败！传入的参数主键为null");
		}
		try {
			// 记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteProcessRepresentativeById(id);
		} catch (Exception e) {
			logger.error("deleteProcessRepresentative出错：", e);
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
	public int deleteProcessRepresentativeByIds(String[] ids) throws Exception {
		int result = 0;
		for (String id : ids) {
			deleteProcessRepresentativeById(id);
			result++;
		}
		return result;
	}

	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private ProcessRepresentativeDTO findById(String id) throws Exception {
		try {
			ProcessRepresentativeDTO dto = dao
					.findProcessRepresentativeById(id);
			return dto;
		} catch (DaoException e) {
			logger.error("findProcessRepresentative出错：", e);
			throw e;
		}
	}

	/**
	 * 查找工艺分工人员任务
	 */
	private ProcessAssignmentDTO findProById(String id) throws Exception {
		try {
			ProcessAssignmentDTO dto = Prodao.findProcessAssignmentBySid(id);
			return dto;
		} catch (DaoException e) {
			logger.error("findProcessAssignment出错：", e);
			throw e;
		}
	}
}
