package com.practo.instahms.pubsub.domain.repository;

import com.practo.instahms.pubsub.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}