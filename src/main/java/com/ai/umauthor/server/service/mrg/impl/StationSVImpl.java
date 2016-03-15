package com.ai.umauthor.server.service.mrg.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.dynasql.SqlRepo;
import com.ai.common.exception.BusinessException;
import com.ai.common.util.IdGenUtil;
import com.ai.common.util.WebUtils;
import com.ai.umauthor.server.dao.StationInfoDao;
import com.ai.umauthor.server.mapper.UmStation2menuMapper;
import com.ai.umauthor.server.mapper.UmStation2stationtypeMapper;
import com.ai.umauthor.server.mapper.UmStationMapper;
import com.ai.umauthor.server.mapper.manual.QueryUmStationMapper;
import com.ai.umauthor.server.model.UmDictitem;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.model.UmStation2menu;
import com.ai.umauthor.server.model.UmStation2menuExample;
import com.ai.umauthor.server.model.UmStation2stationtype;
import com.ai.umauthor.server.model.UmStationExample;
import com.ai.umauthor.server.service.mrg.interfaces.Operator2StationSV;
import com.ai.umauthor.server.service.mrg.interfaces.Station2MenuSV;
import com.ai.umauthor.server.service.mrg.interfaces.Station2StationtypeSV;
import com.ai.umauthor.server.service.mrg.interfaces.StationSV;
import com.ai.umauthor.server.service.mrg.model.MenuStationRelate;
import com.ai.umauthor.server.service.mrg.model.QryUmStation;
import com.ai.umauthor.server.service.mrg.model.SessionUser;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author Typhon Chens
 * 2016年1月19日
 */
@Service
public class StationSVImpl implements StationSV {

    @Autowired
    UmStationMapper stationMapper;
    
    @Autowired
    StationInfoDao stationInfoDao;
    
    @Autowired
    private SqlRepo sqlRepo;
    
    @Autowired
    private Station2MenuSV station2MenuSV;
    
    @Autowired
    private Station2StationtypeSV station2StationtypeSV;
    
    @Autowired
    private Operator2StationSV operator2StationSV;
    @Autowired
    private UmStation2menuMapper station2menuMapper;
    @Autowired
    private UmStation2stationtypeMapper station2stationTypeMapper;
    @Autowired
    private QueryUmStationMapper queryUmStationMapper;
    /**
     * 添加角色
     * @param station
     * @throws Exception 
     * @throws NumberFormatException 
     */
    public void insertStation(UmStation stationBean,String relaMenuIds,String relaTypeIds) throws NumberFormatException,Exception {
		if(stationBean==null){
		    throw new NullPointerException();
		}
		if(StringUtils.isEmpty(stationBean.getStationName())){
		    throw new BusinessException("-2","岗位名不能为空");
		}
		UmStationExample example=new UmStationExample();
		example.createCriteria().andStationNameEqualTo(stationBean.getStationName());
		int c=stationMapper.countByExample(example);
		if(c>0){
		    throw new BusinessException("-3","岗位名重复");
		}
		SessionUser<UmOperator,UmStation,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		UmOperator umOperator=sessionUserInfo.getLoginUser();
		if(umOperator == null){
			throw new BusinessException("-4", "会话超时，请重新登录");
		}
		stationBean.setDomainId(umOperator.getDomainId()+"");//从当前登录用户中获取
		stationBean.setCreateDate(new Date());
		stationBean.setCreateOperatorId(umOperator.getOperatorId());
		stationBean.setOrgId(umOperator.getOrgId());//创建该岗位用户所在组织
		stationBean.setStationState((short) 1);
		stationBean.setStationId(IdGenUtil.timeBasedId());
		stationMapper.insert(stationBean);
		//建立岗位同菜单关联关系
		if(!StringUtils.isEmpty(relaMenuIds)){
			String[] menuIds = relaMenuIds.split(",");
			for (String menuId : menuIds) {
				station2MenuSV.saveStation2MenuRela(stationBean.getStationId(), Long.valueOf(menuId), "");
			}
		}
		//建立岗位同岗位类型关联关系
		if(!StringUtils.isEmpty(relaTypeIds)){
			String[] typeIds = relaTypeIds.split(",");
			for (String typeId : typeIds) {
				station2StationtypeSV.saveStation2TypeRela(stationBean.getStationId(), Long.valueOf(typeId), "");
			}
		}
		//将新增岗位授权给创建用户
		operator2StationSV.saveOperator2Station(umOperator.getOperatorId(), umOperator.getOrgId(),
				stationBean.getStationId(), umOperator.getOperatorId(), new Date(), (short)1);
    }

