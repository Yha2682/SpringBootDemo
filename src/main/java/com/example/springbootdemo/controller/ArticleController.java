package com.example.springbootdemo.controller;


import com.example.springbootdemo.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping("/list")
    public Result<String> list(HttpServletRequest request) {
//        // 直接获取在拦截器中保存的用户信息
        Map<String, Object> claims = (Map<String, Object>) request.getAttribute("claims");
        String username = (String) claims.get("username");
        return Result.success("您好:"+username+"，这里是文章列表！");
    }
}