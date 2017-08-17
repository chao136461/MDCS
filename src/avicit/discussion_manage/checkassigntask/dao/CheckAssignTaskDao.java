package avicit.discussion_manage.checkassigntask.dao;

import java.util.List;
import java.util.Map;

import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.discussion_manage.checkassigntask.dto.CheckAssignTaskDTO;
/**
 * @classname: CheckAssignTaskDao
 * @description: 定义  审查分工待办表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface CheckAssignTaskDao {
    
    /**
	 * 根据登陆人返回待办信息
	 * @param userid
	 * @return List<CheckAssignTaskDTO>
	 * @throws Exception
	 */
	public List<CheckAssignTaskDTO> getCheckAssignTaskDTOListByLoginUserId(String userId);

	/**
	 * 审查分工发待办
	 */
	public void insertCheckAssignData(Map<String, Object> param);

	/**
	 * 审查分工发通知
	 */
	public void insertSysMessageData(Map<String, Object> param);

	/**
	 * 待办后处理
	 * @param Map<String, Object>
	 */
	public void updateCheckAssignTaskAftertreatment(Map<String, Object> param);

}
