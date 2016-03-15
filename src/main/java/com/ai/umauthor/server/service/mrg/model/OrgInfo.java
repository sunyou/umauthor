package com.ai.umauthor.server.service.mrg.model;

import java.util.ArrayList;
import java.util.List;

import com.ai.umauthor.server.model.UmOrg;

/**
 * @author Typhon Chens
 * 2016年1月19日
 */
public class OrgInfo {
    private UmOrg org;
    
    private List<OrgInfo> childList=new ArrayList<OrgInfo>();

    public UmOrg getOrg() {
        return org;
    }

    public void setOrg(UmOrg org) {
        this.org = org;
    }
    
    public void addChildOrg(OrgInfo org){
	childList.add(org);
    }

    public List<OrgInfo> getChildList() {
        return childList;
    }

    public void setChildList(List<OrgInfo> childList) {
        this.childList = childList;
    }
}