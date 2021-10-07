package com.practo.instahms.pubsub.service.callback;

import com.practo.instahms.pubsub.request.CallBackRequest;
import com.practo.instahms.pubsub.util.HttpMethod;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 08/10/21
 */

@Getter
@Setter
@Slf4j
@RequiredArgsConstructor
public abstract class CallbackRequestHandlerBaseService<T extends HttpMethod> {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @NonNull
    private final T method;

    public abstract void execute(final CallBackRequest callBackRequest);
}
