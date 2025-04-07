package com.example.springbootdemo.controller;


import com.example.springbootdemo.pojo.Article;
import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.service.ArticleServer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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

    @Autowired
    private ArticleServer articleServer;
    @PostMapping
    public Result addArticle(@RequestBody @Validated Article article) {
        articleServer.add(article);
        return Result.success();
    }

}