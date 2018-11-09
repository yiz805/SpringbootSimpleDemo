package com.ding.hddk.controller;

import com.ding.hddk.domain.User;
import com.ding.hddk.domain.UserLogin;
import com.ding.hddk.service.UserService;
import com.ding.hddk.service.quartz.QuartzManager;
import com.ding.hddk.service.util.AjaxResult;
import com.ding.hddk.service.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param userLogin
     * @return
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody UserLogin userLogin) {
        return userService.login(userLogin);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody User user) {
        return userService.add(user);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @PostMapping("/logout")
    public AjaxResult logout() {
        return userService.logout();
    }

    /**
     * 未登录
     *
     * @return
     */
    @GetMapping("/unlogin")
    public AjaxResult unlogin() {
        return AjaxResult.getError(ResultCode.MyException, "您尚未登录.", null);
    }

    /**
     * 未授权
     *
     * @return
     */
    @GetMapping("/noRole")
    public AjaxResult noRole() {
        return AjaxResult.getError(ResultCode.MyException, "您未被授权", null);
    }


}
