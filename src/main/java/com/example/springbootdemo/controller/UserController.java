package com.example.springbootdemo.controller;


import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.pojo.User;
import com.example.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public Result register (String username,String password) {
        User user = userService.findByUserName(username);
        if (user == null) {
            //用户名没有被占用，注册
            userService.register(username,password);
            return Result.success();
        }else {
            //被占用了，注册失败
            return Result.error("注册失败，用户名被占用!");
        }
    }
}
