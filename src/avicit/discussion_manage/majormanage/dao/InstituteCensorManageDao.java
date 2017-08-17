package avicit.discussion_manage.majormanage.dao;
import java.util.List;
import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.discussion_manage.majormanage.dto.InstituteCensorManageDTO;
/**
 * @classname: InstituteCensorManageDao
 * @description: 定义 所区审查人员管理 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface InstituteCensorManageDao {
    

    /**
     * @author AVICIT DEV
     * @description:查询 所区审查人员管理 
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public InstituteCensorManageDTO findInstituteCensorManageById(String id);
    
    /**
     * @author AVICIT DEV
     * @description:按照父id查询对象
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public List<InstituteCensorManageDTO> findInstituteCensorManageByPid(String pid);
    
    /**
     * @author AVICIT DEV
     * @description: 新增所区审查人员管理 
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertInstituteCensorManage(InstituteCensorManageDTO instituteCensorManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新所区审查人员管理 
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateInstituteCensorManageSensitive(InstituteCensorManageDTO instituteCensorManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新所区审查人员管理 
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateInstituteCensorManageAll(InstituteCensorManageDTO instituteCensorManageDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除所区审查人员管理
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteInstituteCensorManageById(String id);
    public int deleteInstituteCensorManageByStrId(String id);
    
}
