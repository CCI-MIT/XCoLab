package org.xcolab.commons.http.exceptions.translation.facebook;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.HttpStatusCodeException;

import org.xcolab.commons.http.exceptions.translation.ExceptionTranslator;

public class FacebookExceptionTranslator extends ExceptionTranslator<FacebookErrorResponseObject> {

    public FacebookExceptionTranslator() {
        super(FacebookErrorResponseObject.class);
    }

    @Override
    protected void doTranslateException(HttpStatusCodeException exception, String location,
            FacebookErrorResponseObject exceptionObject, HttpHeaders headers) {
        throw new FacebookException(exception, exceptionObject, location);
    }
    public static class FacebookException extends RuntimeException {
        public FacebookException(HttpStatusCodeException exception,
                FacebookErrorResponseObject responseObject, String location) {
            super(String.format("%s (status %d) encountered error %s: %s  (code: %d, fbtrace_id: %s)",
                    location.replaceFirst("client_secret=[^&]*", "client_secret=redacted")
                            .replaceFirst("code=[^&]*", "code=redacted"),
                    exception.getStatusCode().value(),
                    responseObject.getError().getType(), responseObject.getError().getMessage(),
                    responseObject.getError().getCode(), responseObject.getError().getFbtrace_id()),
                    exception);
        }
    }

}
