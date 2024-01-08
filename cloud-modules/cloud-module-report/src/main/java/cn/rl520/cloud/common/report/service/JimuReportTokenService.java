package cn.rl520.cloud.common.report.service;

import org.jeecg.modules.jmreport.api.JmReportTokenServiceI;
import org.springframework.stereotype.Component;

/**
 * @Author wenbo
 * @Date 2024/1/3 13:35
 *  自定义积木报表鉴权(如果不进行自定义，则所有请求不做权限控制)
 *  1.自定义获取登录token
 *  2.自定义获取登录用户
 */
@Component
public class JimuReportTokenService implements JmReportTokenServiceI {


    @Override
    public String getUsername(String s) {
        return null;
    }

    @Override
    public String[] getRoles(String s) {
        return new String[0];
    }

    @Override
    public Boolean verifyToken(String s) {
        return null;
    }
}
