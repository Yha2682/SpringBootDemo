package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.mapper.CategoryMapper;
import com.example.springbootdemo.pojo.Category;
import com.example.springbootdemo.service.CategoryService;
import com.example.springbootdemo.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServerImpl implements CategoryService {

    @Autowired
    private  CategoryMapper categoryMapper;

    //新建文章
    @Override
    public void add(Category category) {
        //获取数据
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);
        categoryMapper.add(category);
    }

    //查询文章
    @Override
    public List<Category> getCategoryList() {

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        return categoryMapper.getCategoryList(id);
    }

    //获取文章详细
    @Override
    public Category findById(Integer id) {
        Category data = categoryMapper.findById(id);
        return data;
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updata(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }


}
