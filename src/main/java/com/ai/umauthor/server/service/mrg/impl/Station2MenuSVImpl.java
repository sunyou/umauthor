package com.ai.umauthor.server.service.mrg.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.common.util.IdGenUtil;
import com.ai.umauthor.server.mapper.UmStation2menuMapper;
import com.ai.umauthor.server.model.UmStation2menu;
import com.ai.umauthor.server.model.UmStation2menuExample;
import com.ai.umauthor.server.model.UmStation2menuExample.Criteria;
import com.ai.umauthor.server.service.mrg.interfaces.Station2MenuSV;

@Service
@Transactional
public class Station2MenuSVImpl implements Station2MenuSV {

    @Autowired
    private UmStation2menuMapper station2MenuMapper;
    
	@Override
	public void saveStation2MenuRela(Long stationId, Long menuId, String remark) throws Exception {
		UmStation2menu smrela = new UmStation2menu();
		smrela.setId(IdGenUtil.timeBasedId());
		smrela.setStationId(stationId);
		smrela.setMenuId(Long.valueOf(menuId));
		station2MenuMapper.insert(smrela);
	}

	@Override
	public void deleteRelaByStationId(Long stationId) throws Exception {
		this.deleteStation2MenuRela(stationId, null);
	}

	@Override
	public void deleteStation2MenuRela(Long stationId, Long menuId) throws Exception {
		UmStation2menuExample example = new UmStation2menuExample();
		Criteria criteria = example.createCriteria();
		if(stationId != null && stationId > 0){
			criteria.andStationIdEqualTo(stationId);
		}
		if(menuId != null && menuId > 0){
			criteria.andMenuIdEqualTo(menuId);
		}
		station2MenuMapper.deleteByExample(example);
	}
	
	@Override
	public List<UmStation2menu> queryStation2MenuListByStationId(Long stationId) throws Exception {
		UmStation2menuExample example = new UmStation2menuExample();
		example.createCriteria().andStationIdEqualTo(stationId);
		return station2MenuMapper.selectByExample(example);
	}

}
