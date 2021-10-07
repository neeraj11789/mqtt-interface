package com.practo.instahms.pubsub.service.callback;

import com.practo.instahms.pubsub.util.HttpMethod;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 08/10/21
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CallbackRequestHandlerFactory {

    private static final Map<HttpMethod, CallbackRequestHandlerBaseService<? extends HttpMethod>> adapterServiceMap =
            new EnumMap<>(HttpMethod.class);

    public static void createAdapter(final CallbackRequestHandlerBaseService<? extends HttpMethod> service) {
        adapterServiceMap.put(service.getMethod(), service);
    }

    public static Optional<CallbackRequestHandlerBaseService<? extends HttpMethod>> getAdapterService(final HttpMethod method) {
        return Optional.ofNullable(adapterServiceMap.get(method));
    }
}
