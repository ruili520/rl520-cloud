package cn.rl520.cloud.common.mybatis.plus.handler;

import cn.echase.cloud.common.core.domain.BaseEntity;
import cn.echase.cloud.common.core.domain.LoginUser;
import cn.echase.cloud.common.core.exception.EcareException;
import cn.echase.cloud.common.satoken.utils.LoginHelper;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * MP注入处理器
 *
 * @author wenbo
 */
@Slf4j
public class InjectionMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject)
                && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {
                Date current = ObjectUtil.isNotNull(baseEntity.getCreateTime())
                    ? baseEntity.getCreateTime() : new Date();
                baseEntity.setCreateTime(current);
                baseEntity.setUpdateTime(current);
                LoginUser loginUser = getLoginUser();
                if (ObjectUtil.isNotNull(loginUser)) {
                    String userId = ObjectUtil.isNotNull(baseEntity.getCreateBy())
                        ? baseEntity.getCreateBy() : loginUser.getUserId();
                    // 当前已登录 且 创建人为空 则填充
                    baseEntity.setCreateBy(userId);
                    // 当前已登录 且 更新人为空 则填充
                    baseEntity.setUpdateBy(userId);
                    baseEntity.setCreateDept(ObjectUtil.isNotNull(baseEntity.getCreateDept())
                        ? baseEntity.getCreateDept() : loginUser.getDeptId());
                }
            }
        } catch (Exception e) {
            throw new EcareException(HttpStatus.HTTP_UNAUTHORIZED,"自动注入异常 => " + e.getMessage());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject)
                && metaObject.getOriginalObject() instanceof BaseEntity baseEntity) {
                Date current = new Date();
                // 更新时间填充(不管为不为空)
                baseEntity.setUpdateTime(current);
                LoginUser loginUser = getLoginUser();
                // 当前已登录 更新人填充(不管为不为空)
                if (ObjectUtil.isNotNull(loginUser)) {
                    baseEntity.setUpdateBy(loginUser.getUserId());
                }
            }
        } catch (Exception e) {
            throw new EcareException(HttpStatus.HTTP_UNAUTHORIZED,"自动注入异常 => " + e.getMessage());
        }
    }

    /**
     * 获取登录用户
     */
    private LoginUser getLoginUser() {
        LoginUser loginUser;
        try {
            loginUser = LoginHelper.getLoginUser();
        } catch (Exception e) {
            log.warn("自动注入警告 => 用户未登录");
            return null;
        }
        return loginUser;
    }

}
