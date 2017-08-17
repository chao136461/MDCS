package avicit.discussion_manage.regionconfiguration.service;
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
import avicit.platform6.core.sfn.intercept.SelfDefinedQuery;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.discussion_manage.regionconfiguration.dao.RegionConfigurationDao;
import avicit.discussion_manage.regionconfiguration.dto.RegionConfigurationDTO;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname:  RegionConfigurationService
 * @description: 定义 区间配置表实现类
 * @author:  AVICIT DEV
 */
@Service
public class RegionConfigurationService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(RegionConfigurationService.class);
	
    private static final long serialVersionUID = 1L;
    
//	@Autowired
//	private SysLogUtil sysLogUtil;
	
	@Autowired
	private RegionConfigurationDao dao;


	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<RegionConfigurationDTO> searchRegionConfigurationByPage(QueryReqBean<RegionConfigurationDTO> queryReqBean,String sfnConditions) throws Exception {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			RegionConfigurationDTO searchParams = queryReqBean.getSearchParams();
            SelfDefinedQuery sdc=new SelfDefinedQuery(sfnConditions,"t1");
			Page<RegionConfigurationDTO> dataList =  dao.searchRegionConfigurationByPage(searchParams,sdc.parseSql());
			QueryRespBean<RegionConfigurationDTO> queryRespBean =new QueryRespBean<RegionConfigurationDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(DaoException e){
			logger.error("searchRegionConfigurationByPaging出错：", e);
			throw e;
		}
	}
	/**
	 * 按条件查询，不分页
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public List<RegionConfigurationDTO> searchRegionConfiguration(QueryReqBean<RegionConfigurationDTO> queryReqBean) throws Exception {
	    try{
	    	RegionConfigurationDTO searchParams = queryReqBean.getSearchParams();
	    	List<RegionConfigurationDTO> dataList = dao.searchRegionConfiguration(searchParams);
	    	return dataList;
	    }catch(Exception e){
	    	logger.error("searchRegionConfiguration出错：", e);
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
	public RegionConfigurationDTO queryRegionConfigurationByPrimaryKey(String id) throws Exception {
		try{
			RegionConfigurationDTO dto = dao.findRegionConfigurationById(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchRegionConfiguration出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}
	
	
	/**
	 * 通过型号和等级查询单条记录
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public RegionConfigurationDTO findRegionConfiguration(String regionLevel,String typeId) throws Exception {
		try{
			RegionConfigurationDTO dto = dao.findRegionConfiguration(regionLevel,typeId);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchRegionConfiguration出错：", e);
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
	public String insertRegionConfiguration(RegionConfigurationDTO dto) throws Exception {
		try{
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertRegionConfiguration(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		}catch(Exception e){
			logger.error("insertRegionConfiguration出错：", e);
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
	public int updateRegionConfiguration(RegionConfigurationDTO dto) throws Exception {
			//记录日志
			RegionConfigurationDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int ret = dao.updateRegionConfigurationAll(old);
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
	public int updateRegionConfigurationSensitive(RegionConfigurationDTO dto) throws Exception {
		try{
			//记录日志
			RegionConfigurationDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateRegionConfigurationSensitive(old);
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
	public int deleteRegionConfigurationById(String id) throws Exception {
		if(StringUtils.isEmpty(id)){
		   throw new Exception("删除失败！传入的参数主键为null");
		   }
		try{
			//记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteRegionConfigurationById(id);
		}catch(Exception e){
			logger.error("deleteRegionConfiguration出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteRegionConfigurationByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteRegionConfigurationById(id);
			result++;
		}
		return result;
	}
		
	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private RegionConfigurationDTO findById(String id) throws Exception {
		try{
			RegionConfigurationDTO dto = dao.findRegionConfigurationById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("findRegionConfiguration出错：", e);
	    		throw e;
	    }
	}
	/**
	 *根据天数获取对应颜色 
	 * @param day 天数
	 * @return color
	 * @author WCL
	 */
	public String findRegionColor(long day,String type) {

		String color = dao.findRegionColor(day,type);
		return color;
	}
		

}

