package com.practo.instahms.pubsub.config;

import lombok.AllArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.config
 * @date 08/10/21
 */
@AllArgsConstructor
public class HttpRequestInterceptor implements Interceptor {

    private String contentType;

    public Response intercept(Interceptor.Chain chain)
            throws IOException {

        Request originalRequest = chain.request();
        Request requestWithUserAgent = originalRequest
                .newBuilder()
                .header("Content-Type", contentType)
                .build();

        return chain.proceed(requestWithUserAgent);
    }
}
