package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;

import com.ai.umauthor.server.model.UmStation2menu;

public interface Station2MenuSV {

	/**
	 * 保存岗位同菜单关联关系
	 * @param stationId
	 * @param menuId
	 * @param remark
	 * @throws Exception
	 */
	public void saveStation2MenuRela(Long stationId,Long menuId,String remark) throws Exception;
	
	/**
	 * 删除岗位同菜单关联关系
	 * @param stationId
	 * @throws Exception
	 */
	public void deleteRelaByStationId(Long stationId) throws Exception;
	
	/**
	 * @param stationId
	 * @param menuId
	 * @throws Exception
	 */
	public void deleteStation2MenuRela(Long stationId,Long menuId) throws Exception;
	
	/**
	 * 查询岗位同菜单关联关系列表
	 * @param stationId
	 * @return
	 * @throws Exception
	 */
	public List<UmStation2menu> queryStation2MenuListByStationId(Long stationId) throws Exception;
}
