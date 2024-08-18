package com.kafei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kafei.pojo.User;
import com.kafei.service.UserService;
import com.kafei.mapper.UserMapper;
import com.kafei.utils.JwtHelper;
import com.kafei.utils.MD5Util;
import com.kafei.utils.Result;
import com.kafei.utils.ResultCodeEnum;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 23165
 * @description 针对表【news_user】的数据库操作Service实现
 * @createDate 2024-08-15 00:33:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);
        if (loginUser == null) {
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        if (MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())) {
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            Map data = new HashMap();
            data.put("token", token);
            return Result.ok(data);
        }
        return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        if (jwtHelper.isExpiration(token)) {
            //Token过期
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        Long userId = jwtHelper.getUserId(token);
        User user = userMapper.selectById(userId);
        user.setUserPwd("");
        Map data = new HashMap();
        data.put("loginUser", user);
        return Result.ok(data);
    }

    @Override
    public Result checkUserName(String username) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return Result.ok(null);
        }
        return Result.build(null, 505, "用户名占用");
    }

    @Override
    public Result register(User user) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User registUser = userMapper.selectOne(queryWrapper);
        if (registUser == null) {
            user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
            userMapper.insert(user);
            return Result.ok(null);
        }
        return Result.build(null, 505, "用户名占用");
    }

    @Override
    public Result checkLogin(@RequestHeader String token) {
        if (jwtHelper.isExpiration(token)) {
            //Token过期
            return Result.build(null, 504, "loginExpired");
        }
        return Result.ok(null);
    }
}




