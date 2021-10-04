package com.practo.instahms.pubsub.domain.repository;


import com.practo.instahms.pubsub.domain.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.domain.repository
 * @date 04/10/21
 */

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

}
