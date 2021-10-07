package com.practo.instahms.pubsub.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.practo.instahms.pubsub.util.HttpMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Map;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.request
 * @date 07/10/21
 */

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CallBackRequest {

    @NonNull
    private HttpMethod method;

    @NotBlank
    private String url;

    private JsonNode body;

    private Map<String, String> headers;

    private Map<String, Object> pathVariables;
}
