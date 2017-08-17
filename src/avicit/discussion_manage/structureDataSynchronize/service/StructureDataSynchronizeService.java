package avicit.discussion_manage.structureDataSynchronize.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.discussion_manage.discussionmanage.dto.DiscussionManageDTO;
import avicit.discussion_manage.discussionmanage.service.DiscussionManageService;
import avicit.discussion_manage.proposalmanage.dao.ProposalManageDao;
import avicit.discussion_manage.proposalmanage.service.ProposalManageService;
import avicit.discussion_manage.relevanceperson.service.RelevancePersonService;
import avicit.discussion_manage.structuralrelationship.dto.StructuralRelationshipDTO;
import avicit.discussion_manage.structuralrelationship.service.StructuralRelationshipService;
import avicit.discussion_manage.structureDataSynchronize.dao.StructureDataSynchronizeDAO;
import avicit.discussion_manage.structureDataSynchronize.dto.WebserviceXmlStructureDTO;
import avicit.discussion_manage.structuremanage.dto.StructureManageDTO;
import avicit.discussion_manage.structuremanage.service.StructureManageService;
import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.api.sysuser.dto.SysUser;
import avicit.platform6.core.exception.DaoException;

/**
 * @classname:  StructureDataSynchronizeService
 * @description: 定义 结构关系数据同步实现类
 * @author:  heps
 */
@Service
public class StructureDataSynchronizeService implements LoaderConstant{

	private static final Logger logger =  LoggerFactory.getLogger(StructuralRelationshipService.class);
	
	@Autowired
	private StructureDataSynchronizeDAO dao;
	@Autowired
	private StructureManageService service;
	@Autowired
	private StructuralRelationshipService reservice;
	@Autowired
	private ProposalManageService proService;
	@Autowired
	private DiscussionManageService  disService;
	@Autowired
	private RelevancePersonService  relService;
	@Autowired
	private ProposalManageDao dao2;
	private String tnmcode="" ;

