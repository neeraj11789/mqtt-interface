package com.practo.instahms.pubsub.service.callback;

import com.practo.instahms.pubsub.request.CallBackRequest;
import com.practo.instahms.pubsub.util.HttpMethod;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 08/10/21
 */
@Service
public class GetCallBackRequestHandlerService extends CallbackRequestHandlerBaseService<HttpMethod> {

    @Autowired
    private OkHttpClient client;

    public GetCallBackRequestHandlerService() {
        super( HttpMethod.GET );
    }

    @Override
    public void execute(final CallBackRequest callBackRequest) {
        Request request = new Request.Builder()
                .url(callBackRequest.getUrl())
                .build();

        try (Response response = client.newCall(request).execute()) {
            final ResponseBody responseBody = response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
