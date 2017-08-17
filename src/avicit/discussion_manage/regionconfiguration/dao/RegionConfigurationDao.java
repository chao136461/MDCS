package avicit.discussion_manage.regionconfiguration.dao;

import java.util.List;
import java.util.Map;

import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.sfn.intercept.SelfDefined;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.discussion_manage.regionconfiguration.dto.RegionConfigurationDTO;
/**
 * @classname: RegionConfigurationDao
 * @description: 定义  区间配置表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface RegionConfigurationDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询 区间配置表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<RegionConfigurationDTO> searchRegionConfigurationByPage(@Param("bean")RegionConfigurationDTO regionConfigurationDTO,@Param("sfnConditions")SelfDefined sql) ;
    
    /**
     * @author AVICIT DEV
     * @description:查询对象 区间配置表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public List<RegionConfigurationDTO> searchRegionConfiguration(RegionConfigurationDTO regionConfigurationDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description:查询对象 区间配置表
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public RegionConfigurationDTO findRegionConfigurationById(String id);
    
    public RegionConfigurationDTO findRegionConfiguration(@Param("regionLevel")String regionLevel,@Param("typeId")String typeId);
    
        /**
     * @author AVICIT DEV
     * @description: 新增对象 区间配置表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertRegionConfiguration(RegionConfigurationDTO regionConfigurationDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 区间配置表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateRegionConfigurationSensitive(RegionConfigurationDTO regionConfigurationDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 区间配置表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateRegionConfigurationAll(RegionConfigurationDTO regionConfigurationDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除 区间配置表
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteRegionConfigurationById(String id);
    /**
     * 根据天数获取对应颜色
     * @param day 天数
     * @return color 颜色
     * @author WCL
     */
	public String findRegionColor(@Param("day")long day,@Param("type")String type);
    }
