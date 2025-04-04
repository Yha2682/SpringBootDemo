package com.example.springbootdemo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGen() {
        /*生成jwt代码*/
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("username","admin");


        String token = JWT.create()
                .withClaim("user",claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*60*3))//设置有效时间（3小时）
                .sign(Algorithm.HMAC256("Yha"));//指定算法，配置密钥
        System.out.println(token);
    }

    @Test
    public void testParse() {
        //模拟用户持jwt令牌访问
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6ImFkbWluIn0sImV4cCI6MTc0Mzc5NDUzNn0" +
                ".R9d81X2LCybqY1dcm32gOguUgcMjkuQjlciR8FuLYqo";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("Yha")).build();//制定算法

        DecodedJWT jwt = jwtVerifier.verify(token);//验证token,生成解析后的JWT对象
        Map<String, Claim> claims = jwt.getClaims();
        System.out.println(claims.get("user"));


    }

}
