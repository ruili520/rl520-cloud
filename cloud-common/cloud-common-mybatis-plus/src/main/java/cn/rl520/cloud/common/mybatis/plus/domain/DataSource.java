package cn.rl520.cloud.common.mybatis.plus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author wenbo
 * @Date 2024/1/2 23:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataSource {

    private String url;

    private String userName;

    private String password;

}
