package com.practo.instahms.pubsub.service;

import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.practo.instahms.pubsub.config.EventTopicConfiguration;
import com.practo.instahms.pubsub.exception.UnSupportedEventException;
import com.practo.instahms.pubsub.request.EventPublishRequest;
import com.practo.instahms.pubsub.request.EventSubscribeRequest;
import com.practo.instahms.pubsub.service.callback.CallbackRequestHandler;
import com.practo.instahms.pubsub.util.ExceptionHelper;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Service
public class MqttService {

    @Autowired
    @Qualifier("mqttSyncClient")
    private Mqtt5BlockingClient client;

    @Autowired
    private CallbackRequestHandler callbackHandler;

    @Autowired
    private EventTopicConfiguration topicConfiguration;

    public void publish(final EventPublishRequest request){
//        if(!EnumUtils.isValidEnum( EventTopic.class, request.getEvent().name() )){
        if(!topicConfiguration.getPracto_event().containsKey( request.getEvent() )){
            throw new UnSupportedEventException( ExceptionHelper.EVENT_NOT_SUPPORTED.getCode(), ExceptionHelper.EVENT_NOT_SUPPORTED.getMessage() );
        }

        client.publishWith()
                .topic( request.getEvent())
                .payload( UTF_8.encode( request.getPayload().toPrettyString() )  )
                .retain( request.getOptions().isRetainFlag() )
                .qos( request.getOptions().getQos().getQosVal() )
                .send();
        log.info( "Published event {} on topic {}. Request {}", request.getEvent(), topicConfiguration.getPracto_event().get( request.getEvent() ), request );
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