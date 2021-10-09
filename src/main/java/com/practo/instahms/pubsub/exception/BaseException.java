package com.practo.instahms.pubsub.exception;

import lombok.Data;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.exception
 * @date 10/10/21
 */

@Data
public class BaseException extends RuntimeException {
    private Integer code;

    public BaseException(int code, String message) {
        super( message );
        this.code = code;
    }
}
