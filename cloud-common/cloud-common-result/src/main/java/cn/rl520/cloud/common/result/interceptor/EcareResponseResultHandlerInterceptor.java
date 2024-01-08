package cn.rl520.cloud.common.result.interceptor;

import cn.rl520.cloud.common.result.aop.EcareResponseResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author wwb
 * @version 1.0
 * @description 创识科技-养老关于返回数据的统一性拦截器
 * @updateRemark
 * @updateUser
 * @createDate 2021/11/5 14:53
 * @updateDate 2021/11/5 14:53
 **/
@Slf4j
@Component
public class EcareResponseResultHandlerInterceptor implements HandlerInterceptor {

    //定义拦截名称数据
    public static final String INTERCEPTOR_NAME = "ECARE-RESPONSE-RESULT-OBJECT";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //log.debug("方法开始执行，执行的方法接口为{},当前用户的ip信息:{}",request.getRequestURI(),getRealIP(request));
        if (handler instanceof HandlerMethod){
            final HandlerMethod handlerMethod = (HandlerMethod)handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (clazz.isAnnotationPresent(EcareResponseResult.class)){
                //设置此请求需要包装，往下传递在ResponseResultAdvice接口进行判断
                request.setAttribute(INTERCEPTOR_NAME,clazz.getAnnotation(EcareResponseResult.class));
                log.debug("当前接口具有统一拦截封装结果方法");
            }else if (method.isAnnotationPresent(EcareResponseResult.class)){
                //设置此请求需要包装，往下传递在ResponseResultAdvice接口进行判断
                request.setAttribute(INTERCEPTOR_NAME,method.getAnnotation(EcareResponseResult.class));
                log.debug("当前接口具有统一拦截封装结果方法");
            }
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //log.debug("方法执行成功，执行的方法接口为{},当前用户的ip信息:{}",request.getRequestURI(),getRealIP(request));
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }


    public static String getRealIP(HttpServletRequest request){
        String ip = null;
        try {
            //以下两个获取在k8s中，将真实的客户端IP，放到了x-Original-Forwarded-For。而将WAF的回源地址放到了 x-Forwarded-For了。
            ip = request.getHeader("X-Original-Forwarded-For");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Forwarded-For");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("x-forwarded-for");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            //兼容k8s集群获取ip
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
                if ("0:0:0:0:0:0:0:1".equalsIgnoreCase(ip) || "127.0.0.1".equalsIgnoreCase(ip)) {
                    //根据网卡取本机配置的IP
                    InetAddress iNet = null;
                    try {
                        iNet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        log.error("getClientIp error: {}", e);
                    }
                    ip = iNet.getHostAddress();
                }
            }
        } catch (Exception e) {
            log.error("IPUtils ERROR ", e);
        }
        //使用代理，则获取第一个IP地址
        if (!StringUtils.isEmpty(ip) && ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }

}
