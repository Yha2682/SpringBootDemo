package com.example.springbootdemo.controller;


import com.example.springbootdemo.pojo.Article;
import com.example.springbootdemo.pojo.PageBean;
import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.service.ArticleService;
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
        // 直接获取在拦截器中保存的用户信息
        Map<String, Object> claims = (Map<String, Object>) request.getAttribute("claims");
        String username = (String) claims.get("username");
        return Result.success("您好:"+username+"，这里是文章列表！");
    }
    //增加文章数据
    @Autowired
    private ArticleService articleService;
    @PostMapping
    public Result addArticle(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    //根据条件查询文章，带分页
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state) {
        PageBean<Article> pb = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);

    }
    //获取文章详细
    @GetMapping("/detail")
    public Result<Article> detail(Integer id) {
        Article article =  articleService.findById(id);
        return Result.success(article);
    }
    //更新文章分类
    @PutMapping
    public Result update(@RequestBody @Validated Article article) {
        articleService.update(article);
        return Result.success();
    }
    //删除文章
    @DeleteMapping
    public Result deleteById(Integer id) {
        articleService.deleteById(id);
        return Result.success();
    }
}