package com.chz.mySwagger.controller1;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="chz-Test1Controller")
@Slf4j
@RestController
@RequestMapping("/test1")
public class Test1Controller {

    @ApiOperation(value="第一个测试方法-value",notes="第一个测试方法-notes")
    @ApiImplicitParams({
            @ApiImplicitParam(name="p1",value="p1-value", defaultValue = "p1-defaultValue"),
            @ApiImplicitParam(name="p2",value="p2-value", defaultValue = "p2-defaultValue")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
    })
    @GetMapping("/test1")
    public String test1(
            @RequestParam(value = "p1")String p1,
            @RequestParam(value = "p2")String p2
    ) {
        return "test1: p1="+p1+", p2="+p2+";";
    }

}