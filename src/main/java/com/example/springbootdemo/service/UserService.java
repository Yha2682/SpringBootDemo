package com.example.springbootdemo.service;

import com.example.springbootdemo.pojo.User;

public interface UserService {
    //获取用户名
    User findByUserName(String username);


    //注册
    void register(String username, String password);
}
