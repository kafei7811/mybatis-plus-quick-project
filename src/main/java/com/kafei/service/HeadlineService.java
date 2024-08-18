package com.kafei.service;

import com.kafei.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kafei.pojo.PortalVo;
import com.kafei.utils.Result;

/**
* @author 23165
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-08-15 00:33:31
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline);

    Result findHeadlineByHid(Integer hid);

    Result updateHeadlineById(Headline headline);

    Result removeByHid(Integer hid);
}
