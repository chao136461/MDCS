package avicit.discussion_manage.proposalmanage.dao;

import java.util.List;
import java.util.Map;

import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.sfn.intercept.SelfDefined;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.discussion_manage.proposalmanage.dto.ProposalManageDTO;
/**
 * @classname: ProposalManageDao
 * @description: 定义  建议管理 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface ProposalManageDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询 建议管理
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<ProposalManageDTO> searchProposalManageByPage(@Param("bean")ProposalManageDTO proposalManageDTO,@Param("sfnConditions")SelfDefined sql) ;
    
    /**
     * @author AVICIT DEV
     * @description:查询对象 建议管理
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public List<ProposalManageDTO> searchProposalManage(ProposalManageDTO proposalManageDTO);

    /**
     * @author AVICIT DEV
     * @description:查询对象 建议管理
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public ProposalManageDTO findProposalManageById(String id);
    
        /**
     * @author AVICIT DEV
     * @description: 新增对象 建议管理
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertProposalManage(ProposalManageDTO proposalManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 建议管理
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateProposalManageSensitive(ProposalManageDTO proposalManageDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 建议管理
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateProposalManageAll(ProposalManageDTO proposalManageDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除 建议管理
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteProposalManageById(String id);
    /**
     * @author AVICIT DEV
     * @description: 按零件查询专业
     * @date 2017-7-18 16:51:20
     * @param ids
     * @return
     */ 
	public Page<ProposalManageDTO> findByStrId(@Param("id")String id);
	/**
	 * @author XUL	
	 * @date 2017-7-19
	 * 
	 * */
	public int getInfoById();
	
	/**
     * @author AVICIT DEV
     * @description: 按主键id查询对应的回复内容
     * @date 2017-7-19 14:55:20
     * @param ids
     * @return
     */ 
	public List<ProposalManageDTO> queryProposalByParentId(@Param("id")String id);

	public int getDeptbyStr(Map map);
	/**
	 * @author XUL	
	 * @date 2017-7-29
	 * @description 判断是否发表建议
	 * */
	public int getProposalByUserIdAndStrId(@Param("userId")String userId,@Param("strId")String strId);
	/**
	 * @author XUL	
	 * @date 2017-7-29
	 * @description 判断是否有建议未关闭
	 * */
	public int getProposalIsClose(@Param("userId")String userId,@Param("strId")String strId);
	/**
	 * 根据，零件ID查询零件状态
	 * @param strId 零件ID
	 * @return 状态
	 * @author WCL
	 */
	public String queryStructInstanceByStrId(String strId);

    }
