package cn.rl520.cloud.common.core.domain.model;

import cn.rl520.cloud.common.core.domain.TenantEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author wenbo
 * @Date 2024/1/5 16:19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoteClientDto extends TenantEntity {

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端key
     */
    private String clientKey;

    /**
     * 客户端秘钥
     */
    private String clientSecret;

    /**
     * 授权类型
     */
    private String grantType;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * token活跃超时时间
     */
    private Integer activeTimeout;

    /**
     * token固定超时
     */
    private Integer timeout;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    public RemoteClientDto(String clientId) {
        this.clientId = clientId;
    }
}
