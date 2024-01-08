package cn.rl520.cloud.common.system.api.service;

import cn.rl520.cloud.common.core.constant.SecurityConstants;
import cn.rl520.cloud.common.core.constant.ServiceNameConstants;
import cn.rl520.cloud.common.core.domain.LoginUser;
import cn.rl520.cloud.common.core.domain.Result;
import cn.rl520.cloud.common.core.domain.model.RemoteUserDto;
import cn.rl520.cloud.common.core.exception.EcareException;
import cn.rl520.cloud.common.core.exception.user.UserException;
import cn.rl520.cloud.common.system.api.factory.RemoteUserFallbackFactory;
import cn.rl520.cloud.system.domain.SysUser;
import cn.rl520.cloud.system.vo.SysUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 用户服务
 *
 * @author wwb
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {

    @GetMapping("/remote/info/{username}")
    LoginUser getUserInfo(@PathVariable("username") String username, @RequestHeader(SecurityConstants.FROM_SOURCE) String source);

    @PostMapping("/remote/v1/user/info")
    SysUserVo getUserInfo(@RequestBody SysUser sysUser);

    /**
     * 注册用户信息
     */
    @PostMapping("/user/register")
    Result<Boolean> registerUserInfo(RemoteUserDto remoteUserBo) throws UserException, EcareException;




}
