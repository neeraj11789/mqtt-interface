package com.practo.instahms.pubsub.exception;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.exception
 * @date 11/10/21
 */
public class ClientNotFoundException extends BaseException {
    public ClientNotFoundException(final int code, final String message) {
        super(code, message);
    }
}
