package com.ding.hddk.service;

import com.ding.hddk.domain.User;
import com.ding.hddk.domain.UserLogin;
import com.ding.hddk.elasticsearch.entity.BookDO;
import com.ding.hddk.service.util.AjaxResult;

public interface UserService {
    /**
     * 查询所有用户
     *
     * @return
     */
    AjaxResult getAllUser();

    /**
     * 登录
     *
     * @param userLogin
     * @return
     */
    AjaxResult login(UserLogin userLogin);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    AjaxResult add(User user);

    /**
     * 退出登录
     *
     * @return
     */
    AjaxResult logout();

    /**
     * ES获取所有书信息
     *
     * @return
     */
    AjaxResult getBooks();

    /**
     * 添加书籍
     *
     * @param bookDO
     * @return
     */
    AjaxResult addBook(BookDO bookDO);

    /**
     * 修改
     *
     * @param bookDO
     * @return
     */
    AjaxResult updateBook(BookDO bookDO);

    /**
     * 删除书籍
     *
     * @param id
     * @return
     */
    AjaxResult deleteBook(String id);
}
