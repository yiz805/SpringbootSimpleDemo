package com.ding.hddk.authen;

import com.ding.hddk.dao.UserDao;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HddkShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = authenticationToken.getPrincipal().toString();//获取身份
        int userId = userDao.getUserIdByName(username);//获取身份对应的id
        String dbpassword = userDao.getPasswordById(userId);//获取数据库中的密码
        //进行认证，将正确数据给shiro处理
        //密码不用自己比对，AuthenticationInfo认证信息对象，一个接口，new他的实现类对象SimpleAuthenticationInfo
        /*	第一个参数随便放，可以放user对象，程序可在任意位置获取 放入的对象
         * 第二个参数必须放密码，
         * 第三个参数放 当前realm的名字，因为可能有多个realm
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, dbpassword, this.getName());
        //返回给安全管理器，securityManager，由securityManager比对数据库查询出的密码和页面提交的密码
        //如果有问题，向上抛异常，一直抛到控制器
        return info;
    }

    /**
     * 授权(先认证后授权)
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        String role = userDao.getRoleByName(username);//获取用户角色
        Set<String> set = new HashSet<>();
        set.add(role);//将role封装到set
        info.setRoles(set);//设置该用户拥有的角色
        return info;
    }

}
