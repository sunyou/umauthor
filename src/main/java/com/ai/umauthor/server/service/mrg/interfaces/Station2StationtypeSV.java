package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;

import com.ai.umauthor.server.model.UmStation2stationtype;

public interface Station2StationtypeSV {

	/**
	 * 保存岗位同岗位类型关联关系
	 * @param stationId
	 * @param typeId
	 * @param remark
	 * @throws Exception
	 */
	public void saveStation2TypeRela(Long stationId,Long typeId,String remark) throws Exception;
	
	/**
	 * 删除岗位同岗位类型关联关系
	 * @param stationId
	 * @throws Exception
	 */
	public void deleteRelaByStationId(Long stationId) throws Exception;
	
	/**
	 * 删除岗位同岗位类型关联关系
	 * @param stationId
	 * @param typeId
	 * @throws Exception
	 */
	public void deleteStation2TypeRela(Long stationId,Long typeId) throws Exception;
	/**
	 * 查询岗位同岗位类型关联关系列表
	 * @param stationId
	 * @return
	 * @throws Exception
	 */
	public List<UmStation2stationtype> queryRelaByStationId(Long stationId) throws Exception;
}
