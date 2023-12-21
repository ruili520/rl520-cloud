package cn.rl520.cloud.gateway;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.reactor.spring.SaTokenContextRegister;
import org.springframework.boot.LazyInitializationExcludeFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        System.out.println("~~~~网关服务启动成功~~~~");
        System.out.println("Sa-Token 配置如下：" + SaManager.getConfig());
    }

}
