package cn.rl520.cloud.gateway.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
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
    @SentinelResource("test")
    public String test(){
        return "这是服务网关返回的接口数据，当前时间为"+ DateUtil.formatDateTime(new Date())+" ; 版本号为 V.1.0";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    @SentinelResource("login")
    public SaTokenInfo login(){
        StpUtil.login(10001);
        return StpUtil.getTokenInfo();
    }


}
