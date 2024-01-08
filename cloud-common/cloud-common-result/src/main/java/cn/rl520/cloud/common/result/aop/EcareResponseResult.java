package cn.rl520.cloud.common.result.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author wwb
 * @version 1.0
 * @description 创识科技-养老关于统一返回的数据注解
 * @updateRemark
 * @updateUser
 * @createDate 2021/11/5 14:50
 * @updateDate 2021/11/5 14:50
 **/
@Retention(RUNTIME)
@Target({TYPE,METHOD})
@Documented
public @interface EcareResponseResult {

}
