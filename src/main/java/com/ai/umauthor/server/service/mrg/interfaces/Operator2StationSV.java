package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.Date;
import java.util.List;

import com.ai.umauthor.server.service.mrg.model.QryUmOperator2Station;

public interface Operator2StationSV {

	/**
	 * 保存操作人员同岗位间关联关系
	 * @param operatorId
	 * @param orgId
	 * @param stationId
	 * @param authorOperatorId
	 * @param authorDate
	 * @param allowReauthor
	 * @throws Exception
	 */
	public void saveOperator2Station(Long operatorId, String orgId, Long stationId, Long authorOperatorId,
			Date authorDate, short allowReauthor) throws Exception;
	
	/**
	 * 保存操作人员同岗位间关联关系
	 * @param toOperatorId
	 * @param toOrgId
	 * @param stationIds
	 * @param authorOperatorId
	 * @param allowReauthor
	 * @throws Exception
	 */
	public void saveOperator2Station(String toOperatorId,String toOrgId,String stationIds,String oldStaionIds,
			Long authorOperatorId,short allowReauthor) throws Exception;
	/**
	 * 删除同岗位关联关系
	 * @param stationId
	 * @throws Exception
	 */
	public void deleteRelaByStationId(Long stationId) throws Exception;
	
	/**
	 * 回收操作人员岗位权限
	 * @param operator
	 * @param stationId
	 * @throws Exception
	 */
	public void deleteRelaStation(Long operatorId,Long stationId,String orgId) throws Exception;
	
	/**
	 * 根据主键删除同岗位关联关系
	 * @param id
	 * @throws Exception
	 */
	public void deleteRelaById(Long id) throws Exception;
	/**
	 * @author 查询关联岗位列表记录数
	 * @param operatorId
	 * @return
	 * @throws Exception
	 */
	public int queryRelaStationByOperatorCount(Long operatorId,String stationName,String orgName) throws Exception;
	
    /**
     * @author 查询关联岗位列表
     * @param operatorId
     * @return
     * @throws Exception
     */
    public List<QryUmOperator2Station> queryRelaStationByOperator(Long operatorId,String stationName,String orgName,int pageNo,int pageSize) throws Exception;
    
    /**
     * 查询关联岗位详情
     * @param relaId
     * @return
     * @throws Exception
     */
    public QryUmOperator2Station queryRelaStationInfo(Long relaId) throws Exception;
    
    /**
     * 更新关联岗位信息
     * @param relaId
     * @param grantOrgId
     * @param allowReauthor
     * @throws Exception
     */
    public void updateRelaStation(Long relaId,String grantOrgId,short allowReauthor) throws Exception;
}
