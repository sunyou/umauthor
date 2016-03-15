package com.ai.umauthor.web.shiro.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

import com.ai.common.security.PasswordEncoderSerivce;

/**
 * 重写shiro密码效验方法
 * 
 * @author Typhon Chens 2015年9月7日
 */
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     * 获取密码加密器
     */
    private PasswordEncoderSerivce passwordEncoder;

    public PasswordEncoderSerivce getPasswordEncoder() {
	return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoderSerivce passwordEncoder) {
	this.passwordEncoder = passwordEncoder;
    }

    /**
     * 重写获取令牌的凭据的实现方式
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken,
	    AuthenticationInfo info) {
	boolean matches;
	UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
	// info.getCredentials().
	Object accountCredentials = getCredentials(info);

	matches = passwordEncoder.match(new String(token.getPassword()),
		String.valueOf(accountCredentials));

	// passwordEncoder
	return matches;
    }

}
