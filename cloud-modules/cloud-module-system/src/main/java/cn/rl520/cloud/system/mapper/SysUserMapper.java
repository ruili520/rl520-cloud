package cn.rl520.cloud.system.mapper;

import cn.echase.cloud.common.mybatis.plus.mapper.BaseMapperPlus;
import cn.rl520.cloud.system.domain.SysUser;
import cn.rl520.cloud.system.vo.SysUserVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author wwb
 * @since 2024-01-03
 */
public interface SysUserMapper extends BaseMapperPlus<SysUser, SysUserVo> {

//    @DataPermission({
//            @DataColumn(key = "deptName", value = "d.dept_id"),
//            @DataColumn(key = "userName", value = "u.user_id")
//    })
    Page<SysUserVo> selectPageUserList(@Param("page") Page<SysUser> page, @Param(Constants.WRAPPER) Wrapper<SysUser> queryWrapper);


}
