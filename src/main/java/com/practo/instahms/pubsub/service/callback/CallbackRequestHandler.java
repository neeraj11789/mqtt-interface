package com.practo.instahms.pubsub.service.callback;

import com.practo.instahms.pubsub.request.CallBackRequest;
import com.practo.instahms.pubsub.util.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 07/10/21
 */
@Component
public class CallbackRequestHandler {

    public void execute(final CallBackRequest callBackRequest){
        final CallbackRequestHandlerBaseService<? extends HttpMethod> service = CallbackRequestHandlerFactory.getAdapterService( callBackRequest.getMethod() ).orElseThrow( () -> new RuntimeException( "method_not_supported" ) );
        service.execute( callBackRequest );
    }
}
