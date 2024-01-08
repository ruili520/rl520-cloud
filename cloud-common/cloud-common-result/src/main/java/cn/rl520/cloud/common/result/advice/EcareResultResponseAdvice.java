package cn.rl520.cloud.common.result.advice;

import cn.rl520.cloud.common.core.domain.Result;
import cn.rl520.cloud.common.core.exception.EcareException;
import cn.rl520.cloud.common.result.aop.EcareResponseResult;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


/**
 * @author wwb
 * @version 1.0
 * @description 返回response封装操作
 * @updateRemark
 * @updateUser
 * @createDate 2021/11/5 15:30
 * @updateDate 2021/11/5 15:30
 **/
@Slf4j
@ControllerAdvice
public class EcareResultResponseAdvice implements ResponseBodyAdvice<Object> {

    public static final String INTERCEPTOR_NAME = "ECARE-RESPONSE-RESULT-OBJECT";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        EcareResponseResult responseResult = (EcareResponseResult)httpServletRequest.getAttribute(INTERCEPTOR_NAME);
        return responseResult==null?false:true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof EcareException){
            EcareException inException = (EcareException)o;
            log.warn("调用接口为:"+serverHttpRequest.getURI().getRawPath()+",返回数据类型为自定义异常,数据为{}",
                    ((EcareException) o).getMessage());
            return Result.fail(inException.getCode(),inException.getMessage());
        }else if (o instanceof Exception){
            log.error("调用接口为:"+serverHttpRequest.getURI().getRawPath()+",返回数据类型为系统异常,数据为",o);
            return Result.fail(((Exception) o).getMessage());
        }
        if(o instanceof Result<?>){
            String json = JSONUtil.toJsonStr(o);
            if (json.length()>1000){
                log.debug("调用接口为:"+serverHttpRequest.getURI().getRawPath()+",返回数据类型为封装的数据对象");
            }else {
                log.debug("调用接口为:"+serverHttpRequest.getURI().getRawPath()+",返回数据类型为封装的数据对象,数据为{}",JSONUtil.toJsonStr(o));
            }
            return o;
        }
        //当返回类型是String时，用的是StringHttpMessageConverter转换器，无法转换为Json格式
        //必须在方法体上标注RequestMapping(produces = "application/json; charset=UTF-8")
        if(o instanceof String){
            String str  = JSONUtil.toJsonStr(Result.ok(o));
            log.debug("调用接口为:"+serverHttpRequest.getURI().getRawPath()+",返回数据类型为字符串,数据为{}",str);
            return str;
        }
        log.debug("调用接口为:"+serverHttpRequest.getURI().getRawPath()+",返回数据类型为数据对象,数据为{}",JSONUtil.toJsonStr(o));
        return Result.ok(o);
    }
}
