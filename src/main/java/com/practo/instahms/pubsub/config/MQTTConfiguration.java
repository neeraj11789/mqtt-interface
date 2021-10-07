package com.practo.instahms.pubsub.config;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.config
 * @date 07/10/21
 */
@Configuration
@EnableConfigurationProperties({
        MqttSettings.class
})
public class MQTTConfiguration {

    @Bean
    @Qualifier("mqttSyncClient")
    public Mqtt5BlockingClient mqttBlockingClient(MqttSettings settings) {

        //create an MQTT client
        final Mqtt5BlockingClient client = MqttClient.builder()
                .useMqttVersion5()
                .serverHost(settings.getHostname())
                .serverPort(settings.getPort())
                .sslWithDefaultConfig()
                .buildBlocking();

        //connect to HiveMQ Cloud with TLS and username/pw
        client.connectWith()
                .simpleAuth()
                .username(settings.getUsername())
                .password(UTF_8.encode(settings.getPassword()))
                .applySimpleAuth()
                .send();

        return client;
    }

    @Bean
    @Qualifier("mqttAsyncClient")
    public Mqtt5AsyncClient mqttNonBlockingClient(MqttSettings settings) {

        //create an MQTT Async client
        final Mqtt5AsyncClient client = MqttClient.builder()
                .useMqttVersion5()
                .serverHost( settings.getHostname() )
                .serverPort( settings.getPort() )
                .sslWithDefaultConfig()
                .buildAsync();

        //connect to HiveMQ Cloud with TLS and username/pw
        client.connectWith()
                .simpleAuth()
                .username( settings.getUsername() )
                .password( UTF_8.encode( settings.getPassword() ) )
                .applySimpleAuth()
                .send();

        return client;
    }
}
