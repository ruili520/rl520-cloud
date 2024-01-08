package cn.rl520.cloud.common.version.domain;

import cn.rl520.cloud.common.version.interfaces.ApiVersion;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @Author wenbo
 * @Date 2023/12/27 10:53
 * 自定义版本号匹配拦截器
 */
public class ApiRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    /**
     * 定义类级别的映射
     *
     * @param handlerType
     * @return
     */
    @Override
    protected RequestCondition<ApiVersionCondition> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createCondition(apiVersion);
    }

    /**
     * 定义方法级别的映射
     *
     * @param method
     * @return
     */
    @Override
    protected RequestCondition<ApiVersionCondition> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(apiVersion);
    }

    private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
        return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value());
    }

}
