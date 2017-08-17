package avicit.discussion_manage.disputememo.dao;

import java.util.List;
import java.util.Map;

import avicit.platform6.core.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import avicit.platform6.core.sfn.intercept.SelfDefined;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.discussion_manage.disputememo.dto.DisputeMemoDTO;
/**
 * @classname: DisputeMemoDao
 * @description: 定义  争议备忘 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface DisputeMemoDao {
    
    /**
     * @author AVICIT DEV
     * @description: 分页查询 争议备忘
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public Page<DisputeMemoDTO> searchDisputeMemoByPage(@Param("bean")DisputeMemoDTO disputeMemoDTO,@Param("sfnConditions")SelfDefined sql) ;
    
    /**
     * @author AVICIT DEV
     * @description:查询对象 争议备忘
     * @date 2014-12-26 11:13:20
     * @param searchParams
     * @return
     */
    public List<DisputeMemoDTO> searchDisputeMemo(DisputeMemoDTO disputeMemoDTO);

    /**
     * @author AVICIT DEV
     * @description:查询对象 争议备忘
     * @date 2014-12-26 11:13:20
     * @param id
     * @return
     */
    public DisputeMemoDTO findDisputeMemoById(String id);
    
        /**
     * @author AVICIT DEV
     * @description: 新增对象 争议备忘
     * @date 2014-12-26 11:13:20
     * @param paramMap
     * @return
     */
    public int insertDisputeMemo(DisputeMemoDTO disputeMemoDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 争议备忘
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateDisputeMemoSensitive(DisputeMemoDTO disputeMemoDTO);
    
    /**
     * @author AVICIT DEV
     * @description: 更新对象 争议备忘
     * @date 2014-12-26 11:13:20
     * @param paramMap
     */
    public int updateDisputeMemoAll(DisputeMemoDTO disputeMemoDTO);
    
    
    /**
     * @author AVICIT DEV
     * @description: 按主键删除 争议备忘
     * @date 2014-12-26 11:13:20
     * @param ids
     * @return
     */ 
    public int deleteDisputeMemoById(String id);
    /**
     * @author AVICIT DEV
     * @description: 按外键查询 争议备忘
     * @date 2014-12-26 11:13:20
     * @param id 建议id
     * @return DisputeMemoDTO
     */
	public DisputeMemoDTO queryDisputeMemoByFrenKey(String id);
    }
