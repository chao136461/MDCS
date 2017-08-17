﻿package avicit.discussion_manage.relevancepersonbranch.dao;

import avicit.platform6.core.mybatis.MyBatisRepository;

import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.sfn.intercept.SelfDefined;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.discussion_manage.relevanceperson.dto.RelevancePersonDTO;
import avicit.discussion_manage.relevancepersonbranch.dto.RelevancePersonBranchDTO;
/**
 * @classname:  RelevancePersonBranchDao
 * @description: 定义  关联人员表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:   AVICIT DEV
 */
@MyBatisRepository
public interface RelevancePersonBranchDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询关联人员表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<RelevancePersonBranchDTO> searchRelevancePersonBranchByPage(@Param("bean")RelevancePersonBranchDTO relevancePersonBranchDTO,@Param("sfnConditions")SelfDefined sql) ;
    
  
    /**
     * @author AVICIT DEV
     * @description:查询对象 关联人员表
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public RelevancePersonBranchDTO findRelevancePersonBranchById(String id);
    
         /**
     * @author AVICIT DEV
     * @description: 新增对象关联人员表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertRelevancePersonBranch(RelevancePersonBranchDTO relevancePersonBranchDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象关联人员表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateRelevancePersonBranchSensitive(RelevancePersonBranchDTO relevancePersonBranchDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象关联人员表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateRelevancePersonBranchAll(RelevancePersonBranchDTO relevancePersonBranchDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除关联人员表
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteRelevancePersonBranchById(String id);
    
    /**
     * 通过pid查询数据
     * @param pid
     * @return
     */
    public RelevancePersonBranchDTO findRelevancePersonBranchByPid(String pid);
    
    /**
     * 根据通过VPM登录名查询数据
     * @param pid
     * @return
     */
    public RelevancePersonBranchDTO findRelevancePersonBranchByVpmLoginName(String data);

    
}
	
