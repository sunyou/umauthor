package com.ai.umauthor.server.service.mrg.model;

import java.util.ArrayList;
import java.util.List;

import com.ai.umauthor.server.model.UmMenu;

/**
 * @author Typhon Chens
 * 2016年1月19日
 */
public class MenuInfo {
    private UmMenu menu;
    
    private List<MenuInfo> childList=new ArrayList<MenuInfo>();

    public UmMenu getMenu() {
        return menu;
    }

    public void setMenu(UmMenu menu) {
        this.menu = menu;
    }
    
    public void addChildMenu(MenuInfo menu){
	childList.add(menu);
    }

    public List<MenuInfo> getChildList() {
        return childList;
    }

    public void setChildList(List<MenuInfo> childList) {
        this.childList = childList;
    }
}