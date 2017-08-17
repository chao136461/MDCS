package avicit.discussion_manage.factorycomplex.dao;
import java.util.List;

import avicit.platform6.core.mybatis.MyBatisRepository;
import avicit.platform6.core.mybatis.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.sfn.intercept.SelfDefined;
import avicit.discussion_manage.factorycomplex.dto.FactoryComplexDTO;
/**
 * @classname:  FactoryComplexDao
 * @description: 定义  厂区车间表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:   AVICIT DEV
 */
@MyBatisRepository
public interface FactoryComplexDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询厂区车间表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<FactoryComplexDTO> searchFactoryComplexByPage(@Param("bean")FactoryComplexDTO factoryComplexDTO,@Param("sfnConditions")SelfDefined sql);
    /**
     * @author AVICIT DEV
     * @description:查询对象 厂区车间表
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public List<FactoryComplexDTO> searchFactoryComplex(FactoryComplexDTO factoryComplexDTO);

    /**
     * @author AVICIT DEV
     * @description:查询对象 厂区车间表
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public FactoryComplexDTO findFactoryComplexById(String id);
    
    /**
     * @author AVICIT DEV
     * @description: 新增对象出差信息表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertFactoryComplex(FactoryComplexDTO factoryComplexDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象厂区车间表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateFactoryComplexSensitive(FactoryComplexDTO factoryComplexDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象厂区车间表
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateFactoryComplexAll(FactoryComplexDTO factoryComplexDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除厂区车间表
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteFactoryComplexById(String id);
    /**
     * 按车间id查询车间是否存在
     * @param id
     * @return 存在的条数
     */
	public String findByDeptid(@Param("id")String id,@Param("strId")String strId);
	/**
	 * @author xul
	 * */
	public int findByStrIdAndDeptId(@Param("strId")String strId,@Param("deptId")String deptId);
}
