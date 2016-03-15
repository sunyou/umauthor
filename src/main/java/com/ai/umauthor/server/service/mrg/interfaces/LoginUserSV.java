package com.ai.umauthor.server.service.mrg.interfaces;

import com.ai.common.security.ILoginUser;

/**
 * @author Typhon Chens
 * 2016年1月20日
 */
public interface LoginUserSV {
    /**
     * 用户登录标准化接口
     * @param userCode 用户编码
     * @return
     * @throws Exception
     */
    public ILoginUser queryUserInfoByLoginCode(String userCode);
}
