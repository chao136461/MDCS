package avicit.discussion_manage.checkassigntask.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.discussion_manage.checkassigntask.dao.CheckAssignTaskDao;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.properties.PlatformConstant.OpResult;

/**
 * @classname:  CheckAssignTaskAftertreatmentService
 * @description: 待办后处理实现类
 * @author:  heps
 */
@Service
public class CheckAssignTaskAftertreatmentService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(CheckAssignTaskAftertreatmentService.class);
	
    private static final long serialVersionUID = 1L;
    
    @Autowired
	private CheckAssignTaskDao dao;
	/**
	 * 待办后处理
	 * @param structureId 零件ID
	 * @param taskType    待办类型
	 * @param taskUserId  待办人ID
	 * @throws Exception
	 */
	public String updateCheckAssignTaskAftertreatment(String structureId,String taskType,String taskUserId)throws Exception{
		try{
			if(null == structureId || "".equals(structureId)){
				return OpResult.failure +"【零件id为空】";			
			
			}else if(null == taskType || "".equals(taskType)){
				return OpResult.failure +"【待办类型为空】";			
			
			}else if(null == taskUserId || "".equals(taskUserId)){
				return OpResult.failure +"【待办人ID为空】";			
			
			}else{
				Map<String, Object> param = new  HashMap<String, Object>();
				param.put("structureId", structureId);
				param.put("taskType", taskType);
				param.put("taskUserId", taskUserId);
				dao.updateCheckAssignTaskAftertreatment(param);
				return OpResult.success.toString();
			}
	    }catch(Exception e){
	    	logger.error("checkAssignTaskAftertreatment出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}
}