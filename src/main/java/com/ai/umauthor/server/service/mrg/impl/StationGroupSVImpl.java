package com.ai.umauthor.server.service.mrg.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.dynasql.SqlRepo;
import com.ai.common.exception.BusinessException;
import com.ai.common.util.IdGenUtil;
import com.ai.umauthor.server.mapper.UmStationGroupMapper;
import com.ai.umauthor.server.model.UmDictitem;
import com.ai.umauthor.server.model.UmStationGroup;
import com.ai.umauthor.server.model.UmStationGroupExample;
import com.ai.umauthor.server.service.mrg.interfaces.StationGroupSV;
import com.ai.umauthor.server.service.mrg.model.UmStationGroupListBean;
/**
 * 岗位分组
 *
 * @author guohui
 * @date 2016年2月2日
 */
@Service
public class StationGroupSVImpl implements StationGroupSV {
	@Autowired
	private SqlRepo sqlRepo;
	@Autowired
	UmStationGroupMapper stationGroupMapper;
	
	
	@Override
	public void insertStationGroup(UmStationGroup stationGroup) throws BusinessException {
          stationGroup.setGroupId(IdGenUtil.timeBasedId());
          stationGroupMapper.insert(stationGroup);
	}

	@Override
	public void modifyStation(UmStationGroup stationGroup) throws BusinessException {
		     stationGroupMapper.updateByPrimaryKeySelective(stationGroup);
	}

	@Override
	public void deleteStationGroup(long groupId) throws BusinessException {
		stationGroupMapper.deleteByPrimaryKey(groupId);
	}

	@Override
	public List<UmStationGroupListBean> queryStationGroupByCondition(SqlCondition con) throws Exception {
		List<UmStationGroupListBean> list = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.operator_name  from um_station_group t1,um_operator t2  where t1.create_operator_id=t2.operator_id  ");
		list = sqlRepo.fetchBySql(sql.toString(), null, con, UmStationGroupListBean.class);
	return list;
	}

	@Override
	public int countStationGroupByConditionTotal(SqlCondition con) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) as value from um_station_group t1,um_operator t2  where t1.create_operator_id=t2.operator_id");
		List<Map<String, Object>> c = sqlRepo.fetchBySql(sql.toString(), null, con);
		if (!c.isEmpty()) {
			return Integer.parseInt(c.get(0).get("VALUE").toString());
		}
		return 0;
	}

	@Override
	public UmStationGroup getStationGroupByPrimaryKey(long groupId) throws Exception {
		UmStationGroup bean=stationGroupMapper.selectByPrimaryKey(groupId);
		return bean;
	}

	@Override
	public void logicalDeleteByPrimaryKey(UmStationGroup stationGroup) throws Exception {
		   stationGroupMapper.updateByPrimaryKeySelective(stationGroup);
	}

	@Override
	public List<UmStationGroup> queryAllStationGroup() throws Exception {
		return stationGroupMapper.selectByExample(new  UmStationGroupExample());
	}

}
