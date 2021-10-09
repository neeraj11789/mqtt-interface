package com.practo.instahms.pubsub.exception;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.exception
 * @date 10/10/21
 */
public class UnSupportedEventException extends BaseException {
    public UnSupportedEventException(final int code, final String message) {
        super(code, message);
    }
}