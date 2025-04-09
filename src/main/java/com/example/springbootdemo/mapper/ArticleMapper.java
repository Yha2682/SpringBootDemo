package com.example.springbootdemo.mapper;


import com.example.springbootdemo.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ArticleMapper {
    //新增文章
    @Insert("INSERT INTO article (title,content,cover_img,state,category_id,create_user,create_time,update_time) " +
            "values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    //分页文章内容查询
    List<Article> list(Integer userId, Integer categoryId, String state);

    //获取文章详情
    @Select("SELECT * FROM article where id =#{id}")
    Article findByid(Integer id);

    //更新文章
    @Update("UPDATE article SET title=#{title},content = #{content},cover_img = #{coverImg},state = #{state}," +
            "category_id = #{categoryId},update_time = #{updateTime} WHERE id =#{id} ")
    void update(Article article);
    //删除文章
    @Delete("DELETE FROM article WHERE id=#{id}")
    void deleteById(Integer id);
}
