package com.practo.instahms.pubsub.domain;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "event")
@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Where(clause = "is_active = 1")
public class Event extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(unique = true, nullable = false, columnDefinition = "varchar(255)")
    private String event;

    @NonNull
    @Column(nullable = true)
    private Timestamp eventTimestamp ;

    // @Note: Added topic for reference as for older records someone can change mapping
    @Column(nullable = true)
    private String topic;

    @Column(nullable = true)
    private String payload;

    private String requestedIP = "127.0.0.1"; // @todo: Check later and populate

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "client_subscribed_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> subscriberClients;

    @NonNull
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "publisher_client_id", nullable = false)
    private Client publisherClient;

}