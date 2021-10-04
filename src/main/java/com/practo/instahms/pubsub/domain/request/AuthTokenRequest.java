package com.practo.instahms.pubsub.domain.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.domain.request
 * @date 04/10/21
 */
@Data
public class AuthTokenRequest implements Serializable {

    @NotBlank
    private String name;

    @NotEmpty
    private List<String> scopes;
}
