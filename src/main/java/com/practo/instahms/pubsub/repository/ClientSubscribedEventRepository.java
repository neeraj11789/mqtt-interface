package com.practo.instahms.pubsub.repository;

import com.practo.instahms.pubsub.domain.Client;
import com.practo.instahms.pubsub.domain.ClientSubscribedEvent;
import com.practo.instahms.pubsub.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.repository
 * @date 04/10/21
 */

@Repository
public interface ClientSubscribedEventRepository extends JpaRepository<ClientSubscribedEvent, Long> {

    Optional<ClientSubscribedEvent> findByEventEquals(@NonNull Event event);

    Optional<ClientSubscribedEvent> findByEventEqualsAndClientEquals(@NonNull Event event, @NonNull Client client);

}