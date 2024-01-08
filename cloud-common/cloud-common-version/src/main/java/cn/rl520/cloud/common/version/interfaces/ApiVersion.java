package cn.rl520.cloud.common.version.interfaces;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * @Author wenbo
 * @Date 2023/12/27 10:50
 */
@Target({ElementType.METHOD, ElementType.TYPE}) // 类上和方法上都可以
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping    //spring的元注释，表示 Web 映射注释
public @interface ApiVersion {

    /**
     * 自定义版本号
     */
    int value() default 1;
}
