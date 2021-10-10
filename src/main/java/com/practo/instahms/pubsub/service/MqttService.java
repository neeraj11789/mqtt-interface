package com.practo.instahms.pubsub.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient;
import com.practo.instahms.pubsub.config.EventTopicConfiguration;
import com.practo.instahms.pubsub.domain.ClientSubscribedEvent;
import com.practo.instahms.pubsub.exception.UnSupportedEventException;
import com.practo.instahms.pubsub.request.CallBackRequest;
import com.practo.instahms.pubsub.request.EventPublishRequest;
import com.practo.instahms.pubsub.request.EventSubscribeRequest;
import com.practo.instahms.pubsub.service.callback.CallbackRequestHandler;
import com.practo.instahms.pubsub.util.ExceptionHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.List;

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

    @Autowired
    private EventService eventService;

    @Autowired
    private ObjectMapper mapper;

    public void publish(final EventPublishRequest request){
        if(!topicConfiguration.getPractoEvent().containsKey( request.getEvent() )){
            throw new UnSupportedEventException( ExceptionHelper.EVENT_NOT_SUPPORTED.getCode(), ExceptionHelper.EVENT_NOT_SUPPORTED.getMessage() );
        }
        final String topic = topicConfiguration.getPractoEvent().get( request.getEvent() );

        client.publishWith()
                .topic( topic )
                .payload( UTF_8.encode( request.getPayload().toPrettyString() )  )
                .retain( request.getOptions().isRetainFlag() )
                .qos( request.getOptions().getQos().getQosVal() )
                .send();

        // Save event for reference
        eventService.persistEvent(request);
        log.info( "PUBLISHED_EVENT: {} on topic: {}. Request: {}", request.getEvent(), topic, request );
    }

    public void subscribe(final EventSubscribeRequest eventSubscribeRequest) {
        //subscribe to the topic
        client.subscribeWith()
                .topicFilter(eventSubscribeRequest.getEvent())
                .qos( eventSubscribeRequest.getOptions().getQos().getQosVal() )
                .send();
        eventService.persistEventSubscription(eventSubscribeRequest);
    }

    /**
     * Register the listener for subscribed topics
     */
    @PostConstruct
    public void messageListener(){
        //set a callback that is called when a message is received (using the async API style)
        client.toAsync().publishes(SUBSCRIBED, publish -> {
            final String topic = publish.getTopic().toString();
            final String event = topicConfiguration.getPractoEvent().inverse().get( topic );
            log.info(" RECEIVED_EVENT: {} On Topic: {} Qos: {} Retain: {} with Payload: {}" , event, topic, publish.getQos(), publish.isRetain(), UTF_8.decode(publish.getPayload().get()));
            final List<ClientSubscribedEvent> subscribersForEvent = eventService.getSubscribersForEvent( event );
            subscribersForEvent.forEach( se -> {
                log.info( " EXECUTING_CALLBACK Client:{} Event:{} Payload:{}", se.getClient().getClientId(), se, se.getCallback() );
                try {
                    callbackHandler.execute( mapper.readValue( se.getCallback(), CallBackRequest.class ) );
                } catch (JsonProcessingException e) {
                    log.error( "unable to serialize" );
                }
            } );
        });
    }
}