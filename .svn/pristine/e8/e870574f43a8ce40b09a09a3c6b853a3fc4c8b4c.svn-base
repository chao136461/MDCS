package avicit.discussion_manage.informationstatistics.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import avicit.discussion_manage.structuremanage.dto.StructureManageDTO;
import avicit.platform6.core.mybatis.MyBatisRepository;
@MyBatisRepository
public interface InformationStatisticsDao {
	/**
	 * 查询零件  装配件相关讨论区的数量   
	 * @param 
	 * @return
	 */
	public List<Map<String,Object>> searchStructureManage() ;
	/**
	 * 查询审查讨论  确认 完成数量
	 * @return
	 */
	public List<Map<String,Object>> searchAuditOpinion() ;
	/**
	 * 建议关闭状态的统计图形   已关闭  未关闭   争议性关闭
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,Object>> searchSuggestionClosed();
    /**
	 * 各相关部门的建议统计
	 * @return
	 * @throws Exception
	 */
    public List<Map<String,Object>> searchSuggestionStatistical();
}
 

	
