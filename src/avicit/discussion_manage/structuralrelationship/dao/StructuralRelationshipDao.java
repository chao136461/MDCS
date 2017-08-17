package avicit.discussion_manage.structuralrelationship.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.discussion_manage.structuralrelationship.dto.AddFormDataDTO;
import avicit.discussion_manage.structuralrelationship.dto.StructuralRelationshipDTO;
/**
 * @classname: StructuralRelationshipDao
 * @description: 定义  结构关系表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface StructuralRelationshipDao {
    
    /**
     * @author AVICIT DEV
     * @description: 按照父id查询数据
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
     public List<Map<String,Object>> getStructuralRelationshipByParentStrId(String id);
    
     
     /**
      * @author AVICIT DEV
      * @description: 按照id查询零件
      * @date 2014-12-26 11:13:20
      * @param String
      * @return dto
      */
      public Page<AddFormDataDTO> getDgStructuralRelationship(String id);
      /**
       * @author AVICIT DEV
       * @description:查询对象 结构关系表
       * @date 2014-12-26 11:13:20
       * @param id
       * @return
       */
      public StructuralRelationshipDTO findStructuralRelationshipById(String id);
      /**
       * @author AVICIT DEV
       * @description: 按照id查询零件
       * @date 2014-12-26 11:13:20
       * @param String
       * @return dto
       */
       public AddFormDataDTO getAddformDataDTOById(String id);
   /**
     * @author AVICIT DEV
     * @description: 添加零件
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
   public Map<String, Object> insertStructuralRelationship(AddFormDataDTO dto);

   /**
* @author AVICIT DEV
* @description: 新增对象 结构关系表
* @date 2014-12-26 11:13:20
* @param paramMap
* @return
*/
public int insertStructuralRelationships(StructuralRelationshipDTO structuralRelationshipDTO);
/**
 * @author AVICIT DEV
 * @description: 更新对象 结构关系表
 * @date 2014-12-26 11:13:20
 * @param paramMap
 */
public int updateStructuralRelationshipSensitive(StructuralRelationshipDTO structuralRelationshipDTO);
    /**
     * @author AVICIT DEV
     * @description: 更新零件数据
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public Map<String, Object> updateStructureManage(AddFormDataDTO dto);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除 
     * @date 2014-12-26 11:13:20
     * @param Map
     * @return
     */ 
   public Map<String, Object> deleteStructureManage(Map<String, Object> map);

   /**
    * 根据vci编号查询零件id集合
    * @author xul
    * */
   public List<String> getStrIdByCode(String code);


   //判断用户添加的零件是否存在
	public AddFormDataDTO findStrIsHasByclassCodeNew(String classCodeNew);

	
	//保存拖拽后的数据结构
	public void toSaveDragNode(Map<String, Object> map);

	//查询vci树
	public List<Map<String, Object>> getZtreeStructuralRelationshipBySerchData(@Param("bean")AddFormDataDTO serchData);

	//讨论区根据零件id展示vci树
	public List<Map<String, Object>> searchVciZtreeByStrId(String strId);
	
	 //查询型号20170729lf
	public List<StructuralRelationshipDTO> findStructuralType(String type);

	/**
	 * 新增产品节点（根节点）
	 * @param AddFormDataDTO
	 * @throws Exception
	 * heps
	 */
	public void insertProjectRootNode(AddFormDataDTO dto);

	/**
	 * 查询产品是否已经存现
	 * @param String
	 * heps
	 */
	public int searchIsHasProject(String classCode);

	/**
	 * 关系表插数据 
	 * @param Map<String, Object>
	 * heps
	 */
	public void insertProjectRootNodeToMiddleTable(Map<String, Object> map);

}
