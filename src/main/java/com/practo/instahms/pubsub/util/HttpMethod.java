package com.practo.instahms.pubsub.util;

import lombok.Getter;

/**
 * @todo: Check if needs to support more methods
 *
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 08/10/21
 */

@Getter
public enum HttpMethod {

    GET( org.springframework.http.HttpMethod.GET ),
    POST( org.springframework.http.HttpMethod.POST ),
    PATCH( org.springframework.http.HttpMethod.PATCH );

    public final org.springframework.http.HttpMethod method;

    HttpMethod(org.springframework.http.HttpMethod method) {
        this.method = method;
    }
}
