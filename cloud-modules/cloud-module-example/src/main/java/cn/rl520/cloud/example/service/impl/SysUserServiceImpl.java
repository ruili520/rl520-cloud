package cn.rl520.cloud.example.service.impl;

import cn.echase.cloud.common.core.domain.LoginUser;
import cn.echase.cloud.common.core.domain.Result;
import cn.echase.cloud.common.core.exception.EcareException;
import cn.echase.cloud.common.core.utils.DozerUtil;
import cn.echase.cloud.common.core.utils.StringUtils;
import cn.echase.cloud.common.core.web.page.TableDataInfo;
import cn.echase.cloud.common.core.web.service.BaseService;
import cn.rl520.cloud.example.domain.SysUser;
import cn.rl520.cloud.example.mapper.SysUserMapper;
import cn.rl520.cloud.example.vo.SysUserVo;
import cn.rl520.cloud.example.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
