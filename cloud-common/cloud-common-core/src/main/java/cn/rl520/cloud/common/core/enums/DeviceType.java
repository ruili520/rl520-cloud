package cn.rl520.cloud.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 设备类型
 * 针对一套 用户体系
 *
 * @author wenbo
 */
@Getter
@AllArgsConstructor
public enum DeviceType {

    /**
     * pc端
     */
    PC("pc"),

    /**
     * app端
     */
    APP("app"),

    /**
     * 小程序端
     */
    XCX("xcx");

    private final String device;
}
