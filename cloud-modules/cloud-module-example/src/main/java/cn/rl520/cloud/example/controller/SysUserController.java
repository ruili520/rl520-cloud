package cn.rl520.cloud.example.controller;

import cn.echase.cloud.common.core.constant.HttpStatus;
import cn.echase.cloud.common.core.domain.LoginUser;
import cn.echase.cloud.common.core.exception.EcareException;
import cn.echase.cloud.common.core.web.page.TableDataInfo;
import cn.echase.cloud.common.result.aop.EcareResponseResult;
import cn.echase.cloud.common.satoken.utils.LoginHelper;
import cn.rl520.cloud.example.domain.SysUser;
import cn.rl520.cloud.example.service.impl.SysUserServiceImpl;
import cn.rl520.cloud.example.vo.SysUserVo;
import cn.rl520.cloud.example.vo.UserInfoVo;
import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author wwb
 * @since 2024-01-03
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@EcareResponseResult
public class SysUserController{

    private final SysUserServiceImpl sysUserService;

    /**
     * 获取用户的分页情况
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "getUserVoPage",method = RequestMethod.POST)
    public TableDataInfo getUserVoPage(@RequestBody SysUser sysUser){
        return sysUserService.getUserVoPage(sysUser);
    }

    /**
     * 获取用户的列表数据
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "getUserList",method = RequestMethod.POST)
    public List<SysUserVo> getUserList(@RequestBody SysUser sysUser){
        return sysUserService.getUserList(sysUser);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @GetMapping("/info/{username}")
    public LoginUser info(@PathVariable("username") String username) {
        sysUserService.getUserInfo(new SysUser(username));
        return new LoginUser();
    }

    @PostMapping("/info")
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