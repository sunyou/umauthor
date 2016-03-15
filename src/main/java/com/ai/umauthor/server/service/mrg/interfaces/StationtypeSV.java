package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmStationtype;


/**
 * 岗位类型
 * @author fanlx
 */
public interface StationtypeSV {
	
	/**
	 * @Description: queryById
	 * @author fanlx
	 * @date 2016年1月29日 上午10:46:58
	 */
	public UmStationtype queryStationtypeById(Long stationtypeId) throws BusinessException;
	

    /**
     * @Description: 添加岗位类型
     * @author fanlx
     * @date 2016年2月1日 下午3:12:20
     */
    public void insertStationtype(UmStationtype stationtype) throws BusinessException;
    

    /**
     * @Description: 修改岗位类型
     * @author fanlx
     * @date 2016年2月1日 下午3:12:58
     */
    public void modifyStationtype(UmStationtype stationtype) throws BusinessException;
    
    /**
     * @Description: 删除岗位类型
     * @author fanlx
     * @date 2016年2月1日 下午3:14:16
     */
    public void deleteStationtype(String delIds) throws BusinessException;

    /**
     * @Description: 根据指定查询条件SqlCondition查询岗位类型列表记录总数
     * @author fanlx
     * @date 2016年1月28日 下午5:28:57
     */
    public int selectStationtypeCountBySqlCond(SqlCondition cond) throws BusinessException;
    
    /**
     * @Description: 根据指定查询条件SqlCondition查询岗位类型列表
     * @author fanlx
     * @date 2016年1月28日 下午5:45:37
     */
    public List<UmStationtype> selectStationtypeBySqlCond(SqlCondition cond) throws BusinessException;
}
