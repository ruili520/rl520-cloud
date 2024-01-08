package cn.echase.cloud.common.system.api;

import cn.rl520.cloud.common.mybatis.plus.domain.DataSource;
import cn.rl520.cloud.common.mybatis.plus.utils.GeneratorUtils;

/**
 * @Author wenbo
 * @Date 2024/1/3 14:47
 */
public class Test {

    public static void main(String[] args) {
        //根据数据表生成实体类相关数据
        DataSource dataSource = new DataSource("jdbc:mysql://127.0.0.1:3306/ecare-cloud","root","ruili425714");
        GeneratorUtils.generateFile(dataSource,"cloud-system/cloud-system-api",new String[]{"sys_user","sys_role","sys_tenant","sys_client"},"wwb","cn.echase.cloud.common.system.api",null);
    }
}
