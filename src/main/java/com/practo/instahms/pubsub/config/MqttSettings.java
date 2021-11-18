package com.practo.instahms.pubsub.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.config
 * @date 07/10/21
 */

@Primary
@Configuration
@ConfigurationProperties(prefix = "mqtt")
@Setter
@Getter
public class MqttSettings {

    private String hostname;

    private int port;

    private String username;

    private String password;
}
