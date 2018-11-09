package com.ding.hddk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * 用户登录时存用户名和密码
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
