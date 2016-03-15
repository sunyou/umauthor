package com.ai.umauthor.web.shiro.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * 
 * 重写shiro的Session操作类
 * 
 * @author Typhon Chens 2015年9月9日
 */
public class CustomSessionDAO extends AbstractSessionDAO {

    private final static Logger logger = Logger
	    .getLogger(CustomSessionDAO.class);
    @Autowired
 //   private CacheSvc cacheSvc ;///= AiPassInstances.getCacheSvInstance();

    public void update(Session session) throws UnknownSessionException {
	logger.debug("update-----------------------------------<"+session.getId());  
	this.saveSession(session);

    }

    /**
     * 删除会话；当会话过期/会话停止（如用户退出时）会调用
     * 
     * @see
     * org.apache.shiro.session.mgt.eis.SessionDAO#delete(org.apache.shiro.session
     * .Session)
     */
    
    public void delete(Session session) {
	if (session == null || session.getId() == null) {
	    logger.error("session or session id is null");
	    return;
	}
	logger.debug("delete------------------------->"+session.getId());  

	/*cacheSvc.delItem(Constants.CACHESESSIONDATA
		+ session.getId());*/

    }

    /**
     * 获取当前所有活跃用户，如果用户量多此方法影响性能
     * 
     * @see org.apache.shiro.session.mgt.eis.SessionDAO#getActiveSessions()
     */
    
    public Collection<Session> getActiveSessions() {
	Set<Session> sessions = new HashSet<Session>();

	/*Set<String> keys = cacheSvc.getSet(Constants.CACHESESSIONDATA + "*");

	if (keys != null && keys.size() > 0) {
	    for (String key : keys) {

		Session s = (Session) cacheSvc.getItem(key);
		sessions.add(s);
	    }
	}*/
	return sessions;
    }

    /**
     * 如DefaultSessionManager在创建完session后会调用该方法；
     * 如保存到关系数据库/文件系统/NoSQL数据库；
     * 即可以实现会话的持久化；返回会话ID；
     * 主要此处返回的ID.equals(session.getId())；
     * 
     * @see
     * org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doCreate(org.apache
     * .shiro.session.Session)
     */
    
    protected Serializable doCreate(Session session) {
	Serializable sessionId = this.generateSessionId(session);
	logger.debug("doCreate------------------------->"+sessionId);  

	this.assignSessionId(session, sessionId);
	this.saveSession(session);
	///this.create(session);
	return sessionId;

    }

    private void saveSession(Session session)
	    throws UnknownSessionException {
	if (session == null || session.getId() == null) {
	    logger.error("session or session id is null");
	    return;
	}
	// session.setTimeout(maxIdleTimeInMillis);
//	logger.debug("saveSession------------------------->"+Constants.CACHESESSIONDATA + session.getId());  
	//
	logger.info("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  

	// (int)session.getTimeout()
	//cacheSvc.addItem(Constants.CACHESESSIONDATA + session.getId(), session,(int)session.getTimeout() );
    }

    /**
     * 
     * 根据会话ID获取会话
     * @see
     * org.apache.shiro.session.mgt.eis.AbstractSessionDAO#doReadSession(java
     * .io.Serializable)
     */
    
    protected Session doReadSession(Serializable sessionId) {
	if (sessionId == null) {
	    logger.error("session id is null");
	    return null;
	}
	
	//Session session = (Session) cacheSvc.getItem(Constants.CACHESESSIONDATA + sessionId);
	logger.debug("doReadSession<-----------------------------------"+sessionId);  
	return null;
	//return super.doReadSession(sessionId);

    }

}