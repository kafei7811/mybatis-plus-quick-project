package com.kafei.controller;

import com.kafei.pojo.PortalVo;
import com.kafei.service.HeadlineService;
import com.kafei.service.TypeService;
import com.kafei.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private HeadlineService headlineService;
    @Autowired
    private TypeService typeService;

    @GetMapping("findAllTypes")
    public Result findAllTypes() {
        Result result = typeService.findAllTypes();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo) {
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }
    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(@Param("hid") Integer hid) {
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
