package com.ai.umauthor.server.service.mrg.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.umauthor.server.mapper.UmAreasMapper;
import com.ai.umauthor.server.model.UmAreas;
import com.ai.umauthor.server.model.UmAreasExample;
import com.ai.umauthor.server.service.mrg.interfaces.AreasSV;

@Service
public class AreasSVImpl implements AreasSV{
	
	@Autowired
	UmAreasMapper areasMapper;
	
	@Override
	public List<UmAreas> getUmAreasByParent(String parentid) {
		UmAreasExample areas = new UmAreasExample();
		areas.createCriteria().andParentidEqualTo(parentid);
		return areasMapper.selectByExample(areas);
	}

	@Override
	public List<UmAreas> getProvice() {
		UmAreasExample areas = new UmAreasExample();
		areas.createCriteria().andParentidIsNull();
		return areasMapper.selectByExample(areas);
	}

}
