package cn.rl520.cloud.common.result.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author wwb
 * @version 1.0
 * @description 自定义异常处理操作
 * @updateRemark
 * @updateUser
 * @createDate 2021/11/8 10:32
 * @updateDate 2021/11/8 10:32
 **/
@RestControllerAdvice
@Slf4j
public class EcareCustomExtHandler {

    @ExceptionHandler(value = Exception.class)
    Exception HandlerException(Exception e, HttpServletRequest request){
        log.error("抛出系统异常,执行的方法接口:{},异常为:",request.getRequestURI(),e);
        return e;
    }

}
