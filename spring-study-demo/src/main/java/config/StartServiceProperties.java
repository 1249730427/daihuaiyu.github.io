package config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * startService配置类
 *
 * @author daihuaiyu
 * @create: 2021-02-23 17:39
 **/
@Configuration
@ConfigurationProperties("itstack.door")
public class StartServiceProperties {

    private String userIds;

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }
}

