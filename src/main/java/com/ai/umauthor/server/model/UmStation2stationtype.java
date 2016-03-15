package com.ai.umauthor.server.model;

import java.io.Serializable;

public class UmStation2stationtype implements Serializable {
    private Long relationId;

    private Long stationId;

    private Long stationtypeId;

    private String remarks;

    private static final long serialVersionUID = 1L;

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public Long getStationtypeId() {
        return stationtypeId;
    }

    public void setStationtypeId(Long stationtypeId) {
        this.stationtypeId = stationtypeId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}