package avicit.discussion_manage.informationstatistics.service;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import avicit.discussion_manage.informationstatistics.dao.InformationStatisticsDao;
import avicit.discussion_manage.structuremanage.dto.StructureManageDTO;
import avicit.platform6.core.exception.DaoException;

@Service
public class InformationStatisticsService  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger =  LoggerFactory.getLogger(InformationStatisticsService.class);

	@Autowired
	private InformationStatisticsDao informationStatisticsDao;
	

	/**
	 * 查询零件  装配件相关讨论区的数量
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> searchStructureManage() throws Exception {
		
		try{
			List<Map<String,Object>>  structureManageList =informationStatisticsDao.searchStructureManage();
			return structureManageList;
		}catch(DaoException e){
			logger.error("searchStructureManage出错：", e);
			throw e;
		}
	}
	/**
	 * 查询审查讨论  确认 完成数量
	 * @return
	 * @throws Exception
	 */
  public List<Map<String,Object>> searchAuditOpinion() throws Exception {
		
		try{
			List<Map<String,Object>>  auditOpinionList =informationStatisticsDao.searchAuditOpinion();
			return auditOpinionList;
		}catch(DaoException e){
			logger.error("searchAuditOpinion出错：", e);
			throw e;
		}
	}
    /**
	 * 建议关闭状态的统计图形   已关闭  未关闭   争议性关闭
	 * @return
	 * @throws Exception
	 */
   public List<Map<String,Object>> searchSuggestionClosed() throws Exception {
		
		try{
			List<Map<String,Object>>  suggestionClosedList =informationStatisticsDao.searchSuggestionClosed();
			return suggestionClosedList;
		}catch(DaoException e){
			logger.error("searchSuggestionClosed出错：", e);
			throw e;
		}
	}
    /**
	 * 各相关部门的建议统计
	 * @return
	 * @throws Exception
	 */
   public List<Map<String,Object>> searchSuggestionStatistical() throws Exception {
		
		try{
			List<Map<String,Object>>  suggestionStatisticalList =informationStatisticsDao.searchSuggestionStatistical();
			return suggestionStatisticalList;
		}catch(DaoException e){
			logger.error("searchSuggestionStatistical出错：", e);
			throw e;
		}
	}
}

