package com.practo.instahms.pubsub.resource;

import com.practo.instahms.pubsub.domain.AuthToken;
import com.practo.instahms.pubsub.domain.request.AuthTokenRequest;
import com.practo.instahms.pubsub.domain.request.AuthTokenValidateRequest;
import com.practo.instahms.pubsub.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.resource
 * @date 04/10/21
 */

@RestController
@RequestMapping("/v1/auth-token")
@Slf4j
public class AuthResource {

    @Autowired
    private AuthService service;

    @PostMapping()
    private ResponseEntity<AuthToken> register(final @Valid @RequestBody AuthTokenRequest request){
        final AuthToken token = service.registerNewKey( request );
        if (Objects.isNull(token)) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status( HttpStatus.CREATED ).body(token);
    }

    @GetMapping("/search")
    private ResponseEntity<List<AuthToken>> searchToken(final @RequestParam(value = "external_id", required = false) String externalId,
                                                        final @RequestParam(required = false) String name,
                                                        final @RequestParam(required = false) String prefix){

        final Optional<List<AuthToken>> optionalAuthTokens = service.search( externalId, prefix, name );
        return optionalAuthTokens.map( authTokens -> ResponseEntity.ok().body( authTokens ) ).orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/{externalId}")
    private ResponseEntity<AuthToken> getToken(final @PathVariable String externalId){

        final Optional<AuthToken> optionalAuthToken = service.getToken( externalId);
        return optionalAuthToken.map( authToken -> ResponseEntity.ok().body( authToken ) ).orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @GetMapping("/internal/{id}")
    private ResponseEntity<AuthToken> getTokenByInternalId(final @PathVariable Long id){

        final Optional<AuthToken> optionalAuthToken = service.getToken( id);
        return optionalAuthToken.map( authToken -> ResponseEntity.ok().body( authToken ) ).orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping("/validate")
    private ResponseEntity<Object> validate(final @Valid @RequestBody AuthTokenValidateRequest request){
        final Boolean isValidToken = service.validateToken( request );
        return Optional.of( isValidToken ).filter( v -> v.equals( true ) ).map( v -> ResponseEntity.noContent().build() ).orElseGet( () -> ResponseEntity.badRequest().build() );
    }
}
