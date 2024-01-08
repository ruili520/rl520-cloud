package cn.rl520.cloud.system.vo;

import cn.echase.cloud.common.core.domain.TenantEntity;
import cn.rl520.cloud.system.domain.SysUser;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author wwb
 * @since 2024-01-03
 */
@Getter
@Setter
@AutoMapper(target = SysUser.class)
public class SysUserVo extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户类型（sys_user系统用户）
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String sex;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门对象
     */
    private SysDeptVo dept;

    /**
     * 角色对象
     */
    private List<SysRoleVo> roles;
}
