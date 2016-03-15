package com.ai.umauthor.server.service.mrg.model;

import com.ai.umauthor.server.model.UmStation;

public class QryUmStation extends UmStation{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupName;
	private String operatorCode;
	private Long operatorId;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	
	
}
