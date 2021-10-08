package com.practo.instahms.pubsub.service;

import com.practo.instahms.pubsub.domain.AuthToken;
import com.practo.instahms.pubsub.mapper.AuthTokenRequestMapper;
import com.practo.instahms.pubsub.mapper.AuthTokenResponseMapper;
import com.practo.instahms.pubsub.repository.AuthTokenRepository;
import com.practo.instahms.pubsub.request.AuthTokenRequest;
import com.practo.instahms.pubsub.request.AuthTokenValidateRequest;
import com.practo.instahms.pubsub.response.AuthTokenResponse;
import com.practo.instahms.pubsub.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

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
        final AuthToken token = AuthTokenRequestMapper.INSTANCE.requestToAuthToken( request );
        validateUser(request.getUserId());
        if(repository.findByNameEqualsAndUserIdEquals( request.getName(), request.getUserId() ).isPresent()){
            throw new RuntimeException("key_already_present");
        }
        token.setPrefix( Utils.generateTokenPrefix() );
        // @todo: Show normal token while creation only and save the encoded token
//        token.setToken( Base64.getEncoder().encodeToString( Utils.generateToken().getBytes() ) );
        token.setToken( Utils.generateToken() );
        return token;
    }

    private void validateUser(String userId) {
        // @todo: Change to user api
        // throws exception is user does not exist etc
    }

    public AuthTokenResponse getToken(@NotBlank String userId, final String externalId) {
        validateUser( userId );
        final Optional<AuthToken> optionalAuthToken = repository.findByNameEqualsAndUserIdEquals( externalId, userId );
        return optionalAuthToken.map( (at) -> AuthTokenResponseMapper.INSTANCE.authTokenToResponse( at ) ).orElseThrow( () -> new RuntimeException( "token_not_found" ) );
    }

    public Boolean validateToken(final AuthTokenValidateRequest request) {
        validateUser( request.getUserId() );
        final String encodedToken = request.getToken(); // @todo: Use base64 encoded token - save encoded token and compare
        final Optional<AuthToken> byValueEquals = repository.findByTokenEquals( encodedToken );
        return byValueEquals.map( v -> v.getPrefix().equals( request.getPrefix() ) ).orElse( false );
    }
}
