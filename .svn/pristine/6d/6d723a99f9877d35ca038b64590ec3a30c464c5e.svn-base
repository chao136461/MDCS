package avicit.discussion_manage.structuralrelationship.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import avicit.discussion_manage.structuralrelationship.dto.AddFormDataDTO;
import avicit.discussion_manage.structuralrelationship.dto.StructuralRelationshipDTO;
import avicit.discussion_manage.structuralrelationship.dto.ZtreeNodesDTO;
import avicit.discussion_manage.structuralrelationship.service.StructuralRelationshipService;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.core.rest.msg.PageParameter;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;

/**
 * @classname: StructuralRelationshipController
 * @description: 定义  结构关系表 控制层
 * @author:   AVICIT DEV 
 */
@Controller
@Scope("prototype")				 
@RequestMapping("discussion_manage/structuralrelationship/StructuralRelationshipController")
public class StructuralRelationshipController implements LoaderConstant{
    private static final Logger logger =  LoggerFactory.getLogger(StructuralRelationshipController.class);
    
    @Autowired
    private  StructuralRelationshipService service;
    

    
    /**
     * 跳转到管理页面
     * @param request 请求
     * @param reponse 响应
     * @return
     */
    @RequestMapping(value="StructuralRelationshipInfo")
	public ModelAndView toStructuralRelationship(HttpServletRequest request,HttpServletResponse reponse){
		ModelAndView mav = new ModelAndView();
		String url ="platform/discussion_manage/structuralrelationship/StructuralRelationshipController";
		mav.setViewName("avicit/discussion_manage/structuralrelationship/StructuralRelationshipManage");
		request.setAttribute("url", url);
		return mav;
	}
    
    //初始化vci树
    @RequestMapping(value="/gettreeZtree/{level}")
   	@ResponseBody
   	public List<ZtreeNodesDTO> getZtreeStructuralRelationshipByParentId(@PathVariable(value="level")int level,@RequestParam(required=false) String id,HttpServletRequest request){	
   		if(id == null){
   			id ="-100";
   		}else{
   			level=1;
   		}
   		return service.getZtreeStructuralRelationshipByParentStrId(id,level);
   	}
    
    //查询vci树
	@RequestMapping(value="/searchZtree/{level}",method=RequestMethod.POST)
   	@ResponseBody
   	public List<ZtreeNodesDTO> getZtreeStructuralRelationshipBySerchData(HttpServletRequest request,@PathVariable(value="level")int level,@RequestParam(required=false) String id){	
    	AddFormDataDTO addFormDataDTO = new AddFormDataDTO();
    	String className = request.getParameter("className");
    	String classCode = request.getParameter("classCode");
    	//id != 展开查询该节点的子节点
    	if(null != id){
    		return service.getZtreeStructuralRelationshipByParentStrId(id, 1);
    	}else{//否则查询特定vci树
    		addFormDataDTO.setClassName(className);
    		addFormDataDTO.setClassCode(classCode);
    		List<ZtreeNodesDTO> list = service.getZtreeStructuralRelationshipBySerchData(level,addFormDataDTO);
    		return list;
    	}
   	} 
	
	//讨论区根据零件id展示vci树
	@RequestMapping(value="/searchVciZtreeByStrId/{strId}")
	@ResponseBody
	public List<ZtreeNodesDTO> searchVciZtreeByStrId(HttpServletRequest request,@PathVariable(value="strId")String strId)throws Exception{	
		List<ZtreeNodesDTO> result =new ArrayList<ZtreeNodesDTO>();
		result = service.searchVciZtreeByStrId(strId);
		return result;
	}
	
    //跳转添加或编辑页面
    @RequestMapping(value="/operation/{type}/{id}")
	public ModelAndView  toInsertOrModifyStructuralRelationship(@PathVariable(value="type")String type,
												 @PathVariable(value="id")String id,
												 HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView();
		String url ="platform/discussion_manage/structuralrelationship/StructuralRelationshipController/saveStructuralRelationship";
		if("Edit".equals(type)){
			AddFormDataDTO dto = service.getAddformDataDTOById(id);
			request.setAttribute("addformDataDTO", dto);
		}
		request.setAttribute("url", url);
		request.setAttribute("id", id);
		mav.setViewName("avicit/discussion_manage/structuralrelationship/"+"StructuralRelationship"+type);
		return mav;
	}
	
