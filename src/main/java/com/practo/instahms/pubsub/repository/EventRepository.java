package com.practo.instahms.pubsub.repository;

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
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findByEventEquals(@NonNull String event);



}