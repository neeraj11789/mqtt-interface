package com.practo.instahms.pubsub.service;

import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.practo.instahms.pubsub.request.EventPublishRequest;
import com.practo.instahms.pubsub.request.EventSubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import static com.hivemq.client.mqtt.MqttGlobalPublishFilter.SUBSCRIBED;
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

    @Autowired
    private CallbackRequestHandler callbackHandler;

    public void publish(final EventPublishRequest eventPublishRequest){
        client.publishWith()
                .topic( eventPublishRequest.getEvent())
                .payload( UTF_8.encode( eventPublishRequest.getMessage().toPrettyString() )  )
                .send();
    }

    public void subscribe(final EventSubscribeRequest eventSubscribeRequest) {

        //subscribe to the topic "my/test/topic"
        client.subscribeWith()
                .topicFilter(eventSubscribeRequest.getEvent())
                .send();

        //set a callback that is called when a message is received (using the async API style)
        // @todo: Use async client for subscription
        client.toAsync().publishes(SUBSCRIBED, publish -> {
            System.out.println("Received message: " + publish.getTopic() + " -> " + UTF_8.decode(publish.getPayload().get()));

            // @todo: Handle it - if client is disconnected connect again
            // disconnect the client after a message was received
            // client.disconnect();
        });

        callbackHandler.execute(eventSubscribeRequest.getCallback());
    }
}