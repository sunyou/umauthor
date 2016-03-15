package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmOrg;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.service.mrg.model.OrgInfo;
import com.ai.umauthor.server.service.mrg.model.QryUmOrg;

/**
 * @author Typhon Chens
 * 2016年1月19日
 */
public interface OrgSV {
    /**
     * 添加组织机构
     * @param station
     * @throws BusinessException 
     */
    public void insertOrg(UmOrg org) throws BusinessException;
    
    /**
     * 修改组织
     * @param org
     */
    public void modifyOrg(UmOrg org) throws BusinessException;
    
    /**
     * 根据组织ID删除组织
     * @param orgId
     * @throws BusinessException 
     */
    public void deleteOrg(String orgId) throws BusinessException;
    
    /**
     * 根据机构ID查询机构信息
     * @param orgId
     * @return
     * @throws BusinessException 
     */
    public UmOrg queryOrgById(String orgId) throws BusinessException;
    
    /**
     * 根据操作员域和父级机构ID查询机构列表
     * @param orgId
     * @return
     * @throws BusinessException 
     */
    public List<UmOrg> queryOrgsByParentId(String orgId,String Name,String type) throws BusinessException;
    
    
    /**
     * 根据操作员域和父级机构ID查询机构列表
     * @param orgId
     * @return
     * @throws BusinessException 
     */
    public List<UmOrg> queryOrgsByParentId(String orgId) throws BusinessException;
    
    /**
     * 根据操作员域和父级机构ID查询机构列表
     * @param orgId
     * @return
     * @throws BusinessException 
     */
    public List<OrgInfo> queryOrgInfosById(String orgId) throws BusinessException;
    
    /**
     * 根据操作员查询所属的机构
     * @param operatorId 操作员ID
     * @return
     */
    public UmOrg queryOrgByOperator(String operatorId,String domainId);
    
    /**
     * 获取组织机构列表个数(包含所有下级)
     * @param orgId 当前机构(父级)
     * @param cond
     * @return
     * @throws BusinessException 
     */
    public int selectOrgCountBySqlCond(String orgId,SqlCondition cond) throws BusinessException;
    
    /**
     * 获取组织机构列表(包含所有下级)
     * @param orgId 当前机构(父级)
     * @param cond
     * @return
     * @throws BusinessException 
     */
    public List<QryUmOrg> selectOrgListBySqlCond(String orgId,SqlCondition cond) throws BusinessException;
    
    /**
     * 获取组织机构列表(只包含父级的直属下级)
     * @param orgId 当前机构(父级)
     * @param cond
     * @return
     * @throws BusinessException 
     */
    public int selectOrgCountUnderParentBySqlCond(String orgId,SqlCondition cond) throws BusinessException;
    
    /**
     * 获取组织机构列表(只包含父级的直属下级)
     * @param orgId 当前机构(父级)
     * @param cond
     * @return
     * @throws BusinessException 
     */
    public List<QryUmOrg> selectOrgListUnderParentBySqlCond(String orgId,SqlCondition cond) throws BusinessException;
    
    /***
     * 
     * 功能描述：根据当前组织ID，获取父组织信息
     *
     * @author  zhangly
     * <p>创建日期 ：2016年2月18日 下午4:36:30</p>
     *
     * @param orgId
     * @return
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    public UmOrg qryParentOrgByOrgId(String orgId) throws BusinessException;
    
    /**
     * 
     * 功能描述：根据父组织ID，获取子组织对象列表
     *
     * @author  zhangly
     * <p>创建日期 ：2016年2月18日 下午5:22:31</p>
     *
     * @param parentOrgId 父组织ID	
     * @param isRecursion 是否递归
     * @return
     * @throws BusinessException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    public List<UmOrg> qryChildOrgByParentId(String parentOrgId, boolean isRecursion) throws BusinessException;
   
}
