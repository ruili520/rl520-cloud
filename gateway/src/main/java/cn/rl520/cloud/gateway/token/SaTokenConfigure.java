package cn.rl520.cloud.gateway.token;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import cn.rl520.cloud.common.core.constant.HttpStatus;
import cn.rl520.cloud.common.core.utils.StringUtils;
import cn.rl520.cloud.common.satoken.utils.LoginHelper;
import cn.rl520.cloud.gateway.config.properties.IgnoreWhiteProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * [Sa-Token 权限认证] 配置类
 */
@Slf4j
@Configuration
public class SaTokenConfigure {

    // 注册 Sa-Token全局过滤器
    @Bean
    public SaReactorFilter getSaReactorFilter(IgnoreWhiteProperties ignoreWhite) {
        System.out.println(ignoreWhite.getWhites());
        return new SaReactorFilter()
                // 拦截地址
                .addInclude("/**")    /* 拦截全部path */
                // 开放地址
                .addExclude("/favicon.ico","/actuator/**")
                // 鉴权方法：每次访问进入
                .setAuth(obj -> {
                    // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
                    SaRouter.match("/**").notMatch(ignoreWhite.getWhites())
                            .check(r -> {
                                StpUtil.checkLogin();
                                ServerHttpRequest request = SaReactorSyncHolder.getContext().getRequest();
                                String headerCid = request.getHeaders().getFirst(LoginHelper.CLIENT_KEY);
                                String paramCid = request.getQueryParams().getFirst(LoginHelper.CLIENT_KEY);
                                String clientId = StpUtil.getExtra(LoginHelper.CLIENT_KEY).toString();
                                if (!StringUtils.equalsAny(clientId, headerCid, paramCid)) {
                                    // token 无效
                                    throw NotLoginException.newInstance(StpUtil.getLoginType(),
                                            "-100", "客户端ID与Token不匹配",
                                            StpUtil.getTokenValue());
                                }
                            });
                    // 更多匹配 ...  */
                }).setError(e -> {
                    if (e instanceof NotLoginException) {
                        return SaResult.error(e.getMessage()).setCode(HttpStatus.UNAUTHORIZED);
                    }
                    System.out.println(e);
                    return SaResult.error("认证失败，无法访问系统资源").setCode(HttpStatus.UNAUTHORIZED);
                }).setBeforeAuth(obj -> {
                    // ---------- 设置跨域响应头 ----------
                    SaHolder.getResponse()
                            // 允许指定域访问跨域资源
                            .setHeader("Access-Control-Allow-Origin", "*")
                            // 允许所有请求方式
                            .setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE")
                            // 有效时间
                            .setHeader("Access-Control-Max-Age", "3600")
                            // 允许的header参数
                            .setHeader("Access-Control-Allow-Headers", "*");
                    // 如果是预检请求，则立即返回到前端
                    SaRouter.match(SaHttpMethod.OPTIONS)
                            .free(r -> System.out.println("--------OPTIONS预检请求，不做处理"))
                            .back();
                });
    }

}
