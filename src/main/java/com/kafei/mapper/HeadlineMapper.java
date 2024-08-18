package com.kafei.mapper;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kafei.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kafei.pojo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 23165
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-08-15 00:33:31
* @Entity com.kafei.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
    //自定义分页查询方法
    IPage<Map> selectMyPage(IPage page, @Param("portalVo") PortalVo portalVo);

    Map selectDetailMap(Integer hid);

    Map selectHidAndArticleAndTitleAndTypeByHid(@Param("hid") Integer hid);
}




