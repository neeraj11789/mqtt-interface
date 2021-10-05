package com.practo.instahms.pubsub.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;


/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.domain
 * @date 04/10/21
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "api_keys")
@Where(clause = "is_active = 1")
public class AuthToken extends BaseDomain{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String prefix;

    private String value;

    private String scopes;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
