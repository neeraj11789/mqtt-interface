package com.practo.instahms.pubsub.request;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.request
 * @date 07/10/21
 */
@Setter
@Getter
public class EventPublishRequest extends EventMetaData {

    @NotNull
    private JsonNode message;

}