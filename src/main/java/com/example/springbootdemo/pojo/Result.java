package com.example.springbootdemo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//NoArgs 注入无参构造方法
@NoArgsConstructor
//AllArgs全参构造方法
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;//业务状态码 0-成功 1-失败
    private String message;//提示信息
    private T data;//相应数据

    //快速返回操作成功的响应结果，并且带上响应数据（用户注册，成功会返回data数据）
    public static <T> Result<T> success(T data) {
        //	调用 Lombok 自动生成的构造函数
        return new Result<>(0,"操作成功",data);
    }

    //快速返回操作成功响应结果，（删除用户，删除成功或者失败，没有data数据）
    public static Result success() {
        return new Result(0,"操作成功",null);
    }

    public static Result error(String message) {
        return new Result(1,message,null);
    }

}
