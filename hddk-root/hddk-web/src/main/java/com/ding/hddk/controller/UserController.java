package com.ding.hddk.controller;

import com.ding.hddk.domain.User;
import com.ding.hddk.elasticsearch.entity.BookDO;
import com.ding.hddk.service.UserService;
import com.ding.hddk.service.util.AjaxResult;
import com.ding.hddk.service.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取所有的用户
     *
     * @return
     */
    @GetMapping("/getAllUsers")
    public AjaxResult getAllUser() {
        return userService.getAllUser();
    }

    /**
     * 获取书籍
     *
     * @return
     */
    @GetMapping("/getbooks")
    public AjaxResult getBooks() {
        return userService.getBooks();
    }

    @PostMapping("/addbook")
    public AjaxResult addbook(@RequestBody BookDO bookDO) {
        return userService.addBook(bookDO);
    }

    @PostMapping("/updatebook")
    public AjaxResult updatebook(@RequestBody BookDO bookDO) {
        return userService.updateBook(bookDO);
    }

    @DeleteMapping("/deletebook")
    public AjaxResult deletebook(String id) {
        return userService.deleteBook(id);
    }
}
