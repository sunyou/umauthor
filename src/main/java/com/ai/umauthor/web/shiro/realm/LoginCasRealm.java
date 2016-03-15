package com.ai.umauthor.web.shiro.realm;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cas.CasAuthenticationException;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.validation.Assertion;
import org.jasig.cas.client.validation.TicketValidationException;
import org.jasig.cas.client.validation.TicketValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ai.umauthor.web.shiro.load.SessionDataDao;

/**
 * @author Typhon Chens 2016年1月25日
 */
public class LoginCasRealm extends CasRealm {

    private static Logger log = LoggerFactory.getLogger(CasRealm.class);

   /* @Autowired
    private CacheManager cacheManager;*/
    
    private SessionDataDao sessionDataDao;


    public SessionDataDao getSessionDataDao() {
        return sessionDataDao;
    }

    public void setSessionDataDao(SessionDataDao sessionDataDao) {
        this.sessionDataDao = sessionDataDao;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
	    PrincipalCollection principals) {
	SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) principals;
	 String username = (String)principals.getPrimaryPrincipal();

	SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	List<String> roles=null;
	List<String> permissions=null;
	
	//principals.get
	if(sessionDataDao!=null){
	    roles=sessionDataDao.LoadRoleInfo(username);
	    permissions=sessionDataDao.LoadPermissionsInfo(username);
	}
	
	info.addRoles(roles);
        
        info.addStringPermissions(permissions);
	// 这里可以加载缓存的中的数据到认证实体...

	// addRoles(simpleAuthorizationInfo, split(getDefaultRoles()));

	return info;

    }

    /**
     * 扩展CAS桥接器,订制角色体系和资源体系
     */
    @Override
    @SuppressWarnings("unchecked")
    protected AuthenticationInfo doGetAuthenticationInfo(
	    AuthenticationToken token) throws AuthenticationException {
	CasToken casToken = (CasToken) token;
	if (token == null) {
	    return null;
	}

	String ticket = (String) casToken.getCredentials();
	if (!StringUtils.hasText(ticket)) {
	    return null;
	}

	TicketValidator ticketValidator = ensureTicketValidator();

	try {
	    // contact CAS server to validate service ticket
	    Assertion casAssertion = ticketValidator.validate(ticket,
		    getCasService());
	    // get principal, user id and attributes
	    AttributePrincipal casPrincipal = casAssertion.getPrincipal();
	    String userId = casPrincipal.getName();
	    log.debug(
		    "Validate ticket : {} in CAS server : {} to retrieve user : {}",
		    new Object[] { ticket, getCasServerUrlPrefix(), userId });

	    Map<String, Object> attributes = casPrincipal.getAttributes();
	    // refresh authentication token (user id + remember me)
	    casToken.setUserId(userId);
	    String rememberMeAttributeName = getRememberMeAttributeName();
	    String rememberMeStringValue = (String) attributes
		    .get(rememberMeAttributeName);
	    boolean isRemembered = rememberMeStringValue != null
		    && Boolean.parseBoolean(rememberMeStringValue);
	    if (isRemembered) {
		casToken.setRememberMe(true);
	    }
	    // create simple authentication info
	    List<Object> principals = CollectionUtils
		    .asList(userId, attributes);
	    PrincipalCollection principalCollection = new SimplePrincipalCollection(
		    principals, getName());
	    // 获取Cas的登录账号信息,加载到对应权限体系信息
	    if(sessionDataDao!=null){
		sessionDataDao.LoadAuthorityInfo(userId);
	    }
	    return new SimpleAuthenticationInfo(principalCollection, ticket);
	} catch (TicketValidationException e) {
	    throw new CasAuthenticationException("Unable to validate ticket ["
		    + ticket + "]", e);
	}
    }

   
}
