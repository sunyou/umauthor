package com.ai.umauthor.server.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ai.umauthor.server.model.UmMenu;


public interface MenuInfoDao {
    
		
	/**
	 * 根据指定的菜单父ID,区域ID,查询操作员的权限
	 * @param menuId
	 * @param domainId
	 * @param operatorId
	 * @return
	 */
	@Select("SELECT DISTINCT T4.*  FROM UM_OPERATOR2STATION T1, UM_STATION T2, UM_STATION2MENU T3, UM_MENU T4 WHERE T1.STATION_ID = T2.STATION_ID   AND T2.STATION_ID = T3.STATION_ID   AND T3.MENU_ID = T4.MENU_ID  AND T4.DOMAIN_ID =#{domainId}   AND T4.PARENT_MENU_ID=#{menuId}   AND T1.OPERATOR_ID =#{operatorId} UNION SELECT DISTINCT T4.* FROM UM_OPERATOR2MENU T1, UM_MENU T4 WHERE T1.MENU_ID = T4.MENU_ID AND T4.DOMAIN_ID =#{domainId}   AND T4.PARENT_MENU_ID=#{menuId}  AND T1.OPERATOR_ID =#{operatorId}")
	List<UmMenu> queryMenusByOperator(@Param("menuId") Long menuId,@Param("domainId") Long domainId,@Param("operatorId") Long operatorId);

	//@Select("SELECT DISTINCT T4.*  FROM UM_OPERATOR2STATION T1, UM_STATION T2, UM_STATION2MENU T3, UM_MENU T4 WHERE T1.STATION_ID = T2.STATION_ID   AND T2.STATION_ID = T3.STATION_ID   AND T3.MENU_ID = T4.MENU_ID  AND T4.DOMAIN_ID =#{domainId}  AND T1.OPERATOR_ID =#{operatorId} UNION SELECT DISTINCT T4.* FROM UM_OPERATOR2MENU T1, UM_MENU T4 WHERE T1.MENU_ID = T4.MENU_ID   AND T4.PARENT_MENU_ID=#{menuId}  AND T1.OPERATOR_ID =#{operatorId}")
	//List<UmMenu> queryMenusByOperator(@Param("domainId") Long domainId,@Param("operatorId") Long operatorId);
	/**
	 * 根据操作员查询所属的菜单
	 * @param operatorId 操作员ID
	 * @return
	 */
	//@Select("SELECT DISTINCT T4.*  FROM UM_OPERATOR2STATION T1, UM_STATION T2, UM_STATION2MENU T3, UM_MENU T4 WHERE T1.STATION_ID = T2.STATION_ID   AND T2.STATION_ID = T3.STATION_ID   AND T3.MENU_ID = T4.MENU_ID AND T4.MENU_STATE='1' AND T4.DOMAIN_ID =#{domainId}    AND T1.OPERATOR_ID =#{operatorId} UNION SELECT DISTINCT T4.* FROM UM_OPERATOR2MENU T1, UM_MENU T4 WHERE T1.MENU_ID = T4.MENU_ID AND T4.MENU_STATE='1' AND T4.DOMAIN_ID =#{domainId} AND T1.OPERATOR_ID =#{operatorId}")
	//List<UmMenu> queryAllMenusByOperatorId(@Param("operatorId")Long operatorId, @Param("domainId")Long domainId);
	/**
	 * 根据操作员查询所属的菜单
	 * @param operatorId 操作员ID
	 * @return
	 */
	@Select("SELECT DISTINCT T4.*  FROM UM_OPERATOR2STATION T1, UM_STATION T2, UM_STATION2MENU T3, UM_MENU T4 WHERE T1.STATION_ID = T2.STATION_ID   AND T2.STATION_ID = T3.STATION_ID   AND T3.MENU_ID = T4.MENU_ID AND T4.MENU_STATE='1' AND T4.DOMAIN_ID =#{domainId} AND T4.SYSTEM_ID =#{systemId}    AND T1.OPERATOR_ID =#{operatorId} UNION SELECT DISTINCT T4.* FROM UM_OPERATOR2MENU T1, UM_MENU T4 WHERE T1.MENU_ID = T4.MENU_ID AND T4.MENU_STATE='1' AND T4.DOMAIN_ID =#{domainId} AND T4.SYSTEM_ID =#{systemId} AND T1.OPERATOR_ID =#{operatorId}")
	List<UmMenu> queryAllMenusByOperatorId(@Param("operatorId")Long operatorId, @Param("domainId")Long domainId, @Param("systemId")Long  systemId);

	/**
	 * 
	 * 功能描述：根据操作员operatorCode获取有权限菜单
	 *
	 * @author  zhangly
	 * <p>创建日期 ：2016年2月22日 下午4:32:58</p>
	 *
	 * @param operatorCode
	 * @param domainId
	 * @return
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	@Select("SELECT DISTINCT T4.*  FROM UM_OPERATOR2STATION T1, UM_STATION T2, UM_STATION2MENU T3, UM_MENU T4, UM_OPERATOR T5 WHERE T1.STATION_ID = T2.STATION_ID   AND T2.STATION_ID = T3.STATION_ID   AND T3.MENU_ID = T4.MENU_ID AND T1.OPERATOR_ID=T5.OPERATOR_ID AND T4.MENU_STATE='1' AND T5.OPERATOR_STATE='1' AND T4.DOMAIN_ID =#{domainId}    AND T5.OPERATOR_CODE =#{operatorCode}  AND T4.SYSTEM_ID =#{systemId} UNION SELECT DISTINCT T4.* FROM UM_OPERATOR2MENU T1, UM_MENU T4, UM_OPERATOR T5 WHERE T1.MENU_ID = T4.MENU_ID AND T4.MENU_STATE='1' AND T5.OPERATOR_STATE='1' AND T1.OPERATOR_ID=T5.OPERATOR_ID AND T4.DOMAIN_ID =#{domainId} AND T5.OPERATOR_CODE =#{operatorCode}  AND T4.SYSTEM_ID =#{systemId}")
	List<UmMenu> queryAllMenusByOperatorCode(
			@Param("operatorCode") String operatorCode,
			@Param("domainId") Long domainId, @Param("systemId") Long systemId);
}

