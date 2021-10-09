package com.practo.instahms.pubsub.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines the list of events and supported
 * topics for those events
 *
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.util
 * @date 09/10/21
 */

@Getter
@ConfigurationProperties
public class EventTopicConfiguration {

    /**
     * For now we are keeping 1:1 mapping can be changed later
     */
    private final Map<String, String> practo_event = new HashMap<>();
}
