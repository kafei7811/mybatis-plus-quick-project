package com.kafei.mapper;
import java.util.List;

import com.kafei.pojo.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 23165
* @description 针对表【news_type】的数据库操作Mapper
* @createDate 2024-08-15 00:33:31
* @Entity com.kafei.pojo.Type
*/
public interface TypeMapper extends BaseMapper<Type> {
    List<Type> selectTidAndTname();
}




