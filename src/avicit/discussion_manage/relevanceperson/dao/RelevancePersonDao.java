﻿package avicit.discussion_manage.relevanceperson.dao;

import java.util.List;

import avicit.platform6.core.mybatis.MyBatisRepository;

import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.sfn.intercept.SelfDefined;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.discussion_manage.relevanceperson.dto.RelevancePersonDTO;
/**
 * @classname:  RelevancePersonDao
 * @description: 定义  关联人员表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:   AVICIT DEV
 */
@MyBatisRepository
public interface RelevancePersonDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询关联人员表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<RelevancePersonDTO> searchRelevancePersonByPage(@Param("bean")RelevancePersonDTO relevancePersonDTO,@Param("sfnConditions")SelfDefined sql) ;
    

    
    public List<RelevancePersonDTO> searchRelevancePerson(RelevancePersonDTO relevancePersonDTO);
    /**
     * @author AVICIT DEV
     * @description:查询对象 关联人员表
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public RelevancePersonDTO findRelevancePersonById(String id);
    
         /**
     * @author AVICIT DEV
     * @description: 新增对象关联人员表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertRelevancePerson(RelevancePersonDTO relevancePersonDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象关联人员表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateRelevancePersonSensitive(RelevancePersonDTO relevancePersonDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象关联人员表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateRelevancePersonAll(RelevancePersonDTO relevancePersonDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除关联人员表
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteRelevancePersonById(String id);

    /**
     * pid查询数据
     * @param pid
     * @return
     */
	public RelevancePersonDTO findRelevancePersonByPid(String pid);
	
	/**
     * 根据通过VPM登录名查询数据
     * @param pid
     * @return
     */
	public String findRelevancePersonByVpmLoginName(String vpmLoginName);
	
	

	/**
	 * 根据用户登录名查询用户ID
	 * 
	 * */
	public String getUserIdByLoggerName(String userName);
	
    }

