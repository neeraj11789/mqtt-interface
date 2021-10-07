package com.practo.instahms.pubsub.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.request
 * @date 04/10/21
 */
@Data
public class AuthTokenRequest implements Serializable {

    @NotBlank
    private String name;

    @NotBlank
    private String clientId;

    @NotEmpty
    private List<String> scopes;
}
