package avicit.discussion_manage.factorycomplex.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.discussion_manage.factorycomplex.dto.FactoryCensorManageDTO;
/**
 * @classname: FactoryCensorManageDao
 * @description: 定义 厂区审查人员管理 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface FactoryCensorManageDao {
    

    /**
     * @author AVICIT DEV
     * @description:查询 厂区审查人员管理 
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public FactoryCensorManageDTO findFactoryCensorManageById(String id);
    
    /**
     * @author AVICIT DEV
     * @description:按照父id查询对象
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public List<FactoryCensorManageDTO> findFactoryCensorManageByPid(String pid);
    
    /**
     * @author AVICIT DEV
     * @description: 新增厂区审查人员管理 
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertFactoryCensorManage(FactoryCensorManageDTO factoryCensorManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新厂区审查人员管理 
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateFactoryCensorManageSensitive(FactoryCensorManageDTO factoryCensorManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新厂区审查人员管理 
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateFactoryCensorManageAll(FactoryCensorManageDTO factoryCensorManageDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除厂区审查人员管理
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteFactoryCensorManageById(String id);
}
