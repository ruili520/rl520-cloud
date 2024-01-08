package cn.rl520.cloud.common.core.domain.model;

import cn.rl520.cloud.common.core.domain.TenantEntity;
import lombok.Data;

/**
 * @Author wenbo
 * @Date 2024/1/4 17:31
 * 远程调用，用户传餐操作
 */
@Data
public class RemoteUserDto extends TenantEntity {

    private String userId;

    private String userName;

    private String phone;

    private String email;


}
