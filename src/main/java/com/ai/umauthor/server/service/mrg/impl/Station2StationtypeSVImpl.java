package com.ai.umauthor.server.service.mrg.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.common.util.IdGenUtil;
import com.ai.umauthor.server.mapper.UmStation2stationtypeMapper;
import com.ai.umauthor.server.model.UmStation2stationtype;
import com.ai.umauthor.server.model.UmStation2stationtypeExample;
import com.ai.umauthor.server.model.UmStation2stationtypeExample.Criteria;
import com.ai.umauthor.server.service.mrg.interfaces.Station2StationtypeSV;

@Service
@Transactional
public class Station2StationtypeSVImpl implements Station2StationtypeSV {

    @Autowired
    private UmStation2stationtypeMapper station2typeMapper;
    
	@Override
	public void saveStation2TypeRela(Long stationId, Long typeId, String remark) throws Exception {
		UmStation2stationtype ssrela = new UmStation2stationtype();
		ssrela.setRelationId(IdGenUtil.timeBasedId());
		ssrela.setStationId(stationId);
		ssrela.setStationtypeId(Long.valueOf(typeId));
		station2typeMapper.insert(ssrela);
	}

	@Override
	public void deleteRelaByStationId(Long stationId) throws Exception {
		this.deleteStation2TypeRela(stationId, null);
	}

	@Override
	public void deleteStation2TypeRela(Long stationId, Long typeId) throws Exception {
		UmStation2stationtypeExample example = new UmStation2stationtypeExample();
		Criteria criteria = example.createCriteria();
		if(stationId != null && stationId > 0){
			criteria.andStationIdEqualTo(stationId);
		}
		if(typeId != null && typeId > 0){
			criteria.andStationtypeIdEqualTo(typeId);
		}
		station2typeMapper.deleteByExample(example);
	}
	
	@Override
	public List<UmStation2stationtype> queryRelaByStationId(Long stationId) throws Exception {
		UmStation2stationtypeExample example = new UmStation2stationtypeExample();
		example.createCriteria().andStationIdEqualTo(stationId);
		return station2typeMapper.selectByExample(example);
	}
}
