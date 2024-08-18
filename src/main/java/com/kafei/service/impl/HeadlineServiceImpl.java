package com.kafei.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kafei.pojo.Headline;
import com.kafei.pojo.PortalVo;
import com.kafei.service.HeadlineService;
import com.kafei.mapper.HeadlineMapper;
import com.kafei.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 23165
 * @description 针对表【news_headline】的数据库操作Service实现
 * @createDate 2024-08-15 00:33:31
 */
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {
    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        //1.条件拼接 需要非空判断
        //2.分页参数
        IPage<Map> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());

        //3.分页查询
        //查询的结果 "pastHours":"3"   // 发布时间已过小时数 我们查询返回一个map
        //自定义方法
        headlineMapper.selectMyPage(page, portalVo);
        List<Map> records = page.getRecords();
        //4.结果封装
        //分页数据封装
        Map data = new HashMap<>();
        data.put("pageData", records);
        data.put("pageNum", page.getCurrent());
        data.put("pageSize", page.getSize());
        data.put("totalPage", page.getPages());
        data.put("totalSize", page.getTotal());
        Map pageInfo = new HashMap();
        pageInfo.put("pageInfo", data);
        // 响应JSON
        return Result.ok(pageInfo);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map map = headlineMapper.selectDetailMap(hid);
        Map pageInfo = new HashMap();
        pageInfo.put("headline", map);
        return Result.ok(pageInfo);
    }

    @Override
    public Result publish(Headline headline) {
        int insert = headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(Integer hid) {
        Map headline = headlineMapper.selectHidAndArticleAndTitleAndTypeByHid(hid);
        Map pageInfo = new HashMap();
        pageInfo.put("headline", headline);
        return Result.ok(pageInfo);
    }

    @Override
    public Result updateHeadlineById(Headline headline) {
        headline.setVersion(headlineMapper.selectById(headline.getHid()).getVersion());
        int update = headlineMapper.updateById(headline);
        return Result.ok(null);
    }

    @Override
    public Result removeByHid(Integer hid) {
        int i = headlineMapper.deleteById(hid);
        System.out.println(i);
        return Result.ok(null);
    }
}




