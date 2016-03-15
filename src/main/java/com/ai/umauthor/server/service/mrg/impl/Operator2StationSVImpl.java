package com.ai.umauthor.server.service.mrg.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.dynasql.SqlRepo;
import com.ai.common.exception.BusinessException;
import com.ai.common.util.IdGenUtil;
import com.ai.umauthor.server.mapper.UmOperator2stationMapper;
import com.ai.umauthor.server.model.UmOperator2station;
import com.ai.umauthor.server.model.UmOperator2stationExample;
import com.ai.umauthor.server.service.mrg.interfaces.Operator2StationSV;
import com.ai.umauthor.server.service.mrg.model.QryUmOperator2Station;

@Service
@Transactional
public class Operator2StationSVImpl implements Operator2StationSV {

	@Autowired
	private UmOperator2stationMapper operator2stationMapper;
    @Autowired
    private SqlRepo sqlRepo;
    
	@Override
	public void saveOperator2Station(Long operatorId, String orgId, Long stationId, Long authorOperatorId,
			Date authorDate, short allowReauthor) throws Exception {
		UmOperator2stationExample example = new UmOperator2stationExample();
		example.createCriteria().andOperatorIdEqualTo(operatorId).andStationIdEqualTo(stationId).andOrgIdEqualTo(orgId);
		int count = operator2stationMapper.countByExample(example);
		if(count > 0){
			throw new BusinessException("", "该人员已拥有该岗位权限，请勿重复授权");
		}
		UmOperator2station osrela = new UmOperator2station();
		osrela.setId(IdGenUtil.timeBasedId());
		osrela.setOperatorId(operatorId);
		osrela.setOrgId(orgId);
		osrela.setStationId(stationId);
		osrela.setAuthorOperatorId(authorOperatorId);
		osrela.setAuthorDate(authorDate);
		osrela.setAllowReauthor(allowReauthor);
		operator2stationMapper.insert(osrela);
	}

	@Override
	public void deleteRelaByStationId(Long stationId) throws Exception {
		UmOperator2stationExample example = new UmOperator2stationExample();
		example.createCriteria().andStationIdEqualTo(stationId);
		operator2stationMapper.deleteByExample(example);
	}

	@Override
	public void saveOperator2Station(String toOperatorId,String toOrgId,String stationIds,String oldStaionIds,
			Long authorOperatorId,short allowReauthor) throws Exception {
		UmOperator2station osrela = new UmOperator2station();
		osrela.setId(IdGenUtil.timeBasedId());
		osrela.setOperatorId(Long.valueOf(toOperatorId));
		osrela.setOrgId(toOrgId);
		osrela.setAuthorOperatorId(authorOperatorId);
		osrela.setAuthorDate(new Date());
		osrela.setAllowReauthor(allowReauthor);
		if(StringUtils.isEmpty(stationIds)){
			throw new BusinessException("", "");
		}
		List<String> stationList = Arrays.asList(stationIds.split(","));
		List<String> oldStationList = null;
		if(StringUtils.isEmpty(oldStaionIds)){
			oldStationList = new ArrayList<String>();
		}else{
			oldStationList = Arrays.asList(oldStaionIds.split(","));
		}
		//新增岗位集合
		List<String> diffSet1 = new ArrayList<String>(stationList);
		diffSet1.removeAll(oldStationList);
		UmOperator2station temp = null;
		for (String stationId : diffSet1) {
			temp = new UmOperator2station();
			BeanUtils.copyProperties(osrela, temp);
			temp.setStationId(Long.valueOf(stationId));
			operator2stationMapper.insert(temp);
		}
		//删除岗位集合
		List<String> diffSet2 = new ArrayList<String>(oldStationList);
		diffSet2.removeAll(stationList);
		for (String stationId : diffSet2) {
			UmOperator2stationExample example = new UmOperator2stationExample();
			example.createCriteria().andStationIdEqualTo(Long.valueOf(stationId)).andOperatorIdEqualTo(Long.valueOf(toOperatorId));
			operator2stationMapper.deleteByExample(example);
		}
		//交集存在更新的情况
		List<String> diffSet3 = new ArrayList<String>(stationList);
		diffSet3.retainAll(oldStationList);
		for (String stationId : diffSet3) {
			UmOperator2stationExample example = new UmOperator2stationExample();
			example.createCriteria().andStationIdEqualTo(Long.valueOf(stationId)).andOperatorIdEqualTo(Long.valueOf(toOperatorId));
			List<UmOperator2station> list = operator2stationMapper.selectByExample(example);
			if(list.isEmpty())continue;
			temp = list.get(0);
			temp.setAuthorOperatorId(authorOperatorId);
			temp.setAllowReauthor(allowReauthor);
			temp.setOrgId(toOrgId);
			operator2stationMapper.updateByPrimaryKeySelective(temp);
		}
	}

