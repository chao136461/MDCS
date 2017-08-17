package avicit.discussion_manage.mdcsproposalpicture.dao;

import org.apache.ibatis.annotations.Param;

import avicit.discussion_manage.mdcsproposalpicture.dto.MdcsProposalPictureDTO;
import avicit.platform6.core.mybatis.MyBatisRepository;
/**
 * @classname: MdcsProposalPictureDao
 * @description: 定义  建议发表截图信息表 持久层 接口
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * @author:  AVICIT DEV
 */
@MyBatisRepository
public interface MdcsProposalPictureDao{
    /**
	 * 保存建议发表截图信息
	 * @param MdcsProposalPictureDTO
	 */
	public void saveProposalPictureData(MdcsProposalPictureDTO dto);

	/**
	 * 建议发表截图片是否存在
	 * @param String
	 * @return int
	 */
	public int findProposalIsHave(String proposalId);

   /**
	 * 更新建议发表截图信息
	 * @param String
	 * @param String
	 */
	public void updateProposalPictureData(@Param("proposalId")String proposalId,@Param("saveAddress")String saveAddress,@Param("pictureOption")String pictureOption,@Param("pictureInfo")byte[] pictureInfo);

	/**
	 * 根据建议id返回截图矩阵信息
	 * @param String
	 * author by heps
	 */
    public String getPicturePosition(String id);
    /**
     * 根据建议id查询图片
     * @param id 建议id
     * @return
     */
    public MdcsProposalPictureDTO queryCappFileWordByPrimaryKey(String id);
}
