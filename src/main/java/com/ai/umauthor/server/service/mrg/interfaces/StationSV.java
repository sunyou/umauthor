package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.service.mrg.model.MenuStationRelate;
import com.ai.umauthor.server.service.mrg.model.QryUmStation;

/**
 * @author Typhon Chens
 * 2016年1月19日
 */
public interface StationSV {
    
    /**
     * 添加角色
     * @param station
     * @throws BusinessException 
     */
    public void insertStation(UmStation station,String relaMenuIds,String relaTypeIds) throws Exception,NumberFormatException;
    
    /**
     * 修改角色
     * @param station
     */
    public void modifyStation(UmStation station,String relaMenuIds,String relaTypeIds,String oldRelaMenuIds,String oldRelaTypeIds) throws NumberFormatException, Exception;
    
    /**
     * 根据角色ID删除角色
     * @param stationId
     * @throws BusinessException 
     */
    public void deleteStation(Long stationId) throws Exception;
    
    
    /**
     * 根据操作员查询所属的角色
     * @param operatorId 操作员ID
     * @param domainId 域ID
     * @return
     */
    public List<UmStation> queryStationsByOperator(Long operatorId,Long domainId);
    
    /**
     * 根据ORG_ID查询用户所在组织及下级组织拥有的岗位记录数
     * @param orgId
     * @return
     * @throws Exception
     */
    public int queryStationCountByOrgId(String orgId)throws Exception;
    /**
     * 根据ORG_ID查询用户所在组织及下级组织拥有的岗位
     * @param orgId
     * @return
     * @throws BusinessException
     */
    public List<QryUmStation> queryStationsByOrgId(String orgId,String stationName,String groupName,int pageNo,int pageSize) throws Exception;
    
    /**
     * @author sunyou 根据stationId查询岗位信息
     * @param stationId
     * @return
     * @throws Exception
     */
    public UmStation queryStationById(Long stationId) throws Exception;
    
    /**
     * 批量菜单岗位关联------岗位列表查询
     * @author guohui
     *@date 2016年2月17日
     * @param orgId
     * @return
     * @throws Exception
     */
    public List<MenuStationRelate> queryMenuStationsByOrgId(String orgId,String menuId,SqlCondition cond) throws Exception;

    public int queryMenuStationCountByOrgId(String orgId,String menuId,SqlCondition cond)throws Exception;
    
    /**
     * 批量菜单关联岗位保存
     * @author guohui
     *@date 2016年2月18日
     * @param jsonMessage
     * @param menuId
     * @return
     * @throws Exception
     */
    public void saveMenuStation(	String jsonMessage ,String menuId)throws Exception;
    
    /**
     * 批量岗位类型关联----岗位列表查询
     * @author guohui
     *@date 2016年2月18日
     * @param orgId
     * @param stationTypeId
     * @param cond
     * @return
     * @throws Exception
     */
    public List<MenuStationRelate> queryStationTypeStationsByOrgId(String orgId,String stationTypeId,SqlCondition cond) throws Exception;

    public int queryStationTypeStationCountByOrgId(String orgId,String stationTypeId,SqlCondition cond)throws Exception;
    
    /**
     * 批量保存岗位类型岗位关联
     * @author guohui
     *@date 2016年2月18日
     * @param jsonMessage
     * @param menuId
     * @throws Exception
     */
    public void saveStationTypeStation(	String jsonMessage ,String stationTyepId)throws Exception;
    
    /**
     * 
     * 功能描述：根据operatorCode获取操作员岗位信息
     *
     * @author  zhangly
     * <p>创建日期 ：2016年2月23日 上午10:43:03</p>
     *
     * @param operatorCode
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    public List<UmStation> qryStationByOperatorCode(String operatorCode) throws Exception;
    
    /**
     * 
     * 功能描述：根据operatorId获取操作员岗位信息
     *
     * @author  zhangly
     * <p>创建日期 ：2016年2月23日 上午11:11:17</p>
     *
     * @param operatorId
     * @return
     * @throws Exception
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    public List<UmStation> qryStationByOperatorId(Long operatorId) throws Exception;
}
