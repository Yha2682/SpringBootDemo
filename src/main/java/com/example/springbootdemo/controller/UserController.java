package com.example.springbootdemo.controller;


import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.pojo.User;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.utils.JwtUtil;
import com.example.springbootdemo.utils.Md5Util;
import com.example.springbootdemo.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("register")
    public Result<String> register (@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password) {
        //参数校验 (@Pattern(regexp = "^\\s{5,16}$") 正则表达式 获取5-16的非空字符
        //查询用户
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

    //用户登录 post请求
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")  String password) {
        //查找用户名
        User loginUser = userService.findByUserName(username);
        //判断是否存在
        if (loginUser == null) {
            return Result.error("用户名错误!");
        }
        //判断密码是否正确，其中密码是login对象的passwd密文
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {

            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            return Result.success(token);
        }
        return Result.error("密码错误！");
    }

    //根据用户名查询用户 request请求
    @RequestMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name="Authorization") String token*/){
        /*Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String,Object> map =ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    //用户信息更新 put请求
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){

        userService.update(user);
        System.out.println("收到更新请求：" + user);
        return Result.success();

    }

}
