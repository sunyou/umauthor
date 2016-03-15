package com.ai.umauthor.server.service.mrg.model;

import java.math.BigDecimal;
import java.util.Date;

public class QryUmOperator2Station {

	private BigDecimal id;
	private BigDecimal operatorId;
	private String operatorName;
	private String orgId;
	private String orgName;
	private BigDecimal stationId;
	private String stationName;
	private String authorOperatorName;
	private Date authorDate;
	private BigDecimal allowReauthor;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(BigDecimal operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public BigDecimal getStationId() {
		return stationId;
	}
	public void setStationId(BigDecimal stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getAuthorOperatorName() {
		return authorOperatorName;
	}
	public void setAuthorOperatorName(String authorOperatorName) {
		this.authorOperatorName = authorOperatorName;
	}
	public Date getAuthorDate() {
		return authorDate;
	}
	public void setAuthorDate(Date authorDate) {
		this.authorDate = authorDate;
	}
	public BigDecimal getAllowReauthor() {
		return allowReauthor;
	}
	public void setAllowReauthor(BigDecimal allowReauthor) {
		this.allowReauthor = allowReauthor;
	}
}
