package com.ai.umauthor.server.service.mrg.model;

import com.ai.umauthor.server.model.UmOperator;

/**
 * 针对UmOperator的二次构造类
 * @author Typhon Chens
 * 2016年1月25日
 */
public class QryUmOperator extends UmOperator {
    
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
