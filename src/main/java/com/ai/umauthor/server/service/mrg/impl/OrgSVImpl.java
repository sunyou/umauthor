package com.ai.umauthor.server.service.mrg.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.dynasql.SqlRepo;
import com.ai.common.exception.BusinessException;
import com.ai.common.util.IdGenUtil;
import com.ai.umauthor.server.dao.OrgInfoDao;
import com.ai.umauthor.server.dao.StationInfoDao;
import com.ai.umauthor.server.mapper.UmOrgMapper;
import com.ai.umauthor.server.mapper.UmOperatorMapper;
import com.ai.umauthor.server.mapper.UmStationMapper;
import com.ai.umauthor.server.model.TableCols;
import com.ai.umauthor.server.model.UmOrg;
import com.ai.umauthor.server.model.UmOrgExample;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmOperatorExample;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.model.UmStationExample;
import com.ai.umauthor.server.service.mrg.interfaces.OrgSV;
import com.ai.umauthor.server.service.mrg.interfaces.StationSV;
import com.ai.umauthor.server.service.mrg.model.OrgInfo;
import com.ai.umauthor.server.service.mrg.model.QryUmOrg;
import com.google.common.collect.ImmutableMap;

/**
 * @author Typhon Chens
 * 2016年1月19日
 */
@Service
public class OrgSVImpl implements OrgSV {

    @Autowired
    UmOrgMapper orgMapper;
    
    @Autowired
    OrgInfoDao orgInfoDao;
    
    @Autowired
    SqlRepo sqlRepo;
    
    /**
     * 添加机构
     * @param orgBean
     */
    public void insertOrg(UmOrg orgBean) throws BusinessException {
	if(orgBean==null){
	    throw new NullPointerException();
	}
	
	if(StringUtils.isEmpty(orgBean.getDomainId())){
	    throw new BusinessException("-1","域ID不能为空");
	}
	
	if(StringUtils.isEmpty(orgBean.getOrgName())){
	    throw new BusinessException("-2","机构名不能为空");
	}
	if(StringUtils.isEmpty(orgBean.getParentOrgId())){
	    throw new BusinessException("-3","父机构ID不能为空");
	}
	String parentOrgIdCode=null;
	
	/*UmOrg parentOrg=queryOrgById(orgBean.getParentOrgId().toString());
	if(parentOrg==null){
	    throw new BusinessException("-4","父机构无效");
	}*/
	//parentOrgCode=parentOrg.getOrgCode();
	
	//List<UmOrg> orgList=queryOrgsByParentId(parentOrg.getOrgId().toString());
	
	List<UmOrg> orgList=queryOrgsByParentId(orgBean.getParentOrgId().toString());
	orgBean.setOrgId(orgBean.getParentOrgId()+"-"+orgList.size());

	
	orgBean.setOrgState((short) 1);
	//orgBean.setOrgId(IdGenUtil.timeBasedId());
	orgMapper.insert(orgBean);
	
    }

    /**
     * 修改机构
     * @param orgBean
     */
    public void modifyOrg(UmOrg orgBean) throws BusinessException {
	if(StringUtils.isEmpty(orgBean.getOrgId())){
	    throw new BusinessException("-1","机构索引为空");
	}

	orgMapper.updateByPrimaryKeySelective(orgBean);
	
    }

    /**
     * 根据机构ID删除机构
     * @param orgId
     */
    public void deleteOrg(String orgId) throws BusinessException {
	UmOrg tmp=orgMapper.selectByPrimaryKey(orgId);
	tmp.setOrgState((short) 0);
	orgMapper.updateByPrimaryKey(tmp);
	
    }
    
    /**
     * 根据操作员查询所属的机构
     * @param operatorId 操作员ID
     * @return
     */
    public UmOrg queryOrgByOperator(String operatorId,String domainId){
	List<UmOrg> orgList= orgInfoDao.queryOrgByOperator(operatorId,domainId);
	if(orgList.isEmpty()){
	    return null;
	}else{
	    return orgList.get(0);
	}
	 
    }
    
    /**
     * 根据机构ID查询机构信息
     * @param orgId
     * @return
     * @throws BusinessException 
     */
    public UmOrg queryOrgById(String orgId) throws BusinessException{
	if(StringUtils.isEmpty(orgId)){
	    throw new BusinessException("-1","机构ID为空");
	}
	return orgMapper.selectByPrimaryKey(orgId);
    }
   
    /**
     * 根据操作员域和父级机构ID查询机构列表
     * @param operatorId
     * @param domainId
     * @param orgId
     * @return
     * @throws BusinessException 
     */
    public List<OrgInfo> queryOrgInfosById(String orgId) throws BusinessException{
	
	OrgInfo orgInfo=new OrgInfo();
	UmOrg umOrg= queryOrgById(orgId);
	List<OrgInfo> orgList=new ArrayList<OrgInfo>();
	
	orgInfo =queryAffiliateOrgsByParentOrg(umOrg);
	orgList.add(orgInfo);
	return orgList;
    }
    
    /**
     * 根据指定机构查询下属机构
     * @param umOrg
     * @return
     */
    private OrgInfo queryAffiliateOrgsByParentOrg(UmOrg umOrg){
	OrgInfo orgInfo=new OrgInfo();
	orgInfo.setOrg(umOrg);
	List<UmOrg> orgList =orgInfoDao.queryAffiliateOrgsByParentId(umOrg.getOrgId(),umOrg.getDomainId());
	if(!orgList.isEmpty()){
	    for(int i=0;i<orgList.size();i++){
		OrgInfo orgInfoTmp=queryAffiliateOrgsByParentOrg(orgList.get(i));
		orgInfo.addChildOrg(orgInfoTmp);
	    }
	}
	return orgInfo;
    }

