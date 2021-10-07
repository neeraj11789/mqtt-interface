package com.practo.instahms.pubsub.resource;

import com.practo.instahms.pubsub.domain.Client;
import com.practo.instahms.pubsub.request.ClientRequest;
import com.practo.instahms.pubsub.service.ClientService;
import com.practo.instahms.pubsub.util.ClientStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.resource
 * @date 05/10/21
 */
@RestController
@RequestMapping("/v1/clients")
@Slf4j
public class ClientResource {

    @Autowired
    private ClientService service;

    @GetMapping("/{clientId}")
    private ResponseEntity<Client> getClient(final @PathVariable String clientId){
        final Optional<Client> optionalClient = service.getClient( clientId );
        return optionalClient.map( c -> ResponseEntity.ok().body( c ) ).orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping("/{clientId}/connect")
    private ResponseEntity<Client> connect(final @PathVariable String clientId, final @Valid @RequestBody ClientRequest request){
        request.setClientId( clientId ); // update the clientId - uri resource takes preference
        request.setStatus( ClientStatus.online ); // update status for connect
        service.connectClient(request);
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }

    @PutMapping("/{clientId}/disconnect")
    private ResponseEntity<Client> updateClientStatus(final @PathVariable String clientId){
        service.disconnectClient(clientId);
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }
}
