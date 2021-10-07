package com.practo.instahms.pubsub.service.callback;

import com.practo.instahms.pubsub.request.CallBackRequest;
import com.practo.instahms.pubsub.util.HttpMethod;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Map;

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

    public static final Character PATH_VARIABLE_PREFIX = '{';

    public static final Character PATH_VARIABLE_SUFFIX = '}';

    @NonNull
    private final T method;

    public abstract void execute(final OkHttpClient client, final CallBackRequest callBackRequest);

    protected String getUrl(final CallBackRequest callBackRequest) {
        String callBackRequestUrl = callBackRequest.getUrl();
        for (Map.Entry<String, Object> k : callBackRequest.getPathVariables().entrySet()) {
            callBackRequestUrl = callBackRequestUrl.replace( PATH_VARIABLE_PREFIX + k.getKey()
                    + PATH_VARIABLE_SUFFIX, (CharSequence) k.getValue() );
        }
        return callBackRequestUrl;
    }

    protected Headers getHeaders(final CallBackRequest callBackRequest) {
        final Headers.Builder headerBuilder = new Headers.Builder();
        callBackRequest.getHeaders().forEach( headerBuilder::add );
        final Headers headers = headerBuilder.build();
        return headers;
    }
}
