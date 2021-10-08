package com.practo.instahms.pubsub.config;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.config
 * @date 08/10/21
 */

@Configuration
public class HttpClientConfiguration {

    @Value("${http.request.timeout}")
    private Integer requestTimeOut;

    @Bean
    public OkHttpClient okHttpClient(){
        // @todo: can have this extracted in core class - and expose method of those classes
        return new OkHttpClient.Builder()
                .addInterceptor(
                        new HttpRequestInterceptor("application/json"))
                .readTimeout(requestTimeOut, TimeUnit.MILLISECONDS)
                .build();
    }

}
