package com.practo.instahms.pubsub.repository;

import com.practo.instahms.pubsub.domain.ClientSubscribedEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.repository
 * @date 04/10/21
 */

@Repository
public interface ClientSubscribedEventRepository extends JpaRepository<ClientSubscribedEvent, Long> {
}