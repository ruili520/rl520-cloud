package cn.rl520.cloud.common.core.enums;

import cn.rl520.cloud.common.core.constant.HttpStatus;

/**
 * @author wwb
 * @version 1.0
 * @description 返回数据定义枚举类
 * @updateRemark
 * @updateUser
 * @createDate 2021/11/8 10:04
 * @updateDate 2021/11/8 10:04
 **/
public enum ResultCode {

    FAIL(-1,"操作失败"),
    SUCCESS(HttpStatus.SUCCESS,"成功"),
    ;
    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
