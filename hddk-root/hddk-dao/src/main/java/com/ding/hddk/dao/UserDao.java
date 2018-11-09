package com.ding.hddk.dao;

import com.ding.hddk.base.BaseMapper;
import com.ding.hddk.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends BaseMapper<User> {
    /**
     * 通过用户名获取用户id
     *
     * @param userName
     * @return
     */
    @Select("select user_id from t_user where user_name=#{userName}")
    int getUserIdByName(String userName);

    /**
     * 通过用户id查询密码
     *
     * @param userId
     * @return
     */
    @Select("select password from t_user where user_id=#{userId}")
    String getPasswordById(Integer userId);

    /**
     * 通过username查询用户角色
     *
     * @param userName
     * @return
     */
    @Select("select role from t_role where user_id=(select user_id from t_user where user_name=#{userName})")
    String getRoleByName(String userName);
}
