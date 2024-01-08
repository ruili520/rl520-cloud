package cn.rl520.cloud.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EcareGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcareGeneratorApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  代码生成模块启动成功   ლ(´ڡ`ლ)ﾞ  ");
	}

}
