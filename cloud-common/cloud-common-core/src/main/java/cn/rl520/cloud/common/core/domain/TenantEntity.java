package cn.rl520.cloud.common.core.domain;

import lombok.Data;

/**
 * @Author wenbo
 * @Date 2024/1/2 10:39
 * 租户基础实体类
 */
@Data
public class TenantEntity extends BaseEntity {

    /**
     * 租户编号
     */
    private String tenantId;

}
