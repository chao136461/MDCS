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
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.sfn.intercept.SelfDefinedQuery;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.discussion_manage.majormanage.dao.MajorManageDao;
import avicit.discussion_manage.majormanage.dto.MajorManageDTO;


import avicit.discussion_manage.majormanage.dto.InstituteCensorManageDTO;

import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname: MajorManageService
 * @description: 定义  出差信息表 实现类
 * @author:  AVICIT DEV
 */
@Service
public class MajorManageService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(MajorManageService.class);
	
    private static final long serialVersionUID = 1L;
    
//	@Autowired
//	private SysLogUtil sysLogUtil;
	
	@Autowired
	private MajorManageDao dao;
	@Autowired
    private InstituteCensorManageService serviceSub;

	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<MajorManageDTO> searchMajorManageByPage(QueryReqBean<MajorManageDTO> queryReqBean,String sfnConditions) throws Exception {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			MajorManageDTO searchParams = queryReqBean.getSearchParams();
            SelfDefinedQuery sdc=new SelfDefinedQuery(sfnConditions,"t1");
            Page<MajorManageDTO> dataList =  dao.searchMajorManageByPage(searchParams,sdc.parseSql());
			QueryRespBean<MajorManageDTO> queryRespBean =new QueryRespBean<MajorManageDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(DaoException e){
			logger.error("searchMajorManageByPaging出错：", e);
			throw e;
		}
	}
	/**
	 * 按条件查询，不分页
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public List<MajorManageDTO> searchMajorManage(QueryReqBean<MajorManageDTO> queryReqBean) throws Exception {
	    try{
	    	MajorManageDTO searchParams = queryReqBean.getSearchParams();
	    	List<MajorManageDTO> dataList = dao.searchMajorManage(searchParams);
	    	return dataList;
	    }catch(Exception e){
	    	logger.error("searchMajorManage出错：", e);
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
	public MajorManageDTO queryMajorManageByPrimaryKey(String id) throws Exception {
		try{
			MajorManageDTO dto = dao.findMajorManageById(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchMajorManage出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}
	/**
	 * 通过零件id查询单条记录
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 * @author xul
	 */
	public MajorManageDTO queryMajorManageByItemId(String id) throws Exception {
		try{
			MajorManageDTO dto = dao.findMajorManageItemid(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchMajorManage出错：", e);
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
	public String insertMajorManage(MajorManageDTO dto) throws Exception {
		try{
			String id = dto.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertMajorManage(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		}catch(Exception e){
			logger.error("insertMajorManage出错：", e);
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
	public int updateMajorManage(MajorManageDTO dto) throws Exception {
			//记录日志
			MajorManageDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateMajorManageAll(old);
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
	public int updateMajorManageSensitive(MajorManageDTO dto) throws Exception {
			//记录日志
			MajorManageDTO  old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateMajorManageSensitive(old);
			if(count ==0){
				throw new DaoException("数据失效，请重新更新");
			}
			return count;

	}
	
	/**
	 * 按主键单条删除
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int deleteMajorManageById(String id) throws Exception {
		if(StringUtils.isEmpty(id)) throw new Exception("删除失败！传入的参数主键为null");
		try{
			//记录日志
			MajorManageDTO obje = findById(id);
			SysLogUtil.log4Delete(obje);
			//删除子表
			for(InstituteCensorManageDTO sub :serviceSub.queryInstituteCensorManageByPid(obje.getId())){
				serviceSub.deleteInstituteCensorManage(sub);
			}
			//删除主表
			return dao.deleteMajorManageById(id);
		}catch(Exception e){
			logger.error("searchMajorManage出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}
	/**
	 * 删除所有数据
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int deleteMajorManageByStrId(String id) throws Exception {
		if(StringUtils.isEmpty(id)) throw new Exception("删除失败！传入的参数主键为null");
		try{
			//记录日志
			MajorManageDTO obje = findById(id);
			SysLogUtil.log4Delete(obje);
			//删除子表
				serviceSub.deleteInstituteCensorManageByStrId(id);
			//删除主表
			return dao.deleteMajorManageByStrId(id);
		}catch(Exception e){
			logger.error("searchMajorManage出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteMajorManageByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteMajorManageById(id);
			result++;
		}
		return result;
	}
	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private MajorManageDTO findById(String id) throws Exception {
		try{
			MajorManageDTO dto = dao.findMajorManageById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("searchMajorManage出错：", e);
	    		throw e;
	    }
	}
	public String findParamyId(String id,String itemId){
		String parid = dao.findParamyId(id,itemId );
		return parid;
		
	}
	//根据零件id和用户id查找用户审查任务担任的角色
	public String getRolsByStrIdAndUserId(String strId, String userId)  throws Exception{
		
		String stdId = dao.findRolsByStrIdAndUserId(strId,userId);
		return stdId;
	}	

}

