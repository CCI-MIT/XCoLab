package org.xcolab.util.http.exceptions.translation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import org.xcolab.util.http.exceptions.ServiceNotFoundException;

import java.io.IOException;
import java.net.URI;

public abstract class ExceptionTranslator<T> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> exceptionObjectClass;

    protected ExceptionTranslator(Class<T> exceptionObjectClass) {
        this.exceptionObjectClass = exceptionObjectClass;
    }

    public void translateException(HttpStatusCodeException exception,
            HttpMethod httpMethod, URI requestUri) {
        final T exceptionObject = getExceptionObject(exception, httpMethod, requestUri);
        final String location = getLocation(httpMethod, requestUri);
        doTranslateException(exception, location, exceptionObject);
    }

    protected abstract void doTranslateException(HttpStatusCodeException exception,
            String location, T exceptionObject);

    public T getExceptionObject(HttpStatusCodeException exception,
            HttpMethod httpMethod, URI requestUri) {
        try {
            return objectMapper.readValue(exception.getResponseBodyAsString(), exceptionObjectClass);
        } catch (IOException e) {
            final String location = getLocation(httpMethod, requestUri);
            if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ServiceNotFoundException(location);
            } else {
                throw new TranslationException(exception, location, e);
            }
        }
    }

    protected RuntimeException translationException(HttpStatusCodeException exception,
            Throwable cause, String location) {
        return new TranslationException(exception, location, cause);
    }

    private static String getLocation(HttpMethod httpMethod, URI requestUri) {
        return (httpMethod != null ? httpMethod.name() : "(unknown method)")
                + " " + (requestUri != null ? requestUri.toString() : "(unknown path)");
    }

    private static class TranslationException extends RuntimeException {
        TranslationException(HttpStatusCodeException exceptionToTranslate,
                String location, Throwable cause) {
            super(String.format("Could not translate %s for status code %d at %s: %s",
                    exceptionToTranslate.getClass().getSimpleName(),
                    exceptionToTranslate.getStatusCode().value(), location,
                    exceptionToTranslate.getLocalizedMessage()), cause);
        }
    }
}
