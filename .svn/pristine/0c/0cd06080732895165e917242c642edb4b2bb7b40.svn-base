package avicit.discussion_manage.processrepresentative.dao;

import java.util.List;
import java.util.Map;

import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.sfn.intercept.SelfDefined;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.discussion_manage.processrepresentative.dto.ProcessRepresentativeDTO;
/**
 * @classname: ProcessRepresentativeDao
 * @description: 定义  工艺代表任务表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface ProcessRepresentativeDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询 工艺代表任务表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<ProcessRepresentativeDTO> searchProcessRepresentativeByPage(@Param("bean")ProcessRepresentativeDTO processRepresentativeDTO,@Param("sfnConditions")SelfDefined sql) ;
    
    /**
     * @author AVICIT DEV
     * @description:查询对象 工艺代表任务表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public List<ProcessRepresentativeDTO> searchProcessRepresentative(ProcessRepresentativeDTO processRepresentativeDTO);

    /**
     * @author AVICIT DEV
     * @description:查询对象 工艺代表任务表
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public ProcessRepresentativeDTO findProcessRepresentativeById(String id);
    
        /**
     * @author AVICIT DEV
     * @description: 新增对象 工艺代表任务表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertProcessRepresentative(ProcessRepresentativeDTO processRepresentativeDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 工艺代表任务表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateProcessRepresentativeSensitive(ProcessRepresentativeDTO processRepresentativeDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 工艺代表任务表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateProcessRepresentativeAll(ProcessRepresentativeDTO processRepresentativeDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除 工艺代表任务表
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteProcessRepresentativeById(String id);
    }
