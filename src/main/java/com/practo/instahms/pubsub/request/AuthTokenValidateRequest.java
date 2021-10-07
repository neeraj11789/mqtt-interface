package com.practo.instahms.pubsub.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.request
 * @date 05/10/21
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenValidateRequest implements Serializable {

    @NotBlank
    private String prefix;

    @NotBlank
    private String token;
}
