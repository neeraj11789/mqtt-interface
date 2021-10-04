package com.practo.instahms.pubsub.service;

import com.practo.instahms.pubsub.domain.AuthToken;
import com.practo.instahms.pubsub.domain.repository.AuthTokenRepository;
import com.practo.instahms.pubsub.domain.request.AuthTokenRequest;
import com.practo.instahms.pubsub.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.stream.Collectors;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 04/10/21
 */
@Service
public class AuthService {

    @Autowired
    AuthTokenRepository repository;

    public AuthToken registerNewKey(final AuthTokenRequest request) {
        final AuthToken token = createNewToken(request);
        repository.save( token );
        return token;
    }

    private AuthToken createNewToken(final AuthTokenRequest request) {
        final AuthToken token = new AuthToken();
        token.setName( request.getName() );
        final String scopes = request.getScopes().stream().collect( Collectors.joining( "," ) );
        token.setScopes( scopes );
        token.setPrefix( Utils.generateTokenPrefix() );
        token.setValue( Base64.getEncoder().encodeToString( Utils.generateToken().getBytes() ) );
        return token;
    }
}
