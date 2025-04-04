package com.example.springbootdemo.controller;


import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result list(@RequestParam(name = "Authorization") String token, HttpServletResponse response) {

        //验证token
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            return Result.success("好多数据！");

        } catch (Exception e) {
            //Http响应401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return Result.error("未登录！");
        }
    }
}
