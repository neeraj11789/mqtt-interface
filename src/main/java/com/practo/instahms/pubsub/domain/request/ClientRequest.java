package com.practo.instahms.pubsub.domain.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.practo.instahms.pubsub.util.ClientStatus;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.domain.request
 * @date 05/10/21
 */

@Data
public class ClientRequest implements Serializable {

    @NotBlank
    private String clientId;

    @NotBlank
    private String userId;

    @Valid
    private ClientStatus status;
}
