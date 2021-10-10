package com.practo.instahms.pubsub.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practo.instahms.pubsub.config.EventTopicConfiguration;
import com.practo.instahms.pubsub.domain.Client;
import com.practo.instahms.pubsub.domain.ClientSubscribedEvent;
import com.practo.instahms.pubsub.domain.Event;
import com.practo.instahms.pubsub.exception.ClientNotFoundException;
import com.practo.instahms.pubsub.exception.EventNotPublishedException;
import com.practo.instahms.pubsub.repository.ClientSubscribedEventRepository;
import com.practo.instahms.pubsub.repository.EventRepository;
import com.practo.instahms.pubsub.request.EventPublishRequest;
import com.practo.instahms.pubsub.request.EventSubscribeRequest;
import com.practo.instahms.pubsub.util.ExceptionHelper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Autowired
    private ObjectMapper mapper;

    public void persistEvent(final EventPublishRequest request) {
        final Client client = clientService.getClient( request.getClient() ).orElseThrow( () ->
                new ClientNotFoundException( ExceptionHelper.CLIENT_NOT_FOUND.getCode(), ExceptionHelper.CLIENT_NOT_FOUND.getMessage() ) );

        final Event event = repository.findByEventEquals( request.getEvent() ).orElseGet( () -> new Event(request.getEvent()) );
        final String topic = topicConfiguration.getPractoEvent().get( request.getEvent() );
        event.setTopic( topic );
//        event.setPublisherClient( client ); // @Note in our case just one client
//        @todo use merge to update timestamp or (@Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP"))
        repository.save( event );
    }

    @SneakyThrows // @todo: fix
    public void persistEventSubscription(final EventSubscribeRequest request) {
        final Client client = clientService.getClient( request.getClient() ).orElseThrow( () ->
                new ClientNotFoundException( ExceptionHelper.CLIENT_NOT_FOUND.getCode(), ExceptionHelper.CLIENT_NOT_FOUND.getMessage() ) );

        final Event event = repository.findByEventEquals( request.getEvent() ).orElseThrow( () ->
                new EventNotPublishedException( ExceptionHelper.EVENT_NOT_PUBLISHED.getCode(), ExceptionHelper.EVENT_NOT_PUBLISHED.getMessage() ) );

        final ClientSubscribedEvent clientSubscribedEvent = subscribedEventRepository.findByEventEqualsAndClientEquals( event, client ).orElseGet( ClientSubscribedEvent::new );
        clientSubscribedEvent.setEvent( event );
        clientSubscribedEvent.setClient( client );
        clientSubscribedEvent.setCallback( mapper.writeValueAsString( request.getCallback() ) );
        subscribedEventRepository.save( clientSubscribedEvent );
    }

    public Optional<ClientSubscribedEvent> getSubscribersForEvent(final String eventName) {
        final Optional<Event> event = repository.findByEventEquals( eventName );
        return event.flatMap( e -> subscribedEventRepository.findByEventEquals( e ) );
    }
}
