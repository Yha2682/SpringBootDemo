package com.example.springbootdemo.service;

import com.example.springbootdemo.pojo.Article;
import com.example.springbootdemo.pojo.PageBean;

public interface ArticleService {
    //增加文章内容
    void add(Article article);
    //条件分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
    //获取文章详细
    Article findById(Integer id);
    //更新文章
    void update(Article article);
    //删除文章
    void deleteById(Integer id);


}
