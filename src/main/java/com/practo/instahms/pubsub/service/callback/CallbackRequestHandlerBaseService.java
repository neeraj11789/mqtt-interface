package com.practo.instahms.pubsub.service.callback;

import com.practo.instahms.pubsub.request.CallBackRequest;
import com.practo.instahms.pubsub.util.HttpMethod;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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
        if(Objects.nonNull( callBackRequest.getPathVariables() )){
            for (Map.Entry<String, Object> k : callBackRequest.getPathVariables().entrySet()) {
                callBackRequestUrl = callBackRequestUrl.replace( PATH_VARIABLE_PREFIX + k.getKey()
                        + PATH_VARIABLE_SUFFIX, (CharSequence) k.getValue() );
            }
        }
        return callBackRequestUrl;
    }

    protected Optional<Headers> getHeaders(final CallBackRequest callBackRequest) {
        final boolean hasHeaders = Objects.nonNull(callBackRequest.getHeaders());
        if (hasHeaders) {
            final Headers.Builder headerBuilder = new Headers.Builder();
            callBackRequest.getHeaders().entrySet().stream().filter( k -> !StringUtils.isEmpty( k.getKey() )
                    && !StringUtils.isEmpty( k.getValue() ) ).forEach( k -> headerBuilder.add( k.getKey(), k.getValue() ) );
            final Headers headers = headerBuilder.build();
            return Optional.of( headers );
        }
        return Optional.empty();
    }
}
