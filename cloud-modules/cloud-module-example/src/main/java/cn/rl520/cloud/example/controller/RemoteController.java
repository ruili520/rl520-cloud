package cn.rl520.cloud.example.controller;

import cn.echase.cloud.common.core.constant.HttpStatus;
import cn.echase.cloud.common.core.domain.LoginUser;
import cn.echase.cloud.common.core.domain.model.RemoteClientDto;
import cn.echase.cloud.common.core.exception.EcareException;
import cn.echase.cloud.common.satoken.utils.LoginHelper;
import cn.echase.cloud.common.version.interfaces.ApiVersion;
import cn.rl520.cloud.example.domain.SysUser;
import cn.rl520.cloud.example.service.impl.SysClientServiceImpl;
import cn.rl520.cloud.example.service.impl.SysUserServiceImpl;
import cn.rl520.cloud.example.vo.SysClientVo;
import cn.rl520.cloud.example.vo.SysUserVo;
import cn.rl520.cloud.example.vo.UserInfoVo;
import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 远程调用前端控制器
 * </p>
 *
 * @author wwb
 * @since 2024-01-03
 */
@RestController
@RequestMapping("remote")
@RequiredArgsConstructor
public class RemoteController {

    private final SysUserServiceImpl sysUserService;

    private final SysClientServiceImpl sysClientService;

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @GetMapping("/info/{username}")
    public LoginUser info(@PathVariable("username") String username) {
        SysUserVo sysUserVo = sysUserService.getUserInfo(new SysUser(username));
        LoginUser loginUser = new LoginUser();
        loginUser.setTenantId(sysUserVo.getTenantId());
        loginUser.setUserId(sysUserVo.getUserId());
        loginUser.setDeptId(sysUserVo.getDeptId());
        loginUser.setUsername(sysUserVo.getUserName());
        loginUser.setNickname(sysUserVo.getNickName());
        loginUser.setPassword(sysUserVo.getPassword());
        loginUser.setUserType(sysUserVo.getUserType());
//        loginUser.setMenuPermission(permissionService.getMenuPermission(userVo.getUserId()));
//        loginUser.setRolePermission(permissionService.getRolePermission(userVo.getUserId()));
//        loginUser.setDeptName(ObjectUtil.isNull(userVo.getDept()) ? "" : userVo.getDept().getDeptName());
//        List<RoleDTO> roles = BeanUtil.copyToList(userVo.getRoles(), RoleDTO.class);
//        loginUser.setRoles(roles);
        return loginUser;
    }

    @ApiVersion
    @PostMapping("/{version}/client/info")
    public SysClientVo clientInfo(@RequestBody RemoteClientDto remoteClientDto) {
        return sysClientService.getClientVo(remoteClientDto);
    }

    @ApiVersion
    @PostMapping("/{version}/user/info")
    public SysUserVo getInfo(@RequestBody SysUser sysUser) {
        return sysUserService.getUserInfo(sysUser);
    }

    @PostMapping("/register")
    public Boolean registerUserInfo(@RequestBody SysUser sysUser) {
        return true;
    }


    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public UserInfoVo getInfo() {
        UserInfoVo userInfoVo = new UserInfoVo();
        LoginUser loginUser = LoginHelper.getLoginUser();
//        if (TenantHelper.isEnable() && LoginHelper.isSuperAdmin()) {
//            // 超级管理员 如果重新加载用户信息需清除动态租户
//            TenantHelper.clearDynamic();
//        }
        SysUserVo user = sysUserService.selectUserById(loginUser.getUserId());
        if (ObjectUtil.isNull(user)) {
            throw new EcareException(HttpStatus.UNAUTHORIZED,"没有权限访问用户数据");
        }
        userInfoVo.setUser(user);
        userInfoVo.setPermissions(loginUser.getMenuPermission());
        userInfoVo.setRoles(loginUser.getRolePermission());
        return userInfoVo;
    }

}
