package com.ai.umauthor.server.model;

import java.io.Serializable;
import java.util.Date;

public class UmOperator implements Serializable {
    private Long operatorId;

    private Long domainId;

    private String operatorCode;

    private String operatorName;

    private String operatorPsw;

    private String orgId;

    private Short operatorState;

    private Date changePswDate;

    private Date createDate;

    private String remarks;

    private String certNo;

    private Short sexCode;

    private Date birthday;

    private String email;

    private String telNo;

    private String addressDetail;

    private String positionCode;

    private String addFrom;

    private String operatorType;

    private String operatorLevel;

    private static final long serialVersionUID = 1L;

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Long getDomainId() {
        return domainId;
    }

    public void setDomainId(Long domainId) {
        this.domainId = domainId;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode == null ? null : operatorCode.trim();
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getOperatorPsw() {
        return operatorPsw;
    }

    public void setOperatorPsw(String operatorPsw) {
        this.operatorPsw = operatorPsw == null ? null : operatorPsw.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Short getOperatorState() {
        return operatorState;
    }

    public void setOperatorState(Short operatorState) {
        this.operatorState = operatorState;
    }

    public Date getChangePswDate() {
        return changePswDate;
    }

    public void setChangePswDate(Date changePswDate) {
        this.changePswDate = changePswDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public Short getSexCode() {
        return sexCode;
    }

    public void setSexCode(Short sexCode) {
        this.sexCode = sexCode;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo == null ? null : telNo.trim();
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode == null ? null : positionCode.trim();
    }

    public String getAddFrom() {
        return addFrom;
    }

    public void setAddFrom(String addFrom) {
        this.addFrom = addFrom == null ? null : addFrom.trim();
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType == null ? null : operatorType.trim();
    }

    public String getOperatorLevel() {
        return operatorLevel;
    }

    public void setOperatorLevel(String operatorLevel) {
        this.operatorLevel = operatorLevel == null ? null : operatorLevel.trim();
    }
}