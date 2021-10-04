package com.practo.instahms.pubsub.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.practo.instahms.pubsub.util.Constants.DEFAULT_USER;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.domain
 * @date 04/10/21
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseDomain implements Serializable {

    private Timestamp createdAt = Timestamp.valueOf( LocalDateTime.now() );

    private Timestamp updatedAt = Timestamp.valueOf( LocalDateTime.now() );

    private String externalId = UUID.randomUUID().toString();

    private String createdBy = DEFAULT_USER;

    private String updatedBy = DEFAULT_USER;

    @Range(max = 1)
    private int isActive = 1;
}