package com.example.springbootdemo.service;

import com.example.springbootdemo.pojo.User;

public interface UserService {
    //获取用户名
    User findByUserName(String username);


    //注册
    void register(String username, String password);

    //更新
    void update(User user);

    //更新头像
    void updateAvatar(String avatar);

    //更新密码
    void updatePwd(String newPwd);
}
