package com.practo.instahms.pubsub.domain.repository;


import com.practo.instahms.pubsub.domain.AuthToken;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.domain.repository
 * @date 04/10/21
 */

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
    /**
     * @todo: Use JPA Criteria Queries - https://www.baeldung.com/hibernate-criteria-queries
     * @param externalId
     * @param prefix
     * @param name
     * @return
     */
    @Query("select a from AuthToken a where a.externalId = :externalId and a.prefix = :prefix and upper(a.name) like upper(concat(:name, '%')) order by a.id DESC")
    List<AuthToken> search(@Param("externalId") @Nullable String externalId, @Param("prefix") @Nullable String prefix, @Param("name") @Nullable String name);

    Optional<AuthToken> findByExternalIdEquals(@NonNull String externalId);
}
