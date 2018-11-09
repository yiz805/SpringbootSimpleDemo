package com.ding.hddk.authen.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ShiroUserUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroUserUtil.class);

    /**
     * 重新构建一个对象,将需要存储的信息放入info,
     * (使用SimpleprincipalMap验证,授权使用的是SimplePrincipalCollection,不可用)
     *
     * @param password
     * @param username
     * @param userId
     * @return
     */
    public static SimpleAuthenticationInfo createSimpleAuthenticationInfo(char[] password, String username, int userId) {
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo();
        simpleAuthenticationInfo.setCredentials(password);
        SimplePrincipalMap principalCollection = new SimplePrincipalMap();
        principalCollection.put("username", username);
        principalCollection.put("userId", userId);
        simpleAuthenticationInfo.setPrincipals(principalCollection);
        return simpleAuthenticationInfo;
    }

    /**
     * 直接获取当前登录的用户id
     *
     * @return
     */
    public static int getCurrentUserId() {
        SimplePrincipalMap principalCollection = null;
        principalCollection = (SimplePrincipalMap) SecurityUtils.getSubject().getPrincipals();
        int userId = 0;
        if (principalCollection != null) {
            for (Map.Entry<String, Object> entry : principalCollection.entrySet()) {
                if (entry.getKey().equals("userId")) {
                    userId = (int) entry.getValue();
                }
            }
        }
        LOGGER.info("本次登录用户id:{}", userId);
        return userId;
    }

    /**
     * 获取当前用户的用户名,
     * 可通过用户名查找当前用户的userId
     *
     * @return
     */
    public static String getCurrentUserName() {
        SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) SecurityUtils.getSubject().getPrincipals();
        String currentUserName = principalCollection.toString();
        LOGGER.info("当前登录用户名:" + currentUserName);
        return currentUserName;
    }
}
