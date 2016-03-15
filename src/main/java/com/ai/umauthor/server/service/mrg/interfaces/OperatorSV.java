package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.service.mrg.model.QryUmOperator;

public interface OperatorSV {

    /**
     * 添加操作员 默认密码为123456
     * 
     * @throws Exception
     */
    public UmOperator insertOperator(UmOperator operatorBean)
	    throws BusinessException;

    /**
     * 修改操作员
     * 
     * @throws Exception
     */
    public void modifyOperator(UmOperator operatorBean)
	    throws BusinessException;

    /**
     * 删除操作员
     * 
     * @throws Exception
     */
    public void deleteOperator(Long operatorId) throws Exception;

    /**
     * 锁定操作员帐号
     * 
     * @throws Exception
     */
    public void lockOperator(Long operatorId) throws Exception;
    
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
	    String orgId) throws BusinessException;
    
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
    public UmOperator selectOperatorById(String id) throws BusinessException;
    
    /**
     * 根据指定查询条件SqlCondition查询操作员个数
     * @param cond 查询条件构造类SqlCondition
     * @return
     * @throws BusinessException
     */
    public List<QryUmOperator> selectOperatorBySqlCond(SqlCondition cond)
	    throws BusinessException;
    
    /**
     * 根据指定查询条件SqlCondition查询操作员列表
     * @param cond 查询条件构造类SqlCondition
     * @return
     * @throws BusinessException
     */
    public Integer selectOperatorCountBySqlCond(SqlCondition cond) throws BusinessException;

    
    /**
     * 根据组织ID和指定查询条件SqlCondition查询操作员列表
     * @param orgId
     * @param cond
     * @return
     * @throws BusinessException 
     */
    public List<QryUmOperator> selectOperatorByOrgAndSqlCond(String orgId,SqlCondition cond) throws BusinessException;/**
    
    /* 根据指定查询条件SqlCondition查询操作员列表
     * @param cond 查询条件构造类SqlCondition
     * @return
     * @throws BusinessException
     */
    public int selectOperatorCountByOrgAndSqlCond(String orgId,SqlCondition cond) throws BusinessException;
    
    /**
     * 修改用户密码
     * @param operatorId
     * @param pswNew
     * @param pswOld
     * @throws BusinessException
     */
    public void changePsw(Long operatorId,String pswNew,String pswOld) throws BusinessException;
    
    /**
     * 修改用户密码，不用旧密码
     * @param operatorId
     * @param pswNew
     * @param pswOld
     * @throws BusinessException
     */
    public void changePsw(Long operatorId,String pswNew) throws BusinessException;
    
    /**
     * 重置用户密码
     * @param operatorId
     * @throws BusinessException
     */
    public void resetPsw(Long operatorId) throws BusinessException;
    
    /**
     * 
     * 功能描述：根据operatorCode获取操作员信息
     *
     * @author  zhangly
     * <p>创建日期 ：2016年2月22日 下午4:34:29</p>
     *
     * @param code
     * @return
     * @throws BusinessException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    public UmOperator selectOperatorByCode(String code) throws BusinessException;
}
