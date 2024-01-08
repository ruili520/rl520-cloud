package cn.rl520.cloud.common.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @Author wenbo
 * @Date 2024/1/3 11:09
 */
@SpringBootApplication(exclude = {MongoAutoConfiguration.class}, scanBasePackages = {"org.jeecg.modules.jmreport","cn.echase.cloud.common.report"})
@RefreshScope
public class EchaseCloudReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchaseCloudReportApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  报表模块启动成功   ლ(´ڡ`ლ)ﾞ ");
    }

}
