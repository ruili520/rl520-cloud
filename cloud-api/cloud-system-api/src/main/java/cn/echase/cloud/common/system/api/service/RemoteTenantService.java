package cn.echase.cloud.common.system.api.service;

import cn.echase.cloud.common.system.api.domain.vo.RemoteTenantVo;

import java.util.List;

/**
 * @Author wenbo
 * @Date 2024/1/3 15:09
 * 租户服务
 */
public interface RemoteTenantService {

    /**
     * 根据租户id获取租户详情
     * @param tenantId 租户id
     * @return 结果
     */
    RemoteTenantVo queryByTenantId(String tenantId);

    /**
     * 获取租户列表
     * @return 结果
     */
    List<RemoteTenantVo> queryList();

}
