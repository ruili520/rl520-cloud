package cn.rl520.cloud.common.version.domain;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author wenbo
 * @Date 2023/12/27 10:51
 * 自定义版本号筛选规则
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    /**
     * 匹配v[n]
     */
    private final static Pattern VERSION_PREFIX_PATTERN = Pattern.compile(".*v(\\d+).*");

    private final int apiVersion;

    public int getApiVersion() {
        return apiVersion;
    }

    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * 版本号合并方式,按最后定义的版本号为准
     *
     * @param otherVersionCondition
     * @return
     */
    @Override
    public ApiVersionCondition combine(ApiVersionCondition otherVersionCondition) {
        return new ApiVersionCondition(otherVersionCondition.getApiVersion());
    }

    /**
     * 检查uri是否包含版本相关的字段
     *
     * @param httpServletRequest
     * @return
     */
    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(httpServletRequest.getRequestURI());
        if (m.find()) {
            int version = Integer.parseInt(m.group(1));
            if (version == this.apiVersion) {
                return this;
            }
        }
        return null;
    }

    @Override
    public int compareTo(ApiVersionCondition otherVersionCondition, HttpServletRequest httpServletRequest) {
        // 优先匹配最新的版本号
        return Integer.compare(otherVersionCondition.getApiVersion(), this.apiVersion);
    }

}