    //保存数据
	@RequestMapping(value="/saveStructuralRelationship",method=RequestMethod.POST)
	public ModelAndView toSaveStructuralRelationship( HttpServletRequest request,@RequestBody AddFormDataDTO dto){
		ModelAndView mav= new ModelAndView();
		String  addType = dto.getAddType();
		String returnString ="";
		try {
			StringBuffer  msg= findStrIsHasByclassCodeNew(dto);
			if ("0".equals(addType)) {// 新增平级
				
				if(null == msg){//零件不存在
					returnString = service.insertStructuralRelationship(dto,request);
					mav.addObject("type", "0");
					mav.addObject("flag", "success");
					mav.addObject("returnString", returnString);
				}else{
					returnString = msg.toString();
					mav.addObject("returnString", returnString);
				}
			} 
			if ("1".equals(addType)) {// 新增子节点
				if(null == msg){
					 returnString = service.insertStructuralRelationship(dto,request);
					 mav.addObject("type", "1");
					 mav.addObject("flag", "success");
					 mav.addObject("returnString", returnString);
				}else{
					returnString = msg.toString();
					mav.addObject("returnString", returnString);
				}

			}
			if("2".equals(addType)){//更新该节点
				returnString = service.updateStructureManage(dto);
				 mav.addObject("type", "2");
				 mav.addObject("flag", "success");
				 mav.addObject("returnString", returnString);
			}
			if("3".equals(addType)){//新增产品节点（根节点）
				int count = service.searchIsHasProject(dto.getClassCode());
				if(count == 0){
					service.insertProjectRootNode(dto,request);
					mav.addObject("type", "3");
					mav.addObject("flag", "success");
					mav.addObject("returnString", "保存成功！");
				}else{
					mav.addObject("flag", "error");
					mav.addObject("returnString", "该产品已经存在！");
				}
			}
		}catch(Exception e){
			logger.debug(e.getMessage());
			e.printStackTrace();
			mav.addObject("error", e.getMessage());
			return mav;  
		}
		return mav;
	}
	
	//删除数据
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ModelAndView toDeleteStructuralRelationship(@RequestBody String[] ids,HttpServletRequest request){
		ModelAndView mav= new ModelAndView();
		Map<String, Object> map = null;
		try {
			map = service.deleteStructuralRelationship(ids);
			 
		}catch (Exception ex) {
			logger.debug(ex.getMessage());
			ex.printStackTrace();
			mav.addObject("error", ex.getMessage());
			mav.addObject("msg", map.get("returnString"));
			return mav;
		}
		mav.addObject("flag", "success");
		mav.addObject("msg", map.get("returnString"));
		return mav;
	}
	
	//判断用户添加已经存在的零件数据是否正确
	private  StringBuffer  findStrIsHasByclassCodeNew(AddFormDataDTO dto) throws Exception{
			String  classCodeNew  =  dto.getClassCode();
			AddFormDataDTO  strDTO = service.findStrIsHasByclassCodeNew(classCodeNew);
			StringBuffer  errorMsg = new StringBuffer();
			if(null  != strDTO && !"".equals(strDTO)){
				String className = strDTO.getClassName()==null?"":strDTO.getClassName();
				String classCode = strDTO.getClassCode();
				String classType = strDTO.getClassType()==null?"":strDTO.getClassType();
				String  edition   = strDTO.getEdition()==null?"":strDTO.getEdition();
				String classNameNew = dto.getClassName();
				String classTypeNew = dto.getClassType();
				String editionNew   = dto.getEdition();
				if(!className.equals(classNameNew)){
					errorMsg.append("<font color='red'>该零件已经存在</font></br>分类名称：【").append(classNameNew).append("】错误\n");
				}
				if(!classCode.equals(classCodeNew)){
					errorMsg.append("分类编号：【").append(classCodeNew).append("】错误\n ");
				}
				if(!edition.equals(editionNew)){
					errorMsg.append("版本：【").append(editionNew).append("】错误\n ");
				}
				if(!classType.equals(classTypeNew)){
					errorMsg.append("分类类型：【").append(classType).append(" 】错误");
				}
				if( errorMsg.length() == 0){
					return null;
				}
				return errorMsg;
			}
			return null;
	}
	
	//保存拖拽后的数据结构
	@RequestMapping(value="/saveDragNode",method=RequestMethod.POST)
	public ModelAndView toSaveDragNode(@RequestBody String[] ids,HttpServletRequest request){
		ModelAndView mav= new ModelAndView();
		try {
			service.toSaveDragNode(ids);
			return mav.addObject("flag", "success");
		} catch (Exception e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
			mav.addObject("flag", "error");
			mav.addObject("msg", "执行失败！");
			return mav;
		}
	}
	
	//点击节点返回该节点零件信息
    @RequestMapping(value="/getDgStructuralRelationship")
    @ResponseBody
	public Map<String,Object>  getDgStructuralRelationship(PageParameter pageParameter,HttpServletRequest request) throws Exception{
			QueryReqBean<AddFormDataDTO> queryReqBean = new QueryReqBean<AddFormDataDTO>();
			queryReqBean.setPageParameter(pageParameter);
			HashMap<String,Object> map = new HashMap<String, Object>();
			String id = ServletRequestUtils.getStringParameter(request, "id", "");
			QueryRespBean<AddFormDataDTO> result =null;
			try {
				result = service.getDgStructuralRelationship(queryReqBean,id);
			} catch (Exception ex) {
				logger.debug(ex.getMessage());
				return map;  
			}
			for(AddFormDataDTO dto :result.getResult()){
				String  designerName = sysUserLoader.getSysUserNameById(dto.getDesignerId());
				dto.setDesignerName(designerName);
    		}
			map.put("total",result.getPageParameter().getTotalCount());
			map.put("rows", result.getResult());
			logger.debug("成功获取分页数据");
			return map;
	}
    
    //查询型号20170729lf
    
    @RequestMapping(value="/findStructuralType",method=RequestMethod.POST)
    @ResponseBody
    public List<StructuralRelationshipDTO> findStructuralType(String type,HttpServletRequest request,HttpServletResponse response) throws Exception{
    		ModelAndView mav = new ModelAndView();
    		List<StructuralRelationshipDTO> list =service.findStructuralType(type);
        		request.setAttribute("list", list);
				return list;
        			
    }
}