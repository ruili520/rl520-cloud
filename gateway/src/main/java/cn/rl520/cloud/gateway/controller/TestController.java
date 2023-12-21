package cn.rl520.cloud.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 定义测试使用控制器
 */
@RestController
public class TestController {

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        return "这是服务网关返回的接口数据，当前时间为"+new Date();
    }
}
