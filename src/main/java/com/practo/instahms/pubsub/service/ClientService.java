package com.practo.instahms.pubsub.service;

import com.practo.instahms.pubsub.domain.Client;
import com.practo.instahms.pubsub.repository.ClientRepository;
import com.practo.instahms.pubsub.request.ClientRequest;
import com.practo.instahms.pubsub.util.ClientStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.service
 * @date 05/10/21
 */

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Optional<Client> getClient(final String clientId) {
        return repository.findByClientIdEquals( clientId );
    }

    public void connectClient(final ClientRequest request) {
        final Optional<Client> optionalClient = getClient( request.getClientId() );

        final Client client = optionalClient.orElseGet( () -> {
            final Client newClient = new Client();
            newClient.setClientId( request.getClientId() );
            newClient.setUserId( request.getUserId() );
            return newClient;
        } );
        client.setStatus( request.getStatus() );
        repository.save( client );
    }

    public void disconnectClient(final String clientId) {
        final Optional<Client> optionalClient = getClient( clientId );
        // @todo: Proper Exception Handling SpringBoot and Application
        final Client client = optionalClient.orElseThrow( () -> new RuntimeException( "Client Not Found" ) );
        client.setStatus( ClientStatus.offline );
        repository.save( client );
    }
}
