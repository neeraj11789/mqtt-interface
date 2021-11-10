package com.practo.instahms.pubsub.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.request
 * @date 07/10/21
 */

@Data
public class EventSubscribeRequest implements Serializable {

    private CallBackRequest callback;

    private Options options = new Options();

    private String event;

    private String client;
}
