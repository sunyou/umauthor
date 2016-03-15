package com.ai.umauthor.server.model;

import java.io.Serializable;
import java.util.Date;

public class UmOperator2station implements Serializable {
    private Long id;

    private Long operatorId;

    private String orgId;

    private Long stationId;

    private String remarks;

    private Long authorOperatorId;

    private Date authorDate;

    private Short allowReauthor;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Long getAuthorOperatorId() {
        return authorOperatorId;
    }

    public void setAuthorOperatorId(Long authorOperatorId) {
        this.authorOperatorId = authorOperatorId;
    }

    public Date getAuthorDate() {
        return authorDate;
    }

    public void setAuthorDate(Date authorDate) {
        this.authorDate = authorDate;
    }

    public Short getAllowReauthor() {
        return allowReauthor;
    }

    public void setAllowReauthor(Short allowReauthor) {
        this.allowReauthor = allowReauthor;
    }
}