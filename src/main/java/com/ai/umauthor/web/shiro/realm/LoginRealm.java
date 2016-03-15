package com.ai.umauthor.web.shiro.realm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.common.security.ILoginUser;
import com.ai.common.util.WebUtils;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.model.UmStationtype;
import com.ai.umauthor.server.service.mrg.interfaces.LoginUserSV;
import com.ai.umauthor.server.service.mrg.model.SessionUser;
import com.ai.umauthor.web.shiro.authc.AgricultureToken;



@Service
@Transactional
public class LoginRealm extends AuthorizingRealm {
	private static final Logger log = Logger.getLogger(LoginRealm.class);

    /*
     * @Inject private UserService userService;
     */

 /*  
    private UserDetailsService loginUserSV;
    */
	public boolean isDebug=true;
	
	@Autowired
	private LoginUserSV loginUserSV;
	

    @Autowired
    private CredentialsMatcher credentialsMatcher;
    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
	    PrincipalCollection principals) {

      //  LoginUserInfo loginUserInfo= WebUtils.getSessionUserInfo();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<String> stations = new ArrayList<String>();  
        List<String> permissions = new ArrayList<String>();
        SessionUser<UmOperator,UmStationtype,UmMenu,Object> sessionUserInfo=WebUtils.getSessionUserInfo();
        //=new SessionUser<UmOperator,UmStationtype,UmMenu,UmPermission>();
        List<UmMenu> menuList= sessionUserInfo.getMenus();
        
        for (Iterator iterator = menuList.iterator(); iterator.hasNext();) {
	    UmMenu umMenu = (UmMenu) iterator.next();
	    permissions.add(umMenu.getMenuCode());
	}
        if(isDebug){
            permissions.add("permission-demo-1");
        }
        //给当前用户设置角色
        info.addRoles(stations);
        
        info.addStringPermissions(permissions);
        //给当前用户设置权限
   /*     info.addStringPermission("PlantBaseList:Detail");
        info.addStringPermission("FarmerList:Detail");*/
        return info;
	
    }
    
    /**
	 * 设定Password校验.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
	    //该句作用是重写shiro的密码验证，让shiro用我自己的验证
	    setCredentialsMatcher(credentialsMatcher);
	}


    /**
     * 登录认证;
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
	    AuthenticationToken authenticationToken)
	    throws AuthenticationException {
	
	// UsernamePasswordToken对象用来存放提交的登录信息
	AgricultureToken token = (AgricultureToken) authenticationToken;

	String username=token.getUsername();
	
	ILoginUser loginUser=loginUserSV.queryUserInfoByLoginCode(username);
	// 查出是否有此用户
	
	//token
	
	if (loginUser != null) {
	   
	    if(loginUser.getIsValid()==0){
		 throw new LockedAccountException();
	    }
	    
	  AuthenticationInfo authcInfo =new SimpleAuthenticationInfo(loginUser.getLoginUserName(),
		  loginUser.getPassword(), getName());  

	//    this.setSession("currentUser", userinfo);  
	   SessionUser<UmOperator,UmStation,UmMenu,Object> sessionUserInfo=new SessionUser<UmOperator,UmStation,UmMenu,Object>();
	   sessionUserInfo.setLoginUser((UmOperator)loginUser);
	   WebUtils.setSessionUserInfo(sessionUserInfo);
	    
	    ///System.out.println(WebUtils.getSessionUserInfo().);
	    // 若存在，将此用户存放到登录认证info中
	    return authcInfo;
	}
	return null;
    }

}
