package cn.rl520.cloud.common.mybatis.plus.handler;

import cn.echase.cloud.common.core.domain.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Mybatis异常处理器
 *
 * @author wenbo
 */
@Slf4j
@RestControllerAdvice
public class MybatisExceptionHandler {

    /**
     * 主键或UNIQUE索引，数据重复异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result<Void> handleDuplicateKeyException(DuplicateKeyException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',数据库中已存在记录'{}'", requestURI, e.getMessage());
        return Result.fail("数据库中已存在该记录，请联系管理员确认");
    }

    /**
     * Mybatis系统异常 通用处理
     */
    @ExceptionHandler(MyBatisSystemException.class)
    public Result<Void> handleCannotFindDataSourceException(MyBatisSystemException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String message = e.getMessage();
        if ("CannotFindDataSourceException".contains(message)) {
            log.error("请求地址'{}', 未找到数据源", requestURI);
            return Result.fail("未找到数据源，请联系管理员确认");
        }
        log.error("请求地址'{}', Mybatis系统异常", requestURI, e);
        return Result.fail(message);
    }

}