	public String  updateStructureDataSynchronize(String data)throws Exception{
		try {
				
			Vector<WebserviceXmlStructureDTO> vector = XmlStrBeanVectorWeberviceDataUtil.getXmlStrBeanVectorByWeberviceData(data);
			
			Iterator<WebserviceXmlStructureDTO>	iterator = vector.iterator();
		
			while(iterator.hasNext()){
				WebserviceXmlStructureDTO  dto = iterator.next();
				List<WebserviceXmlStructureDTO> subDtoList = dto.getSubparts();
				//机型
				String jixing= dto.getJixing();
				//该机型是否已经配置默认专业(厂区配置一条，所区配置两条)，所以检查是否为三条
				int count = dao.searchIfHasConfig(jixing);
				if(count != 3){
					return "this project not configuration info";
				}
				String jx="";//机型ID
				String partId="";//零件ID
				//查询机型id
				jx = dao.findJiXinClassCode(jixing);
				if(jx==null||"".equals(jx)){
					 jx = this.insertStrShip(dto,"6");//新增零件表记录，设定类型为6的为机型 
					 this.insertStrRel(jx,dto,"6","","");	  //新增零件关系表，
				}
				if(subDtoList.size()>0){
					//零件ID
					partId= dao.findHasClassCode(dto.getClassCode());
					if("".equals (partId)|| partId==null){
						partId = this.insertStrShip(dto,"");//新增零件表记录
						this.insertStrRel(jx,dto,"",partId,jx);	//新增零件关系表
					}else{
						this.updateStrManger(partId,dto);
					}
					String subId= "";
					//删除关系表对应零件
					dao.deletStrShip(jx,partId);
					
					for (int i = 0; i < subDtoList.size(); i++) {
						WebserviceXmlStructureDTO subDto = subDtoList.get(i);
						String subPartId = dao.findHasClassCode(subDto.getClassCode());
						if(subPartId==null||"".equals(subPartId)){
							subId = this.insertStrShip(subDto,"");//新增零件表记录
							this.insertStrRel(jx,subDto,"",subId,partId);//新增零件关系表
						}else{
							this.updateStrManger(subPartId,subDto);
							this.insertStrRel(jx,subDto,"",subPartId,partId);//新增零件关系表
						}
					}
				}else{
					String subId="";
					String subPartId = dao.findHasClassCode(dto.getClassCode());
					if(subPartId==null||"".equals(subPartId)){
						subId = this.insertStrShip(dto,"");//新增零件表记录
						if("0".equals(dto.getClassType())){
							this.insertStrRel(jx,dto,"",subId,jx);	//新增零件关系表
						}
					}else{
						this.updateStrManger(subPartId,dto);
					}
				}
			}
			return "success";
		}catch (Exception e) {
			logger.error("updateStructureDataSynchronize出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}
	/**
	 * 修改
	 * @param jxId 机型ID
	 * @param dto xml字符串对象
	 * @throws Exception 
	 */
	private void updateStrManger(String jxId,WebserviceXmlStructureDTO dto) throws Exception {
		StructureManageDTO  strdto=null;
			strdto = new StructureManageDTO();
			//成熟度
			strdto.setStatus(dto.getStatus());
			//版本
			strdto.setEdition(dto.getEdition());
			//设计者
			strdto.setDesignerId(relService.getUserIdByUserName(dto.getDesignerId()));
			//零件类型
			strdto.setClassType(dto.getClassType());
			//零件编号
			strdto.setClassCode(dto.getClassCode());
			//零件名称
			strdto.setClassName(dto.getClassName());
			//零件表非机型id
			strdto.setId(jxId);
		try {
			service.updateStructureManageSensitive(strdto);
		} catch (Exception e) {
			logger.error("updateStructureManageSensitive出错：", e);
			e.printStackTrace();
		}
	}
	/**
	 * 零件表添加
	 * @param dto xml对象
	 * @param type 添加类型，6为机型添加，其他为零件
	 * @return ID，零件id
	 * @throws Exception
	 */
	private String  insertStrShip(WebserviceXmlStructureDTO dto,String type) throws Exception {
		String  id = "";
		StructureManageDTO  strdto=null;
		if(type.equals("6")){//type=6 。说明该零件为机型
			strdto = new StructureManageDTO();
			//成熟度
			strdto.setStatus("");
			//版本
			strdto.setEdition("");
			//设计者
			strdto.setDesignerId("");
			//零件类型
			strdto.setClassType(type);
			//零件编号
			strdto.setClassCode(dto.getJixing());
			//零件名称
			strdto.setClassName("");
			//零件表添加
		}else{
			strdto = new StructureManageDTO();
			//成熟度
			strdto.setStatus(dto.getStatus());
			//版本
			strdto.setEdition(dto.getEdition());
			//设计者
			strdto.setDesignerId(relService.getUserIdByUserName(dto.getDesignerId()));
			//零件类型
			strdto.setClassType(dto.getClassType());
			//零件编号
			strdto.setClassCode(dto.getClassCode());
			//零件名称
			strdto.setClassName(dto.getClassName());
			//零件表添加
		}
		id = service.insertStructureManage(strdto);
		if(!"6".equals(type)&&"M2".equals(dto.getStatus())){
			this.createDiscussion(strdto,id);
		}
		return id;
	}
	/**
	 * 关系表数据添加
	 * @param jxid 机型ID
	 * @param dto xml对象
	 * @param type 添加类型，6为机型添加，其他为零件
	 * @param partId 零件prentID
	 * @param parentId 关系表PrentID
	 * @throws Exception
	 */
	private  void   insertStrRel(String jxid,WebserviceXmlStructureDTO dto, String type,String partId,String parentId) throws Exception {
		StructuralRelationshipDTO shipDto =null;
		if(type.equals("6")){//type=6 。说明该零件为机型
			shipDto= new StructuralRelationshipDTO();
			//零件ID
			shipDto.setStrId(jxid);
			//实例编号
			shipDto.setInstancenumber("");
			shipDto.setParentStrId("-100");
			shipDto.setInstanceId(jxid);
		}else{
			shipDto = new StructuralRelationshipDTO();
			//零件ID
			shipDto.setStrId(partId);
			//实例编号
			shipDto.setInstancenumber(dto.getInstancenumber());
			//机型关系作为产品ID
			shipDto.setParentStrId(parentId);
			shipDto.setInstanceId(jxid);
			//创建时间
			shipDto.setCreatetime(dto.getCreatetime());
			//修改时间
			shipDto.setModifytime(dto.getModifytime());
			//关系表添加
		}
		reservice.insertStructuralRelationships(shipDto);
	}
	/**
	 *创建讨论区
	 * @param strdto 零件表对象
	 * @param id 零件id
	 */
	private void createDiscussion(StructureManageDTO strdto,String id){
		DiscussionManageDTO dto = new DiscussionManageDTO();
		try {
		dto.setStrId(id);//零件id
			tnmcode = this.getInfoById();
		dto.setTnmCode(tnmcode);//零件讨论区流水号
		dto.setCreateDate(new Date());
		Map<String, Object>  param = new HashMap<String, Object>();
		param.put("id", id);//零件id
		param.put("clientIp", "127.0.0.1");//创建人IP
		//集成系统登录名对应本系统登录iD
		String userId = relService.getUserIdByUserName(strdto.getDesignerId());
		param.put("sysUserId", userId);//零件创建人
			//对应零件讨论区状态
			String status = disService.getStatusByStrId(id);
			if(status==null||"".equals(status)){
				dto.setStatus("a");
				disService.insertDiscussionManage(dto,param,id);
				String classType = service.findClassType(id);
				if (classType.equals("0")) {
					//根据角色code返回角色对象（工艺代表）
					List<SysUser> userlist = sysUserRoleLoader.getSysUserListBySysRoleCode("tec_represent");
					disService.insertProcessGongyiBystrId(id,userlist);
				}
			}
		} catch (Exception e) {
			logger.error("insertDiscussionManage出错：", e);
			e.printStackTrace();
		}
	}
	
	public String getInfoById() {
		String str = "JY";
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String dates = sf.format(new Date());
		// 根据当天日期查询除最大的值
		Integer number = dao2.getInfoById();
		if(tnmcode.equals("")|| null ==tnmcode){
			number++;
		}
		// 如果最大值存在则加1
		if (number != null) {
			number = number + 1;
		} else {// 否则给初始值00000
			number = 000;
		}
		// 格式化number
		String number2 = String.format("%03d", number);
		str += dates + number2;
		return str;
	}
	
	
}
