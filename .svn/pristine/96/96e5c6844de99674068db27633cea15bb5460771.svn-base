package avicit.discussion_manage.factorycomplex.service;
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

import avicit.discussion_manage.factorycomplex.dto.FactoryCensorManageDTO;
import avicit.discussion_manage.factorycomplex.dao.FactoryCensorManageDao;

import avicit.platform6.modules.system.syslog.service.SysLogUtil;


/**
 * @classname: FactoryCensorManageService
 * @description: 定义  厂区审查人员管理 实现类
 * @author:  AVICIT DEV
 */
@Service
public class FactoryCensorManageService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(FactoryCensorManageService.class);
	
    private static final long serialVersionUID = 1L;
    
	@Autowired
	private FactoryCensorManageDao dao;
	/**
	 * 通过主键查询单条记录
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public FactoryCensorManageDTO queryFactoryCensorManageByPrimaryKey(String id) throws Exception {
		try{
			FactoryCensorManageDTO dto = dao.findFactoryCensorManageById(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchFactoryCensorManage出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}
	/**
	 * 通过父键查询单条记录
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public List<FactoryCensorManageDTO> queryFactoryCensorManageByPid(String pid) throws Exception {
		try{
			List<FactoryCensorManageDTO> dto = dao.findFactoryCensorManageByPid(pid);
			//记录日志
			return dto;
		}catch(Exception e){
	    	logger.error("searchFactoryCensorManage出错：", e);
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
	public String insertFactoryCensorManage(FactoryCensorManageDTO dto) throws Exception {
		try{
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertFactoryCensorManage(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		}catch(Exception e){
			logger.error("insertFactoryCensorManage出错：", e);
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
	public int updateFactoryCensorManage(FactoryCensorManageDTO dto) throws Exception {
			
			//记录日志
			FactoryCensorManageDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto,old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateFactoryCensorManageAll(old);
			if(count ==0){
				throw new DaoException("数据失效，请重新更新");
			}
			return count;

	}
	/**
	 * 修改对象部分字段
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateFactoryCensorManageSensitive(FactoryCensorManageDTO dto) throws Exception {
		try{
			//记录日志
			FactoryCensorManageDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, findById(dto.getId()));
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateFactoryCensorManageSensitive(old);
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
	public int deleteFactoryCensorManageById(String id) throws Exception {
		if(StringUtils.isEmpty(id)){
		throw new Exception("删除失败！传入的参数主键为null");
		}
		try{
			//记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteFactoryCensorManageById(id);
		}catch(Exception e){
			logger.error("searchFactoryCensorManage出错：", e);
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
	public int deleteFactoryCensorManage(FactoryCensorManageDTO dto) throws Exception {
		try{
			//记录日志
			SysLogUtil.log4Delete(dto);
			return dao.deleteFactoryCensorManageById(dto.getId());
		}catch(Exception e){
			logger.error("searchFactoryCensorManage出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteFactoryCensorManageByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteFactoryCensorManageById(id);
			result++;
		}
		return result;
	}
	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private FactoryCensorManageDTO findById(String id) throws Exception {
		try{
			FactoryCensorManageDTO dto = dao.findFactoryCensorManageById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("searchFactoryCensorManage出错：", e);
	    		throw e;
	    }
	}

}