	@Override
	public int queryRelaStationByOperatorCount(Long operatorId,String stationName,String orgName) throws Exception {
		SqlCondition cond = new SqlCondition();
		cond.eq("operator_id", operatorId);
		if(!StringUtils.isEmpty(stationName)){
			cond.like("station_name", stationName);
		}
		if(!StringUtils.isEmpty(orgName)){
			cond.like("org_name", orgName);
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) as total from um_operator2station t1, um_station t2, um_org t3 where t1.station_id = t2.station_id(+) and t1.org_id = t3.org_id(+)");
		List<Map<String, Object>> c = sqlRepo.fetchBySql(sql.toString(), null, cond);
		if (!c.isEmpty()) {
			return Integer.parseInt(c.get(0).get("total").toString());
		}
		return 0;
	}
	
	@Override
	public List<QryUmOperator2Station> queryRelaStationByOperator(Long operatorId,String stationName,String orgName, int pageNo, int pageSize)
			throws Exception {
		SqlCondition cond = new SqlCondition();
		cond.eq("operator_id", operatorId);
		if(!StringUtils.isEmpty(stationName)){
			cond.like("station_name", stationName);
		}
		if(!StringUtils.isEmpty(orgName)){
			cond.like("org_name", orgName);
		}
		cond.page(pageNo, pageSize);
		List<QryUmOperator2Station> stationList = 
				sqlRepo.fetchByConfig("sqlOperators.queryRelaStation", null, cond, QryUmOperator2Station.class);
		return stationList;
	}

	@Override
	public void deleteRelaById(Long id) throws Exception {
		operator2stationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteRelaStation(Long operatorId, Long stationId,String orgId) throws Exception {
		UmOperator2stationExample example = new UmOperator2stationExample();
		example.createCriteria().andStationIdEqualTo(stationId).andOperatorIdEqualTo(operatorId).andOrgIdEqualTo(orgId);
		operator2stationMapper.deleteByExample(example);
	}

	@Override
	public QryUmOperator2Station queryRelaStationInfo(Long relaId) throws Exception {
		SqlCondition cond = new SqlCondition();
		cond.eq("id", relaId);
		List<QryUmOperator2Station> relaList = 
				sqlRepo.fetchByConfig("sqlOperators.queryRelaStation", null, cond, QryUmOperator2Station.class);
		if(relaList.isEmpty()){
			throw new BusinessException("", "记录不存在");
		}
		return relaList.get(0);
	}

	@Override
	public void updateRelaStation(Long relaId, String grantOrgId, short allowReauthor) throws Exception {
		UmOperator2station rela = operator2stationMapper.selectByPrimaryKey(relaId);
		if(rela == null){
			throw new BusinessException("", "更新信息不存在！");
		}
		rela.setOrgId(grantOrgId);
		rela.setAllowReauthor(allowReauthor);
		operator2stationMapper.updateByPrimaryKeySelective(rela);
	}
}
