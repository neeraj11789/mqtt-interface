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
        return new OkHttpClient.Builder()
                .addInterceptor(
                        new DefaultContentTypeInterceptor("application/json"))
                .readTimeout(requestTimeOut, TimeUnit.MILLISECONDS)
                .build();
    }

}