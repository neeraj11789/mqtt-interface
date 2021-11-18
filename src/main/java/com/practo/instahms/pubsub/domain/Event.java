package com.practo.instahms.pubsub.domain;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(unique = true, nullable = false, columnDefinition = "varchar(255)")
    private String event;

    // @Note: Added topic for reference as for older records someone can change mapping
    @Column(nullable = false)
    private String topic;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "client_subscribed_event",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"))
    private List<Client> subscriberClients;

//    @ManyToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name = "publisher_client_id", nullable = false)
//    private Client publisherClient;

}