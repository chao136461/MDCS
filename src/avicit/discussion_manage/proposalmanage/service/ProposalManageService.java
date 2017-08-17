package avicit.discussion_manage.proposalmanage.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.sfn.intercept.SelfDefinedQuery;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.discussion_manage.discussionmanage.dao.DiscussionManageDao;
import avicit.discussion_manage.discussionmanage.dto.DiscussionInfoDTO;
import avicit.discussion_manage.discussionmanage.dto.DiscussionManageDTO;
import avicit.discussion_manage.proposalmanage.dao.ProposalManageDao;
import avicit.discussion_manage.proposalmanage.dto.ProposalManageDTO;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname: ProposalManageService
 * @description: 定义 建议管理实现类
 * @author: AVICIT DEV
 */
@Service
public class ProposalManageService implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(ProposalManageService.class);

	private static final long serialVersionUID = 1L;

	// @Autowired
	// private SysLogUtil sysLogUtil;

	@Autowired
	private ProposalManageDao dao;

	@Autowired
	private DiscussionManageDao discussdao;

	/**
	 * 按条件分页查询
	 * 
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public QueryRespBean<ProposalManageDTO> searchProposalManageByPage(QueryReqBean<ProposalManageDTO> queryReqBean,
			String sfnConditions) throws Exception {
		try {
			PageHelper.startPage(queryReqBean.getPageParameter());
			ProposalManageDTO searchParams = queryReqBean.getSearchParams();
			SelfDefinedQuery sdc = new SelfDefinedQuery(sfnConditions, "t1");
			Page<DiscussionManageDTO> pro = null;
			if (searchParams != null) {
				String parms[]=null;
				String dt= searchParams.getSerdeptNames();
				if(dt!=null&&!"".equals(dt)){
					parms=dt.split(",");
				}
				// pro =dao.findByStrId(searchParams.getStrId());
				if("1".equals(searchParams.getComplex())){//所
					pro = discussdao.findMajorDeptUserByStrId(searchParams.getStrId(),parms);
				}else if("2".equals(searchParams.getComplex())){//厂
					pro = discussdao.findFactoryDeptUserByStrId(searchParams.getStrId(),parms);
				}else{//其它
					pro = discussdao.findDeptUserByStrId(searchParams.getStrId(),parms);
				}
				// 零件对应的专业以及人员
			}
			QueryRespBean<ProposalManageDTO> queryRespBean = new QueryRespBean<ProposalManageDTO>();
			Page<ProposalManageDTO> dataList = null;
			// 创建分页数据集合
			Page<ProposalManageDTO> data = new Page<ProposalManageDTO>();
			//List<ProposalManageDTO> data =new ArrayList<ProposalManageDTO>();
			int i = 0;
			dataList = dao.searchProposalManageByPage(searchParams, sdc.parseSql());
			ProposalManageDTO pd = null;

			if (dataList.size() > 0) {
				for (ProposalManageDTO da : dataList) {// 外层 建议列表
					String  flag = "0";
					String dept = "";
					for (int j = 0; j < pro.size(); j++) {// 内层循环
						boolean deflag= true;
						if (pro.get(j).getDutyUserIds() == null) {// 如果该零件下的这个专业没有审查人员
							pd = new ProposalManageDTO();
							pd.setProposalName("");
							pd.setDeptName(pro.get(j).getWorkshopId());
							pro.remove(pro.get(j));
							j--;
							data.add(i, pd);
							i++;
						} else {// 如果该零件下的这个专业没有有审查人员  -1为人员不在审查人员名单内
							//if (!da.getProposalUserId().equals("1")) {
								if (pro.get(j).getDutyUserIds().indexOf(da.getProposalUserId()) != -1) {
									flag = "1";
									pd = da;
									dept = pro.get(j).getWorkshopId();
								}
								else{
									for (int j2 = 0; j2 < dataList.size(); j2++) {//
										if (pro.get(j).getDutyUserIds().indexOf(dataList.get(j2).getProposalUserId()) != -1) {
											deflag=false;
											break;
										}
									}
									if(deflag){
										pd = new ProposalManageDTO();
										pd.setProposalName("");
										pd.setDeptName(pro.get(j).getWorkshopId());
										pro.remove(pro.get(j));
										j--;
										data.add(i, pd);
										i++;
									};
								}
							//}
						}
					}
					if (flag .equals("1")){
						pd = da;
						pd.setDeptName(dept);
						data.add(pd);
						i++;
					}else if(flag.equals("0")) {
						pd = da;
						pd.setDeptName("");
						data.add(i,pd);
						i++;
					}
				}
			}else{
				ProposalManageDTO pds =null;
				for (DiscussionManageDTO dis : pro) {
					 pds = new ProposalManageDTO();
					 pds.setDeptName(dis.getWorkshopId());
					 pds.setProposalName("");
					data.add(i,pds);
					i++;
				}
			}
			 Collections.sort(data, new Comparator<ProposalManageDTO>() {  
				  
		            public int compare(ProposalManageDTO o1, ProposalManageDTO o2) {  
		  
		                //
		                if (o1.getDeptName() != o2.getDeptName()) {  
		                    return 0;  
		                }  
		                if (o1.getDeptName() == o2.getDeptName()) {  
		                    return 1;  
		                }  
		                return -1;  
		            }  
		        });
			 
			queryRespBean.setResult(data);
			return queryRespBean;
		} catch (DaoException e) {
			logger.error("searchProposalManageByPaging出错：", e);
			throw e;
		}
	}

	public int getDeptbyStr(Map map) throws Exception {
		try {
			int count = dao.getDeptbyStr(map);
			return count;
		} catch (Exception e) {
			logger.error("searchgetDeptbyStr出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 按条件查询，不分页
	 * 
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public List<ProposalManageDTO> searchProposalManage(QueryReqBean<ProposalManageDTO> queryReqBean) throws Exception {
		try {
			ProposalManageDTO searchParams = queryReqBean.getSearchParams();
			List<ProposalManageDTO> dataList = dao.searchProposalManage(searchParams);
			return dataList;
		} catch (Exception e) {
			logger.error("searchProposalManage出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 通过主键查询单条记录
	 * 
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public ProposalManageDTO queryProposalManageByPrimaryKey(String id) throws Exception {
		try {
			ProposalManageDTO dto = dao.findProposalManageById(id);
			// 记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		} catch (Exception e) {
			logger.error("searchProposalManage出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 新增对象
	 * 
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public String insertProposalManage(ProposalManageDTO dto) throws Exception {
		try {
			String id = ComUtil.getId();
			if(dto.getId()==null||"".equals(dto.getId())){
				dto.setId(id);
			}
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertProposalManage(dto);
			// 记录日志
			SysLogUtil.log4Insert(dto);
			return id;
		} catch (Exception e) {
			logger.error("insertProposalManage出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 修改对象全部字段
	 * 
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateProposalManage(ProposalManageDTO dto) throws Exception {
		// 记录日志
		ProposalManageDTO old = findById(dto.getId());
		SysLogUtil.log4Update(dto, old);
		PojoUtil.setSysProperties(dto, OpType.update);
		PojoUtil.copyProperties(old, dto, true);
		int ret = dao.updateProposalManageAll(old);
		if (ret == 0) {
			throw new DaoException("数据失效，请重新更新");
		}
		return ret;

	}

	/**
	 * 修改对象部分字段
	 * 
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateProposalManageSensitive(ProposalManageDTO dto) throws Exception {
		try {
			// 记录日志
			ProposalManageDTO old = findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto, true);
			int count = dao.updateProposalManageSensitive(old);
			if (count == 0) {
				throw new DaoException("数据失效，请重新更新");
			}
			return count;
		} catch (Exception e) {
			logger.error("searchDemoBusinessTrip出错：", e);
			throw new DaoException(e.getMessage(), e);
		}

	}

	/**
	 * 按主键单条删除
	 * 
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int deleteProposalManageById(String id) throws Exception {
		if (StringUtils.isEmpty(id)) {
			throw new Exception("删除失败！传入的参数主键为null");
		}
		try {
			// 记录日志
			SysLogUtil.log4Delete(findById(id));
			return dao.deleteProposalManageById(id);
		} catch (Exception e) {
			logger.error("deleteProposalManage出错：", e);
			throw new DaoException(e.getMessage(), e);
		}
	}

	/**
	 * 批量删除数据
	 * 
	 * @param ids
	 *            id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteProposalManageByIds(String[] ids) throws Exception {
		int result = 0;
		for (String id : ids) {
			deleteProposalManageById(id);
			result++;
		}
		return result;
	}

	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private ProposalManageDTO findById(String id) throws Exception {
		try {
			ProposalManageDTO dto = dao.findProposalManageById(id);
			return dto;
		} catch (DaoException e) {
			logger.error("findProposalManage出错：", e);
			throw e;
		}
	}

	public String getInfoById() {
		String str = "JY";
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		String dates = sf.format(new Date());
		// 根据当天日期查询除最大的值
		Integer number = dao.getInfoById();
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
	/**
	 * 获取建议回复数据集合
	 * 
	 * @param id 建议id
	 * @return dto回复数据集合
	 * @throws Exception
	 */
	public List<ProposalManageDTO> queryProposalManageByParentId(String id) {
		try{
			List<ProposalManageDTO> dto = dao.queryProposalByParentId(id);
			return dto;
		} catch (DaoException e) {
			logger.error("queryProposalByParentId出错：", e);
			throw e;
		}
	}
	/**
	 * 
	 * 
	 * */
	public int getProposalByUserIdAndStrId(String userId,String strId){
		int count;
		try {
			count = dao.getProposalByUserIdAndStrId(userId, strId);
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int getProposalIsClose(String userId,String strId){
		int count;
		try {
			count = dao.getProposalIsClose(userId, strId);
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 根据零件id查询类型
	 * @param strId
	 * @author WCL
	 */
	public String queryStructInstanceByStrId(String strId) {
		
		String instance = dao.queryStructInstanceByStrId(strId);
		return instance;
	}
}
