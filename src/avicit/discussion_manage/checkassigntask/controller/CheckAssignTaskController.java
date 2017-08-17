package avicit.discussion_manage.checkassigntask.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.discussion_manage.checkassigntask.dto.CheckAssignTaskDTO;
import avicit.discussion_manage.checkassigntask.service.CheckAssignTaskService;

/**
 * @classname: CheckAssignTaskController
 * @description: 定义 审查分工待办表 控制层
 * @author: AVICIT DEV
 */
@Controller
@Scope("prototype")
@RequestMapping("discussion_manage/checkassigntask/CheckAssignTaskController")
public class CheckAssignTaskController implements LoaderConstant {
	private static final Logger logger = LoggerFactory.getLogger(CheckAssignTaskController.class);

	@Autowired
	private CheckAssignTaskService service;

	/**
	 * 跳转到 CheckAssignTaskManager.jsp
	 * @param request请求
	 * @param reponse响应
	 * @return ModelAndView
	 * @throws Exception 
	 */
	@RequestMapping(value = "toCheckAssignTaskManager")
	public ModelAndView toCheckAssignTaskManager(HttpServletRequest request,HttpServletResponse reponse) throws Exception {
		ModelAndView mav = new ModelAndView();
		String userId = ServletRequestUtils.getStringParameter(request, "userId", "");
		List<CheckAssignTaskDTO> dto = service.getCheckAssignTaskDTOListByLoginUserId(userId);
		request.setAttribute("rows", dto);
		request.setAttribute("total", dto.size());
		mav.setViewName("avicit/discussion_manage/checkassigntask/CheckAssignTaskManager");
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("title", "待办1");
		param.put("content", "的婆婆是店铺看看看客人【二【看普通客人pk慢慢订票可怕可怕破格楼盘【看【看看【OK【OK吗鬼门关");
		param.put("structureId", "vci");
		param.put("sendUser", "1");
		param.put("sendDeptid", "8a58ab4d4c2141fd014c217cdc5102b6");
		param.put("recvUser", "1");
//		service.insertSysMessageData(param, request);
		
		Map<String,Object> param2 = new HashMap<String,Object>();
		param2.put("taskTitle", "待办1");
		param2.put("taskType",  "1");
		param2.put("structureId", "8EE2CE995E734F0C84591459BC952246");
		param2.put("sponsorUserId", "1");
		param2.put("sponsorUserName", "平台管理员");
		param2.put("taskUserId", "1");
		param2.put("taskUserName", "平台管理员");
		param2.put("taskUrl", "taskUrl");
//		service.insertCheckAssignData(param2, request);
		
		return mav;
	}

}
