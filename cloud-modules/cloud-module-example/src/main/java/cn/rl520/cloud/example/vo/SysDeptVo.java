package cn.rl520.cloud.example.vo;

import cn.rl520.cloud.common.core.domain.TenantEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author wwb
 * @since 2024-01-03
 */
@Getter
@Setter
public class SysDeptVo extends TenantEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     */
    private Long id;

    /**
     * 部门id
     */
    private String deptId;

    /**
     * 父部门id
     */
    private Long parentId;

    /**
     * 祖级列表
     */
    private String ancestors;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 负责人
     */
    private Long leader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 部门状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;
}
