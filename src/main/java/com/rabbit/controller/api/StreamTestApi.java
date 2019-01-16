package com.rabbit.controller.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qinxy on 2018/12/10.
 */
@RestController
@RequestMapping(value = "/demo")
@Api(value = ApiTag.STREAM, tags = ApiTag.STREAM, description = ApiTag.STREAM_DESCRIPTION)
public class StreamTestApi {


}
