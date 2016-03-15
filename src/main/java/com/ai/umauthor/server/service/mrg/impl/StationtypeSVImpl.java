package com.ai.umauthor.server.service.mrg.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.dynasql.SqlRepo;
import com.ai.common.exception.BusinessException;
import com.ai.common.util.IdGenUtil;
import com.ai.umauthor.server.mapper.UmStationtypeMapper;
import com.ai.umauthor.server.model.UmStationtype;
import com.ai.umauthor.server.service.mrg.interfaces.StationtypeSV;


/**
 * 岗位类型
 * @author fanlx
 */
@Service
public class StationtypeSVImpl implements StationtypeSV {

    @Autowired
    UmStationtypeMapper stationtypeMapper;
    
    @Autowired
    SqlRepo sqlRepo;
    
    /**
     * @Description: 添加岗位类型
     * @author fanlx
     * @date 2016年2月1日 下午3:12:20
     */
    @Override
    public void insertStationtype(UmStationtype stationtype) throws BusinessException {
		if(stationtype == null){
		    throw new NullPointerException();
		}
		
		if(StringUtils.isEmpty(stationtype.getDomainId())){
		    throw new BusinessException("-1","域ID不能为空");
		}
		
		if(StringUtils.isEmpty(stationtype.getStationtypeCode())){
		    throw new BusinessException("-2","岗位类型编码编码不能为空");
		}
		
		if(StringUtils.isEmpty(stationtype.getStationtypeName())){
		    throw new BusinessException("-3","岗位类型名称名称不能为空");
		}
	
		//主键
		stationtype.setStationtypeId(IdGenUtil.timeBasedId());
		stationtypeMapper.insert(stationtype);
	
    }

    /**
     * @Description: 修改岗位类型
     * @author fanlx
     * @date 2016年2月1日 下午3:12:58
     */
    @Override
    public void modifyStationtype(UmStationtype stationtype) throws BusinessException {
    	if(stationtype.getStationtypeId()==null||stationtype.getStationtypeId()<=0){
    		throw new BusinessException("-1","权限索引为空");
    	}

		stationtypeMapper.updateByPrimaryKeySelective(stationtype);
		
    }

    /**
     * @Description: 删除岗位类型
     * @author fanlx
     * @date 2016年2月1日 下午3:14:16
     */
    @Override
    public void deleteStationtype(String delIds) throws BusinessException {
    	String[] ids = delIds.split(",");
    	for(String id : ids){
    		UmStationtype tmp=stationtypeMapper.selectByPrimaryKey(Long.parseLong(id));
    		tmp.setStationtypeState((short) 0);
    		stationtypeMapper.updateByPrimaryKey(tmp);
    	}

    }
       
    
    /* (non-Javadoc)
     * @see com.ai.umauthor.server.service.mrg.interfaces.PermissionSV#selectPermissionCountBySqlCond(com.ai.common.dynasql.SqlCondition)
     */
    @Override
    public int selectStationtypeCountBySqlCond(SqlCondition cond) throws BusinessException{
		try {
			List<Map<String, Object>> c = sqlRepo.fetchBySql("select count(1) as value from um_stationtype where 1=1",
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
	 * @see com.ai.umauthor.server.service.mrg.interfaces.PermissionSV#selectPermissionBySqlCond(com.ai.common.dynasql.SqlCondition)
	 */
	@Override
	public List<UmStationtype> selectStationtypeBySqlCond(SqlCondition cond) throws BusinessException {
		try {
		    List<UmStationtype> list = sqlRepo.fetchBySql("select * from um_stationtype where 1=1",
		    		 null, cond, UmStationtype.class);
		    return list;
		     
		} catch (Exception e) {
		    throw new BusinessException("-99",e.getMessage());
		}
	}

	/**
	 * @Description: queryById
	 * @author fanlx
	 * @date 2016年1月29日 上午10:46:58
	 */
	@Override
	public UmStationtype queryStationtypeById(Long stationtypeId) throws BusinessException {
		return stationtypeMapper.selectByPrimaryKey(stationtypeId);
	}
}
