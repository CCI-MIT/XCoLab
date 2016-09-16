package org.xcolab.util.http.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.IOException;

public final class ServiceExceptionTranslatorUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ServiceExceptionTranslatorUtil() {
    }

    static HttpServiceExceptionObject getExceptionObject(HttpStatusCodeException exception,
            String path) {
        try {
            return objectMapper.readValue(exception.getResponseBodyAsString(), HttpServiceExceptionObject.class);
        } catch (IOException e) {
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ServiceNotFoundException(path);
            } else {
                throw new TranslationException(exception, path, e);
            }
        }
    }

    public static void translateException(HttpStatusCodeException exception, String path) {
        final HttpServiceExceptionObject exceptionObject = getExceptionObject(exception, path);
        switch (exception.getStatusCode()) {
            case NOT_FOUND:
                throw new UncheckedEntityNotFoundException(exceptionObject);
            case BAD_REQUEST:
                throw new Http400BadRequestException(exceptionObject);
            case TOO_MANY_REQUESTS:
                throw new Http429TooManyRequestsException(exceptionObject);
            case INTERNAL_SERVER_ERROR:
                throw new Http500InternalServiceException(exceptionObject);
            default:
                throw new HttpRuntimeException(exceptionObject, exception);
        }
    }

    private static class TranslationException extends RuntimeException {
        public TranslationException(HttpStatusCodeException exceptionToTranslate, String path, Throwable cause) {
            super(
                    String.format("Could not translate %s for status code %d at path %s: %s",
                            exceptionToTranslate.getClass().getSimpleName(), exceptionToTranslate.getStatusCode().value(),
                            path, exceptionToTranslate.getLocalizedMessage()),
                    cause);
        }
    }
}
