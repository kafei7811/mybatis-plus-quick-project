package com.kafei.service;

import com.kafei.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kafei.utils.Result;

/**
* @author 23165
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-08-15 00:33:31
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result register(User user);

    Result checkLogin(String token);
}
