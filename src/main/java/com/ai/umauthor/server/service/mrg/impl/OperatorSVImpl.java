package com.ai.umauthor.server.service.mrg.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.dynasql.SqlRepo;
import com.ai.common.exception.BusinessException;
import com.ai.common.security.ILoginUser;
import com.ai.common.security.PasswordEncoderSerivce;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.IdGenUtil;
import com.ai.umauthor.server.mapper.UmOperatorMapper;
import com.ai.umauthor.server.model.TableCols;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmOperatorExample;
import com.ai.umauthor.server.service.mrg.interfaces.LoginUserSV;
import com.ai.umauthor.server.service.mrg.interfaces.OperatorSV;
import com.ai.umauthor.server.service.mrg.model.LoginUser;
import com.ai.umauthor.server.service.mrg.model.QryUmOperator;

@Service("OperatorSV")
@Transactional
public class OperatorSVImpl implements OperatorSV, LoginUserSV {
    private static final Logger log = LoggerFactory
	    .getLogger(OperatorSVImpl.class);
    @Autowired
    UmOperatorMapper operatorMapper;

    @Autowired
    PasswordEncoderSerivce passwordEncoder;

    @Autowired
    SqlRepo sqlRepo;

    /**
     * 添加操作员 默认密码为123456
     * 
     * @throws Exception
     */
    public UmOperator insertOperator(UmOperator operatorBean)
	    throws BusinessException {
	if (operatorBean == null) {
	    throw new NullPointerException();
	}

	if (StringUtils.isEmpty(operatorBean.getOperatorCode())) {
	    throw new BusinessException("-1", "登录账号为空");
	}

	// TODO缺少默认密码配置
	if(StringUtils.isEmpty(operatorBean.getOperatorPsw())) {
		operatorBean.setOperatorPsw(passwordEncoder.encrypt("123456"));
	} else {
		operatorBean.setOperatorPsw(passwordEncoder.encrypt(operatorBean.getOperatorPsw()));
	}
	UmOperatorExample example = new UmOperatorExample();
	example.createCriteria().andOperatorCodeEqualTo(
		operatorBean.getOperatorCode());
	int c = operatorMapper.countByExample(example);
	if (c > 0) {
	    throw new BusinessException("-2", "登录账号重复");
	}
	operatorBean.setOperatorState((short) 1);

	operatorBean.setCreateDate(new Date());
	operatorBean.setOperatorId(IdGenUtil.timeBasedId());
	operatorMapper.insert(operatorBean);
	return operatorBean;
    }

    /**
     * 修改操作员
     * 
     * @throws Exception
     */
    public void modifyOperator(UmOperator operatorBean)
	    throws BusinessException {

	if (operatorBean.getOperatorId() == null
		|| operatorBean.getOperatorId() <= 0) {
	    throw new BusinessException("-1", "用户索引为空");
	}
	/*
	 * UmOperatorExample example=new UmOperatorExample();
	 * example.createCriteria().andOperatorIdEqualTo(operatorBean.
	 * getOperatorId());
	 */
	/*
	 * UmOperator
	 * tmp=operatorMapper.selectByPrimaryKey(operatorBean.getOperatorId());
	 * BeanUtils.copyProperties(operatorBean,tmp);
	 */
	operatorMapper.updateByPrimaryKeySelective(operatorBean);

    }

    /**
     * 根据登陆工号查询员工实体。 域ID&组织可选传入
     * 
     * @param code
     *            登陆工号
     * @param domainId
     *            域ID
     * @param orgId
     *            组织ID
     * @return
     * @throws BusinessException
     */
    public UmOperator selectOperatorByCode(String code, String domainId,
	    String orgId) throws BusinessException {
	if (code == null) {
	    throw new BusinessException("-1", "登录名为空");
	}
	UmOperatorExample example = new UmOperatorExample();
	if (!StringUtils.isEmpty(code)) {
	    example.createCriteria().andOperatorCodeEqualTo(code);
	}
	if (!StringUtils.isEmpty(domainId)) {
	    example.createCriteria().andDomainIdEqualTo(new Long(domainId));
	}
	if (!StringUtils.isEmpty(orgId)) {
	    example.createCriteria().andOrgIdEqualTo(orgId);
	}

	List<UmOperator> umOperatorList = operatorMapper
		.selectByExample(example);

	if (umOperatorList.isEmpty()) {
	    return null;
	} else {
	    return umOperatorList.get(0);
	}

    }

