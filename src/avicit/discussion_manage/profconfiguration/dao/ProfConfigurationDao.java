package avicit.discussion_manage.profconfiguration.dao;

import java.util.List;
import java.util.Map;

import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.sfn.intercept.SelfDefined;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.discussion_manage.profconfiguration.dto.ProfConfigurationDTO;
/**
 * @classname: ProfConfigurationDao
 * @description: 定义  专业配置表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface ProfConfigurationDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询 专业配置表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<ProfConfigurationDTO> searchProfConfigurationByPage(@Param("bean")ProfConfigurationDTO profConfigurationDTO,@Param("sfnConditions")SelfDefined sql) ;
    
    /**
     * @author AVICIT DEV
     * @description:查询对象 专业配置表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public List<ProfConfigurationDTO> searchProfConfiguration(ProfConfigurationDTO profConfigurationDTO);

    /**
     * @author AVICIT DEV
     * @description:查询对象 专业配置表
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public ProfConfigurationDTO findProfConfigurationById(String id);
    
    /**
     * 通过typeId查询对象
     * @param typeId
     * @return
     */
    public ProfConfigurationDTO findProfConfigurationByTypeId(String typeId);
    
        /**
     * @author AVICIT DEV
     * @description: 新增对象 专业配置表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertProfConfiguration(ProfConfigurationDTO profConfigurationDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 专业配置表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateProfConfigurationSensitive(ProfConfigurationDTO profConfigurationDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 专业配置表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateProfConfigurationAll(ProfConfigurationDTO profConfigurationDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除 专业配置表
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteProfConfigurationById(String id);
    /**
	 * 根据typeID和厂区标识查询对应专业
	 * @param param 配置专业
	 * @return dto配置专业对象
	 */
	public ProfConfigurationDTO searchProfConfigurationByTypeId(
			ProfConfigurationDTO param);
    }
