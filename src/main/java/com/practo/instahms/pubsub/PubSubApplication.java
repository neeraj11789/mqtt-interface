package com.practo.instahms.pubsub;

import com.practo.instahms.pubsub.config.EventTopicConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(EventTopicConfiguration.class)
public class PubSubApplication {

	public static void main(String[] args) {
		SpringApplication.run( PubSubApplication.class, args);
	}

}
