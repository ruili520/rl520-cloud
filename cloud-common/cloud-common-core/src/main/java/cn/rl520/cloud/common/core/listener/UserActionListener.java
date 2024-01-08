package cn.rl520.cloud.common.core.listener;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import cn.rl520.cloud.common.core.utils.ServletUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author wenbo
 * @Date 2024/1/2 14:42
 * 用户行为 侦听器的实现
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserActionListener implements SaTokenListener {


    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        UserAgent userAgent = UserAgentUtil.parse(ServletUtils.getRequest().getHeader("User-Agent"));
        String ip = ServletUtils.getClientIP();
        log.error("获取到登陆操作，类型：{},ip:{},loginModel:{},tokenValue:{}",loginType,ip,loginModel,tokenValue);
    }

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {

    }

    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {

    }

    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {

    }

    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {

    }

    @Override
    public void doCreateSession(String id) {

    }

    @Override
    public void doLogoutSession(String id) {

    }

    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {

    }
}
