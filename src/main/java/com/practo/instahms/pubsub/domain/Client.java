package com.practo.instahms.pubsub.domain;

import com.practo.instahms.pubsub.util.ClientStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.domain
 * @date 05/10/21
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "client")
@Where(clause = "is_active = 1")
public class Client extends BaseDomain{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    private String userId;

    @NotBlank
    private String clientId;

    @Enumerated(EnumType.STRING)
    private ClientStatus status;

}