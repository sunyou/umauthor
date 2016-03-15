package com.ai.umauthor.server.service.mrg.model;

import com.ai.umauthor.server.model.UmOrg;

/**
 * @author Typhon Chens
 * 2016年1月29日
 */
public class QryUmOrg extends UmOrg {
    public String parentOrgName;

    public String getParentOrgName() {
        return parentOrgName;
    }

    public void setParentOrgName(String parentOrgName) {
        this.parentOrgName = parentOrgName;
    }
}
