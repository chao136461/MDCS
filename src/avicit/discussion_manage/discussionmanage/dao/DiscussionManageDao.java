package avicit.discussion_manage.discussionmanage.dao;

import java.util.List;
import java.util.Map;

import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.sfn.intercept.SelfDefined;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.discussion_manage.discussionmanage.dto.DiscussionInfoDTO;
import avicit.discussion_manage.discussionmanage.dto.DiscussionManageDTO;
import avicit.discussion_manage.modelInterveneCheck.dto.InterveneDTO;
/**
 * @classname: DiscussionManageDao
 * @description: 定义  讨论区管理 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface DiscussionManageDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询 讨论区管理
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<DiscussionManageDTO> searchDiscussionManageByPage(@Param("bean")DiscussionManageDTO discussionManageDTO,@Param("sfnConditions")SelfDefined sql) ;
    
    /**
     * @author AVICIT DEV
     * @description:查询对象 讨论区管理
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public List<DiscussionManageDTO> searchDiscussionManage(DiscussionManageDTO discussionManageDTO);

    /**
     * @author AVICIT DEV
     * @description:查询对象 讨论区管理
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public DiscussionManageDTO findDiscussionManageById(String id);
    
        /**
     * @author AVICIT DEV
     * @description: 新增对象 讨论区管理
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertDiscussionManage(DiscussionManageDTO discussionManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 讨论区管理
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateDiscussionManageSensitive(DiscussionManageDTO discussionManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 讨论区管理
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateDiscussionManageAll(DiscussionManageDTO discussionManageDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除 讨论区管理
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteDiscussionManageById(String id);
    /**
     * @author xul
     * @param dept 
     * 
     * */
    public Page<DiscussionManageDTO> findDeptUserByStrId(@Param("id")String id, @Param("array")String[] parms);
    /**
     * 厂区查询
     * @author wcl
     * @param string 
     * 
     * */
    public Page<DiscussionManageDTO> findFactoryDeptUserByStrId(@Param("id")String id, @Param("array")String[] parms);
    /**
     * 所区查询
     * @author wcl
     * @param string 
     * 
     * */
    public Page<DiscussionManageDTO> findMajorDeptUserByStrId(@Param("id")String id, @Param("array")String[] parms);

    /**
	 * 按照零件id查询讨论区信息
	 * @param id
	 * @return list
	 */
	public Page<DiscussionInfoDTO> selectDiscussionData(String strId);

	/**
	 * 按照零件id查看是否存在讨论区
	 * */
	public int findDiscussionBystrId(String strId);
	
	

	/**
	 * 按照零件id创建厂区默认车间专业
	 * @param id
	 */
	public void insertManageFactoryZhuanYeByStrId(Map<String, Object>  param);
	/**
	 * xul
	 * */
	public int getInfoById();
	 /**
     * 厂区查询
     * @author wuc
     * @param string 
     * 
     * */
	
    public Page<DiscussionManageDTO> findDeptByStrId(@Param("id")String id);
    /**
     * 厂区查询
     * @author wuc
     * @param string 
     * 
     * */
    public Page<DiscussionManageDTO> findFactoryDeptByStrId(@Param("id")String id);
    /**
     * 所区查询
     * @author wuc
     * @param string 
     * 
     * */
    public Page<DiscussionManageDTO> findMajorDeptByStrId(@Param("id")String id);


    /**
	 * 加载模型干涉表格数据通过当前选择节点
	 * @param String 零件id
	 * @param String 结构表id
	 * @return QueryRespBean<InterveneDTO>
	 */
    public Page<InterveneDTO> getInterveneDataByStrId(@Param("strId")String strId,@Param("instancenumber")String instancenumber);
    //根据零件id查询讨论区状态
	public String getStatusByStrId(String strId);


	/**
	 * 强制通过干涉检查结果
	 * @param String[] 
	 * @return ModelAndView
	 * author by heps
	 */
	public void updateInterveneResult(String id);

	
	/**
	 * 查询零件类型通过零件id
	 * @param String 
	 * @return String
	 * author by heps
	 */
	public String findstrType(String strId);

	/**
	 * 加载模型干涉表格数据通过实例号
	 * @param String 
	 * @return Page<InterveneDTO>
	 * author by heps
	 */
	public Page<InterveneDTO> getInterveneDataByInstancenumber(String instancenumber);
}
