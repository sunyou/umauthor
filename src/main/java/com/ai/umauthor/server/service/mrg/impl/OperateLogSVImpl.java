package com.ai.umauthor.server.service.mrg.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.dynasql.SqlRepo;
import com.ai.common.exception.BusinessException;
import com.ai.common.util.IdGenUtil;
import com.ai.umauthor.server.mapper.UmOperateLogMapper;
import com.ai.umauthor.server.model.UmOperateLog;
import com.ai.umauthor.server.service.mrg.interfaces.OperateLogSV;

/**
 * 操作日志
 * @author fanlx
 */
@Service
public class OperateLogSVImpl implements OperateLogSV {
	
    @Autowired
    SqlRepo sqlRepo;
    
    @Autowired
    UmOperateLogMapper logMapper;

	/* (non-Javadoc)
	 * @see com.ai.umauthor.server.service.mrg.interfaces.OperateLogSV#selectLogCountBySqlCond(com.ai.common.dynasql.SqlCondition)
	 */
	@Override
	public int selectLogCountBySqlCond(SqlCondition cond) throws BusinessException {
		try {
			List<Map<String, Object>> c = sqlRepo.fetchBySql("select count(1) as value from um_operate_log where 1=1",
					null, cond);
			if (!c.isEmpty()) {
				return new Integer(c.get(0).get("VALUE").toString());
			}
			return 0;
		} catch (Exception e) {
			throw new BusinessException("-99", e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see com.ai.umauthor.server.service.mrg.interfaces.OperateLogSV#selectLogBySqlCond(com.ai.common.dynasql.SqlCondition)
	 */
	@Override
	public List<UmOperateLog> selectLogBySqlCond(SqlCondition cond) throws BusinessException {
		try {
		    List<UmOperateLog> list = sqlRepo.fetchBySql("select * from um_operate_log where 1=1",
		    		 null, cond, UmOperateLog.class);
		    return list;
		     
		} catch (Exception e) {
		    throw new BusinessException("-99",e.getMessage());
		}
	}

	@Override
	public void saveOperateLog(String operatorCode, Long domainId, String operateType, String operateDesc) throws BusinessException {
		UmOperateLog log = new UmOperateLog();
		
		log.setLogId(IdGenUtil.timeBasedId());
		log.setDomainId(domainId);
		log.setOperatorCode(operatorCode);
		log.setOperatorType(operateType);
		log.setOperatorDesc(operateDesc);
		log.setOperatorDate(new Date());
		
		logMapper.insert(log);
	}

}
