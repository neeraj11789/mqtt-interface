package com.practo.instahms.pubsub.service.callback;

import com.practo.instahms.pubsub.request.CallBackRequest;
import com.practo.instahms.pubsub.util.HttpMethod;
import okhttp3.OkHttpClient;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 08/10/21
 */

public class PatchCallBackRequestHandlerService extends CallbackRequestHandlerBaseService<HttpMethod> {

    public PatchCallBackRequestHandlerService() {
        super( HttpMethod.PATCH );
    }

    @Override
    public void execute(final OkHttpClient client, final CallBackRequest callBackRequest) {
        throw new RuntimeException("Method not implemented");
    }
}