    /* (non-Javadoc)
     * @see com.ai.umauthor.server.service.mrg.interfaces.OrgSV#queryOrgsById(java.lang.String)
     */
    @Override
    public List<UmOrg> queryOrgsByParentId(String orgId) throws BusinessException {
	
	return queryOrgsByParentId(orgId,null,null);
    }
    
    /* (non-Javadoc)
     * @see com.ai.umauthor.server.service.mrg.interfaces.OrgSV#queryOrgsById(java.lang.String)
     */
    @Override
    public List<UmOrg> queryOrgsByParentId(String orgId,String name,String type) throws BusinessException {
	UmOrgExample example=new UmOrgExample();
	com.ai.umauthor.server.model.UmOrgExample.Criteria  c=example.createCriteria();
	c.andParentOrgIdEqualTo(orgId);
	if(!StringUtils.isEmpty(name)){
	    c.andOrgNameLike(name);
	}
	if(!StringUtils.isEmpty(type)){
	    c.andOrgTypeEqualTo(type);
	}
	
	
	
	return orgMapper.selectByExample(example);
    }

    /* (non-Javadoc)
     * @see com.ai.umauthor.server.service.mrg.interfaces.OrgSV#selectOrgCountBySqlCond(java.lang.String, com.ai.common.dynasql.SqlCondition)
     */
    @Override
    public int selectOrgCountBySqlCond(String orgId, SqlCondition cond) throws BusinessException {
	String orgIdCode=null;
	if(!StringUtils.isEmpty(orgId)){
	    UmOrg umOrg=queryOrgById(orgId);
	    if(umOrg==null){
		throw new BusinessException("-2","无效机构");
	    }
	    
	    
	    if(!StringUtils.isEmpty(umOrg.getOrgCode())){
		orgIdCode=umOrg.getOrgCode();
	    }
	}
	TableCols.UmOrgCols t = TableCols.UmOrgCols;
	SqlCondition condT = new SqlCondition();
	condT.likeLeft(t.orgId, orgId);
	condT.merge(cond);
	 try {
	     List<Map<String, Object>> c=null;
	     c=sqlRepo.fetchBySql(
	    		"select count(1) as value from UM_ORG where 1=1 ", null, condT);
	    	if(!c.isEmpty()){
		 return new Integer(c.get(0).get("VALUE").toString());
		
	     }
	     return 0;
	} catch (Exception e) {
	   // e.printStackTrace();
	    throw new BusinessException("-99",e.getMessage());
	}
    }

    /* (non-Javadoc)
     * @see com.ai.umauthor.server.service.mrg.interfaces.OrgSV#selectOrgListBySqlCond(java.lang.String, com.ai.common.dynasql.SqlCondition)
     */
    @Override
    public List<QryUmOrg> selectOrgListBySqlCond(String orgId, SqlCondition cond) throws BusinessException {
	String orgIdCode=null;
	if(!StringUtils.isEmpty(orgId)){
	    UmOrg umOrg=queryOrgById(orgId);
	    if(umOrg==null){
		throw new BusinessException("-2","无效机构");
	    }
	    
	    
	    if(!StringUtils.isEmpty(umOrg.getOrgCode())){
		orgIdCode=umOrg.getOrgCode();
	    }
	}
	TableCols.UmOrgCols t = TableCols.UmOrgCols;
	SqlCondition condT = new SqlCondition();
	condT.likeLeft(t.orgId, orgId);
	condT.merge(cond);
	 try {
	     List<QryUmOrg> c=null;

		c=sqlRepo.fetchByConfig(
		    		"sqlOrg.query", null, condT,QryUmOrg.class);

	    	
	     return c;
	} catch (Exception e) {
	   // e.printStackTrace();
	    throw new BusinessException("-99",e.getMessage());
	}
	

	
    }

	@Override
	public int selectOrgCountUnderParentBySqlCond(String orgId,
			SqlCondition cond) throws BusinessException {

		TableCols.UmOrgCols t = TableCols.UmOrgCols;
		SqlCondition condT = new SqlCondition();
		condT.eq(t.parentOrgId, orgId);
		condT.merge(cond);
		 try {
		     List<Map<String, Object>> c=null;
		     c=sqlRepo.fetchBySql(
		    		"select count(1) as value from UM_ORG where 1=1 ", null, condT);
		    	if(!c.isEmpty()){
			 return new Integer(c.get(0).get("VALUE").toString());
			
		     }
		     return 0;
		} catch (Exception e) {
		   // e.printStackTrace();
		    throw new BusinessException("-99",e.getMessage());
		}
	}

	@Override
	public List<QryUmOrg> selectOrgListUnderParentBySqlCond(String orgId,
			SqlCondition cond) throws BusinessException {
		
		TableCols.UmOrgCols t = TableCols.UmOrgCols;
		SqlCondition condT = new SqlCondition();
		condT.eq(t.parentOrgId, orgId);
		condT.merge(cond);
		 try {
		     List<QryUmOrg> c=null;

			c=sqlRepo.fetchByConfig(
			    		"sqlOrg.query", null, condT,QryUmOrg.class);

		     return c;
		} catch (Exception e) {
		   // e.printStackTrace();
		    throw new BusinessException("-99",e.getMessage());
		}
	}

	@Override
	public UmOrg qryParentOrgByOrgId(String orgId) throws BusinessException {
		return this.orgInfoDao.getParentOrgByOrgId(orgId);
	}

	@Override
	public List<UmOrg> qryChildOrgByParentId(String parentOrgId, boolean isRecursion) throws BusinessException {
		if(isRecursion) {
			return this.orgInfoDao.qryChildOrgByParentId(parentOrgId);
		} else {
			return this.queryOrgsByParentId(parentOrgId);
		}
	}
}
