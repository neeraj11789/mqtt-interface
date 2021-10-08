package com.practo.instahms.pubsub.service.callback;

import com.practo.instahms.pubsub.request.CallBackRequest;
import com.practo.instahms.pubsub.util.HttpMethod;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 08/10/21
 */

@Service
public class GetCallBackRequestHandlerService extends CallbackRequestHandlerBaseService<HttpMethod> {

    public GetCallBackRequestHandlerService() {
        super( HttpMethod.GET );
    }

    @Override
        public void execute(final OkHttpClient client, final CallBackRequest callBackRequest) {

        final Request.Builder requestBuilder = new Request.Builder();
        getHeaders( callBackRequest ).ifPresent( requestBuilder::headers );

        final Request request = requestBuilder.url( getUrl( callBackRequest ) ).build();

        try (Response response = client.newCall(request).execute()) {
            final ResponseBody responseBody = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUrl(final CallBackRequest callBackRequest){
        final String url = super.getUrl( callBackRequest );
        final HttpUrl.Builder urlBuilder = Objects.requireNonNull( HttpUrl.parse( url ) ).newBuilder();

        if(Objects.nonNull( callBackRequest.getQueryParams() )){
            for (Map.Entry<String, String> k : callBackRequest.getQueryParams().entrySet()) {
                urlBuilder.addQueryParameter( k.getKey(), k.getValue() );
            }
        }
        return urlBuilder.build().toString();
    }
}