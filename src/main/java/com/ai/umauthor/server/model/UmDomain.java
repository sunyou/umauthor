package com.ai.umauthor.server.model;

import java.io.Serializable;

public class UmDomain implements Serializable {
    private Long domainId;

    private String domainName;

    private Short domainState;

    private String remarks;

    private static final long serialVersionUID = 1L;

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName == null ? null : domainName.trim();
    }

    public Short getDomainState() {
        return domainState;
    }

    public void setDomainState(Short domainState) {
        this.domainState = domainState;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}