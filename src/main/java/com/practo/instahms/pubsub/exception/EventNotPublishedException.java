package com.practo.instahms.pubsub.exception;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.exception
 * @date 11/10/21
 */
public class EventNotPublishedException extends BaseException {
    public EventNotPublishedException(final int code, final String message) {
        super(code, message);
    }
}
