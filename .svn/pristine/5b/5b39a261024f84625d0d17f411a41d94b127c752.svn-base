package avicit.discussion_manage.majormanage.dao;
import java.util.List;

import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.sfn.intercept.SelfDefined;
import avicit.discussion_manage.majormanage.dto.MajorManageDTO;
/**
 * @classname:  MajorManageDao
 * @description: 定义  所区专业管理 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:   AVICIT DEV
 */
@MyBatisRepository
public interface MajorManageDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询所区专业管理
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<MajorManageDTO> searchMajorManageByPage(@Param("bean")MajorManageDTO majorManageDTO,@Param("sfnConditions")SelfDefined sql) ;
    
    /**
     * @author AVICIT DEV
     * @description:查询对象 所区专业管理
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public List<MajorManageDTO> searchMajorManage(MajorManageDTO majorManageDTO);

    /**
     * @author AVICIT DEV
     * @description:查询对象 所区专业管理
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     * @author xul
     */
    public MajorManageDTO findMajorManageItemid(String id);
    
    /**
     * @author AVICIT DEV
     * @description:查询对象 所区专业管理
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public MajorManageDTO findMajorManageById(String id);
    
    /**
     * @author AVICIT DEV
     * @description: 新增对象出差信息表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertMajorManage(MajorManageDTO majorManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象所区专业管理
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateMajorManageSensitive(MajorManageDTO majorManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象所区专业管理
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateMajorManageAll(MajorManageDTO majorManageDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除所区专业管理
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteMajorManageById(String id);
    public int deleteMajorManageByStrId(String id);
    public String findParamyId(@Param("id")String id,@Param("strId") String itemId);
    //根据零件id和用户id查找用户审查任务担任的角色
	public String findRolsByStrIdAndUserId(@Param("strId")String strId,@Param("userId") String userId);
}
