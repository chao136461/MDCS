package avicit.discussion_manage.modelInterveneCheck.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.discussion_manage.modelInterveneCheck.dao.SynchronousInterveneDataDAO;
import avicit.discussion_manage.modelInterveneCheck.dto.InterveneDTO;
import avicit.discussion_manage.modelInterveneCheck.util.ResolveInterveneXml;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.properties.PlatformConstant.OpType;


@Service
public class SynchronousInterveneDataervice implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger logger =  LoggerFactory.getLogger(SynchronousInterveneDataervice.class);

	@Autowired
	private SynchronousInterveneDataDAO dao;
	
	/**
	 * 保存干涉数据
	 * @param String
	 * @throws Exception
	 */
	public  String  saveResolveInterveneXmlData(String data) throws Exception{
		String resultInfo = "";
		try {
			List<InterveneDTO> result = ResolveInterveneXml.getInterveneDataByWs(data);
			for (int i = 0; i < result.size(); i++) {
				InterveneDTO dto = result.get(i);
				String classCode = dto.getClassCode();
				String strId = dao.findStrIdByClassCode(classCode);
				if(null != strId && !"".equals(strId)){
					//零件表存在该零件信息
					int count = dao.findInterveneIdCount(strId, dto.getInstanceNumber());
					if(count > 0){
						//模型干涉表存在该零件信息则更新
						dto.setStrId(strId);
						PojoUtil.setSysProperties(dto, OpType.insert);
						dto.setVersion(dto.getVersion()+1);
						dao.updateInterveneDataByStrId(dto);
					}else{
						//模型干涉表存不在该零件信息则新增
						dto.setId(ComUtil.getId());
						dto.setStrId(strId);
						String instanceNumber  = dto.getInstanceNumber();
						String parentId = dao.findParentIdByStrId(strId,instanceNumber);
						parentId = parentId==null?"":parentId;
						dto.setParentId(parentId);
						PojoUtil.setSysProperties(dto, OpType.insert);
						dao.insertResolveInterveneXmlData(dto);
					}
				}else{
					//零件表不存在该零件信息返回提示（格式示例classCode$instanceNumber##classCode$instanceNumber）		
					resultInfo += dto.getClassCode()+"$"+dto.getInstanceNumber()+"##";	
				}
			}
			if(resultInfo.length() > 0){
				return resultInfo.substring(0, resultInfo.length()-2);
			}else{
				return "success";
			}
		} catch (Exception e) {
			logger.error("saveResolveInterveneXmlData出错：",e);
			throw new DaoException("",e);
		}
	}
}