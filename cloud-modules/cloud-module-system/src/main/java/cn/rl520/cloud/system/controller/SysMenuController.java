package cn.rl520.cloud.system.controller;

import cn.rl520.cloud.common.result.aop.EcareResponseResult;
import cn.rl520.cloud.common.satoken.utils.LoginHelper;
import cn.rl520.cloud.system.domain.SysMenu;
import cn.rl520.cloud.system.service.impl.SysMenuServiceImpl;
import cn.rl520.cloud.system.vo.RouterVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author wwb
 * @since 2024-01-06
 */
@RestController
@RequestMapping("/sysMenu")
@EcareResponseResult
@RequiredArgsConstructor
public class SysMenuController {

    private final SysMenuServiceImpl menuService;

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public List<RouterVo> getRouters() {
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(LoginHelper.getUserId());
        return menuService.buildMenus(menus);
    }
}
