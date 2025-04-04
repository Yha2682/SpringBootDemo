package com.example.springbootdemo.exception;

import com.example.springbootdemo.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//返回 JSON
public class GloBalExceptionHandler {

    @ExceptionHandler(Exception.class)
    //捕获全局异常
    public Result handleException(Exception e) {
        //把完整的异常信息打印到控制台，方便排查问题。
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage():"操作失败！");
    }
}
