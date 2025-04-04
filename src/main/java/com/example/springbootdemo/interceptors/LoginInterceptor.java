package com.example.springbootdemo.interceptors;

import com.example.springbootdemo.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头的 token
        String authHeader = request.getHeader("Authorization");

        //请求头中有没有携带 Authorization 字段，并且是不是以 "Bearer " 开头
        if (authHeader != null/* && authHeader.startsWith("Bearer ")*/) {
            /*String token = authHeader.replace("Bearer ", "");*/
            try {
                Map<String, Object> claims = JwtUtil.parseToken(authHeader);
                // 把 claims 存入 request 属性供后续使用
                request.setAttribute("claims", claims);
                return true; // 放行
            } catch (Exception e) {
                // token 无效
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                //防止乱码
                response.setContentType("application/json;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");

                response.getWriter().write("无效或过期的token！");
                return false;
            }
        }

        // 没有 token
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("请登录！");
        return false;
    }
}
