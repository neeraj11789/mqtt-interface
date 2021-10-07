package com.practo.instahms.pubsub.service.callback;

import com.practo.instahms.pubsub.request.CallBackRequest;
import com.practo.instahms.pubsub.util.HttpMethod;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 08/10/21
 */
@Service
public class PostCallBackRequestHandlerService extends CallbackRequestHandlerBaseService<HttpMethod> {

    public PostCallBackRequestHandlerService() {
        super( HttpMethod.POST );
    }

    @Override
    public void execute(final OkHttpClient client, final CallBackRequest callBackRequest) {
        // @todo: Add validations
        RequestBody body = RequestBody.create(JSON, callBackRequest.getBody().toString());

        Request request = new Request.Builder()
                .url(getUrl(callBackRequest))
                .post(body)
                .headers( getHeaders(callBackRequest) )
                .build();

        try (Response response = client.newCall(request).execute()) {
            final ResponseBody responseBody = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
