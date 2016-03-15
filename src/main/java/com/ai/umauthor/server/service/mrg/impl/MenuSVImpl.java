package com.ai.umauthor.server.service.mrg.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.dynasql.SqlRepo;
import com.ai.common.exception.BusinessException;
import com.ai.common.util.IdGenUtil;
import com.ai.common.util.WebUtils;
import com.ai.umauthor.server.dao.MenuInfoDao;
import com.ai.umauthor.server.mapper.UmMenuMapper;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmMenuExample;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.service.mrg.interfaces.MenuSV;
import com.ai.umauthor.server.service.mrg.interfaces.Operator2MenuSV;
import com.ai.umauthor.server.service.mrg.interfaces.Station2MenuSV;
import com.ai.umauthor.server.service.mrg.model.SessionUser;
import com.ai.umauthor.web.converter.ComparatorUmMenu;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;

/**
 * @author Typhon Chens
 * 2016年1月19日
 */
@Service
@Transactional
public class MenuSVImpl implements MenuSV {

    @Autowired
    UmMenuMapper menuMapper;
    @Autowired
    MenuInfoDao menuInfoDao;
    @Autowired
    private SqlRepo sqlRepo;
    @Autowired
    private Operator2MenuSV operator2MenuSV;
    @Autowired
    private Station2MenuSV station2MenuSV;
    /**
     * 添加菜单
     * @param menuBean
     * @throws Exception 
     */
    public void insertMenu(UmMenu menuBean) throws Exception {
		if(menuBean==null){
		    throw new NullPointerException();
		}
		
		if(StringUtils.isEmpty(menuBean.getMenuName())){
		    throw new BusinessException("-3","菜单名不能为空");
		}
		if(StringUtils.isEmpty(menuBean.getParentMenuId())){
		    throw new BusinessException("-4","父菜单ID不能为空");
		}
		//判断menuCode是否重复
		UmMenuExample exam = new UmMenuExample();
		exam.createCriteria().andMenuCodeEqualTo(menuBean.getMenuCode());
		int count = menuMapper.countByExample(exam);
		if(count > 0){
			throw new BusinessException("-1", "该菜单编码已存在！");
		}
		SessionUser<UmOperator,UmStation,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
		if(sessionUserInfo != null){
			UmOperator umOperator=sessionUserInfo.getLoginUser();
			menuBean.setDomainId(umOperator.getDomainId());//从当前登录用户中获取
		}else{
			menuBean.setDomainId(1L);
		}
//		menuBean.setSystemId(1L);//暂时写死
		menuBean.setMenuState((short) 1);
		menuBean.setMenuId(IdGenUtil.timeBasedId());
		menuBean.setCreateDate(new Date());
		menuMapper.insert(menuBean);
		//将菜单默认授权给operator_id为0用户，0用户后续作为admin管理员
		operator2MenuSV.saveOperMenuRela(0L, menuBean.getMenuId(), "默认授权菜单给Admin");
    }

    /**
     * 修改菜单
     * @param menuBean
     */
    public void modifyMenu(UmMenu menuBean) throws BusinessException {
		if(menuBean.getMenuId() ==null||menuBean.getMenuId()<=0){
		    throw new BusinessException("-1","菜单索引为空");
		}
		menuMapper.updateByPrimaryKeySelective(menuBean);
    }

    /**
     * 根据菜单ID删除菜单
     * @param menuId
     */
    public void deleteMenu(Long menuId) throws Exception {
//		UmMenu tmp=menuMapper.selectByPrimaryKey(menuId);
//		tmp.setMenuState((short) 0);
//		menuMapper.updateByPrimaryKey(tmp);
    	//判断是否有子节点
    	UmMenuExample example = new UmMenuExample();
    	example.createCriteria().andParentMenuIdEqualTo(menuId);
    	int count = menuMapper.countByExample(example);
    	if(count > 0){
    		throw new BusinessException("-1","该节点包含子节点，不允许级联删除！");
    	}
    	menuMapper.deleteByPrimaryKey(menuId);
    	//删除操作员同菜单关联关系
    	operator2MenuSV.delOperMenuRela(null, menuId);
    	//删除菜单同岗位关联关系
    	station2MenuSV.deleteStation2MenuRela(null, menuId);
    }
    
    /**
     * 根据操作员查询所属的菜单
     * @param operatorId 操作员ID
     * @return
     */
    public List<UmMenu>  queryMenusAllByOperator(Long operatorId,Long domainId){
    	List<UmMenu> menuList =  menuInfoDao.queryAllMenusByOperatorId(operatorId,domainId,(long)1);
    	ComparatorUmMenu comparator=new ComparatorUmMenu();
    	Collections.sort(menuList, comparator);
    	return menuList;
    }
    /**
     * 根据操作员查询所属的菜单
     * @param operatorId 操作员ID
     * @return
     */
    public List<UmMenu>  queryMenusAllByOperator(Long operatorId,Long domainId,Long systemId){
    	List<UmMenu> menuList =  menuInfoDao.queryAllMenusByOperatorId(operatorId,domainId,systemId);
    	ComparatorUmMenu comparator=new ComparatorUmMenu();
    	Collections.sort(menuList, comparator);
    	return menuList;
    }
   
    /**
     * 根据操作员域和父级菜单ID查询菜单列表
     * @param operatorId
     * @param domainId
     * @param menuId
     * @return
     */
    public List<UmMenu> queryMenusByOperator(String operatorId,String domainId,String menuId){
	List<UmMenu> menuList =menuInfoDao.queryMenusByOperator(new Long(menuId),new Long(operatorId),new Long(domainId));
	return menuList;
    }

	@Override
	public List<UmMenu> queryMenuListByParentMenuId(String parentMenuId,String systemId) throws BusinessException {
		List<UmMenu> list = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("select * from um_menu where parent_menu_id=:parent_menu_id ");
			SqlCondition cond = new SqlCondition();
			cond.eq("parent_menu_id", parentMenuId);
			if(!StringUtils.isEmpty(systemId)){
				cond.eq("system_id", systemId);
				sql.append(" and system_id=:system_id");
			}
			//sql.append(" order by menu_order asc");
			cond.orderBy("menu_order");
			list = sqlRepo.fetchBySql(sql.toString(), null, cond, UmMenu.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public UmMenu queryMenuById(Long menuId) throws Exception {
		return menuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public boolean hasChildrens(String parentMenuId) throws BusinessException {
		UmMenuExample example = new UmMenuExample();
		example.createCriteria().andParentMenuIdEqualTo(Long.valueOf(parentMenuId));
		int count = menuMapper.countByExample(example);
		if(count > 0)return true;
		return false;
	}

	@Override
	public void saveOrder(String orderedJson) throws BusinessException {
		JSONArray array = JSONArray.parseArray(orderedJson);
		JSONObject jsobj;
		UmMenu menu;
		for (int i = 0; i < array.size(); i++) {
			jsobj = array.getJSONObject(i);
			menu = new UmMenu();
			menu.setMenuId(Long.parseLong(jsobj.getString("menuId")));
			menu.setMenuOrder(Long.parseLong(jsobj.getString("menuOrder")));
			menuMapper.updateByPrimaryKeySelective(menu);
		}
	}

	@Override
	public List<UmMenu> queryMenusByOperatorCode(String operatorCode, Long domainId,Long systemId) throws BusinessException {
		
		return menuInfoDao.queryAllMenusByOperatorCode(operatorCode, domainId,systemId);
	}
}
