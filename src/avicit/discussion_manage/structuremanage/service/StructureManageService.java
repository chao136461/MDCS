package avicit.discussion_manage.structuremanage.service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.platform6.api.sysuser.dto.SysDept;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.sfn.intercept.SelfDefinedQuery;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.discussion_manage.majormanage.dao.InstituteCensorManageDao;
import avicit.discussion_manage.majormanage.dao.MajorManageDao;
import avicit.discussion_manage.structuremanage.dao.StructureManageDao;
import avicit.discussion_manage.structuremanage.dto.ResearchDTO;
import avicit.discussion_manage.structuremanage.dto.StructureManageDTO;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;
import avicit.discussion_manage.majormanage.dto.MajorManageDTO;
import avicit.discussion_manage.majormanage.dto.InstituteCensorManageDTO;
import avicit.discussion_manage.majormanage.service.InstituteCensorManageService;
import avicit.discussion_manage.majormanage.service.MajorManageService;
/**
 * @classname:  StructureManageService
 * @description: 定义 装配件结构管理实现类
 * @author:  AVICIT DEV
 */
@Service
public class StructureManageService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(StructureManageService.class);
	
    private static final long serialVersionUID = 1L;
    
//	@Autowired
//	private SysLogUtil sysLogUtil;
	
	@Autowired
	private StructureManageDao dao;
	@Autowired
	private MajorManageService majorService;
	@Autowired
	private InstituteCensorManageService instituteService;

	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<StructureManageDTO> searchStructureManageByPage(QueryReqBean<StructureManageDTO> queryReqBean,String sfnConditions) throws Exception {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			StructureManageDTO searchParams = queryReqBean.getSearchParams();
            SelfDefinedQuery sdc=new SelfDefinedQuery(sfnConditions,"t1");
			Page<StructureManageDTO> dataList =  dao.searchStructureManageByPage(searchParams,sdc.parseSql());
			QueryRespBean<StructureManageDTO> queryRespBean =new QueryRespBean<StructureManageDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(DaoException e){
			logger.error("searchStructureManageByPaging出错：", e);
			throw e;
		}
	}
	/**
	 * 按条件查询，不分页
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public List<StructureManageDTO> searchStructureManage(QueryReqBean<StructureManageDTO> queryReqBean) throws Exception {
	    try{
	    	StructureManageDTO searchParams = queryReqBean.getSearchParams();
	    	List<StructureManageDTO> dataList = dao.searchStructureManage(searchParams);
	    	return dataList;
	    }catch(Exception e){
	    	logger.error("searchStructureManage出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
    }
	/**
	 * 通过主键查询单条记录
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public StructureManageDTO queryStructureManageByPrimaryKey(String id) throws Exception {
		try{
			StructureManageDTO dto = dao.findStructureManageById(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchStructureManage出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}

	/**
	 * 新增对象
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public String insertStructureManage(StructureManageDTO dto) throws Exception {
		try{
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertStructureManage(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		}catch(Exception e){
			logger.error("insertStructureManage出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}
	/**
	 * 修改对象全部字段
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateStructureManage(StructureManageDTO dto) throws Exception {
			//记录日志
			StructureManageDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int ret = dao.updateStructureManageAll(old);
			if(ret ==0){
				throw new DaoException("数据失效，请重新更新");
			}
			return ret;

	}
	/**
	 * 修改对象部分字段
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateStructureManageSensitive(StructureManageDTO dto) throws Exception {
		try{
			//记录日志
			StructureManageDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateStructureManageSensitive(old);
			if(count ==0){
				throw new DaoException("数据失效，请重新更新");
			}
			return count;
		}catch(Exception e){
			logger.error("searchDemoBusinessTrip出错：", e);
			throw new DaoException(e.getMessage(),e);
		}

	}
	
	/**
	 * 按主键单条删除
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int deleteStructureManageById(String id) throws Exception {
		if(StringUtils.isEmpty(id)){
		   throw new Exception("删除失败！传入的参数主键为null");
		   }
		try{
			//记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteStructureManageById(id);
		}catch(Exception e){
			logger.error("deleteStructureManage出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteStructureManageByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteStructureManageById(id);
			result++;
		}
		return result;
	}
		
	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private StructureManageDTO findById(String id) throws Exception {
		try{
			StructureManageDTO dto = dao.findStructureManageById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("findStructureManage出错：", e);
	    		throw e;
	    }
	}
	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	public StructureManageDTO findByCode(String code) throws Exception {
		try{
			StructureManageDTO dto = dao.findStructureManageByCode(code);
			return dto;
		}catch(DaoException e){
	    		logger.error("findStructureManage出错：", e);
	    		throw e;
	    }
	}
	public String findClassType(String strId){
		String classType = "";
		try {
			classType = dao.findClassTypeInfo(strId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return classType;
	}
	public String getStrcodeByStrId(String strId) throws Exception{
		StructureManageDTO dto =this.queryStructureManageByPrimaryKey(strId);
		String strCode = dto.getClassCode();
		return strCode ;
		
	}
}

