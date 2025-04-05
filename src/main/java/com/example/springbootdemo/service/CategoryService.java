package com.example.springbootdemo.service;

import com.example.springbootdemo.pojo.Category;

import java.util.List;

public interface CategoryService {

    //新建文章
    void add(Category category);
    //查询文章
    List<Category> getCategoryList();
    //获取文章详细
    Category findById(Integer id);
}
