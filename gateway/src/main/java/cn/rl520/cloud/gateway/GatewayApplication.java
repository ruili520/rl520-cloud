package cn.rl520.cloud.gateway;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        System.out.println("~~~~网关服务启动成功~~~~");
        System.out.println("Sa-Token 配置如下：" + SaManager.getConfig());
    }

}
