package com.ai.umauthor.server.service.mrg.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.common.util.IdGenUtil;
import com.ai.umauthor.server.mapper.UmOperator2menuMapper;
import com.ai.umauthor.server.model.UmOperator2menu;
import com.ai.umauthor.server.model.UmOperator2menuExample;
import com.ai.umauthor.server.service.mrg.interfaces.Operator2MenuSV;

@Service
@Transactional
public class Operator2MenuSVImpl implements Operator2MenuSV {

	@Autowired
	private UmOperator2menuMapper omMapper;
	
	@Override
	public void saveOperMenuRela(Long operatorId, Long menuId, String remarks) throws Exception {
		UmOperator2menu rela = new UmOperator2menu();
		rela.setId(IdGenUtil.timeBasedId());
		rela.setMenuId(menuId);
		rela.setOperatorId(operatorId);
		rela.setRemarks(remarks);
		omMapper.insert(rela);
	}

	@Override
	public void delOperMenuRela(Long operatorId, Long menuId) throws Exception {
		UmOperator2menuExample example = new UmOperator2menuExample();
		com.ai.umauthor.server.model.UmOperator2menuExample.Criteria criteria = example.createCriteria();
		if(operatorId != null && operatorId > 0){
			criteria.andOperatorIdEqualTo(operatorId);
		}
		if(menuId != null && menuId > 0){
			criteria.andMenuIdEqualTo(menuId);
		}
		omMapper.deleteByExample(example);
	}

}
