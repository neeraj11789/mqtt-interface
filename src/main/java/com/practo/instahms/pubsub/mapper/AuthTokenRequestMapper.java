package com.practo.instahms.pubsub.mapper;

import com.practo.instahms.pubsub.domain.AuthToken;
import com.practo.instahms.pubsub.request.AuthTokenRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.mapper
 * @date 08/10/21
 */

@Mapper
public interface AuthTokenRequestMapper {

    AuthTokenRequestMapper INSTANCE = Mappers.getMapper(AuthTokenRequestMapper.class);

    @Mapping( source = "scopes", target = "scopes", qualifiedByName = "listToCSV" )
    AuthToken requestToAuthToken(final AuthTokenRequest authToken);

    @Named( "listToCSV" )
    static String mapScopes(final List<String> scopes){
        return scopes.stream().collect( Collectors.joining( "," ) );
//        return String.join( ",", scopes );
    }
}