    /**
     * 修改角色
     * @param station
     * @throws Exception 
     * @throws NumberFormatException 
     */
    public void modifyStation(UmStation stationBean,String relaMenuIds,String relaTypeIds,String oldRelaMenuIds,String oldRelaTypeIds) throws NumberFormatException, Exception {
		if(stationBean.getStationId() ==null||stationBean.getStationId()<=0){
		    throw new BusinessException("-1","角色索引为空");
		}
		/*UmOperatorExample example=new UmOperatorExample();
		example.createCriteria().andOperatorIdEqualTo(operatorBean.getOperatorId());*/
		UmStation tmp=stationMapper.selectByPrimaryKey(stationBean.getStationId());
//		BeanUtils.copyProperties(stationBean,tmp);
		SessionUser<UmOperator,UmStation,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		UmOperator umOperator=sessionUserInfo.getLoginUser();
		if(umOperator == null){
			throw new BusinessException("-4", "会话超时，请重新登录");
		}
		tmp.setUpdateDate(new Date());
		tmp.setUpdateOperatorId(umOperator.getOperatorId());
		tmp.setStationState(stationBean.getStationState());
		tmp.setGroupId(stationBean.getGroupId());
		tmp.setStationDesc(stationBean.getStationDesc());
		stationMapper.updateByPrimaryKey(tmp);
		//处理岗位菜单关联关系
		if(!StringUtils.isEmpty(relaMenuIds)){
			List<String> newMenuIds = Arrays.asList(relaMenuIds.split(","));
			List<String> oldMenuIds = Arrays.asList(oldRelaMenuIds.split(","));
			//表示新增关联菜单
			List<String> diffSet1 = new ArrayList<String>(newMenuIds);
			diffSet1.removeAll(oldMenuIds);
			if(!diffSet1.isEmpty()){
				for (String menuId : diffSet1) {
					station2MenuSV.saveStation2MenuRela(stationBean.getStationId(), Long.valueOf(menuId), "");
				}
			}
			//表示移出关联菜单
			List<String> diffSet2 = new ArrayList<String>(oldMenuIds);
			diffSet2.removeAll(newMenuIds);
			if(!diffSet2.isEmpty()){
				for (String menuId : diffSet2) {
					station2MenuSV.deleteStation2MenuRela(stationBean.getStationId(), Long.valueOf(menuId));
				}
			}
		}
		//处理岗位同岗位类型关联关系
		if(!StringUtils.isEmpty(relaTypeIds)){
			List<String> newTypeIds = Arrays.asList(relaTypeIds.split(","));
			List<String> oldTypeIds = Arrays.asList(oldRelaTypeIds.split(","));
			//表示新增关联岗位类型
			List<String> diffSet1 = new ArrayList<String>(newTypeIds);
			diffSet1.removeAll(oldTypeIds);
			if(!diffSet1.isEmpty()){
				for (String typeId : diffSet1) {
					station2StationtypeSV.saveStation2TypeRela(stationBean.getStationId(), Long.valueOf(typeId), "");
				}
			}
			//表示移出关联岗位类型
			List<String> diffSet2 = new ArrayList<String>(oldTypeIds);
			diffSet2.removeAll(newTypeIds);
			if(!diffSet2.isEmpty()){
				for (String typeId : diffSet2) {
					station2StationtypeSV.deleteStation2TypeRela(stationBean.getStationId(), Long.valueOf(typeId));
				}
			}
		}
    }

