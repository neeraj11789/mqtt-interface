package com.practo.instahms.pubsub.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.util
 * @date 10/10/21
 */
@Getter
@AllArgsConstructor
public enum ExceptionHelper {

    // @Note: Series 4001 - 4099 for events, 4100 - 4199 for client
    EVENT_NOT_SUPPORTED(4001 , "Event is not supported by the system currently"),
    CLIENT_NOT_FOUND(4104 , "Client Not Found");

    final private int code;

    final private String message;
}
