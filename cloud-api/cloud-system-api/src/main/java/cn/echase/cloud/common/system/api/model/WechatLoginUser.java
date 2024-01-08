package cn.echase.cloud.common.system.api.model;

import cn.echase.cloud.common.core.domain.LoginUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;

/**
 * 微信用户身份权限
 * @Author wenbo
 * @Date 2024/1/3 14:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WechatLoginUser extends LoginUser {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * openid
     */
    private String openid;

}