    /**
     * 根据角色ID删除角色
     * @param stationId
     * @throws Exception 
     */
    public void deleteStation(Long stationId) throws Exception {
//		UmStation tmp=stationMapper.selectByPrimaryKey(stationId);
//		tmp.setStationState((short) 0);
//		stationMapper.updateByPrimaryKey(tmp);
    	stationMapper.deleteByPrimaryKey(stationId);
    	//删除岗位同菜单关联关系
    	station2MenuSV.deleteRelaByStationId(stationId);
    	//删除岗位同岗位类型关联关系
    	station2StationtypeSV.deleteRelaByStationId(stationId);
    	//删除员工同岗位关联关系
    	operator2StationSV.deleteRelaByStationId(stationId);
    }
    
    /**
     * 根据操作员查询所属的角色
     * @param operatorId 操作员ID
     * @return
     */
    public List<UmStation> queryStationsByOperator(Long operatorId,Long domainId){
		List<UmStation> stationList =stationInfoDao.queryStationsByOperator(operatorId,domainId);
		return stationList;
    }

    @Override
    public int queryStationCountByOrgId(String orgId) throws Exception {
    	return stationInfoDao.queryStationCountByOrgId(orgId+"%");
    }
    
    /**
     * 根据ORG_ID查询用户所在组织及下级组织拥有的岗位
     */
	@Override
	public List<QryUmStation> queryStationsByOrgId(String orgId,String stationName,String groupName,int pageNo,int pageSize) throws Exception {
//		List<QryUmStation> stationList =stationInfoDao.queryStationsByOrgId(orgId+"%",startNum,endNum);
		SqlCondition cond = new SqlCondition();
		if(!StringUtils.isEmpty(orgId)){
			cond.like("org_id", orgId);
		}
		if(!StringUtils.isEmpty(stationName)){
			cond.like("station_name", stationName);
		}
		if(!StringUtils.isEmpty(groupName)){
			cond.like("group_name", groupName);
		}
		cond.page(pageNo, pageSize);
		List<QryUmStation> stationList = sqlRepo.fetchByConfig("sqlStation.query", null, cond, QryUmStation.class);
		return stationList;
	}

	@Override
	public UmStation queryStationById(Long stationId) throws Exception {
		return stationMapper.selectByPrimaryKey(stationId);
	}

	@Override
	public List<MenuStationRelate> queryMenuStationsByOrgId(String orgId,String menuId, SqlCondition cond) throws Exception {
		List<MenuStationRelate> list = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*, t1.id as relate_id,t3.group_name from UM_STATION t left join um_station2menu t1 on t.station_id = t1.station_id and menu_id = '"+menuId+"' left join UM_STATION_GROUP t3 on t.group_id=t3.group_id ");
		list = sqlRepo.fetchBySql(sql.toString(), null, cond, MenuStationRelate.class);
	  return list;
	}

