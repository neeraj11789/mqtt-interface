package com.practo.instahms.pubsub.repository;


import com.practo.instahms.pubsub.domain.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.repository
 * @date 04/10/21
 */

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {

    Optional<AuthToken> findByTokenEquals(@NonNull String value);

    Optional<AuthToken> findByNameEqualsAndUserIdEquals(@NonNull String name, @Nullable String userId);

}
