package cn.rl520.cloud.system.mapper;

import cn.echase.cloud.common.core.constant.UserConstants;
import cn.echase.cloud.common.mybatis.plus.mapper.BaseMapperPlus;
import cn.rl520.cloud.system.domain.SysMenu;
import cn.rl520.cloud.system.vo.SysMenuVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author wwb
 * @since 2024-01-06
 */
public interface SysMenuMapper extends BaseMapperPlus<SysMenu, SysMenuVo> {

    /**
     * 根据用户ID查询菜单
     *
     * @return 菜单列表
     */
    default List<SysMenu> selectMenuTreeAll() {
        LambdaQueryWrapper<SysMenu> lqw = new LambdaQueryWrapper<SysMenu>()
                .in(SysMenu::getMenuType, UserConstants.TYPE_DIR, UserConstants.TYPE_MENU)
                .eq(SysMenu::getStatus, UserConstants.MENU_NORMAL)
                .orderByAsc(SysMenu::getParentId)
                .orderByAsc(SysMenu::getOrderNum);
        return this.selectList(lqw);
    }

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户ID
     * @return 菜单列表
     */
    List<SysMenu> selectMenuTreeByUserId(String userId);


}
