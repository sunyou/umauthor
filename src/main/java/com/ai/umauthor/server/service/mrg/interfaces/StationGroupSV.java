package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmDictitem;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmStationGroup;
import com.ai.umauthor.server.service.mrg.model.UmStationGroupListBean;

/**
 * 岗位分组
 * 
 * @author guohui
 * @date 2016年2月2日
 */
public interface StationGroupSV {
	/**
	 * 添加岗位分组
	 * 
	 * @author guohui
	 * @date 2016年2月2日
	 * @param stationGroup
	 * @throws BusinessException
	 */
	public void insertStationGroup(UmStationGroup stationGroup) throws BusinessException;

	/**
	 * 修改岗位分组
	 * 
	 * @author guohui
	 * @date 2016年2月2日
	 * @param stationGroup
	 * @throws BusinessException
	 */
	public void modifyStation(UmStationGroup stationGroup) throws BusinessException;

	/**
	 * 根据主键删除岗位分组
	 * 
	 * @author guohui
	 * @date 2016年2月2日
	 * @param groupId
	 * @throws BusinessException
	 */
	public void deleteStationGroup(long groupId) throws BusinessException;

	/**
	 * 根据条件查询岗位分组列表
	 * 
	 * @author guohui
	 * @date 2016年2月2日
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public List<UmStationGroupListBean> queryStationGroupByCondition(SqlCondition con) throws Exception;

	/**
	 * 根据条件查询岗位分组数量
	 * 
	 * @author guohui
	 * @date 2016年2月2日
	 * @param con
	 * @return
	 * @throws Exception
	 */
	public int countStationGroupByConditionTotal(SqlCondition con) throws Exception;

	/**
	 * 根据主键获取岗位分组
	 * 
	 * @author guohui
	 * @date 2016年2月2日
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	public UmStationGroup getStationGroupByPrimaryKey(long groupId) throws Exception;

	/**
	 * 逻辑删除
	 * 
	 * @author guohui
	 * @date 2016年2月1日
	 * @param dictitem
	 * @throws Exception
	 */
	public void logicalDeleteByPrimaryKey(UmStationGroup stationGroup) throws Exception;
	
	/**
	 * 查询所有岗位分类信息
	 * @author guohui
	 *@date 2016年2月2日
	 * @return
	 * @throws Exception
	 */
	public List<UmStationGroup> queryAllStationGroup() throws Exception;
}
