package avicit.discussion_manage.profconfiguration.service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.sfn.intercept.SelfDefinedQuery;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.discussion_manage.profconfiguration.dao.ProfConfigurationDao;
import avicit.discussion_manage.profconfiguration.dto.ProfConfigurationDTO;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname:  ProfConfigurationService
 * @description: 定义 专业配置表实现类
 * @author:  AVICIT DEV
 */
@Service
public class ProfConfigurationService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(ProfConfigurationService.class);
	
    private static final long serialVersionUID = 1L;
    
//	@Autowired
//	private SysLogUtil sysLogUtil;
	
	@Autowired
	private ProfConfigurationDao dao;


	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<ProfConfigurationDTO> searchProfConfigurationByPage(QueryReqBean<ProfConfigurationDTO> queryReqBean,String sfnConditions) throws Exception {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			ProfConfigurationDTO searchParams = queryReqBean.getSearchParams();
            SelfDefinedQuery sdc=new SelfDefinedQuery(sfnConditions,"t1");
			Page<ProfConfigurationDTO> dataList =  dao.searchProfConfigurationByPage(searchParams,sdc.parseSql());
			QueryRespBean<ProfConfigurationDTO> queryRespBean =new QueryRespBean<ProfConfigurationDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(DaoException e){
			logger.error("searchProfConfigurationByPaging出错：", e);
			throw e;
		}
	}
	/**
	 * 按条件查询，不分页
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public List<ProfConfigurationDTO> searchProfConfiguration(QueryReqBean<ProfConfigurationDTO> queryReqBean) throws Exception {
	    try{
	    	ProfConfigurationDTO searchParams = queryReqBean.getSearchParams();
	    	List<ProfConfigurationDTO> dataList = dao.searchProfConfiguration(searchParams);
	    	return dataList;
	    }catch(Exception e){
	    	logger.error("searchProfConfiguration出错：", e);
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
	public ProfConfigurationDTO queryProfConfigurationByPrimaryKey(String id) throws Exception {
		try{
			ProfConfigurationDTO dto = dao.findProfConfigurationById(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchProfConfiguration出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}
	
	
	/**
	 * 通过typeId查询单条记录
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public ProfConfigurationDTO findProfConfigurationByTypeId(String typeId) throws Exception {
		try{
			ProfConfigurationDTO dto = dao.findProfConfigurationByTypeId(typeId);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchProfConfiguration出错：", e);
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
	public String insertProfConfiguration(ProfConfigurationDTO dto) throws Exception {
		try{
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertProfConfiguration(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		}catch(Exception e){
			logger.error("insertProfConfiguration出错：", e);
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
	public int updateProfConfiguration(ProfConfigurationDTO dto) throws Exception {
			//记录日志
			ProfConfigurationDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int ret = dao.updateProfConfigurationAll(old);
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
	public int updateProfConfigurationSensitive(ProfConfigurationDTO dto) throws Exception {
		try{
			//记录日志
			ProfConfigurationDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateProfConfigurationSensitive(old);
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
	public int deleteProfConfigurationById(String id) throws Exception {
		if(StringUtils.isEmpty(id)){
		   throw new Exception("删除失败！传入的参数主键为null");
		   }
		try{
			//记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteProfConfigurationById(id);
		}catch(Exception e){
			logger.error("deleteProfConfiguration出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteProfConfigurationByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteProfConfigurationById(id);
			result++;
		}
		return result;
	}
		
	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private ProfConfigurationDTO findById(String id) throws Exception {
		try{
			ProfConfigurationDTO dto = dao.findProfConfigurationById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("findProfConfiguration出错：", e);
	    		throw e;
	    }
	}
	/**
	 * 根据typeID和厂区标识查询对应专业
	 * @param param 配置专业
	 * @return dto配置专业对象
	 */
	public ProfConfigurationDTO searchProfConfigurationByTypeId(
			ProfConfigurationDTO param) {
		ProfConfigurationDTO dto = dao.searchProfConfigurationByTypeId(param);
		return dto;
	}
			

}

