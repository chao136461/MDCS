﻿package avicit.discussion_manage.processassignment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import avicit.discussion_manage.processassignment.dto.ProcessRouteDTO;
import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.sfn.intercept.SelfDefined;
/**
 * @classname:  ProcessRouteDao
 * @description: 定义  分工路线表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:   AVICIT DEV
 */
@MyBatisRepository
public interface ProcessRouteDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询分工路线表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<ProcessRouteDTO> searchProcessRouteByPage(@Param("bean")ProcessRouteDTO processRouteDTO,@Param("sfnConditions")SelfDefined sql) ;
    
  
    /**
     * @author AVICIT DEV
     * @description:查询对象 分工路线表
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public ProcessRouteDTO findProcessRouteById(String id);
    
         /**
     * @author AVICIT DEV
     * @description: 新增对象分工路线表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertProcessRoute(ProcessRouteDTO processRouteDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象分工路线表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateProcessRouteSensitive(ProcessRouteDTO processRouteDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象分工路线表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateProcessRouteAll(ProcessRouteDTO processRouteDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除分工路线表
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteProcessRouteById(String id);
    /**
     * @author AVICIT WUC
     * @description: 按零件ID查找路线分工
     * @date 2017年7月3日 15:29:01
     * @param ids
     * @return
     */
	public ProcessRouteDTO findByProSid(String id);

	/**
     * @author AVICIT WUC
     * @description: 获取厂区
     * @date 2017年7月4日 09:46:47
     * @param 
     * @return
     */
	@SuppressWarnings("rawtypes")
	public List getFactoryMap();
    }
