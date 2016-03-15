package com.ai.umauthor.web.shiro.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 重写shiro登录Token
 * 
 * @author Typhon Chens 2015年9月24日
 */
public class AgricultureToken extends UsernamePasswordToken {

    private String domain = "0";

    public AgricultureToken(String loginName, String password, String host,
	    String domain) {

	super(loginName, password, host);

	//

	this.domain = domain;

    }

    public String getDomain() {
	return domain;
    }

    public void setDomain(String domain) {
	this.domain = domain;
    }

}
