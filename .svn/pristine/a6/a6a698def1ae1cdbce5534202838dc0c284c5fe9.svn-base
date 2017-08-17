package avicit.discussion_manage.factorycomplex.service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.mvel2.optimizers.impl.refl.nodes.ArrayLength;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import avicit.platform6.api.syspermissionresource.permissionanalysis.core.support.LoaderConstant;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.core.sfn.intercept.SelfDefinedQuery;
import avicit.platform6.core.mybatis.pagehelper.Page;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.discussion_manage.factorycomplex.dao.FactoryComplexDao;
import avicit.discussion_manage.factorycomplex.dto.ExcelFactoryComplexDTO;
import avicit.discussion_manage.factorycomplex.dto.FactoryComplexDTO;


import avicit.discussion_manage.factorycomplex.dto.FactoryCensorManageDTO;
import avicit.discussion_manage.structuremanage.service.StructureManageService;

import avicit.platform6.modules.system.syslog.service.SysLogUtil;

/**
 * @classname: FactoryComplexService
 * @description: 定义  出差信息表 实现类
 * @author:  AVICIT DEV
 */
@Service
public class FactoryComplexService  implements Serializable {

	private static final Logger logger =  LoggerFactory.getLogger(FactoryComplexService.class);
	
    private static final long serialVersionUID = 1L;
    
//	@Autowired
//	private SysLogUtil sysLogUtil;
	
	@Autowired
	private FactoryComplexDao dao;
	@Autowired
    private FactoryCensorManageService serviceSub;
	

