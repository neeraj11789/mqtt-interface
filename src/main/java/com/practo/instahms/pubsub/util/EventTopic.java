package com.practo.instahms.pubsub.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Defines the list of events and supported
 * topics for those events
 *
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.util
 * @date 09/10/21
 */

@Getter
@AllArgsConstructor
public enum EventTopic {

    DOCTOR_CONSULTATION_REQUESTED("com/practo/insta/consultation/doctor/requested"),
    DOCTOR_CONSULTATION_APPROVED("com/practo/insta/consultation/doctor/approved"),
    DOCTOR_CONSULTATION_STARTED("com/practo/insta/consultation/doctor/started"),
    DOCTOR_CONSULTATION_ENDED("com/practo/insta/consultation/doctor/ended"),
    DOCTOR_CONSULTATION_CLOSED("com/practo/insta/consultation/doctor/closed");

    /**
     * For now we are keeping 1:1 mapping can be changed later
     */
    final private String topic;
}
