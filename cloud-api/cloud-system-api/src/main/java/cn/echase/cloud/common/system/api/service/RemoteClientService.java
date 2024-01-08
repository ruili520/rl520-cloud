package cn.rl520.cloud.common.system.api.service;

import cn.rl520.cloud.common.core.constant.ServiceNameConstants;
import cn.rl520.cloud.common.core.domain.model.RemoteClientDto;
import cn.rl520.cloud.common.system.api.factory.RemoteUserFallbackFactory;
import cn.rl520.cloud.system.vo.SysClientVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author wenbo
 * @Date 2024/1/3 15:09
 * 客户端服务
 */
@FeignClient(contextId = "remoteClientService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteClientService {

    @PostMapping("/remote/v1/client/info")
    SysClientVo getClientInfo(@RequestBody RemoteClientDto remoteClientDto);

}
