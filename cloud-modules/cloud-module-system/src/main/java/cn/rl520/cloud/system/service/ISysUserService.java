package cn.rl520.cloud.system.service;

import cn.echase.cloud.common.core.web.page.TableDataInfo;
import cn.rl520.cloud.system.domain.SysUser;
import cn.rl520.cloud.system.vo.SysUserVo;

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
