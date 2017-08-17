package avicit.discussion_manage.inforconfiguration.service;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.discussion_manage.inforconfiguration.dao.InforConfigurationDao;
import avicit.discussion_manage.inforconfiguration.dto.InforConfigurationDTO;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname:  InforConfigurationService
 * @description: 定义 信息配置表实现类
 * @author:  AVICIT DEV
 */
@Service
public class InforConfigurationService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(InforConfigurationService.class);
	
    private static final long serialVersionUID = 1L;
    
//	@Autowired
//	private SysLogUtil sysLogUtil;
	
	@Autowired
	private InforConfigurationDao dao;


	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<InforConfigurationDTO> searchInforConfigurationByPage(QueryReqBean<InforConfigurationDTO> queryReqBean) throws Exception {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			InforConfigurationDTO searchParams = queryReqBean.getSearchParams();

			Page<InforConfigurationDTO> dataList =  dao.searchInforConfigurationByPage(searchParams);
			QueryRespBean<InforConfigurationDTO> queryRespBean =new QueryRespBean<InforConfigurationDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(DaoException e){
			logger.error("searchInforConfigurationByPaging出错：", e);
			throw e;
		}
	}
	/**
	 * 按条件查询，不分页
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public List<InforConfigurationDTO> searchInforConfiguration(QueryReqBean<InforConfigurationDTO> queryReqBean) throws Exception {
	    try{
	    	InforConfigurationDTO searchParams = queryReqBean.getSearchParams();
	    	List<InforConfigurationDTO> dataList = dao.searchInforConfiguration(searchParams);
	    	return dataList;
	    }catch(Exception e){
	    	logger.error("searchInforConfiguration出错：", e);
	    	throw new DaoException("",e);
	    }
    }
	/**
	 * 通过主键查询单条记录
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public InforConfigurationDTO queryInforConfigurationByPrimaryKey(String id) throws Exception {
		try{
			InforConfigurationDTO dto = dao.findInforConfigurationById(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchInforConfiguration出错：", e);
	    	throw new DaoException("",e);
	    }
	}

	/**
	 * 新增对象
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public String insertInforConfiguration(InforConfigurationDTO dto) throws Exception {
		try{
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertInforConfiguration(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		}catch(Exception e){
			logger.error("insertInforConfiguration出错：", e);
			throw new DaoException("",e);
		}
	}
	/**
	 * 修改对象全部字段
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateInforConfiguration(InforConfigurationDTO dto) throws Exception {
			//记录日志
			InforConfigurationDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int ret = dao.updateInforConfigurationAll(old);
			if(ret ==0){
				throw new DaoException("数据失效，请从新更新");
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
	public int updateInforConfigurationSensitive(InforConfigurationDTO dto) throws Exception {
		try{
			//记录日志
			InforConfigurationDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateInforConfigurationSensitive(old);
			if(count ==0){
				throw new DaoException("数据失效，请从新更新");
			}
			return count;
		}catch(Exception e){
			logger.error("searchDemoBusinessTrip出错：", e);
			throw new DaoException("",e);
		}

	}
	
	/**
	 * 按主键单条删除
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int deleteInforConfigurationById(String id) throws Exception {
		if(StringUtils.isEmpty(id)) throw new Exception("删除失败！传入的参数主键为null");
		try{
			//记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteInforConfigurationById(id);
		}catch(Exception e){
			logger.error("deleteInforConfiguration出错：", e);
			throw new DaoException("",e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteInforConfigurationByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteInforConfigurationById(id);
			result++;
		}
		return result;
	}
	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private InforConfigurationDTO findById(String id) throws Exception {
		try{
			InforConfigurationDTO dto = dao.findInforConfigurationById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("findInforConfiguration出错：", e);
	    		throw e;
	    }
	}
	
	public InforConfigurationDTO findDataByType(String typeId) throws Exception {
		try{
			InforConfigurationDTO dto = dao.findDataByType(typeId);
			return dto;
		}catch(DaoException e){
	    		logger.error("findInforConfiguration出错：", e);
	    		throw e;
	    }
	}
		

}

