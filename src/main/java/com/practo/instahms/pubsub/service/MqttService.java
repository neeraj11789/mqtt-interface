package com.practo.instahms.pubsub.service;

import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.practo.instahms.pubsub.request.EventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 07/10/21
 */
@Service
public class MqttService {

    @Autowired
    @Qualifier("mqttSyncClient")
    private Mqtt5BlockingClient client;

    public void publish(final EventRequest eventRequest){
        client.publishWith()
                .topic(eventRequest.getEventName())
                .payload( UTF_8.encode( eventRequest.getMessage().toPrettyString() )  )
                .send();
    }
}
