package com.ding.hddk.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
public class User {
    @Id
    private Integer userId;

    private String userName;

    private String password;

    private Integer credits;

    private String lastIp;

    private String lastVisit;

}
