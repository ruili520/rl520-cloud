package cn.rl520.cloud.common.core.web.service;

import cn.rl520.cloud.common.core.constant.UserConstants;
import cn.rl520.cloud.common.core.utils.DateUtils;
import cn.rl520.cloud.common.core.utils.StringUtils;
import cn.rl520.cloud.common.core.web.page.TableDataInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @Author wenbo
 * @Date 2024/1/4 11:32
 */
public class BaseService {

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(IPage<?> page) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setData(page.getRecords());
        rspData.setTotal(page.getTotal());
        rspData.setCurrent(page.getCurrent());
        rspData.setSize(page.getSize());
        return rspData;
    }

    /**
     * 赋值租户信息
     */
    protected <T> QueryWrapper<T> WrapperBuild(String tenantId) {
        QueryWrapper<T> queryWrapper = Wrappers.query();
        queryWrapper.eq("del_flag", UserConstants.USER_NORMAL).eq(StringUtils.isNotEmpty(tenantId),"tenant_id",tenantId);
        return queryWrapper;
    }



}
