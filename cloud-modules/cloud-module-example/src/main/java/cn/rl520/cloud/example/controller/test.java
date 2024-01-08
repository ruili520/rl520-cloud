package cn.rl520.cloud.example.controller;

import cn.echase.cloud.common.mybatis.plus.domain.DataSource;
import cn.echase.cloud.common.mybatis.plus.utils.GeneratorUtils;

/**
 * @Author wenbo
 * @Date 2024/1/3 15:53
 */
public class test {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource("jdbc:mysql://127.0.0.1:3306/ecare-cloud","root","ruili425714");
        GeneratorUtils.generateFile(dataSource,"cloud-modules/cloud-module-system",new String[]{"sys_menu"},"wwb","cn.echase.cloud.system",null);

    }
}
