package com.practo.instahms.pubsub.config;

import com.practo.instahms.pubsub.exception.BaseException;
import com.practo.instahms.pubsub.exception.UnSupportedEventException;
import com.practo.instahms.pubsub.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.management.BadAttributeValueExpException;

/**
 * @author Neeraj Gupta<neeraj11789@gmail.com>
 * @package com.practo.instahms.pubsub.config
 * @date 10/10/21
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handler for Non-Acceptable Requests
     *
     * @param baseException
     * @param request
     * @return
     */
    @ExceptionHandler(value = {UnSupportedEventException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<ErrorResponse> handleNotAcceptableException(final BaseException baseException, final WebRequest request) {
        return buildErrorResponse( baseException, HttpStatus.NOT_ACCEPTABLE, baseException.getCode(), request );
    }

    /**
     * Handler for NotFound Resources
     * @param notFoundException
     * @param request
     * @return
     */
    @ExceptionHandler(value = {ChangeSetPersister.NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(final BaseException notFoundException, final WebRequest request){
        return buildErrorResponse(notFoundException, HttpStatus.NOT_FOUND, notFoundException.getCode(), request);
    }

    /**
     * Handler for Bad Requests
     * @param baseException
     * @param request
     * @return
     */
    @ExceptionHandler(value = {BadAttributeValueExpException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleBadRequestException(final BaseException baseException, final WebRequest request){
        log.error("Bad Request Exception", baseException );
        return buildErrorResponse( baseException, HttpStatus.BAD_REQUEST, baseException.getCode(), request);
    }


    /**
     * Handler for Internal Server Errors
     *
     * @param serverException
     * @param request
     * @return
     */
    @ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleAllUncaughtException(final BaseException serverException, final WebRequest request) {
        return buildErrorResponse( serverException, HttpStatus.INTERNAL_SERVER_ERROR, serverException.getCode(), request );
    }

    /**
     * Response Builder for Exceptions
     *
     * @param exception
     * @param httpStatus
     * @param errorCode
     * @param request
     * @return
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(final Exception exception, final HttpStatus httpStatus, final Integer errorCode, final WebRequest request) {
        final ErrorResponse errorResponse = new ErrorResponse( errorCode, exception.getMessage() );
        log.error( "Error Processing Request {} ErrorCode {}, HttpStatus {}, Exception", request, errorCode, httpStatus, ExceptionUtils.getRootCause( exception ) );
        return ResponseEntity.status( httpStatus ).body( errorResponse );
    }
}