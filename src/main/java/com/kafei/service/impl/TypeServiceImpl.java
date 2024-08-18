package com.kafei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kafei.pojo.PortalVo;
import com.kafei.pojo.Type;
import com.kafei.service.TypeService;
import com.kafei.mapper.TypeMapper;
import com.kafei.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 23165
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-08-15 00:33:31
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{
    @Autowired
    private TypeMapper typeMapper;
    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectTidAndTname();
        return Result.ok(types);
    }

}




