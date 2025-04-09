package com.example.springbootdemo.controller;

import com.example.springbootdemo.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping
    public Result<String> upload(MultipartFile file) throws Exception {
//        把文件内容存储到本地磁盘上
        String originalFilename = file.getOriginalFilename();
//        保证文件的名字是唯一的，从而防止文件覆盖
        String fileName = UUID.randomUUID().toString()+originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("E:\\Desktop\\files\\"+originalFilename));
        return Result.success("URL地址。。。。");
        //调用阿里云
//        String url = AliOssUtil.uploadFile(fileName,file.getInputStream());
//        return Result.success(url);
    }
}
