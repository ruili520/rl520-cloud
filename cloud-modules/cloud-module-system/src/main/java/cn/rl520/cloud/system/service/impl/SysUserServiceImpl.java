package cn.rl520.cloud.system.service.impl;

import cn.rl520.cloud.common.core.exception.EcareException;
import cn.rl520.cloud.common.core.utils.StringUtils;
import cn.rl520.cloud.common.core.web.page.TableDataInfo;
import cn.rl520.cloud.common.core.web.service.BaseService;
import cn.rl520.cloud.system.domain.SysUser;
import cn.rl520.cloud.system.mapper.SysUserMapper;
import cn.rl520.cloud.system.service.ISysUserService;
import cn.rl520.cloud.system.vo.SysUserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author wwb
 * @since 2024-01-03
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends BaseService implements ISysUserService {

    private final SysUserMapper sysUserMapper;

    @Override
    public TableDataInfo getUserVoPage(SysUser sysUser) {
        return getDataTable(sysUserMapper.selectVoPage(new Page<>(sysUser.getCurrent(),sysUser.getSize()),null, SysUserVo.class));
    }

    @Override
    public List<SysUserVo> getUserList(SysUser sysUser) {
        return sysUserMapper.selectVoList();
    }

    @Override
    public SysUserVo getUserInfo(SysUser sysUser) {
        QueryWrapper<SysUser> wrapper =  WrapperBuild(null);
        wrapper.eq(StringUtils.isNotEmpty(sysUser.getUserName()),"user_name",sysUser.getUserName())
                .eq(StringUtils.isNotEmpty(sysUser.getUserId()),"user_id",sysUser.getUserId());
        SysUserVo sysUserVo = sysUserMapper.selectVoOne(wrapper);
        if (StringUtils.isNull(sysUser)) {
            throw new EcareException("用户不存在");
        }
        return sysUserVo;
    }

    @Override
    public SysUserVo selectUserById(String userId) {
        QueryWrapper<SysUser> wrapper = WrapperBuild(null);
        wrapper.eq("uu_id",userId);
        return sysUserMapper.selectVoOne(wrapper);
    }


}
