package avicit.discussion_manage.checkassigntask.service;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.platform6.api.session.SessionHelper;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.properties.PlatformConstant.OpResult;
import avicit.discussion_manage.checkassigntask.dao.CheckAssignTaskDao;
import avicit.discussion_manage.checkassigntask.dto.CheckAssignTaskDTO;

/**
 * @classname:  CheckAssignTaskService
 * @description: 定义 审查分工待办表实现类
 * @author:  AVICIT DEV
 */
@Service
public class CheckAssignTaskService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(CheckAssignTaskService.class);
	
    private static final long serialVersionUID = 1L;
    
//	@Autowired
//	private SysLogUtil sysLogUtil;
	
	@Autowired
	private CheckAssignTaskDao dao;
	/**
	 * 根据登陆人返回待办信息
	 * @param userid
	 * @return List<CheckAssignTaskDTO>
	 * @throws Exception
	 */
	public List<CheckAssignTaskDTO> getCheckAssignTaskDTOListByLoginUserId(String userId)throws Exception{
		try{
	    	List<CheckAssignTaskDTO> dataList = dao.getCheckAssignTaskDTOListByLoginUserId(userId);
	    	return dataList;
	    }catch(Exception e){
	    	logger.error("getCheckAssignTaskDTOListByLoginUserId出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}
	
	/**
	 * 审查分工发待办
	 * @param  Map<String,Object>(待办标题taskTitle,待办类型taskType,零部件ID structureId,待办发起人ID sponsorUserId,待办人ID taskUserId)
	 * @param  request
	 * @return Map<String,Object>(成功标识flag：成功success，失败failure)
	 * @throws Exception
	 */
	public Map<String,Object> insertCheckAssignData(Map<String,Object> param,HttpServletRequest request)throws Exception{
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			String taskTitle = param.get("taskTitle").toString();
			String taskType = param.get("taskType").toString();
			String structureId = param.get("structureId").toString();
			String sponsorUserId = param.get("sponsorUserId").toString();
			String sponsorUserName = param.get("sponsorUserName").toString();
			String taskUserId = param.get("taskUserId").toString();
			String taskUserName = param.get("taskUserName").toString();
			if(taskTitle == null || taskType == null || structureId == null || sponsorUserId == null || sponsorUserName == null || taskUserId == null || taskUserName == null){
				map.put("flag", OpResult.failure);
				return map;
			}
			if("1".equals(taskType)){
				param.put("taskUrl", "");
			}
			if("2".equals(taskType)){
				param.put("taskUrl", "");
			}

			if("3".equals(taskType)){
				param.put("taskUrl", "");
			}

			if("4".equals(taskType)){
				param.put("taskUrl", "");
			}
			if("5".equals(taskType)){
				param.put("taskUrl", "");
			}
			String id = ComUtil.getId();
			String clientIp  = SessionHelper.getClientIp(request);
			String sysUserId = SessionHelper.getLoginSysUserId(request);
			param.put("id", id);
			param.put("sysUserId", sysUserId);
			param.put("clientIp", clientIp);
			param.put("sendDate", new Date());
			dao.insertCheckAssignData(param);
			map.put("flag", OpResult.success);
	    	return map;
	    }catch(Exception e){
	    	logger.error("insertCheckAssignData出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}
	
	/**
	 * 审查分工发通知
	 * @param  Map<String,Object>(通知标题title，通知内容content，关联零部件structureId，消息发送人sendUser，消息发送人sendDeptid,消息接收人recvUser)
	 * @param  request
	 * @return Map<String,Object>(成功标识flag：成功success，失败failure)
	 * @throws Exception
	 */
	public Map<String,Object> insertSysMessageData(Map<String,Object> param,HttpServletRequest request)throws Exception{
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			String title = param.get("title").toString();
			String content = param.get("content").toString();
			String structureId = param.get("structureId").toString();
			String sendUser = param.get("sendUser").toString();
			String sendDeptid = param.get("sendDeptid").toString();
			String recvUser = param.get("recvUser").toString();
			if(title == null || content == null || structureId == null || sendUser == null || sendDeptid == null  || recvUser == null){
				map.put("flag", OpResult.failure);
				return map;
			}
			String id = ComUtil.getId();
			String clientIp  = SessionHelper.getClientIp(request);
			String sysUserId = SessionHelper.getLoginSysUserId(request);
			param.put("id", id);
			param.put("sysUserId", sysUserId);
			param.put("clientIp", clientIp);
			param.put("sendDate", new Date());
			dao.insertSysMessageData(param);
			map.put("flag", OpResult.success);
	    	return map;
	    }catch(Exception e){
	    	logger.error("insertSysMessageData出错：", e);
	    	throw new DaoException(e.getMessage(),e);
	    }
	}
}