    /**
     * 删除操作员
     * 
     * @throws Exception
     */
    public void deleteOperator(Long operatorId) throws Exception {
	// Auto-generated method stub
	UmOperator tmp = operatorMapper.selectByPrimaryKey(operatorId);
	tmp.setOperatorState((short) 0);
	operatorMapper.updateByPrimaryKey(tmp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ai.umauthor.server.service.mrg.interfaces.UmOperatorSV#lockOperator(
     * java.lang.Long)
     */
    @Override
    public void lockOperator(Long operatorId) throws Exception {
	UmOperator tmp = operatorMapper.selectByPrimaryKey(operatorId);
	tmp.setOperatorState((short) 2);
	operatorMapper.updateByPrimaryKey(tmp);

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ai.umauthor.server.service.mrg.interfaces.LoginUserSV#
     * queryUserInfoByLoginCode(java.lang.String)
     */
    @Override
    public ILoginUser queryUserInfoByLoginCode(String userCode) {
	LoginUser loginUser = new LoginUser();
	UmOperator operator = null;
	try {
	    operator = selectOperatorByCode(userCode, null, null);
	    if (operator != null) {
		/*
		 * loginUser.setOperatorName(operator.getOperatorName());
		 * loginUser.setOperatorCode(operator.getOperatorCode());
		 * loginUser.setOperatorPsw(operator.getOperatorPsw());
		 * loginUser.setOperatorState(operator.getOperatorState());
		 */
		BeanUtils.copyPropertiesToTargetSuper(operator, loginUser);
	    }
	} catch (Exception e) {
	    log.error("ERROR", e);
	}

	return loginUser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ai.umauthor.server.service.mrg.interfaces.OperatorSV#
     * selectOperatorBySqlCond(com.ai.common.dynasql.SqlCondition)
     */
    @Override
    public List<QryUmOperator> selectOperatorBySqlCond(SqlCondition cond)
	    throws BusinessException {
	try {
	    List<QryUmOperator> c = sqlRepo.fetchByConfig("sqlOperators.query",
		    null, cond, QryUmOperator.class);
	    return c;

	} catch (Exception e) {
	    log.error("ERROR", e);
	    throw new BusinessException("-99", e.getMessage());
	}
    }

    public List<QryUmOperator> selectOperatorByOrgAndSqlCond(String orgId,
	    SqlCondition cond) throws BusinessException {
	if (StringUtils.isEmpty(orgId)) {

	}
	TableCols.UmOperatorCols t = TableCols.UmOperatorCols;
	SqlCondition condT = new SqlCondition();
	condT.eq(t.orgId, orgId);
	condT.eq(t.operatorState, 1);
	condT.merge(cond);
	return selectOperatorBySqlCond(condT);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ai.umauthor.server.service.mrg.interfaces.OperatorSV#
     * selectOperatorCountBySqlCond(com.ai.common.dynasql.SqlCondition)
     */
    @Override
    public Integer selectOperatorCountBySqlCond(SqlCondition cond)
	    throws BusinessException {
	try {
	    List<Map<String, Object>> c = sqlRepo.fetchBySql(
		    "select count(1) as value from UM_OPERATOR where 1=1",
		    null, cond);
	    if (!c.isEmpty()) {
		return new Integer(c.get(0).get("VALUE").toString());

	    }
	    return 0;
	} catch (Exception e) {
	    log.error("ERROR", e);
	    throw new BusinessException("-99", e.getMessage());
	}

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ai.umauthor.server.service.mrg.interfaces.OperatorSV#
     * selectOperatorCountByOrgAndSqlCond(java.lang.String,
     * com.ai.common.dynasql.SqlCondition)
     */
    @Override
    public int selectOperatorCountByOrgAndSqlCond(String orgId,
	    SqlCondition cond) throws BusinessException {
	TableCols.UmOperatorCols t = TableCols.UmOperatorCols;
	SqlCondition condT = new SqlCondition();
	condT.eq(t.orgId, orgId);
	condT.eq(t.operatorState, 1);
	condT.merge(cond);
	return selectOperatorCountBySqlCond(condT);
    }

    @Override
    public void changePsw(Long operatorId, String pswNew, String pswOld)
	    throws BusinessException {
	UmOperator tmp = operatorMapper.selectByPrimaryKey(operatorId);
	if (passwordEncoder.match(pswOld, tmp.getOperatorPsw())) {
	    UmOperator operator = new UmOperator();
	    operator.setOperatorId(operatorId);
	    operator.setOperatorPsw(passwordEncoder.encrypt(pswNew));
	    operatorMapper.updateByPrimaryKeySelective(operator);
	} else {
	    throw new BusinessException("-99", "旧密码不正确");
	}
    }

    @Override
    public void resetPsw(Long operatorId) throws BusinessException {
	UmOperator tmp = new UmOperator();
	tmp.setOperatorId(operatorId);
	tmp.setOperatorPsw(passwordEncoder.encrypt("123456"));
	operatorMapper.updateByPrimaryKeySelective(tmp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ai.umauthor.server.service.mrg.interfaces.OperatorSV#selectOperatorById
     * (java.lang.String)
     */
    @Override
    public UmOperator selectOperatorById(String operatorId) throws BusinessException {
	
	UmOperatorExample example = new UmOperatorExample();
	if (StringUtils.isEmpty(operatorId)) {
	    throw new BusinessException("-1", "id为空");
	}
	
	UmOperator operator = operatorMapper.selectByPrimaryKey(new Long(operatorId));
	return operator;
    }

	@Override
	public UmOperator selectOperatorByCode(String code) throws BusinessException {
		UmOperatorExample example = new UmOperatorExample();
		UmOperatorExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(code)) {
			criteria.andOperatorCodeEqualTo(code);
		}
		criteria.andOperatorStateEqualTo((short)1);

		List<UmOperator> umOperatorList = operatorMapper.selectByExample(example);

		if (umOperatorList.isEmpty()) {
		    return null;
		} else {
		    return umOperatorList.get(0);
		}
	}
	
	@Override
	public void changePsw(Long operatorId, String pswNew)
			throws BusinessException {
		UmOperator operator = new UmOperator();
	    operator.setOperatorId(operatorId);
	    operator.setOperatorPsw(passwordEncoder.encrypt(pswNew));
	    operatorMapper.updateByPrimaryKeySelective(operator);
		
	}
}
