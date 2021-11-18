package com.practo.instahms.pubsub.request;

import com.practo.instahms.pubsub.util.QOS;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.request
 * @date 10/10/21
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Options {

    @Valid
    private QOS qos = QOS.zero;

    @Valid
    private boolean retainFlag = false;
}
