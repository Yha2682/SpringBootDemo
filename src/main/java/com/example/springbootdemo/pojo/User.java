package com.example.springbootdemo.pojo;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;

//lombok  在编译阶段,为实体类自动生成setter  getter toString
// pom文件中引入依赖   在实体类上添加注解
@Data
public class User {
    private Integer id;//主键ID
    private String username;//用户名

    @JsonIgnore//让springmvc把当前对象转换成json字符串的时候，忽略password,最终的json字符串中就没有password这个属性了
                //但是不知道为什么没用
    private String password;//密码

    private String nickname;//昵称
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