	@Override
	public int queryMenuStationCountByOrgId(String orgId,String menuId,SqlCondition cond) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) as value from UM_STATION t left join um_station2menu t1 on t.station_id = t1.station_id and menu_id = '"+menuId+"' left join UM_STATION_GROUP t3 on t.group_id=t3.group_id ");
		List<Map<String, Object>> c = sqlRepo.fetchBySql(sql.toString(), null, cond);
		if (!c.isEmpty()) {
			return Integer.parseInt(c.get(0).get("VALUE").toString());
		}
		return 0;
	}

	@Override
	public void saveMenuStation(String jsonMessage, String menuId) throws Exception {
	//step1 删除该菜单原有岗位关联
		List<MenuStationRelate> allMenuStationList=queryMenuStationsByOrgId(null,menuId,new SqlCondition());
		if (allMenuStationList!=null) {
			for(MenuStationRelate menuStation: allMenuStationList){
				if (!StringUtils.isEmpty(menuStation.getRelateId())) {
					station2menuMapper.deleteByPrimaryKey(menuStation.getRelateId());
			  }
			}
		}
		//step2 重新创建菜单岗位关系
		JSONArray jsonArray = JSONArray.parseArray(jsonMessage);
		JSONObject jsobj;
		UmStation2menu newBean;
			for (int i = 0; i < jsonArray.size(); i++) {
				jsobj = jsonArray.getJSONObject(i);
				newBean=new UmStation2menu();
				newBean.setId(IdGenUtil.timeBasedId());
				newBean.setStationId(Long.parseLong( jsobj.getString("stationId") ));
				newBean.setMenuId(Long.parseLong(menuId));
				station2menuMapper.insert(newBean);
			}
	}

	@Override
	public List<MenuStationRelate> queryStationTypeStationsByOrgId(String orgId, String stationTypeId, SqlCondition cond)
			throws Exception {
		List<MenuStationRelate> list = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*, t1.relation_id as relate_id,t3.group_name from UM_STATION t left join um_station2stationtype t1 on t.station_id = t1.station_id and t1.STATIONTYPE_ID='"+stationTypeId+"' left join UM_STATION_GROUP t3 on t.group_id=t3.group_id");
		list = sqlRepo.fetchBySql(sql.toString(), null, cond, MenuStationRelate.class);
	  return list;
	}

	@Override
	public int queryStationTypeStationCountByOrgId(String orgId, String stationTypeId, SqlCondition cond)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) as value  from UM_STATION t left join um_station2stationtype t1 on t.station_id = t1.station_id and t1.STATIONTYPE_ID='"+stationTypeId+"' left join UM_STATION_GROUP t3 on t.group_id=t3.group_id");
		List<Map<String, Object>> c = sqlRepo.fetchBySql(sql.toString(), null, cond);
		if (!c.isEmpty()) {
			return Integer.parseInt(c.get(0).get("VALUE").toString());
		}
		return 0;
	}

	@Override
	public void saveStationTypeStation(String jsonMessage, String stationTyepId) throws Exception {
		//step1 删除岗位类型原有岗位关联
				List<MenuStationRelate> allStationTypeStationList=queryStationTypeStationsByOrgId(null,stationTyepId,new SqlCondition());
				if (allStationTypeStationList!=null) {
					for(MenuStationRelate menuStation: allStationTypeStationList){
						if (!StringUtils.isEmpty(menuStation.getRelateId())) {
							station2stationTypeMapper.deleteByPrimaryKey(menuStation.getRelateId());
					  }
					}
				}
				//step2 重新创建岗位类型岗位关系
				JSONArray jsonArray = JSONArray.parseArray(jsonMessage);
				JSONObject jsobj;
				UmStation2stationtype newBean;
					for (int i = 0; i < jsonArray.size(); i++) {
						jsobj = jsonArray.getJSONObject(i);
						newBean=new UmStation2stationtype();
						newBean.setRelationId(IdGenUtil.timeBasedId());
						newBean.setStationId(Long.parseLong( jsobj.getString("stationId") ));
						newBean.setStationtypeId(Long.parseLong(stationTyepId));
						station2stationTypeMapper.insert(newBean);
					}
	}

	@Override
	public List<UmStation> qryStationByOperatorCode(String operatorCode) throws Exception {
		QryUmStation where = new QryUmStation();
		where.setOperatorCode(operatorCode);
		return queryUmStationMapper.qryStationByOperator(where);
	}

	@Override
	public List<UmStation> qryStationByOperatorId(Long operatorId) throws Exception {
		QryUmStation where = new QryUmStation();
		where.setOperatorId(operatorId);
		return queryUmStationMapper.qryStationByOperator(where);
	}


}