	/**
	 * 按条件分页查询
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public QueryRespBean<FactoryComplexDTO> searchFactoryComplexByPage(QueryReqBean<FactoryComplexDTO> queryReqBean,String sfnConditions) throws Exception {
		try{
			PageHelper.startPage(queryReqBean.getPageParameter());
			FactoryComplexDTO searchParams = queryReqBean.getSearchParams();
            SelfDefinedQuery sdc=new SelfDefinedQuery(sfnConditions,"t1");
            Page<FactoryComplexDTO> dataList =  dao.searchFactoryComplexByPage(searchParams,sdc.parseSql());
			QueryRespBean<FactoryComplexDTO> queryRespBean =new QueryRespBean<FactoryComplexDTO>();

			queryRespBean.setResult(dataList);
			return queryRespBean;
		}catch(DaoException e){
			logger.error("searchFactoryComplexByPaging出错：", e);
			throw e;
		}
	}
	/**
	 * 按条件查询，不分页
	 * @param queryReqBean
	 * @return
	 * @throws Exception
	 */
	public List<FactoryComplexDTO> searchFactoryComplex(QueryReqBean<FactoryComplexDTO> queryReqBean) throws Exception {
	    try{
	    	FactoryComplexDTO searchParams = queryReqBean.getSearchParams();
	    	List<FactoryComplexDTO> dataList = dao.searchFactoryComplex(searchParams);
	    	return dataList;
	    }catch(Exception e){
	    	logger.error("searchFactoryComplex出错：", e);
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
	public FactoryComplexDTO queryFactoryComplexByPrimaryKey(String id) throws Exception {
		try{
			FactoryComplexDTO dto = dao.findFactoryComplexById(id);
			//记录日志
			SysLogUtil.log4Query(dto);
			return dto;
		}catch(Exception e){
	    	logger.error("searchFactoryComplex出错：", e);
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
	public String insertFactoryComplex(FactoryComplexDTO dto) throws Exception {
		try{
			if(dto.getTemplate()==null){
				dto.setTemplate("N");
				String id = ComUtil.getId();
				dto.setId(id);
			}
			
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertFactoryComplex(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return dto.getId();
		}catch(Exception e){
			logger.error("insertFactoryComplex出错：", e);
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
	public int updateFactoryComplex(FactoryComplexDTO dto) throws Exception {
			//记录日志
			FactoryComplexDTO old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateFactoryComplexAll(old);
			if(count ==0){
				throw new DaoException("数据失效，请重新更新");
			}
			return count;

	}
	/**
	 * 修改对象部分字段
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int updateFactoryComplexSensitive(FactoryComplexDTO dto) throws Exception {
			//记录日志
			FactoryComplexDTO  old =findById(dto.getId());
			SysLogUtil.log4Update(dto, old);
			PojoUtil.setSysProperties(dto, OpType.update);
			PojoUtil.copyProperties(old, dto,true);
			int count = dao.updateFactoryComplexSensitive(old);
			if(count ==0){
				throw new DaoException("数据失效，请重新更新");
			}
			return count;

	}
	
	/**
	 * 按主键单条删除
	 * @param id
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public int deleteFactoryComplexById(String id) throws Exception {
		if(StringUtils.isEmpty(id)) throw new Exception("删除失败！传入的参数主键为null");
		try{
			//记录日志
			FactoryComplexDTO obje = findById(id);
			SysLogUtil.log4Delete(obje);
			//删除子表
			for(FactoryCensorManageDTO sub :serviceSub.queryFactoryCensorManageByPid(obje.getId())){
				serviceSub.deleteFactoryCensorManage(sub);
			}
			//删除主表
			return dao.deleteFactoryComplexById(id);
		}catch(Exception e){
			logger.error("searchFactoryComplex出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}

	/**
	 * 批量删除数据
	 * @param ids id的数组
	 * @return
	 * @throws Exception
	 */
	public int deleteFactoryComplexByIds(String[] ids) throws Exception{
		int result =0;
		for(String id : ids ){
			deleteFactoryComplexById(id);
			result++;
		}
		return result;
	}
	/**
	 * 日志专用，内部方法，不再记录日志
	 */
	private FactoryComplexDTO findById(String id) throws Exception {
		try{
			FactoryComplexDTO dto = dao.findFactoryComplexById(id);
			return dto;
		}catch(DaoException e){
	    		logger.error("searchFactoryComplex出错：", e);
	    		throw e;
	    }
	}
	/**
	 * 按车间id判断车间是否存在
	 * @param id
	 * @return 存在返回车间主键factoryid
	 */
	public String findByDeptId(String id,String strId) {
		String factoryid = dao.findByDeptid(id,strId);
		return factoryid;
	}
	/**
	 * 专业分配
	 * @param data
	 * @throws Exception
	 */
	public void factoryDeptUtil(String data) throws Exception{
		FactoryComplexDTO dto = new FactoryComplexDTO();
		if(data.contains(",")){//如果传入的专业为多个
			String [] deptid=data.split(",");//按“,”分割
			for (int i = 0,length=deptid.length; i <length; i++) {
				dto.setWorkshopId(deptid[i]);
				dto.setTemplate("Y");
				List<FactoryComplexDTO> list = dao.searchFactoryComplex(dto);
				if(list.size()>0){//如果对应专业存在不添加
					return;
				}else{//添加
					this.insertFactoryComplexs(dto);
				}
			}
		}else{//单个
			dto.setWorkshopId(data);
			dto.setTemplate("Y");
			List<FactoryComplexDTO> list = dao.searchFactoryComplex(dto);
			if(list.size()>0){//如果对应专业存在不添加
				return;
			}else{//添加
				this.insertFactoryComplexs(dto);
			}
		}
	}
	/**
	 * 新增对象
	 * @param dto
	 * @param logBase
	 * @return
	 * @throws Exception
	 */
	public String insertFactoryComplexs(FactoryComplexDTO dto) throws Exception {
		try{
			String id = ComUtil.getId();
			dto.setId(id);
			PojoUtil.setSysProperties(dto, OpType.insert);
			dao.insertFactoryComplex(dto);
			//记录日志
			SysLogUtil.log4Insert(dto);
			return dto.getId();
		}catch(Exception e){
			logger.error("insertFactoryComplex出错：", e);
			throw new DaoException(e.getMessage(),e);
		}
	}
}

