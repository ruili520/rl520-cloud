package cn.rl520.cloud.common.core.exception;

import cn.rl520.cloud.common.core.constant.HttpStatus;
import cn.rl520.cloud.common.core.enums.ResultCode;
import lombok.Data;

/**
 * @author wwb
 * @version 1.0
 * @description 返回数据抛出异常
 * @updateRemark
 * @updateUser
 * @createDate 2021/11/8 10:13
 * @updateDate 2021/11/8 10:13
 **/
@Data
public class EcareException extends RuntimeException{

    private Integer code;

    private String message;

    public EcareException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public EcareException(String message) {
        this.code = HttpStatus.ERROR;
        this.message = message;
    }

    public EcareException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

}
