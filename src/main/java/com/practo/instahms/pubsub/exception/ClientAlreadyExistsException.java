package com.practo.instahms.pubsub.exception;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.exception
 * @date 24/10/21
 */
public class ClientAlreadyExistsException extends BaseException {
    public ClientAlreadyExistsException(final int code, final String message) {
        super(code, message);
    }
}
