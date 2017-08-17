package avicit.discussion_manage.majormanage.service;
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

import avicit.discussion_manage.majormanage.dto.InstituteCensorManageDTO;
import avicit.discussion_manage.majormanage.dao.InstituteCensorManageDao;

import avicit.platform6.modules.system.syslog.service.SysLogUtil;


/**
 * @classname: InstituteCensorManageService
 * @description: 定义  所区审查人员管理 实现类
 * @author:  AVICIT DEV
 */
@Service
public class InstituteCensorManageService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(InstituteCensorManageService.class);
	
    private static final long serialVersionUID = 1L;
    
	@Autowired
	private InstituteCensorManageDao dao;
	/**
	 * 通过主键查询单条记录
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public InstituteCensorManageDTO queryInstituteCensorManageByPrimaryKey(String id) throws Exception {
		try{
			InstituteCensorManageDTO dto = dao.findInstituteCensorManageById(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchInstituteCensorManage出错：", e);
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
	public List<InstituteCensorManageDTO> queryInstituteCensorManageByPid(String pid) throws Exception {
		try{
			List<InstituteCensorManageDTO> dto = dao.findInstituteCensorManageByPid(pid);
			//记录日志
			return dto;
		}catch(Exception e){
	    	logger.error("searchInstituteCensorManage出错：", e);
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
	public String insertInstituteCensorManage(InstituteCensorManageDTO dto) throws Exception {
		try{
			String id = dto.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertInstituteCensorManage(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		}catch(Exception e){
			logger.error("insertInstituteCensorManage出错：", e);
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
	public int updateInstituteCensorManage(InstituteCensorManageDTO dto) throws Exception {
			
			//记录日志
			InstituteCensorManageDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto,old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateInstituteCensorManageAll(old);
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
	public int updateInstituteCensorManageSensitive(InstituteCensorManageDTO dto) throws Exception {
		try{
			//记录日志
			InstituteCensorManageDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, findById(dto.getId()));
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateInstituteCensorManageSensitive(old);
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
	public int deleteInstituteCensorManageById(String id) throws Exception {
		if(StringUtils.isEmpty(id)){
		throw new Exception("删除失败！传入的参数主键为null");
		}
		try{
			//记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteInstituteCensorManageById(id);
		}catch(Exception e){
			logger.error("searchInstituteCensorManage出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}
	/**
	 * 按主键Str_id删除
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int deleteInstituteCensorManageByStrId(String id) throws Exception {
		if(StringUtils.isEmpty(id)){
		throw new Exception("删除失败！传入的参数主键为null");
		}
		try{
			//记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteInstituteCensorManageByStrId(id);
		}catch(Exception e){
			logger.error("searchInstituteCensorManage出错：", e);
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
	public int deleteInstituteCensorManage(InstituteCensorManageDTO dto) throws Exception {
		try{
			//记录日志
			SysLogUtil.log4Delete(dto);
			return dao.deleteInstituteCensorManageById(dto.getId());
		}catch(Exception e){
			logger.error("searchInstituteCensorManage出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteInstituteCensorManageByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteInstituteCensorManageById(id);
			result++;
		}
		return result;
	}
	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private InstituteCensorManageDTO findById(String id) throws Exception {
		try{
			InstituteCensorManageDTO dto = dao.findInstituteCensorManageById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("searchInstituteCensorManage出错：", e);
	    		throw e;
	    }
	}
		

}

