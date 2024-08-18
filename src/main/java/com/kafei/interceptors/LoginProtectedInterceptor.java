package com.kafei.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafei.utils.JwtHelper;
import com.kafei.utils.Result;
import com.kafei.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 23165
 * @description 登录包含拦截器，检查请求头是否包含有效token
 */
@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtHelper jwtHelper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中获取token
        String token = request.getHeader("token");
        //检查是否有效
        if (!jwtHelper.isExpiration(token)) {
            //没过期，放行
            return true;
        }
        //无效返回错误信息
        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
        ObjectMapper objectMapper = new ObjectMapper();
        //将结果转为json字符串
        String json = objectMapper.writeValueAsString(result);
        //将json字符串写入响应体
        response.getWriter().write(json);
        System.out.print("");
        //不放行
        return false;
    }
}
