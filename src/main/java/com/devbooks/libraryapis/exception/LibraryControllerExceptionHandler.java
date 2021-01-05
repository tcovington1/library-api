package com.devbooks.libraryapis.exception;

import com.devbooks.libraryapis.model.common.LibraryApiError;
import com.devbooks.libraryapis.utils.LibraryApiUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class LibraryControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(LibraryResourceNotFoundException.class)
    public final ResponseEntity<LibraryApiError> handleLibraryResourceNotFoundException(
            LibraryResourceNotFoundException e, WebRequest webRequest) {
        return new ResponseEntity<>(new LibraryApiError(e.getTraceId(), e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LibraryResourceAlreadyExistsException.class)
    public final ResponseEntity<LibraryApiError> handleLibraryResourceAlreadyExistsException(
            LibraryResourceAlreadyExistsException e, WebRequest webRequest) {
        return new ResponseEntity<>(new LibraryApiError(e.getTraceId(), e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(LibraryResourceBadRequestException.class)
    public final ResponseEntity<LibraryApiError> handleLibraryResourceBadRequestException(
            LibraryResourceBadRequestException e, WebRequest webRequest) {
        return new ResponseEntity<>(new LibraryApiError(e.getTraceId(), e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<LibraryApiError> handleAllExceptions(
            Exception e, WebRequest webRequest) {

        String traceId =  getTraceId(webRequest);

        return new ResponseEntity<>(new LibraryApiError(traceId, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private String getTraceId(WebRequest webRequest) {
        String traceId = webRequest.getHeader("Trace-Id");
        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();

        }
        return traceId;
    }
}
