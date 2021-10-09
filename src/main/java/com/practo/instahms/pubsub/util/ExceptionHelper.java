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

    EVENT_NOT_SUPPORTED(4001 , "Event is not supported by the system currently");

    final private int code;

    final private String message;
}
