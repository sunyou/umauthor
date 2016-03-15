package com.ai.umauthor.server.service.mrg.model;

import com.ai.umauthor.server.model.UmStationtype;

/**
 * 对UmStationtype的二次构造类
 * @author fanlx
 */
public class QryUmStationtype extends UmStationtype{

	private String domainName;
  
	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}	
	
}
