package com.rabbit.controller.api;

import com.rabbit.controller.BaseApi;
import com.rabbit.producer.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinxy on 2018/11/20.
 */
@RestController
@RequestMapping(value = "/demo")
@Api(value = ApiTag.DEMO, tags = ApiTag.DEMO, description = ApiTag.DEMO_DESCRIPTION)
public class DemoApi extends BaseApi {

    @Autowired
    private Producer producer;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ApiOperation(value = "测试",notes = "测试")
    @ResponseBody
    public String hello(){
        producer.send("hello");
        return "hello world";
    }

    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    @ApiOperation(value = "测试",notes = "测试")
    @ResponseBody
    public String demo(){
        producer.demo("demo");
        return "DEMO";
    }


}
