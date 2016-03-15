package com.ai.umauthor.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.service.mrg.model.QryUmStation;


public interface StationInfoDao {
	
	//@Select("SELECT T2.* FROM UM_OPERATOR2STATION T1, UM_STATION T2 WHERE T1.STATION_ID = T2.STATION_ID AND T1.OPERATOR_ID=#{operatorId}")
	//List<UmStation> queryStationsByOperator(@Param("operatorId") Long operatorId);
	
	
	@Select("SELECT T2.* FROM UM_OPERATOR2STATION T1, UM_STATION T2 WHERE T1.STATION_ID = T2.STATION_ID  AND T2.DOMAIN_ID=#{domainId} AND T1.OPERATOR_ID=#{operatorId}")
	List<UmStation> queryStationsByOperator(@Param("operatorId") Long operatorId,@Param("domainId") Long domainId);
	
	@Select("select count(1) from UM_STATION t where t.org_id like #{orgId}")
	public int queryStationCountByOrgId(@Param("orgId") String orgId) throws Exception;
	
	@Select("select * from (select t.*,st.stationtype_name group_name, rownum rn from UM_STATION t,UM_STATIONTYPE st where t.group_id = st.stationtype_id(+) and t.org_id like #{orgId} and rownum < #{endNum} order by t.create_date desc ) tt where tt.rn > #{startNum}")
	public List<QryUmStation> queryStationsByOrgId(@Param("orgId") String orgId,@Param("startNum") Integer startNum,@Param("endNum") Integer endNum) throws Exception;
	
}
