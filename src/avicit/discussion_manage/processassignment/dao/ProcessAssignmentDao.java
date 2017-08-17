package avicit.discussion_manage.processassignment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import avicit.discussion_manage.processassignment.dto.ProcessAssignmentDTO;
import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.sfn.intercept.SelfDefined;

/**
 * @classname: ProcessAssignmentDao
 * @description: 定义 工艺分工人员任务表 持久层 接口 通过@MapperScannerConfigurer扫描目录中的所有接口,
 *               动态在Spring Context中生成实现. 方法名称必须与Mapper.xml中保持一致.
 * @author: AVICIT DEV
 */
@MyBatisRepository
public interface ProcessAssignmentDao {

	/**
	 * @author AVICIT DEV
	 * @description: 分页查询 工艺分工人员任务表
	 * @date 2014-12-26 11:13:20
	 * @param searchParams
	 * @return
	 */
	public Page<ProcessAssignmentDTO> searchProcessAssignmentByPage(
			@Param("bean") ProcessAssignmentDTO processAssignmentDTO, @Param("sfnConditions") SelfDefined sql);

	/**
	 * @author AVICIT DEV
	 * @description:查询对象 工艺分工人员任务表
	 * @date 2014-12-26 11:13:20
	 * @param searchParams
	 * @return
	 */
	public List<ProcessAssignmentDTO> searchProcessAssignment(ProcessAssignmentDTO processAssignmentDTO);

	/**
	 * @author AVICIT DEV
	 * @description:查询对象 工艺分工人员任务表
	 * @date 2014-12-26 11:13:20
	 * @param id
	 * @return
	 */
	public ProcessAssignmentDTO findProcessAssignmentById(String id);

	/**
	 * @author AVICIT DEV
	 * @description: 新增对象 工艺分工人员任务表
	 * @date 2014-12-26 11:13:20
	 * @param paramMap
	 * @return
	 */
	public int insertProcessAssignment(ProcessAssignmentDTO processAssignmentDTO);

	/**
	 * @author AVICIT DEV
	 * @description: 更新对象 工艺分工人员任务表
	 * @date 2014-12-26 11:13:20
	 * @param paramMap
	 */
	public int updateProcessAssignmentSensitive(ProcessAssignmentDTO processAssignmentDTO);

	/**
	 * @author AVICIT DEV
	 * @description: 更新对象 工艺分工人员任务表
	 * @date 2014-12-26 11:13:20
	 * @param paramMap
	 */
	public int updateProcessAssignmentAll(ProcessAssignmentDTO processAssignmentDTO);

	/**
	 * @author AVICIT DEV
	 * @description: 按主键删除 工艺分工人员任务表
	 * @date 2014-12-26 11:13:20
	 * @param ids
	 * @return
	 */
	public int deleteProcessAssignmentById(String id);

	/**
	 * @author wuc
	 * @description: 按零件ID查找工艺分工人员任务
	 * @date 2017年6月22日 16:31:53
	 * @param sid
	 * @return
	 */
	public ProcessAssignmentDTO findProcessAssignmentBySid(String sid);

	/**
	 * @author AVICIT WUC
	 * @description: 按照父id查询数据
	 * @date 2017年6月26日 17:11:11
	 * @param searchParams
	 * @return
	 */

	public List<Map<String, Object>> gettreeProcessassignmentByStrId(String parentId);

	/**
	 * @author AVICIT WUC
	 * @description: 按照父id查询数据
	 * @date 2017年6月30日 16:11:11
	 * @param stid
	 * @return
	 */
	public List<Map<String, Object>> getprocessRoteByStrId(String stid);
	/**
	 * @author AVICIT WUC
	 * @description: 按照父id删除专业分工表数据
	 * @date 2017年7月4日 16:51:45
	 * @param stid
	 * @return
	 */
	public int deleteFactoryComById(String id);
	/**
	 * @author AVICIT WUC
	 * @description: 按照父id删除专业分工子表数据
	 * @date 2017年7月4日 16:51:45
	 * @param stid
	 * @return
	 */
	public int deleteFactoryCenById(String id);

	public List<String> findFactoryComById(String id);
	public int findFactoryCenById(String id);
	/**
	 * 添加审查任务之前半段是否有重复的审查任务
	 * */
	public int serchAssingnmentBystrIdAndUserId(@Param("strId")String strId,@Param("userId")String userId);

	public List<String> getAssignmentByuserId(String userId);
}
