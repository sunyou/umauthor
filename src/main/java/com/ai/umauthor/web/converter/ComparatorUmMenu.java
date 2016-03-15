package com.ai.umauthor.web.converter;

import java.util.Comparator;

import com.ai.umauthor.server.model.UmMenu;

/**
 * 菜单内存排序扩展类
 * @author Typhon Chens 2016年2月3日
 */
public class ComparatorUmMenu implements Comparator<UmMenu> {
    
    /**
     * 先按照排序字段menuOrder比较,如果相同则按照创建时间比较
     */
    public int compare(UmMenu menu0, UmMenu menu1) {
	
	Long menuOrder0=menu0.getMenuOrder()==null?0:menu0.getMenuOrder();
	Long menuOrder1=menu1.getMenuOrder()==null?0:menu1.getMenuOrder();
	
	int flag = menuOrder0.compareTo(menuOrder1);
	if (flag == 0) {
	    return menu0.getCreateDate().compareTo(menu1.getCreateDate());
	} else {
	    return flag;
	}
    }

}
