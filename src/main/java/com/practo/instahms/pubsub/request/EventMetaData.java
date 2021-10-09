package com.practo.instahms.pubsub.request;

import com.practo.instahms.pubsub.util.EventTopic;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.request
 * @date 07/10/21
 */
@Setter
@Getter
public class EventMetaData {

    private EventTopic event;

    private String eventId = UUID.randomUUID().toString();

    private Timestamp eventTimestamp = Timestamp.valueOf( LocalDateTime.now());

    @NotBlank
    private String requestedIP = "127.0.0.1"; // @todo: Check later and populate
}
