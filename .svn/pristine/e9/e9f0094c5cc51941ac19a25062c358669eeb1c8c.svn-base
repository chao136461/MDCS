package avicit.discussion_manage.mdcsproposalpicture.service;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.discussion_manage.mdcsproposalpicture.dao.MdcsProposalPictureDao;
import avicit.discussion_manage.mdcsproposalpicture.dto.MdcsProposalPictureDTO;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.properties.PlatformConstant.OpType;

/**
 * @classname:  MdcsProposalPictureService
 * @description: 定义 建议发表截图信息表实现类
 * @author:  AVICIT DEV
 */
@Service
public class MdcsProposalPictureService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(MdcsProposalPictureService.class);
	
    private static final long serialVersionUID = 1L;
    
	@Autowired
	private MdcsProposalPictureDao dao;

	/**
	 * 保存建议发表截图信息
	 * @param String proposalId
	 * @param String saveAddress
	 * @param String pictureOption
	 * @return String
	 * @throws DaoException
	 */
	public String saveProposalPictureData(String proposalId,String saveAddress, String pictureOption,byte[] pictureInfo)throws Exception{
		try {
			MdcsProposalPictureDTO dto = new MdcsProposalPictureDTO();
			int count =  dao.findProposalIsHave(proposalId);
			if(count > 0){
			
				dao.updateProposalPictureData(proposalId,saveAddress,pictureOption,pictureInfo);
			}else{
				dto.setId(ComUtil.getId());
				dto.setProposalId(proposalId);
				dto.setSaveAddress(saveAddress);
				dto.setPictureInfo(pictureInfo);
				dto.setPictureOption(pictureOption);
				PojoUtil.setSysProperties(dto, OpType.insert);
				dao.saveProposalPictureData(dto);
			}
			return "success";
		} catch (DaoException e) {
			logger.error("saveProposalPictureData出错：",e);
			throw e;
		}
	}

	/**
	 * 根据建议id返回截图矩阵信息
	 * @param String
	 * author by heps
	 */
	public String getPicturePosition(String id)throws DaoException{
		try {
			String result = dao.getPicturePosition(id);
			
			return result;
		} catch (DaoException e){
			logger.error("getPicturePosition出错：",e);
			throw e;
		}
	}
	/**
	 * 根据建议id查询图片
	 * @param id 建议id
	 * @return
	 */
	public MdcsProposalPictureDTO queryCappFileWordByPrimaryKey(String id) {
		MdcsProposalPictureDTO dto = dao.queryCappFileWordByPrimaryKey(id);
		return dto;
	}
}