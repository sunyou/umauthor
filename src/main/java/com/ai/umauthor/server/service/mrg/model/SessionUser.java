package com.ai.umauthor.server.service.mrg.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 记录当前登录用户信息
 * @author Typhon Chens
 * 2016年1月20日
 * @param <U> 系统中用户信息对应的实体类
 * @param <R> 系统中角色信息对应的实体类
 * @param <M> 系统中菜单信息对应的实体类
 * @param <P> 系统中权限信息对应的实体类
 */
public class SessionUser<U,R,M,P>{
    
    /**
     * 获取当前登录用户信息
     */
    public U loginUser;
    
    /**
     * 假设有角色切换场景时,记录登录用户当前使用的角色
     */
    public R currentStation;

    
    public U getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(U loginUser) {
        this.loginUser = loginUser;
    }

    public R getCurrentStation() {
        return currentStation;
    }

    public void setCurrentStation(R currentStation) {
        this.currentStation = currentStation;
    }

    public List<R> getStations() {
        return stations;
    }

    public void setStations(List<R> stations) {
        this.stations = stations;
    }

    public List<M> getMenus() {
        return menus;
    }

    public void setMenus(List<M> menus) {
        this.menus = menus;
    }

    public List<P> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<P> permissions) {
        this.permissions = permissions;
    }

    /**
     * 登录用户所拥有的角色
     */
    public List<R> stations=new ArrayList<R>();
    
    /**
     * 登录用户所拥有的菜单
     */
    public List<M> menus=new ArrayList<M>();
    
    /**
     * 登录用户所拥有的权限
     */
    public List<P> permissions=new ArrayList<P>();
    
    
   

}
