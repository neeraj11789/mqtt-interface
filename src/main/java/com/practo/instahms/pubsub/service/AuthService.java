package com.practo.instahms.pubsub.service;

import com.practo.instahms.pubsub.domain.AuthToken;
import com.practo.instahms.pubsub.domain.Client;
import com.practo.instahms.pubsub.repository.AuthTokenRepository;
import com.practo.instahms.pubsub.request.AuthTokenRequest;
import com.practo.instahms.pubsub.request.AuthTokenValidateRequest;
import com.practo.instahms.pubsub.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    ClientService service;

    public AuthToken registerNewKey(final AuthTokenRequest request) {
        final AuthToken token = createNewToken(request);
        repository.save( token );
        return token;
    }

    private AuthToken createNewToken(final AuthTokenRequest request) {
        final AuthToken token = new AuthToken();
        final Optional<Client> optionalClient = service.getClient( request.getClientId() );
        final Client client = optionalClient.orElseThrow( () -> new RuntimeException( "Client Not Found" ) );
        token.setClient( client );
        token.setName( request.getName() );
        final String scopes = request.getScopes().stream().collect( Collectors.joining( "," ) );
        token.setScopes( scopes );
        token.setPrefix( Utils.generateTokenPrefix() );
        token.setValue( Base64.getEncoder().encodeToString( Utils.generateToken().getBytes() ) );
        return token;
    }

    public Optional<List<AuthToken>> search(final String externalId, final String prefix, final String name) {
//        final List<AuthToken> tokens = repository.search( externalId, prefix, name );
        final List<AuthToken> tokens = repository.search( externalId, prefix, name );
        return Optional.ofNullable( tokens );
    }

    public Optional<AuthToken> getToken(final String externalId) {
        return repository.findByExternalIdEquals( externalId );
    }

    public Optional<AuthToken> getToken(final Long id) {
        return repository.findById( id );
    }

    public Boolean validateToken(final AuthTokenValidateRequest request) {
        final String encodedString = request.getToken();
        final Optional<AuthToken> byValueEquals = repository.findByValueEquals( encodedString );
        return byValueEquals.map( v -> v.getPrefix().equals( request.getPrefix() ) ).orElse( false );
    }
}
