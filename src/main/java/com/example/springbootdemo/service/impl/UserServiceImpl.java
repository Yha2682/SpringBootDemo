package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.mapper.UserMapper;
import com.example.springbootdemo.pojo.User;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByUserName(String username) {
        User name = userMapper.findByUserName(username);
        return name;
    }

    @Override
    public void register(String username, String password) {
        //密码加密 调用Md5Util

        String md5Password = Md5Util.getMD5String(password);

        //添加用户
        userMapper.add(username,md5Password);
    }

    //更新
    @Override
    public void update(User user) {

        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }
}
