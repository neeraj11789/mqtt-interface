package com.practo.instahms.pubsub.service.callback;

import com.practo.instahms.pubsub.request.CallBackRequest;
import com.practo.instahms.pubsub.util.HttpMethod;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;

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

        Request request = new Request.Builder()
                .headers( getHeaders( callBackRequest ) )
                .url(getUrl( callBackRequest ))
                .build();

        try (Response response = client.newCall(request).execute()) {
            final ResponseBody responseBody = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
