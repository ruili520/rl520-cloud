package cn.rl520.cloud.common.core.domain.vo;

import cn.rl520.cloud.common.core.domain.TenantEntity;
import lombok.Data;

/**
 * @Author wenbo
 * @Date 2024/1/5 16:19
 */
@Data
public class RemoteClientVo extends TenantEntity {

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


}
