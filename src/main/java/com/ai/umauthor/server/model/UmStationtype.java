package com.ai.umauthor.server.model;

import java.io.Serializable;

public class UmStationtype implements Serializable {
    private Long stationtypeId;

    private Long domainId;

    private String stationtypeName;

    private String stationtypeCode;

    private Short stationtypeState;

    private String remarks;

    private static final long serialVersionUID = 1L;

    public Long getStationtypeId() {
        return stationtypeId;
    }

    public void setStationtypeId(Long stationtypeId) {
        this.stationtypeId = stationtypeId;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String getStationtypeName() {
        return stationtypeName;
    }

    public void setStationtypeName(String stationtypeName) {
        this.stationtypeName = stationtypeName == null ? null : stationtypeName.trim();
    }

    public String getStationtypeCode() {
        return stationtypeCode;
    }

    public void setStationtypeCode(String stationtypeCode) {
        this.stationtypeCode = stationtypeCode == null ? null : stationtypeCode.trim();
    }

    public Short getStationtypeState() {
        return stationtypeState;
    }

    public void setStationtypeState(Short stationtypeState) {
        this.stationtypeState = stationtypeState;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}