package avicit.discussion_manage.structuremanage.dao;

import java.util.List;
import java.util.Map;

import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.sfn.intercept.SelfDefined;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.discussion_manage.structuremanage.dto.StructureManageDTO;
/**
 * @classname: StructureManageDao
 * @description: 定义  装配件结构管理 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface StructureManageDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询 装配件结构管理
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<StructureManageDTO> searchStructureManageByPage(@Param("bean")StructureManageDTO structureManageDTO,@Param("sfnConditions")SelfDefined sql) ;
    
    /**
     * @author AVICIT DEV
     * @description:查询对象 装配件结构管理
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public List<StructureManageDTO> searchStructureManage(StructureManageDTO structureManageDTO);

    /**
     * @author AVICIT DEV
     * @description:查询对象 装配件结构管理
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public StructureManageDTO findStructureManageById(String id);

    /**
     * @author AVICIT DEV
     * @description:查询对象 装配件结构管理
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public StructureManageDTO findStructureManageByCode(String code);
        /**
     * @author AVICIT DEV
     * @description: 新增对象 装配件结构管理
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertStructureManage(StructureManageDTO structureManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 装配件结构管理
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateStructureManageSensitive(StructureManageDTO structureManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 装配件结构管理
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateStructureManageAll(StructureManageDTO structureManageDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除 装配件结构管理
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteStructureManageById(String id);
    /**
     * 查询零件是不是vci
     * @author xul 
     * @date 2017/8/1
     * */
    public String findClassTypeInfo(String strId);
    }
