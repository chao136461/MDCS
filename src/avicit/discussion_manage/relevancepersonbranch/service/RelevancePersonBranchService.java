﻿package avicit.discussion_manage.relevancepersonbranch.service;
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
import avicit.discussion_manage.relevanceperson.dto.RelevancePersonDTO;
import avicit.discussion_manage.relevancepersonbranch.dao.RelevancePersonBranchDao;
import avicit.discussion_manage.relevancepersonbranch.dto.RelevancePersonBranchDTO;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname:  RelevancePersonBranchService
 * @description: 定义 关联人员表实现类
 * @author:  AVICIT DEV
 */
@Service
public class RelevancePersonBranchService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(RelevancePersonBranchService.class);
	
    private static final long serialVersionUID = 1L;
	
	@Autowired
	private RelevancePersonBranchDao dao;


	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<RelevancePersonBranchDTO> searchRelevancePersonBranchByPage(QueryReqBean<RelevancePersonBranchDTO> queryReqBean,String sfnConditions) throws Exception {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			RelevancePersonBranchDTO searchParams = queryReqBean.getSearchParams();
            SelfDefinedQuery sdc=new SelfDefinedQuery(sfnConditions,"t1");
            Page<RelevancePersonBranchDTO> dataList =  dao.searchRelevancePersonBranchByPage(searchParams,sdc.parseSql());
			QueryRespBean<RelevancePersonBranchDTO> queryRespBean =new QueryRespBean<RelevancePersonBranchDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(DaoException e){
			logger.error("searchRelevancePersonBranchByPaging出错：", e);
			throw e;
		}
	
    }
		/**
	 * 批量新增或修改对象
	 * @param demos
	 * @return
	 * @throws Exception
	 */
	
	public void insertOrUpdateRelevancePersonBranch(List<RelevancePersonBranchDTO> demos)throws Exception {
		RelevancePersonBranchDTO dto=null;
		for(RelevancePersonBranchDTO demo :demos){
			dto= this.findRelevancePersonBranchByPid(demo.getId());
			if(dto==null){
			 dto = new RelevancePersonBranchDTO();
			dto.setVpmLoginName(demo.getVpmLoginName());
			dto.setPid(demo.getId());
			dto.setLoginName(demo.getLoginName());
			this.insertRelevancePersonBranch(dto);
			}else{
				dto.setVpmLoginName(demo.getVpmLoginName());
				dto.setPid(demo.getId());
				dto.setLoginName(demo.getLoginName());
				this.updateRelevancePersonBranch(dto);
			}
		}
	}


	/**
	 * 新增对象
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public int insertRelevancePersonBranch(RelevancePersonBranchDTO dto) throws Exception {
		try{
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			int ret = dao.insertRelevancePersonBranch(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return ret;
		}catch(Exception e){
			logger.error("insertRelevancePersonBranch出错：", e);
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
	public int updateRelevancePersonBranch(RelevancePersonBranchDTO dto) throws Exception {
			//记录日志
			RelevancePersonBranchDTO old =findRelevancePersonBranchByPid(dto.getPid());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int ret = dao.updateRelevancePersonBranchSensitive(old);
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
	public int deleteRelevancePersonBranchById(String id) throws Exception {
		if(StringUtils.isEmpty(id)) return 0;
		try{
			//记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteRelevancePersonBranchById(id);
		}catch(Exception e){
			logger.error("deleteRelevancePersonBranch出错：", e);
			throw new DaoException("",e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteRelevancePersonBranchByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteRelevancePersonBranchById(id);
			result++;
		}
		return result;
	}
		/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private RelevancePersonBranchDTO findById(String id) throws Exception {
		try{
			RelevancePersonBranchDTO dto = dao.findRelevancePersonBranchById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("findRelevancePersonBranch出错：", e);
	    		throw e;
	    }
	}
	
	/**
	 * 根据pid查询数据
	 */
	public RelevancePersonBranchDTO findRelevancePersonBranchByPid(String pid) throws Exception{
		RelevancePersonBranchDTO dto =dao.findRelevancePersonBranchByPid(pid);
		return dto;
	}
	
	/**
	 * 根据通过VPM登录名获取本系统登录名
	 */
	public RelevancePersonBranchDTO findRelevancePersonBranchByVpmLoginName(String data) throws Exception{
		RelevancePersonBranchDTO dto =dao.findRelevancePersonBranchByVpmLoginName(data);
		return dto;
	}	
		

}

