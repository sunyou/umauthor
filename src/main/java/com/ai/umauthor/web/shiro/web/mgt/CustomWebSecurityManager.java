package com.ai.umauthor.web.shiro.web.mgt;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cas.CasToken;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

/**
 * @author Typhon Chens
 * 2016年2月4日
 */
public class CustomWebSecurityManager extends DefaultWebSecurityManager{
    public Realm casRealm;
    
    public Realm defaultRealm;
    
    
    public Realm getCasRealm() {
        return casRealm;
    }

    public void setCasRealm(Realm casRealm) {
        this.casRealm = casRealm;
    }

    public Realm getDefaultRealm() {
        return defaultRealm;
    }

    public void setDefaultRealm(Realm defaultRealm) {
        this.defaultRealm = defaultRealm;
    }

    public SubjectFactory getCasSubjectFactory() {
        return casSubjectFactory;
    }

    public void setCasSubjectFactory(SubjectFactory casSubjectFactory) {
        this.casSubjectFactory = casSubjectFactory;
    }

    public SubjectFactory getDefaultSubjectFactory() {
        return defaultSubjectFactory;
    }

    public void setDefaultSubjectFactory(SubjectFactory defaultSubjectFactory) {
        this.defaultSubjectFactory = defaultSubjectFactory;
    }

    public SubjectFactory casSubjectFactory;
    
    public SubjectFactory defaultSubjectFactory;

    @Override
    public Subject login(Subject subject, AuthenticationToken token)
	    throws AuthenticationException {
	if (token != null && token instanceof CasToken) {
	    setSubjectFactory(casSubjectFactory);
	    setRealm(casRealm);
	}
	
	return super.login(subject, token);
    }

    @Override
    public void logout(Subject subject) {
	// TODO Auto-generated method stub
	super.logout(subject);
    }
    
    
}
