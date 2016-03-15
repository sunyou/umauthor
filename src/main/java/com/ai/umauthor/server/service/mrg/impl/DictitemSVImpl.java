package com.ai.umauthor.server.service.mrg.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.common.domain.AjaxBean;
import com.ai.common.dynasql.SqlCondition;
import com.ai.common.dynasql.SqlRepo;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.mapper.UmDictitemMapper;
import com.ai.umauthor.server.model.TableCols;
import com.ai.umauthor.server.model.UmDictitem;
import com.ai.umauthor.server.model.UmDictitemExample;
import com.ai.umauthor.server.model.UmDictitemKey;
import com.ai.umauthor.server.service.mrg.interfaces.DictitemSV;

/**
 * 字典
 * 
 * @author guohui
 * @date 2016年1月28日
 */
@Service
public class DictitemSVImpl implements DictitemSV {
	@Autowired
	private SqlRepo sqlRepo;
	@Autowired
	UmDictitemMapper dictitemMapper;

	@Override
	public void insertDictitem(UmDictitem dictitem) throws BusinessException {
		dictitemMapper.insert(dictitem);
	}

	@Override
	public void modifyDictitem(UmDictitem dictitem) throws BusinessException {
		dictitemMapper.updateByPrimaryKey(dictitem);
	}

	@Override
	public void deleteDictitem(String dictKey, String itemKey) throws BusinessException {
		UmDictitemKey dictitemKey = new UmDictitemKey();
		dictitemKey.setDictKey(dictKey);
		dictitemKey.setItemKey(itemKey);
		dictitemMapper.deleteByPrimaryKey(dictitemKey);
	}

	@Override
	public List<UmDictitem> queryDictitemByCondition(SqlCondition con) throws Exception {
		List<UmDictitem> list = null;
			StringBuilder sql = new StringBuilder();
			sql.append(" select * from um_dictitem  where 1=1 ");
			list = sqlRepo.fetchBySql(sql.toString(), null, con, UmDictitem.class);
		return list;
	}

	@Override
	public int countDictitemByConditionTotal(SqlCondition con) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) as value from um_dictitem  where 1=1 ");
		List<Map<String, Object>> c = sqlRepo.fetchBySql(sql.toString(), null, con);
		if (!c.isEmpty()) {
			return Integer.parseInt(c.get(0).get("VALUE").toString());
		}
		return 0;
	}

	@Override
	public UmDictitem getDictitemByPrimaryKey(String dictKey, String itemKey) throws Exception {
		UmDictitemKey dictitemKey = new UmDictitemKey();
		dictitemKey.setDictKey(dictKey);
		dictitemKey.setItemKey(itemKey);
		  UmDictitem bean=dictitemMapper.selectByPrimaryKey(dictitemKey);
		return bean;
	}

	@Override
	public void logicalDeleteByPrimaryKey(UmDictitem dictitem) throws Exception {
		dictitemMapper.updateByPrimaryKeySelective(dictitem);
	}


	@Override
	public List<UmDictitem> queryUmDictitemByDictKey(String dictKey) throws BusinessException, Exception {
		if(StringUtils.isBlank(dictKey)) {
			throw new BusinessException(AjaxBean.ERROR_CODE, "参数dictKey不能为空");
		}
		UmDictitemExample example = new UmDictitemExample();
		UmDictitemExample.Criteria criteria = example.createCriteria();
		criteria.andItemStateEqualTo("1");
		criteria.andDictKeyEqualTo(dictKey);
		
		return dictitemMapper.selectByExample(example);
	}

}
