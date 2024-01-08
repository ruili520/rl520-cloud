package cn.rl520.cloud.common.core.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author wenbo
 * @Date 2024/1/2 20:43
 * 用户登录对象
 */
@Data
@NoArgsConstructor
public class LoginBody {

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 授权类型
     */
    private String grantType;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 验证码
     */
    private String code;

    /**
     * 唯一标识
     */
    private String uuid;

}
