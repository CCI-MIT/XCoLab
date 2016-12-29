package org.xcolab.util.http.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.IOException;
import java.net.URI;

public final class ServiceExceptionTranslatorUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ServiceExceptionTranslatorUtil() {
    }

    static HttpServiceExceptionObject getExceptionObject(HttpStatusCodeException exception,
            HttpMethod httpMethod, URI requestUri) {
        try {
            return objectMapper.readValue(exception.getResponseBodyAsString(), HttpServiceExceptionObject.class);
        } catch (IOException e) {
            final String location = getLocation(httpMethod, requestUri, "(unknown path)");
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ServiceNotFoundException(location);
            } else {
                throw new TranslationException(exception, location, e);
            }
        }
    }

    public static void translateException(HttpStatusCodeException exception, HttpMethod httpMethod, URI requestUri) {
        final HttpServiceExceptionObject exceptionObject = getExceptionObject(exception, httpMethod, requestUri);
        final String location = getLocation(httpMethod, requestUri, exceptionObject.getPath());
        switch (exception.getStatusCode()) {
            case NOT_FOUND:
                throw new UncheckedEntityNotFoundException(exceptionObject, location);
            case BAD_REQUEST:
                throw new Http400BadRequestException(exceptionObject, location);
            case TOO_MANY_REQUESTS:
                throw new Http429TooManyRequestsException(exceptionObject, location);
            case INTERNAL_SERVER_ERROR:
                throw new Http500InternalServiceException(exceptionObject, location);
            default:
                throw new HttpRuntimeException(exceptionObject, location, exception);
        }
    }

    private static String getLocation(HttpMethod httpMethod, URI requestUri, String fallbackUri) {
        return (httpMethod != null ? httpMethod.name() : "(unknown method)")
                + " " + (requestUri != null ? requestUri.toString() : fallbackUri);
    }

    private static class TranslationException extends RuntimeException {
        public TranslationException(HttpStatusCodeException exceptionToTranslate,
                String location, Throwable cause) {
            super(
                    String.format("Could not translate %s for status code %d at %s: %s",
                            exceptionToTranslate.getClass().getSimpleName(), exceptionToTranslate.getStatusCode().value(),
                            location, exceptionToTranslate.getLocalizedMessage()),
                    cause);
        }
    }
}
