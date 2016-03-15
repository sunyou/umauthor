package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmOperateLog;

public interface OperateLogSV {

	/**
	 * @Description: 根据指定查询条件SqlCondition查询操作日志列表记录总数
	 * @author fanlx
	 * @date 2016年2月2日 下午2:54:33
	 */
	public int selectLogCountBySqlCond(SqlCondition cond) throws BusinessException;
    

    /**
     * @Description: 根据指定查询条件SqlCondition查询操作日志列表
     * @author fanlx
     * @date 2016年2月2日 下午2:54:15
     */
    public List<UmOperateLog> selectLogBySqlCond(SqlCondition cond) throws BusinessException;
    
    /**
     * @Description: 保存操作日志
     * @author fanlx
     * @date 2016年2月3日 下午4:13:46
     */
    public void saveOperateLog(String operatorCode, Long domainId, String operateType, String operateDesc) throws BusinessException;
}
