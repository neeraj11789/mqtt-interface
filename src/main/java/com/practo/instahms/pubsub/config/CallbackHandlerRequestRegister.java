package com.practo.instahms.pubsub.config;

import com.practo.instahms.pubsub.service.callback.CallbackRequestHandlerFactory;
import com.practo.instahms.pubsub.service.callback.GetCallBackRequestHandlerService;
import com.practo.instahms.pubsub.service.callback.PatchCallBackRequestHandlerService;
import com.practo.instahms.pubsub.service.callback.PostCallBackRequestHandlerService;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;


/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.config
 * @date 08/10/21
 */

@Component
@DependsOn({"okHttpClient"})
public class CallbackHandlerRequestRegister {

    public CallbackHandlerRequestRegister() {
        CallbackRequestHandlerFactory.createAdapter( new GetCallBackRequestHandlerService() );
        CallbackRequestHandlerFactory.createAdapter( new PostCallBackRequestHandlerService() );
        CallbackRequestHandlerFactory.createAdapter( new PatchCallBackRequestHandlerService() );
    }
}
