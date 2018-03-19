package org.xcolab.util.http.exceptions.translation.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import org.xcolab.util.http.exceptions.Http400BadRequestException;
import org.xcolab.util.http.exceptions.Http401UnauthorizedException;
import org.xcolab.util.http.exceptions.Http403ForbiddenException;
import org.xcolab.util.http.exceptions.Http409ConflictException;
import org.xcolab.util.http.exceptions.Http429TooManyRequestsException;
import org.xcolab.util.http.exceptions.Http500InternalServiceException;
import org.xcolab.util.http.exceptions.HttpRuntimeException;
import org.xcolab.util.http.exceptions.ServiceNotFoundException;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;
import org.xcolab.util.http.exceptions.translation.ExceptionTranslator;

import java.util.stream.Collectors;

public class ServiceExceptionTranslator extends ExceptionTranslator<HttpServiceExceptionObject> {

    public ServiceExceptionTranslator() {
        super(HttpServiceExceptionObject.class);
    }

    @Override
    protected void doTranslateException(HttpStatusCodeException exception, String location,
            HttpServiceExceptionObject exceptionObject, HttpHeaders headers) {
        final String serviceName;
        if (headers.containsKey("X-Application-Context")) {
            serviceName = headers.get("X-Application-Context").stream()
                    .collect(Collectors.joining(","));
        } else {
            serviceName = "unknown-service";
        }
        switch (exception.getStatusCode()) {
            case NOT_FOUND:
                throw new UncheckedEntityNotFoundException(exceptionObject, location, serviceName);
            case BAD_REQUEST:
                throw new Http400BadRequestException(exceptionObject, location, serviceName);
            case TOO_MANY_REQUESTS:
                throw new Http429TooManyRequestsException(exceptionObject, location, serviceName);
            case INTERNAL_SERVER_ERROR:
                throw new Http500InternalServiceException(exceptionObject, location, serviceName);
            case FORBIDDEN:
                throw new Http403ForbiddenException(exceptionObject, location, serviceName);
            case UNAUTHORIZED:
                throw new Http401UnauthorizedException(exceptionObject, location, serviceName);
            case CONFLICT:
                throw new Http409ConflictException(exceptionObject, location, serviceName);
            default:
                throw new HttpRuntimeException(exceptionObject, location, serviceName, exception);
        }
    }

    @Override
    protected RuntimeException translationException(HttpStatusCodeException exception,
            Throwable cause, String location) {
        if (exception.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ServiceNotFoundException(location);
        } else {
            return super.translationException(exception, cause, location);
        }
    }
}
