package cn.rl520.cloud.common.gen.utils.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
