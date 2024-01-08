package cn.rl520.cloud.common.result.interceptor;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author wwb
 * @version 1.0
 * @description 配置类
 * @updateRemark
 * @updateUser
 * @createDate 2021/11/5 15:22
 * @updateDate 2021/11/5 15:22
 **/
@Slf4j
@Configuration
public class EcareResultWebMvcConfigurer implements WebMvcConfigurer {

    @Resource
    private EcareResponseResultHandlerInterceptor ecareResponseResultHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(ecareResponseResultHandlerInterceptor);
    }
}
