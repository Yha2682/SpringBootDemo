package com.example.springbootdemo.mapper;


import com.example.springbootdemo.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    //新增
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)" +
            "VALUES(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    //查询文章
    @Select("SELECT * FROM category where create_user=#{id}")
    List<Category> getCategoryList(Integer id);

    //查询文章详细
    @Select("SELECT * FROM category where id=#{id}")
    Category findById(Integer id);
}
