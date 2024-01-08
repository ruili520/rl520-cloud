package com.alibaba.csp.sentinel.dashboard;

import com.alibaba.csp.sentinel.init.InitExecutor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Sentinel dashboard application.
 *
 * @author wwb
 */
@SpringBootApplication
@Slf4j
public class DashboardApplication {

    public static void main(String[] args) {
        triggerSentinelInit();
        SpringApplication.run(DashboardApplication.class, args);
        log.info("哨兵控制台启动成功");
    }

    private static void triggerSentinelInit() {
        new Thread(() -> InitExecutor.doInit()).start();
    }
}
