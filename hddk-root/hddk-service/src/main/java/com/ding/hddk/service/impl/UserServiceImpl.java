package com.ding.hddk.service.impl;

import com.ding.hddk.authen.util.CodeUtil;
import com.ding.hddk.authen.util.ShiroUserUtil;
import com.ding.hddk.dao.UserDao;
import com.ding.hddk.domain.User;
import com.ding.hddk.domain.UserLogin;
import com.ding.hddk.elasticsearch.BookES;
import com.ding.hddk.elasticsearch.entity.BookDO;
import com.ding.hddk.service.UserService;
import com.ding.hddk.service.job.HelloJob;
import com.ding.hddk.service.quartz.QuartzManager;
import com.ding.hddk.service.util.AjaxResult;
import com.ding.hddk.service.util.ResultCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookES bookES;
    @Autowired
    private Scheduler scheduler;

    @Override
    public AjaxResult getAllUser() {
        String username = ShiroUserUtil.getCurrentUserName();
        int userId = userDao.getUserIdByName(username);
        QuartzManager.addJob(scheduler,HelloJob.class,);
        return AjaxResult.getOK(userDao.selectAll());
    }

    @Override
    public AjaxResult login(UserLogin userLogin) {
        LOGGER.info("登录:{}", userLogin.getUserName());
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(userLogin.getUserName());
        //MD5加密
        token.setPassword(CodeUtil.md5Encode(userLogin.getPassword()).toCharArray());
        try {
            //此步将 调用realm的认证方法
            SecurityUtils.getSubject().login(token);
        } catch (Exception e) {
            return AjaxResult.getError(ResultCode.ParamException, "登录失败.", null);
        }
        return AjaxResult.getOK();
    }

    @Override
    public AjaxResult add(User user) {
        //将用户的密码MD5加密并set
        user.setPassword(CodeUtil.md5Encode(user.getPassword()));
        userDao.insert(user);
        return AjaxResult.getOK();
    }

    @Override
    public AjaxResult logout() {
        LOGGER.info("退出登录");
        SecurityUtils.getSubject().logout();
        return AjaxResult.getOK();
    }

    @Override
    public AjaxResult getBooks() {
        return AjaxResult.getOK(bookES.getBooks());
    }

    @Override
    public AjaxResult addBook(BookDO bookDO) {
        bookES.addBook(bookDO);
        return AjaxResult.getOK();
    }

    @Override
    public AjaxResult updateBook(BookDO bookDO) {
        bookES.updateBook(bookDO);
        return AjaxResult.getOK();
    }

    @Override
    public AjaxResult deleteBook(String id) {
        bookES.deleteBook(id);
        return AjaxResult.getOK();
    }
}
