package com.ai.umauthor.web.shiro.load;

import java.util.List;

/**
 * 
 * Session数据加载接口
 * 
 * @author Typhon Chens
 * 2016年2月4日
 */
public interface SessionDataDao {

    /**
     * 根据用户id加载对应权限体系信息
     * @param userId
     */
    public void LoadAuthorityInfo(String userId);
    
    /**
     * 根据用户id加载对应角色实体
     * @param userId
     */
    public List<String> LoadRoleInfo(String userId);
    
    /**
     * 根据用户id加载对应权限实体
     * @param userId
     */
    public List<String> LoadPermissionsInfo(String userId);
}
