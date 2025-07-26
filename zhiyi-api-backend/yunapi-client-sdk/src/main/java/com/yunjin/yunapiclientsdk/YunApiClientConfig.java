package com.yunjin.yunapiclientsdk;

import com.yunjin.yunapiclientsdk.client.YunApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * YunApi 客户端配置
 *
 */
@Configuration
@ConfigurationProperties("yunapi.client")
@Data
@ComponentScan
class YunApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public YunApiClient yunApiClient() {
        return new YunApiClient(accessKey, secretKey);
    }

}
