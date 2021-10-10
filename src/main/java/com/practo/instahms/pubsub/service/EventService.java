package com.practo.instahms.pubsub.service;

import com.practo.instahms.pubsub.config.EventTopicConfiguration;
import com.practo.instahms.pubsub.domain.Client;
import com.practo.instahms.pubsub.domain.Event;
import com.practo.instahms.pubsub.exception.ClientNotFoundException;
import com.practo.instahms.pubsub.repository.ClientSubscribedEventRepository;
import com.practo.instahms.pubsub.repository.EventRepository;
import com.practo.instahms.pubsub.request.EventPublishRequest;
import com.practo.instahms.pubsub.util.ExceptionHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 11/10/21
 */

@Service
@Slf4j
public class EventService {
    @Autowired
    private EventRepository repository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EventTopicConfiguration topicConfiguration;

    @Autowired
    private ClientSubscribedEventRepository subscribedEventRepository;

    public void persist(final EventPublishRequest request) {
        final Client client = clientService.getClient( request.getClient() ).orElseThrow( () ->
                new ClientNotFoundException( ExceptionHelper.CLIENT_NOT_FOUND.getCode(), ExceptionHelper.CLIENT_NOT_FOUND.getMessage() ) );
        final Event event = new Event( request.getEvent(), request.getEventTimestamp(), client); //@todo: use-mapper
        final String topic = topicConfiguration.getPractoEvent().get( request.getEvent() );
        event.setTopic( topic );
        event.setPayload( request.getPayload().toString() );
        repository.save( event );
    }
}
