package com.ding.hddk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 登录日志实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_login_log")
public class LoginLog {
    @Id
    private Integer loginLogId;

    private Integer userId;

    private String ip;

    private Date logDate;

}
