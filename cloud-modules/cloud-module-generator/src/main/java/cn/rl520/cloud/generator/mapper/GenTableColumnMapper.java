package cn.rl520.cloud.generator.mapper;

import cn.rl520.cloud.common.mybatis.plus.mapper.BaseMapperPlus;
import cn.rl520.cloud.generator.domain.GenTableColumn;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 业务字段 数据层
 *
 * @author wenbo
 */
@InterceptorIgnore(dataPermission = "true", tenantLine = "true")
public interface GenTableColumnMapper extends BaseMapperPlus<GenTableColumn, GenTableColumn> {
    /**
     * 根据表名称查询列信息
     *
     * @param tableName 表名称
     * @param dataName  数据源名称
     * @return 列信息
     */
    @DS("#dataName")
    List<GenTableColumn> selectDbTableColumnsByName(@Param("tableName") String tableName, String dataName);

}
