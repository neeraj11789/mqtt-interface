package com.practo.instahms.pubsub.mapper;

import com.practo.instahms.pubsub.domain.AuthToken;
import com.practo.instahms.pubsub.response.AuthTokenResponse;
import com.practo.instahms.pubsub.util.Utils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.mapper
 * @date 08/10/21
 */

@Mapper
public interface AuthTokenResponseMapper {

    AuthTokenResponseMapper INSTANCE = Mappers.getMapper(AuthTokenResponseMapper.class);

    @Mapping( source = "token", target = "token", qualifiedByName = "maskedToken")
    AuthTokenResponse authTokenToResponse(AuthToken token);

    @Named( "maskedToken" )
    static String mapToken(final String value){
        return Utils.mask( value, value.length() - 4, '*' );
    }
}