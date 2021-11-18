package com.practo.instahms.pubsub.mapper;

import com.practo.instahms.pubsub.domain.Client;
import com.practo.instahms.pubsub.request.ClientRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.mapper
 * @date 08/10/21
 */

@Mapper
public interface ClientRequestMapper {

    ClientRequestMapper INSTANCE = Mappers.getMapper(ClientRequestMapper.class);

//    @Mapping(source = "numberOfSeats", target = "seatCount")
    Client requestToClient(final ClientRequest request);
}