package com.practo.instahms.pubsub.service.callback;

import com.practo.instahms.pubsub.request.CallBackRequest;
import com.practo.instahms.pubsub.util.HttpMethod;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 07/10/21
 */
@Component
@DependsOn("okHttpClient")
public class CallbackRequestHandler {

    @Autowired
    private OkHttpClient client;

    public CallbackRequestHandler() {
        CallbackRequestHandlerFactory.createAdapter( new GetCallBackRequestHandlerService() );
        CallbackRequestHandlerFactory.createAdapter( new PostCallBackRequestHandlerService() );
        CallbackRequestHandlerFactory.createAdapter( new PatchCallBackRequestHandlerService() );
    }

    public void execute(final CallBackRequest callBackRequest){
        final CallbackRequestHandlerBaseService<? extends HttpMethod> service = CallbackRequestHandlerFactory.getAdapterService( callBackRequest.getMethod() ).orElseThrow( () -> new RuntimeException( "method_not_supported" ) );
        service.execute( client, callBackRequest );
    }
}