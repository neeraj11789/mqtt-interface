package com.practo.instahms.pubsub.resource;

import com.practo.instahms.pubsub.domain.AuthToken;
import com.practo.instahms.pubsub.domain.request.AuthTokenRequest;
import com.practo.instahms.pubsub.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.resource
 * @date 04/10/21
 */

@RestController
@RequestMapping("/v1/auth")
@Slf4j
public class AuthResource {

    @Autowired
    private AuthService service;

    @PostMapping()
    private ResponseEntity<AuthToken> register(final @RequestBody AuthTokenRequest request){
        final AuthToken token = service.registerNewKey( request );
        if (Objects.isNull(token)) {
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().body(token);
    }
}
