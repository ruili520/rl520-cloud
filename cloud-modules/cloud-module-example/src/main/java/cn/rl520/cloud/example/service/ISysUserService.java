package cn.rl520.cloud.example.service;

import cn.rl520.cloud.common.core.domain.LoginUser;
import cn.rl520.cloud.common.core.domain.Result;
import cn.rl520.cloud.common.core.web.page.TableDataInfo;
import cn.rl520.cloud.example.domain.SysUser;
import cn.rl520.cloud.example.vo.SysUserVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author wwb
 * @since 2024-01-03
 */
public interface ISysUserService {


    /**
     * 查询用户分页数据信息
     * @param sysUser
     * @return
     */
    public TableDataInfo getUserVoPage(SysUser sysUser);


    public List<SysUserVo> getUserList(SysUser sysUser);

    SysUserVo getUserInfo(SysUser sysUser);

    SysUserVo selectUserById(String userId);

}
