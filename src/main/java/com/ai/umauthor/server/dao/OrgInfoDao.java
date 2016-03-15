package com.ai.umauthor.server.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmOrg;


public interface OrgInfoDao {

    /**
     * 根据操作员获取组织列表
     * @param operatorId 操作员ID
     * @param domainId 域
     * @return
     */
   @Select("SELECT T2.* FROM UM_OPERATOR T1, UM_ORG T2 WHERE T1.ORG_ID = T2.ORG_ID AND T2.DOMAIN_ID = #{domainId}  AND T1.OPERATOR_ID = #{operatorId}")
   List<UmOrg> queryOrgByOperator(@Param("operatorId")String operatorId,  @Param("domainId")String domainId);

    /**
     * 根据指定的机构ID获取下属机构
     * @param orgId
     * @param domainId
     * @return
     */
   @Select("SELECT T1.* FROM UM_ORG T1 WHERE T1.ORG_STATE=1 AND T1.PARENT_ORG_ID = #{orgId}  AND T1.DOMAIN_ID = #{domainId}")
    List<UmOrg> queryAffiliateOrgsByParentId(@Param("orgId")String orgId, @Param("domainId")Long domainId);
   
   /**
    * 
    * 功能描述：根据当前组织ID，获取父组织信息
    *
    * @author  zhangly
    * <p>创建日期 ：2016年2月18日 下午4:54:11</p>
    *
    * @param orgId
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   @Select("select x.* from um_org x where org_id=(select y.parent_org_id from um_org y where org_id=#{orgId})")
   UmOrg getParentOrgByOrgId(@Param("orgId")String orgId);

   /**
    * 
    * 功能描述：根据父组织ID，获取子、子孙组织对象列表
    *
    * @author  zhangly
    * <p>创建日期 ：2016年2月18日 下午6:21:53</p>
    *
    * @param parentOrgId
    * @return
    *
    * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
    */
   @Select("SELECT * FROM UM_ORG START WITH PARENT_ORG_ID=#{parentOrgId} CONNECT BY PARENT_ORG_ID=PRIOR ORG_ID")
   List<UmOrg> qryChildOrgByParentId(@Param("parentOrgId")String parentOrgId);
}

