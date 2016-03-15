package com.ai.umauthor.server.service.mrg.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.umauthor.server.mapper.UmSystemMapper;
import com.ai.umauthor.server.model.UmSystem;
import com.ai.umauthor.server.model.UmSystemExample;
import com.ai.umauthor.server.service.mrg.interfaces.SystemSV;

@Service
@Transactional
public class SystemSVImpl implements SystemSV {

	@Autowired
	private UmSystemMapper systemMapper;
	
	@Override
	public List<UmSystem> querySystemList() throws Exception {
		UmSystemExample example = new UmSystemExample();
//		example.createCriteria()
		return systemMapper.selectByExample(example);
	}

}
