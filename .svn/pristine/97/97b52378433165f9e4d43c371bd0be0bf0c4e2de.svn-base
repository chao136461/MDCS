package avicit.discussion_manage.disputememo.service;
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
import avicit.discussion_manage.disputememo.dao.DisputeMemoDao;
import avicit.discussion_manage.disputememo.dto.DisputeMemoDTO;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname:  DisputeMemoService
 * @description: 定义 争议备忘实现类
 * @author:  AVICIT DEV
 */
@Service
public class DisputeMemoService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(DisputeMemoService.class);
	
    private static final long serialVersionUID = 1L;
    
//	@Autowired
//	private SysLogUtil sysLogUtil;
	
	@Autowired
	private DisputeMemoDao dao;


	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<DisputeMemoDTO> searchDisputeMemoByPage(QueryReqBean<DisputeMemoDTO> queryReqBean,String sfnConditions) throws Exception {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			DisputeMemoDTO searchParams = queryReqBean.getSearchParams();
            SelfDefinedQuery sdc=new SelfDefinedQuery(sfnConditions,"t1");
			Page<DisputeMemoDTO> dataList =  dao.searchDisputeMemoByPage(searchParams,sdc.parseSql());
			QueryRespBean<DisputeMemoDTO> queryRespBean =new QueryRespBean<DisputeMemoDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(DaoException e){
			logger.error("searchDisputeMemoByPaging出错：", e);
			throw e;
		}
	}
	/**
	 * 按条件查询，不分页
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public List<DisputeMemoDTO> searchDisputeMemo(QueryReqBean<DisputeMemoDTO> queryReqBean) throws Exception {
	    try{
	    	DisputeMemoDTO searchParams = queryReqBean.getSearchParams();
	    	List<DisputeMemoDTO> dataList = dao.searchDisputeMemo(searchParams);
	    	return dataList;
	    }catch(Exception e){
	    	logger.error("searchDisputeMemo出错：", e);
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
	public DisputeMemoDTO queryDisputeMemoByPrimaryKey(String id) throws Exception {
		try{
			DisputeMemoDTO dto = dao.findDisputeMemoById(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchDisputeMemo出错：", e);
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
	public String insertDisputeMemo(DisputeMemoDTO dto) throws Exception {
		try{
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertDisputeMemo(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		}catch(Exception e){
			logger.error("insertDisputeMemo出错：", e);
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
	public int updateDisputeMemo(DisputeMemoDTO dto) throws Exception {
			//记录日志
			DisputeMemoDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int ret = dao.updateDisputeMemoAll(old);
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
	public int updateDisputeMemoSensitive(DisputeMemoDTO dto) throws Exception {
		try{
			//记录日志
			DisputeMemoDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateDisputeMemoSensitive(old);
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
	public int deleteDisputeMemoById(String id) throws Exception {
		if(StringUtils.isEmpty(id)){
		   throw new Exception("删除失败！传入的参数主键为null");
		   }
		try{
			//记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteDisputeMemoById(id);
		}catch(Exception e){
			logger.error("deleteDisputeMemo出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteDisputeMemoByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteDisputeMemoById(id);
			result++;
		}
		return result;
	}
		
	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private DisputeMemoDTO findById(String id) throws Exception {
		try{
			DisputeMemoDTO dto = dao.findDisputeMemoById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("findDisputeMemo出错：", e);
	    		throw e;
	    }
	}
	/**
	 * 查询对象全部字段
	 * @param id 建议id
	 * @return DisputeMemoDTO
	 * @throws Exception
	 */
	public DisputeMemoDTO queryDisputeMemoByFrenKey(String id) {
		
		try{
			DisputeMemoDTO dto = dao.queryDisputeMemoByFrenKey(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchDisputeMemo出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}
		

}

