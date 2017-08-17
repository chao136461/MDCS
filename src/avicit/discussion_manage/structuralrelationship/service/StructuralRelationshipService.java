package avicit.discussion_manage.structuralrelationship.service;
import java.io.Serializable;
import java.util.ArrayList;
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
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.discussion_manage.structuralrelationship.dao.StructuralRelationshipDao;
import avicit.discussion_manage.structuralrelationship.dto.AddFormDataDTO;
import avicit.discussion_manage.structuralrelationship.dto.StructuralRelationshipDTO;
import avicit.discussion_manage.structuralrelationship.dto.ZtreeNodesDTO;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname:  StructuralRelationshipService
 * @description: 定义 结构关系表实现类
 * @author:  AVICIT DEV
 */
@Service
public class StructuralRelationshipService implements Serializable{

	private static final long serialVersionUID = 1L;

	private static final Logger logger =  LoggerFactory.getLogger(StructuralRelationshipService.class);
	
	@Autowired
	private StructuralRelationshipDao dao;

	
	//插入数据
	public String insertStructuralRelationship(AddFormDataDTO dto,HttpServletRequest request) throws Exception{
		
		try{
			String designerId = SessionHelper.getLoginSysUserId(request);
			String clientIp = SessionHelper.getClientIp(request);
			dto.setDesignerId(designerId);
			dto.setLastUpdateIp(clientIp);
			dao.insertStructuralRelationship(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return dto.getReturnString();
		}catch(Exception e){
			logger.error("insertStructuralRelationship出错：", e);
			throw new DaoException(e.getMessage(),e);
		}	
	}
	/**
	 * 新增对象
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public String insertStructuralRelationships(StructuralRelationshipDTO dto) throws Exception {
		try{
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertStructuralRelationships(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		}catch(Exception e){
			logger.error("insertStructuralRelationship出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}
	/**
	 * 修改对象部分字段
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateStructuralRelationshipSensitive(StructuralRelationshipDTO dto) throws Exception {
		try{
			//记录日志
			StructuralRelationshipDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateStructuralRelationshipSensitive(old);
			if(count ==0){
				throw new DaoException("数据失效，请重新更新");
			}
			return count;
		}catch(Exception e){
			logger.error("searchDemoBusinessTrip出错：", e);
			throw new DaoException(e.getMessage(),e);
		}

	}
	/**
	 * 通过主键查询单条记录
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<AddFormDataDTO> getDgStructuralRelationship(QueryReqBean<AddFormDataDTO> queryReqBean,String id) throws Exception {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			Page<AddFormDataDTO> dataList =  dao.getDgStructuralRelationship(id);
			QueryRespBean<AddFormDataDTO> queryRespBean =new QueryRespBean<AddFormDataDTO>();
			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(Exception e){
			logger.error("getAddformDataDTOById出错：", e);
			throw new DaoException("",e);
		}
	}
	
	
	/**
	 * 通过零件查询单条记录
	 * @param id
	 * @return AddFormDataDTO
	 * @throws Exception
	 */
	public AddFormDataDTO getAddformDataDTOById(String id) throws Exception {
		try{
			AddFormDataDTO dto =  dao.getAddformDataDTOById(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
			logger.error("getAddformDataDTOById出错：", e);
			throw new DaoException("",e);
		}
	}
	
	//更新数据
	public String updateStructureManage(AddFormDataDTO dto) throws Exception{
		
		try {
			 dao.updateStructureManage(dto);
			 return dto.getReturnString();
		} catch (Exception e) {
			logger.error("updatesStructureManage出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
		
	}
	
	//删除数据
	public Map<String, Object> deleteStructuralRelationship(String[] ids) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", ids[0]);
		map.put("pId", ids[1]);
	    dao.deleteStructureManage(map);
		return map;
	}

	//按照父id查询Ztree数据
		public List<ZtreeNodesDTO> getZtreeStructuralRelationshipByParentStrId(String parentId,int level){
			List<ZtreeNodesDTO> tree = new ArrayList<ZtreeNodesDTO>();
			if (level == 0) {
				return null;
			}
			level--;
			
			List<Map<String, Object>> subTrees = dao.getStructuralRelationshipByParentStrId(parentId);
			
			for (Map<String, Object> map : subTrees) {
				ZtreeNodesDTO node = new ZtreeNodesDTO();
				node.setpId(map.get("ParentStrId").toString());
				String id = map.get("StrId").toString();
				String tabId = map.get("id").toString();
				node.setId(id);
				node.setTabId(tabId);
				node.setName(map.get("ClassCode").toString());
				HashMap<String, Object> attr = new HashMap<String, Object>(1);
				Integer count = Integer.valueOf(map.get("childCount").toString());
				attr.put("count",count);
				node.setAttributes(attr);
				if (count > 0) {
					if (count > 0) {
						node.setOpen("true");
						node.setIsParent("true");
						node.setIcon("static/js/platform/component/zTree/css/zTreeStyle/img/diy/chilun2.png");
					} else {
						node.setOpen("false");
					}
					node.setChildren(this.getZtreeStructuralRelationshipByParentStrId(id,level));
				} else {
					node.setIcon("static/js/platform/component/zTree/css/zTreeStyle/img/diy/chilun3.png");
					node.setOpen("open");
				}
				tree.add(node);
			}
			return tree;
		}

		/**
		    * 根据vci编号查询零件id集合
		    * @author xul
		    * */
		public List<String> getStrIdByCode(String code) throws Exception{
			List<String> ids = dao.getStrIdByCode(code);
			return ids;
		}

		
		//判断用户添加的零件是否存在
		public AddFormDataDTO findStrIsHasByclassCodeNew(String classCodeNew) throws Exception{
			try {
				return dao.findStrIsHasByclassCodeNew(classCodeNew);
			} catch (Exception e) {
				logger.error("findStrIsHasById出错：",e);
				throw new DaoException("",e);
			}
		}
		
		//保存拖拽后的数据结构
		public void toSaveDragNode(String[] ids) {
			Map<String, Object>  map = new HashMap<String, Object>();
			try {
				 map.put("targetNodePid", ids[0]);
				 map.put("tabId", ids[1]);
				 //map.put("nodeParentId", ids[2]);
				 dao.toSaveDragNode(map);
			} catch (Exception e) {
				logger.error("toSaveDragNode出错：",e);
				throw new DaoException("",e);
			}
		}
		
		 //查询vci树
		public List<ZtreeNodesDTO> getZtreeStructuralRelationshipBySerchData(int level, AddFormDataDTO serchData) {
			List<ZtreeNodesDTO> tree = new ArrayList<ZtreeNodesDTO>();
			if (level == 0) {
				return null;
			}
			level--;
			List<Map<String, Object>> subTrees = dao.getZtreeStructuralRelationshipBySerchData(serchData);
			
			for (Map<String, Object> map : subTrees) {
				ZtreeNodesDTO node = new ZtreeNodesDTO();
				node.setpId(map.get("ParentStrId").toString());
				String id = map.get("StrId").toString();
				String tabId = map.get("id").toString();
				node.setId(id);
				node.setTabId(tabId);
				node.setName(map.get("ClassCode").toString());
				node.setIcon("static/js/platform/component/zTree/css/zTreeStyle/img/diy/chilun2.png");
				HashMap<String, Object> attr = new HashMap<String, Object>(1);
				Integer count = Integer.valueOf(map.get("childCount").toString());
				attr.put("count",count);
				node.setAttributes(attr);
				if (count > 0) {
					if (count > 0) {
						node.setOpen("true");
						node.setIsParent("true");
					} else {
						node.setOpen("false");
					}
					node.setChildren(this.getZtreeStructuralRelationshipByParentStrId(id,level));
				} else {
					node.setOpen("open");
				}
				tree.add(node);
			}
			return tree;
		}

		//讨论区根据零件id展示vci树
		public List<ZtreeNodesDTO> searchVciZtreeByStrId(String strId)throws Exception{
			
			List<ZtreeNodesDTO> tree = new ArrayList<ZtreeNodesDTO>();
			try {
				List<Map<String, Object>> searchNodes = dao.searchVciZtreeByStrId(strId);
				for(Map<String, Object> map : searchNodes){
					ZtreeNodesDTO node = new ZtreeNodesDTO();
					String id = map.get("StrId").toString();
					String pId = map.get("ParentStrId").toString();
					String classCode = map.get("ClassCode").toString();
					String children = map.get("children").toString();
					String instancenumber = (map.get("instancenumber")==null?"":map.get("instancenumber")).toString();
					node.setId(id);
					node.setpId(pId);
					node.setName(classCode);
					node.setInstancenumber(instancenumber);
					node.setOpen("true");
					if("0".equals(children)){
						node.setIcon("static/js/platform/component/zTree/css/zTreeStyle/img/diy/chilun3.png");
					}else{
						node.setIcon("static/js/platform/component/zTree/css/zTreeStyle/img/diy/chilun2.png");
					}
					tree.add(node);
				}
				return tree;
			} catch (Exception e) {
				logger.error("searchVciZtreeByStrId出错：",e);
				throw e;
			}
		}
		/**
		 * 日志专用，内部方法，不再记录日志
		 */
		private StructuralRelationshipDTO findById(String id) throws Exception {
			try{
				StructuralRelationshipDTO dto = dao.findStructuralRelationshipById(id);
				return dto;
			}catch(DaoException e){
		    		logger.error("findStructuralRelationship出错：", e);
		    		throw e;
		    }
		}
		 //查询型号20170729lf
		public List<StructuralRelationshipDTO> findStructuralType(String type){
			List<StructuralRelationshipDTO> list = dao.findStructuralType(null);
			return list;
		}
		
		/**
		 * 新增产品节点（根节点）
		 * @param AddFormDataDTO
		 * @throws Exception
		 * heps
		 */
		public void insertProjectRootNode(AddFormDataDTO dto,HttpServletRequest request)throws Exception{
			try {
				PojoUtil.setSysProperties(dto, OpType.insert);
				dto.setId(ComUtil.getId());
				//零部件表插数据
				dao.insertProjectRootNode(dto);
				Map<String, Object>  map = new HashMap<String, Object>();
				String tabId =  ComUtil.getId();
				map.put("id",tabId);
				map.put("strId",dto.getId());
				map.put("createdBy", SessionHelper.getLoginSysUserId(request));
				map.put("lastUpdateIp", SessionHelper.getClientIp(request));
				//关系表插数据 
				dao.insertProjectRootNodeToMiddleTable(map);
			} catch (DaoException e) {
				logger.error("insertProjectRootNode出错：",e);
				throw e;
			}
		}
		
		/**
		 * 查询产品是否已经存现
		 * @param String
		 * @throws Exception
		 * heps
		 */
		public int searchIsHasProject(String classCode)throws Exception {
			try {
				int count = dao.searchIsHasProject(classCode);
				return count;
			} catch (DaoException e) {
				logger.error("searchIsHasProject出错：",e);
				throw e;
			}
		}
}