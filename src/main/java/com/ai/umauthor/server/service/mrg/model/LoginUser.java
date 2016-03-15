package com.ai.umauthor.server.service.mrg.model;

import com.ai.common.security.ILoginUser;
import com.ai.umauthor.server.model.UmOperator;

/**
 * 用户登录接口返回使用的过度类
 * 登录验证使用
 * @author Typhon Chens
 * 2016年1月20日
 */
public class LoginUser extends UmOperator implements ILoginUser {
    /* (non-Javadoc)
     * @see com.ai.common.security.ILoginUser#getLoginUserName()
     */
    @Override
    public String getLoginUserName() {
	return super.getOperatorCode();
    }

    /* (non-Javadoc)
     * @see com.ai.common.security.ILoginUser#getPassword()
     */
    @Override
    public String getPassword() {
	// TODO Auto-generated method stub
	return super.getOperatorPsw();
    }

    /* (non-Javadoc)
     * @see com.ai.common.security.ILoginUser#getIsValid()
     */
    @Override
    public int getIsValid() {
	
	return super.getOperatorState();
    }
}
