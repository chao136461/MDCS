package avicit.discussion_manage.discussionmanage.service;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.discussion_manage.discussionmanage.dao.DiscussionManageDao;
import avicit.discussion_manage.discussionmanage.dto.DiscussionInfoDTO;
import avicit.discussion_manage.discussionmanage.dto.DiscussionManageDTO;
import avicit.discussion_manage.modelInterveneCheck.dto.InterveneDTO;
import avicit.discussion_manage.processassignment.dto.ProcessAssignmentDTO;
import avicit.discussion_manage.processassignment.service.ProcessAssignmentService;
import avicit.discussion_manage.processrepresentative.dto.ProcessRepresentativeDTO;
import avicit.discussion_manage.processrepresentative.service.ProcessRepresentativeService;
import avicit.platform6.api.sysuser.dto.SysUser;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.sfn.intercept.SelfDefinedQuery;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname:  DiscussionManageService
 * @description: 定义 讨论区管理实现类
 * @author:  AVICIT DEV
 */
@Service
public class DiscussionManageService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(DiscussionManageService.class);
	
    private static final long serialVersionUID = 1L;
    
//	@Autowired
//	private SysLogUtil sysLogUtil;
	
	@Autowired
	private DiscussionManageDao dao;
	@Autowired
	private ProcessAssignmentService service;
	@Autowired
	private ProcessRepresentativeService representativeService;

	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<DiscussionManageDTO> searchDiscussionManageByPage(QueryReqBean<DiscussionManageDTO> queryReqBean,String sfnConditions) throws Exception {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			DiscussionManageDTO searchParams = queryReqBean.getSearchParams();
            SelfDefinedQuery sdc=new SelfDefinedQuery(sfnConditions,"t1");
			Page<DiscussionManageDTO> dataList =  dao.searchDiscussionManageByPage(searchParams,sdc.parseSql());
			QueryRespBean<DiscussionManageDTO> queryRespBean =new QueryRespBean<DiscussionManageDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(DaoException e){
			logger.error("searchDiscussionManageByPaging出错：", e);
			throw e;
		}
	}
	/**
	 * 按条件查询，不分页
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public List<DiscussionManageDTO> searchDiscussionManage(QueryReqBean<DiscussionManageDTO> queryReqBean) throws Exception {
	    try{
	    	DiscussionManageDTO searchParams = queryReqBean.getSearchParams();
	    	List<DiscussionManageDTO> dataList = dao.searchDiscussionManage(searchParams);
	    	return dataList;
	    }catch(Exception e){
	    	logger.error("searchDiscussionManage出错：", e);
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
	public DiscussionManageDTO queryDiscussionManageByPrimaryKey(String id) throws Exception {
		try{
			DiscussionManageDTO dto = dao.findDiscussionManageById(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchDiscussionManage出错：", e);
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
	
	public String insertDiscussionManage(DiscussionManageDTO dto,Map<String, Object>  param,String strId) throws Exception {
		try{
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertDiscussionManage(dto);
			//通过零件id给所区和厂区专业表插入默认专业 by heps
			dao.insertManageFactoryZhuanYeByStrId(param);
			//如果默认专业有对象的审查人员给审查建议表插入审查建议    by  xul
			this.insertProcessAssignmentBystrId(strId);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		}catch(Exception e){
			logger.error("insertDiscussionManage出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}
	/**
	 * 修改对象全部字段
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateDiscussionManage(DiscussionManageDTO dto) throws Exception {
			//记录日志
			DiscussionManageDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int ret = dao.updateDiscussionManageAll(old);
			if(ret ==0){
				throw new DaoException("数据失效，请重新更新");
			}
			return ret;

	}
	/**
	 * 修改对象部分字段
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateDiscussionManageSensitive(DiscussionManageDTO dto) throws Exception {
		try{
			//记录日志
			DiscussionManageDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateDiscussionManageSensitive(old);
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
	 * 按主键单条删除
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int deleteDiscussionManageById(String id) throws Exception {
		if(StringUtils.isEmpty(id)){
		   throw new Exception("删除失败！传入的参数主键为null");
		   }
		try{
			//记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteDiscussionManageById(id);
		}catch(Exception e){
			logger.error("deleteDiscussionManage出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteDiscussionManageByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteDiscussionManageById(id);
			result++;
		}
		return result;
	}
		
	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private DiscussionManageDTO findById(String id) throws Exception {
		try{
			DiscussionManageDTO dto = dao.findDiscussionManageById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("findDiscussionManage出错：", e);
	    		throw e;
	    }
	}
	
	/**
	 * 按照零件id查询讨论区信息
	 * @param id
	 * @return list
	 */
	public QueryRespBean<DiscussionInfoDTO> selectDiscussionData(QueryReqBean<DiscussionInfoDTO> queryReqBean,String strId)throws Exception{
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			Page<DiscussionInfoDTO> dataList =  dao.selectDiscussionData(strId);
			QueryRespBean<DiscussionInfoDTO> queryRespBean =new QueryRespBean<DiscussionInfoDTO>();
			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(Exception e){
			logger.error("getAddformDataDTOById出错：", e);
			throw new DaoException("",e);
		}
	}
	public QueryRespBean<DiscussionManageDTO> searchDeptUserByStrId(QueryReqBean<DiscussionManageDTO> queryReqBean,String id) {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			Page<DiscussionManageDTO> dataList =  dao.findDeptUserByStrId(id,null);
			QueryRespBean<DiscussionManageDTO> queryRespBean =new QueryRespBean<DiscussionManageDTO>();
			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(Exception e){
			logger.error("getAddformDataDTOById出错：", e);
			throw new DaoException("",e);
		}
	}
	public int findDiscussionBystrId(String id){
		int count = dao.findDiscussionBystrId(id);
		return count;
		
	}
	/**
	 * @author xul
	 * 讨论区自动编号
	 * */
	public String getInfoById(){
		String str = "TL";
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String dates = sf.format(new Date());
		//根据当天日期查询除最大的值
		Integer number = dao.getInfoById();
		//如果最大值存在则加1
		if(number!=0){
			number=number+1;
		}else{//否则给初始值00000
			number=001;
		}
		//格式化number
		String number2 = String.format("%03d", number);
		str+=dates+number2;
		return str;
	}
	/**
	*插入审查建议 by xul
	*@author WCL  2017/8/3 添加了一个默认审查状态
	*/
	public String insertProcessAssignmentBystrId(String strId){
		List<DiscussionManageDTO> dataList =  dao.findDeptUserByStrId(strId, null);
		List<String> userList= new ArrayList<String>();
		for (DiscussionManageDTO discussionManageDTO : dataList) {
			String dutyUserId= discussionManageDTO.getDutyUserIds();
			if (dutyUserId != null &&!"".equals(dutyUserId)) {
				userList.add(dutyUserId);
			}
		}
		if (userList.size()>0) {
			for (int i = 0; i < userList.size(); i++) {
				try {
					int count = service.serchAssingnmentBystrIdAndUserId(strId, userList.get(i));
					if (count==0) {
						ProcessAssignmentDTO assignmentDTO =new ProcessAssignmentDTO();
						assignmentDTO.setStrId(strId);
						assignmentDTO.setDutyUserId(userList.get(i));
						assignmentDTO.setTaskType("2");
						//默认审查专业
						assignmentDTO.setExamineStatu("1");
						assignmentDTO.setPublicationTime(new Date());
						service.insertProcessAssignment(assignmentDTO);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return strId;
	}
	/**
	 * 给工艺分工人员指派 插入数据
	 * @throws Exception 
	 * */
	public String insertProcessGongyiBystrId(String strId,List<SysUser> userlist) throws Exception{
		if (userlist.size()>0) {
			for (int i = 0; i < userlist.size(); i++) {
				ProcessRepresentativeDTO representativeDTO = new ProcessRepresentativeDTO();
				representativeDTO.setStrId(strId);
				representativeDTO.setDutyUserId(userlist.get(i).getId());
				representativeDTO.setTaskType("1");
				representativeDTO.setPublicationTime(new Date());
				representativeDTO.setStatus("N");
				representativeService.insertProcessRepresentative(representativeDTO);
			}
		}
		return strId;
	}
	
	/**
	 * 加载模型干涉表格数据通过当前选择节点
	 * @param String 零件id
	 * @return QueryRespBean<InterveneDTO>
	 */
	public QueryRespBean<InterveneDTO> getInterveneDataByStrId(QueryReqBean<InterveneDTO> queryReqBean, String strId,String instancenumber)throws Exception{
		Page<InterveneDTO> dataList = null;
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			String strType = dao.findstrType(strId);
			if("2".equals(strType)){
				dataList = dao.getInterveneDataByInstancenumber(instancenumber);
			}else{
				dataList = dao.getInterveneDataByStrId(strId,instancenumber);
			}
			QueryRespBean<InterveneDTO> queryRespBean =new QueryRespBean<InterveneDTO>();
			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(Exception e){
			logger.error("getInterveneDataByStrId出错：", e);
			throw new DaoException("",e);
		}
		
	}
	public String getStatusByStrId(String strId) throws Exception {
		String status = dao.getStatusByStrId(strId);
		return status;
	}
	
	/**
	 * 强制通过干涉检查结果
	 * @param String[] 
	 * @return ModelAndView
	 * author by heps
	 */
	public void updateInterveneResult(String[] ids)throws DaoException{
		try{
			for (String id : ids) {
				dao.updateInterveneResult(id);
			}
		}catch(DaoException e){
			logger.error("updateInterveneResult出错：", e);
			throw e;
		}
		
	}
}