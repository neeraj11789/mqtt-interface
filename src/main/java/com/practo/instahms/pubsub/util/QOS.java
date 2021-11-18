package com.practo.instahms.pubsub.util;

import com.hivemq.client.mqtt.datatypes.MqttQos;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.util
 * @date 10/10/21
 */

@Getter
@AllArgsConstructor
public enum QOS {

    zero (MqttQos.AT_LEAST_ONCE),
    one (MqttQos.EXACTLY_ONCE),
    two (MqttQos.AT_MOST_ONCE);

    final private MqttQos qosVal;
}
