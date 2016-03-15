package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;

import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmMenu;

/**
 * @author Typhon Chens
 * 2016年1月19日
 */
public interface MenuSV {
    /**
     * 添加菜单
     * @param station
     * @throws BusinessException 
     */
    public void insertMenu(UmMenu menu) throws Exception;
    
    /**
     * 修改菜单
     * @param station
     */
    public void modifyMenu(UmMenu menu) throws Exception;
    
    /**
     * 根据菜单ID删除菜单
     * @param stationId
     * @throws BusinessException 
     */
    public void deleteMenu(Long menuId) throws Exception;
    
    /**
     * 根据操作员查询所属的菜单
     * @param operatorId 操作员ID
     * @return
     */
    public List<UmMenu> queryMenusAllByOperator(Long operatorId,Long domainId);
    
    /**
     * 根据操作员查询所属的菜单
     * @param operatorId 操作员ID
     * @return
     */
    public List<UmMenu> queryMenusAllByOperator(Long operatorId,Long domainId,Long systemId);
    /**
     * 根据操作员域和父级菜单ID查询菜单列表
     * @param operatorId
     * @param domainId
     * @param menuId
     * @return
     */
    public List<UmMenu> queryMenusByOperator(String operatorId,String domainId,String menuId);
    
    /**
     * @根据父级菜单ID获取子菜单列表
     * @param parentMenuId
     * @return
     * @throws BusinessException
     */
    public List<UmMenu> queryMenuListByParentMenuId(String parentMenuId,String systemId) throws BusinessException;
    
    /**
     * 是否有子集？
     * @param parentMenuId
     * @return
     * @throws BusinessException
     */
    public boolean hasChildrens(String parentMenuId) throws BusinessException;
    /**
     * 查询菜单详情
     * @param menuId
     * @return
     * @throws Exception
     */
    public UmMenu queryMenuById(Long menuId) throws Exception;
    
    /**
     * @Description: 保存菜单排序
     * @author fanlx
     * @date 2016年2月17日 下午5:34:26
     */
    public void saveOrder(String orderedJson) throws BusinessException;
    
    /**
     * 
     * 功能描述：根据operatorCode和域ID获取有权限菜单
     *
     * @author  zhangly
     * <p>创建日期 ：2016年2月22日 下午4:41:30</p>
     *
     * @param operatorCode
     * @param domainId
     * @return
     * @throws BusinessException
     *
     * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
     */
    public List<UmMenu> queryMenusByOperatorCode(String operatorCode, Long domainId,Long systemId) throws BusinessException;
}
