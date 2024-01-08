package cn.rl520.cloud.common.core.web.page;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 * @author ruoyi
 */
@Data
@NoArgsConstructor
public class TableDataInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;

    private List<?> data;

    private long size;

    private long current;

    /**
     * 分页
     * 
     * @param data 列表数据
     * @param total 总记录数
     */
    public TableDataInfo(List<?> data,int total) {
        this.data = data;
        this.total = total;
    }

    public TableDataInfo(List<?> data,int total,long size,long current) {
        this.data = data;
        this.total = total;
        this.size = size;
        this.current = current;
    }
}