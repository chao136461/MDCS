package avicit.discussion_manage.structureDataSynchronize.dao;

import org.apache.ibatis.annotations.Param;

import avicit.discussion_manage.structureDataSynchronize.dto.WebserviceXmlStructureDTO;
import avicit.platform6.core.mybatis.MyBatisRepository;

/**
 * @classname: StructureDataSynchronizeDAO
 * @description: 定义  结构关系表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  heps
 */
@MyBatisRepository
public interface StructureDataSynchronizeDAO {

	
	//判断该零件是否存在
	public String findHasClassCode(String classCode);

	//更新零部件信息
	public void updateStructureData(WebserviceXmlStructureDTO dto);

	//插入零部件信息
	public void insertStructureData(WebserviceXmlStructureDTO dto);
	/**
	 * 查机型
	 * @param jixing
	 * @return
	 */
	public String findJiXinClassCode(String jixing);
	/**
	 * 查父id
	 * @param strId
	 * @return
	 */
	public String findPartbyId(String strId);
	/**
	 * 删除零件关系表操作
	 * @param jx 机型id
	 * @param partId 零件id
	 */
	public void deletStrShip(@Param("jx")String jx, @Param("partId")String partId);

	/**
	 * 该机型是否已经配置默认专业
	 * @param String 
	 * @return int
	 * by heps
	 */
	public int searchIfHasConfig(String jixing);

}
