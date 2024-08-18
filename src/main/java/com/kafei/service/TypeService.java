package com.kafei.service;

import com.kafei.pojo.PortalVo;
import com.kafei.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kafei.utils.Result;

/**
* @author 23165
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-08-15 00:33:31
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();

}
