package com.ding.hddk.config;

import com.ding.hddk.authen.HddkShiroRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class HddkShiroConfiguration {
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/unlogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noRole");
        // 拦截器.
//        Map<String, Filter> filters = new LinkedHashMap<>();
//        filters.put("userhddk", hddkUserShiroFilter);
//        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> definitionMap = new LinkedHashMap<>();
        // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
        //设置URL权限
        definitionMap.put("/user/**", "roles[admin]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(definitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 注入SecurityManager和自定义Realm
     *
     * @param hddkShiroRealm
     * @return
     */
    @Bean
    public SecurityManager securityManager(HddkShiroRealm hddkShiroRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(hddkShiroRealm);
        return securityManager;
    }
}
