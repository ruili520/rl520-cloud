package cn.echase.cloud.common.system.api.factory;

import cn.echase.cloud.common.core.domain.LoginUser;
import cn.echase.cloud.common.core.domain.Result;
import cn.echase.cloud.common.core.domain.model.RemoteUserDto;
import cn.echase.cloud.common.core.exception.EcareException;
import cn.echase.cloud.common.core.exception.user.UserException;
import cn.echase.cloud.common.system.api.service.RemoteUserService;
import cn.echase.cloud.system.domain.SysUser;
import cn.echase.cloud.system.vo.SysUserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 * 
 * @author ruoyi
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {

    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable) {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService() {
            @Override
            public LoginUser getUserInfo(String username, String source) {
                throw new EcareException("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public SysUserVo getUserInfo(SysUser sysUser) {
                throw new EcareException("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public Result<Boolean> registerUserInfo(RemoteUserDto remoteUserBo) throws UserException, EcareException {
                return Result.fail("注册用户失败:" + throwable.getMessage(),false);
            }
        };
    }
}
