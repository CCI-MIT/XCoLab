package org.xcolab.util.http.exceptions.translation.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

import org.xcolab.util.http.exceptions.Http400BadRequestException;
import org.xcolab.util.http.exceptions.Http401UnauthorizedException;
import org.xcolab.util.http.exceptions.Http403ForbiddenException;
import org.xcolab.util.http.exceptions.Http429TooManyRequestsException;
import org.xcolab.util.http.exceptions.Http500InternalServiceException;
import org.xcolab.util.http.exceptions.HttpRuntimeException;
import org.xcolab.util.http.exceptions.ServiceNotFoundException;
import org.xcolab.util.http.exceptions.UncheckedEntityNotFoundException;
import org.xcolab.util.http.exceptions.translation.ExceptionTranslator;

public class ServiceExceptionTranslator extends ExceptionTranslator<HttpServiceExceptionObject> {

    public ServiceExceptionTranslator() {
        super(HttpServiceExceptionObject.class);
    }

    @Override
    protected void doTranslateException(HttpStatusCodeException exception, String location,
            HttpServiceExceptionObject exceptionObject) {
        switch (exception.getStatusCode()) {
            case NOT_FOUND:
                throw new UncheckedEntityNotFoundException(exceptionObject, location);
            case BAD_REQUEST:
                throw new Http400BadRequestException(exceptionObject, location);
            case TOO_MANY_REQUESTS:
                throw new Http429TooManyRequestsException(exceptionObject, location);
            case INTERNAL_SERVER_ERROR:
                throw new Http500InternalServiceException(exceptionObject, location);
            case FORBIDDEN:
                throw new Http403ForbiddenException(exceptionObject, location);
            case UNAUTHORIZED:
                throw new Http401UnauthorizedException(exceptionObject, location);
            default:
                throw new HttpRuntimeException(exceptionObject, location, exception);
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
