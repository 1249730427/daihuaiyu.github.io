package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.StartService;

/**
 * 自动配置类
 *
 * @author daihuaiyu
 * @create: 2021-02-23 17:41
 **/
@Configuration
@ConditionalOnClass(StartService.class)
@EnableConfigurationProperties(StartServiceProperties.class)
public class StartServiceAutoConfigure {

    @Autowired
    private StartServiceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "itstack.door", value = "enabled", havingValue = "true")
    StartService startService(){

        return new StartService(properties.getUserIds());
    }
}

