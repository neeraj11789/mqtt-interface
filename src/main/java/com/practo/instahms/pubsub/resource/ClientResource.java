package com.practo.instahms.pubsub.resource;

import com.practo.instahms.pubsub.domain.Client;
import com.practo.instahms.pubsub.request.ClientRequest;
import com.practo.instahms.pubsub.request.EventPublishRequest;
import com.practo.instahms.pubsub.request.EventSubscribeRequest;
import com.practo.instahms.pubsub.service.ClientService;
import com.practo.instahms.pubsub.service.MqttService;
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

    @Autowired
    private MqttService mqttService;

    @PostMapping()
    private ResponseEntity<Client> newClient(final @Valid @RequestBody ClientRequest request){
        request.setStatus( ClientStatus.offline ); // register new client with offline status
        service.createClient(request);
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }

    @GetMapping("/{clientId}")
    private ResponseEntity<Client> getClient(final @PathVariable String clientId){
        final Optional<Client> optionalClient = service.getClient( clientId );
        return optionalClient.map( c -> ResponseEntity.ok().body( c ) ).orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PutMapping("/{clientId}/connect")
    private ResponseEntity<Client> connect(final @PathVariable String clientId){
        service.connectClient(clientId);
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }

    @PutMapping("/{clientId}/disconnect")
    private ResponseEntity<Client> updateClientStatus(final @PathVariable String clientId){
        service.disconnectClient(clientId);
        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }

    @PostMapping("/{clientId}/publish")
    private ResponseEntity<Object> publish(final @PathVariable String clientId,
                                           final @Valid @RequestBody EventPublishRequest event){
        mqttService.publish( event );
        return ResponseEntity.status( HttpStatus.OK ).build();
    }

    @PostMapping("/{clientId}/subscribe")
    private ResponseEntity<Object> subscribe(final @PathVariable String clientId,
                                             final @Valid @RequestBody EventSubscribeRequest event ){
        mqttService.subscribe( event );
        return ResponseEntity.status( HttpStatus.OK ).build();
    }
}
