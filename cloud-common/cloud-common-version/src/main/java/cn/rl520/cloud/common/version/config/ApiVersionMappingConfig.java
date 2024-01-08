package cn.rl520.cloud.common.version.config;

import cn.rl520.cloud.common.version.domain.ApiRequestMappingHandlerMapping;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @Author wenbo
 * @Date 2023/12/27 10:55
 */
@AutoConfiguration
public class ApiVersionMappingConfig implements WebMvcRegistrations {

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiRequestMappingHandlerMapping();
    }


}
