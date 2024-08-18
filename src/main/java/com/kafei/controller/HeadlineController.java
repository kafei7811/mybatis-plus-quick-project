package com.kafei.controller;

import com.kafei.pojo.Headline;
import com.kafei.service.HeadlineService;
import com.kafei.utils.AcquireDate;
import com.kafei.utils.JwtHelper;
import com.kafei.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {
    @Autowired
    private AcquireDate acquireDate;
    @Autowired
    private HeadlineService headlineService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("publish")
    public Result publish(@RequestHeader String token,@RequestBody Headline headline) throws ParseException {
        int userId = jwtHelper.getUserId(token).intValue();
        Date nowDate = acquireDate.getNowDate();
        headline.setCreateTime(nowDate);
        headline.setUpdateTime(nowDate);
        headline.setPublisher(userId);
        Result result = headlineService.publish(headline);
        return result;
    }
    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(@Param("hid") Integer hid){
        Result result = headlineService.findHeadlineByHid(hid);
        return result;
    }
    @PostMapping("update")
    public Result update(@RequestBody Headline headline) throws ParseException {
        Date nowDate = acquireDate.getNowDate();
        headline.setUpdateTime(nowDate);
        Result result = headlineService.updateHeadlineById(headline);
        return result;
    }
    @PostMapping("removeByHid")
    public Result removeByHid(@Param("hid") Integer hid){
        Result result = headlineService.removeByHid(hid);
        return result;
    }
}
