package com.kafei.controller;

import com.kafei.pojo.User;
import com.kafei.service.UserService;
import com.kafei.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("login")
    public Result login(@RequestBody User user) {
        Result userloginResult = userService.login(user);
        return userloginResult;
    }
    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result result = userService.getUserInfo(token);
        return result;
    }
    @PostMapping("checkUserName")
    public Result checkUserName(@Param("username") String username){
        Result result = userService.checkUserName(username);
        return result;
    }
    @PostMapping("regist")
    public Result register(@RequestBody User user){
        Result result = userService.register(user);
        return result;
    }
    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        Result result = userService.checkLogin(token);
        return result;
    }
}
