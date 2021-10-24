package com.practo.instahms.pubsub.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Table(name = "client_subscribed_event")
@Getter
@Setter
@Entity
@NoArgsConstructor
@Where(clause = "is_active = 1")
public class ClientSubscribedEvent extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NonNull
    private String callback;
}