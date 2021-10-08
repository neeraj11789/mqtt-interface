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
@Table(name = "auth_token")
@Where(clause = "is_active = 1")
public class AuthToken extends BaseDomain{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "varchar(255)", nullable = false, unique = true)
    private String prefix;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String token;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String scopes;

    // @todo: Move this flow to the User Auth API
    // @todo: From here we would be calling those apis for authentication
//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    private Client client;

    @Column(columnDefinition = "varchar(255)", nullable = false)
    private String userId;
}
