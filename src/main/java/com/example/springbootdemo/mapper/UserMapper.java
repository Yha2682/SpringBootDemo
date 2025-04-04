package com.example.springbootdemo.mapper;

import com.example.springbootdemo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    //根据用户名查询用户
    @Select("SELECT * from user where username=#{username}")
    User findByUserName(String username);


    //添加用户
    @Insert("insert into user(username,password,create_time,updata_time)" +
            "values (#{username},#{password},now(),now())")
    void add(String username, String password);
}
