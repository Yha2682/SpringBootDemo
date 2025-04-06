package com.example.springbootdemo.controller;


import com.example.springbootdemo.pojo.Result;
import com.example.springbootdemo.pojo.User;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.utils.JwtUtil;
import com.example.springbootdemo.utils.Md5Util;
import com.example.springbootdemo.utils.ThreadLocalUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static io.micrometer.common.util.StringUtils.*;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("register")
    public Result<String> register (@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password) {
        //参数校验 (@Pattern(regexp = "^\\s{5,16}$") 正则表达式 获取5-16的非空字符
        //查询用户
        User user = userService.findByUserName(username);
        if (user == null) {
            //用户名没有被占用，注册
            userService.register(username,password);
            return Result.success();
        }else {
            //被占用了，注册失败
            return Result.error("注册失败，用户名被占用!");
        }
    }

    //用户登录 post请求
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")  String password) {
        //查找用户名
        User loginUser = userService.findByUserName(username);
        //判断是否存在
        if (loginUser == null) {
            return Result.error("用户名错误!");
        }
        //判断密码是否正确，其中密码是login对象的passwd密文
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {

            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);

            return Result.success(token);
        }
        return Result.error("密码错误！");
    }

    //根据用户名查询用户 request请求
    @RequestMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name="Authorization") String token*/){
        /*Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String,Object> map =ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    //用户信息更新 put请求
    @PutMapping("/update")
    public Result update(@RequestBody/*MVC框架自动读取请求主体的json格式数据*/ @Validated User user){

        userService.update(user);
        System.out.println("收到更新请求：" + user);
        return Result.success();

    }

    //用户头像更新
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL/*@URL检查url是否合法*/ String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    //用户更新密码
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //校验参数
        String oldPwd = params.get("oldPwd");
        String newPwd = params.get("newPwd");
        String rePwd = params.get("rePwd");

        //不为空
        if (StringUtils.isEmpty(oldPwd) || StringUtils.isEmpty(newPwd) || StringUtils.isEmpty(rePwd)) {
            return Result.error("有字段是空的！！！，参数不符合要求！！");
        }

        //检验原密码是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码不正确！！");
        }
        //newPwd和rePwd是否一致
        if (!rePwd.equals/*比较两个对象“内容”是否相等*/(newPwd)) {
            return Result.error("新旧密码不一样！！");
        }

        //调用service完成密码更新
        userService.updatePwd(newPwd);
        return Result.success();
    }

}
