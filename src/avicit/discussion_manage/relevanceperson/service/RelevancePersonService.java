﻿package avicit.discussion_manage.relevanceperson.service;
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
import avicit.discussion_manage.relevanceperson.dao.RelevancePersonDao;
import avicit.discussion_manage.relevanceperson.dto.RelevancePersonDTO;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname:  RelevancePersonService
 * @description: 定义 关联人员表实现类
 * @author:  AVICIT DEV
 */
@Service
public class RelevancePersonService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(RelevancePersonService.class);
	
    private static final long serialVersionUID = 1L;
	
	@Autowired
	private RelevancePersonDao dao;


	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<RelevancePersonDTO> searchRelevancePersonByPage(QueryReqBean<RelevancePersonDTO> queryReqBean,String sfnConditions) throws Exception {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			RelevancePersonDTO searchParams = queryReqBean.getSearchParams();
            SelfDefinedQuery sdc=new SelfDefinedQuery(sfnConditions,"t1");
            Page<RelevancePersonDTO> dataList =  dao.searchRelevancePersonByPage(searchParams,sdc.parseSql());
			QueryRespBean<RelevancePersonDTO> queryRespBean =new QueryRespBean<RelevancePersonDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(DaoException e){
			logger.error("searchRelevancePersonByPaging出错：", e);
			throw e;
		}
	
    }
	
	
	
	//查询数据不分页
	 public List<RelevancePersonDTO> searchRelevancePerson(QueryReqBean<RelevancePersonDTO> queryReqBean) throws Exception {
		 RelevancePersonDTO searchParams = queryReqBean.getSearchParams();
		List<RelevancePersonDTO> list = dao.searchRelevancePerson(searchParams);
		return list;
		
	};
	
	
	
		/**
	 * 批量新增或修改对象
	 * @param demos
	 * @return
	 * @throws Exception
	 */
	
	public void insertOrUpdateRelevancePerson(List<RelevancePersonDTO> demos)throws Exception {
		RelevancePersonDTO dto=null;
		for(RelevancePersonDTO demo :demos){
			dto= this.findRelevancePersonByPid(demo.getId());
			if(dto==null){
			 dto = new RelevancePersonDTO();
			dto.setVpmLoginName(demo.getVpmLoginName());
			dto.setPid(demo.getId());
			dto.setLoginName(demo.getLoginName());
			this.insertRelevancePerson(dto);
			}else{
				dto.setVpmLoginName(demo.getVpmLoginName());
				dto.setPid(demo.getId());
				dto.setLoginName(demo.getLoginName());
				this.updateRelevancePerson(dto);
			}
		}
	}


	/**
	 * 新增对象
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int insertRelevancePerson(RelevancePersonDTO dto) throws Exception {
		try{
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			int ret = dao.insertRelevancePerson(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return ret;
		}catch(Exception e){
			logger.error("insertRelevancePerson出错：", e);
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
	public int updateRelevancePerson(RelevancePersonDTO dto) throws Exception {
			//记录日志
			RelevancePersonDTO old =findRelevancePersonByPid(dto.getPid());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int ret = dao.updateRelevancePersonSensitive(old);
			if(ret ==0){
				throw new DaoException("数据失效，请重新更新");
			}
			return ret;

	}
	
	/**
	 * 按主键单条删除
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int deleteRelevancePersonById(String id) throws Exception {
		if(StringUtils.isEmpty(id)) return 0;
		try{
			//记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteRelevancePersonById(id);
		}catch(Exception e){
			logger.error("deleteRelevancePerson出错：", e);
			throw new DaoException("",e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteRelevancePersonByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteRelevancePersonById(id);
			result++;
		}
		return result;
	}
		/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private RelevancePersonDTO findById(String id) throws Exception {
		try{
			RelevancePersonDTO dto = dao.findRelevancePersonById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("findRelevancePerson出错：", e);
	    		throw e;
	    }
	}
	
	/**
	 * 根据pid查询数据
	 */
	public RelevancePersonDTO findRelevancePersonByPid(String pid) throws Exception{
		RelevancePersonDTO dto =dao.findRelevancePersonByPid(pid);
		return dto;
	}

	
	/**
	 * 根据通过VPM登录名获取本系统登录名
	 */
	public String findRelevancePersonByVpmLoginName(String vpmLoginName) throws Exception{
		String loginName =dao.findRelevancePersonByVpmLoginName(vpmLoginName);
		
		return loginName;
	}	


	/**
	 * 根据用户登录名查询用户ID
	 * 
	 * */

	public String getUserIdByUserName(String userName) throws Exception {
		

		String userId = dao.getUserIdByLoggerName(userName);
		
		return userId;
	}
		

}

