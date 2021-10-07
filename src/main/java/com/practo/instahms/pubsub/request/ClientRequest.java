package com.practo.instahms.pubsub.request;

import com.practo.instahms.pubsub.util.ClientStatus;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.request
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
