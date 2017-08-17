package avicit.discussion_manage.inforconfiguration.dao;

import java.util.List;
import java.util.Map;

import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.discussion_manage.inforconfiguration.dto.InforConfigurationDTO;
/**
 * @classname: InforConfigurationDao
 * @description: 定义  信息配置表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface InforConfigurationDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询 信息配置表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<InforConfigurationDTO> searchInforConfigurationByPage(InforConfigurationDTO inforConfigurationDTO) ;
    
    /**
     * @author AVICIT DEV
     * @description:查询对象 信息配置表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public List<InforConfigurationDTO> searchInforConfiguration(InforConfigurationDTO inforConfigurationDTO);

    /**
     * @author AVICIT DEV
     * @description:查询对象 信息配置表
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public InforConfigurationDTO findInforConfigurationById(String typeId);
    
    /**
     * 查询
     */
    public InforConfigurationDTO findDataByType(String typeId);
    
    
    /**
     * @author AVICIT DEV
     * @description: 新增对象 信息配置表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertInforConfiguration(InforConfigurationDTO inforConfigurationDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 信息配置表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateInforConfigurationSensitive(InforConfigurationDTO inforConfigurationDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 信息配置表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateInforConfigurationAll(InforConfigurationDTO inforConfigurationDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除 信息配置表
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteInforConfigurationById(String id);
}
