package com.practo.instahms.pubsub.domain.repository;

import com.practo.instahms.pubsub.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByClientIdEquals(final @NonNull String clientId);

}