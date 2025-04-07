package com.example.springbootdemo.anno;


import com.example.springbootdemo.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

//元注解，使注解可以抽取到帮助文档里面
@Documented
@Constraint(
        validatedBy = {StateValidation.class}//指定给注解提供校验规则的类
)
//元注解，表示注解可以用在什么地方
@Target(ElementType.FIELD)//表示能用在属性上
//表示注解能保留到什么时间段
@Retention(RetentionPolicy.RUNTIME)//保留到运行时间段
public @interface State {

    //提供校验失败后的提示信息
    String message() default "{State参数不正确，只能是已发布或者是草稿！！}";
    //指定分组
    Class<?>[] groups() default {};
    //负载 能获取到State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
