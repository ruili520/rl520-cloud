package cn.rl520.cloud.system.service;

import cn.rl520.cloud.system.domain.SysMenu;
import cn.rl520.cloud.system.vo.RouterVo;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author wwb
 * @since 2024-01-06
 */
public interface ISysMenuService {

    /**
     * 根据用户ID查询菜单树信息
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(String userId);

    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    List<RouterVo> buildMenus(List<SysMenu> menus);



}
