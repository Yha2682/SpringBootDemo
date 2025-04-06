package com.example.springbootdemo.controller;


import com.example.springbootdemo.pojo.Category;
import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.service.CategoryService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //新建文章
    @PostMapping
    public Result addCategory(@RequestBody @Validated(Category.Add.class) Category category) {
        //获取数据
        categoryService.add(category);
        return Result.success();
    }
    //查询所有文章
    @GetMapping
    public Result<List<Category>> getCategoryList() {
        List<Category> data = categoryService.getCategoryList();
        return Result.success(data);
    }
    //查询文章详细
    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        Category date = categoryService.findById(id);
        return Result.success(date);
    }
    //更新文章分类 put：全部都要更新（文章内容全部都能修改）
    @PutMapping
    public Result updateCategory(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }
    //删除文章分类
    @DeleteMapping
    public Result<Category> deleteCategory(Integer id) {
        categoryService.deleteById(id);
        return Result.success();
    }
}
