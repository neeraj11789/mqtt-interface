package com.practo.instahms.pubsub.service;

import com.practo.instahms.pubsub.request.CallBackRequest;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 07/10/21
 */
@Component
public class CallbackRequestHandler {

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public void execute(final CallBackRequest callBackRequest){
        // @todo: Add validations
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, callBackRequest.getBody().toString());
        Request request = new Request.Builder()
                .url(callBackRequest.getUrl())
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            final ResponseBody responseBody = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
