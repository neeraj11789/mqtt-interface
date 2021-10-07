package com.practo.instahms.pubsub.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

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

    private HttpMethod method;

    @NotBlank
    private String url;

    @NonNull
    private JsonNode body;

    private HttpHeaders headers;

    private Map<String, Object> options;
}
