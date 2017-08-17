package avicit.discussion_manage.modelInterveneCheck.dao;

import org.apache.ibatis.annotations.Param;

import avicit.discussion_manage.modelInterveneCheck.dto.InterveneDTO;
import avicit.platform6.core.mybatis.MyBatisRepository;

@MyBatisRepository
public interface SynchronousInterveneDataDAO {

	/**
	 * 模型干涉表存不在该零件信息则新增
	 * @param InterveneDTO
	 * @throws Exception
	 */
	public void insertResolveInterveneXmlData(InterveneDTO interveneDTO);

	/**
	 * 根据classCode得到零件id
	 * @param String
	 * @throws Exception
	 */
	public String findStrIdByClassCode(String classCode);

	/**
	 * 根据strId得到零件parentId
	 * @param String
	 * @throws Exception
	 */
	public String findParentIdByStrId(@Param("strId")String strId, @Param("instanceNumber")String instanceNumber);

	/**
	 * 模型干涉表存在该零件信息则更新
	 * @param InterveneDTO
	 */
	public void updateInterveneDataByStrId(InterveneDTO dto);

	/**
	 * 查询模型干涉表是否存在该零件信息
	 * @param strId
	 * @param instanceNumber
	 * @return int
	 */
	public int findInterveneIdCount(@Param("strId")String strId, @Param("instanceNumber")String instanceNumber);

}
