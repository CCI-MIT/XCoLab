package org.xcolab.commons.http.exceptions.translation;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResponseErrorHandler;

import org.xcolab.commons.http.interceptors.UriAwareResponseInterceptor;

import java.io.IOException;
import java.net.URI;

public class TranslationErrorHandler implements ResponseErrorHandler {
    private final ResponseErrorHandler defaultErrorHandler = new DefaultResponseErrorHandler();
    private final ExceptionTranslator<?> exceptionTranslator;

    public TranslationErrorHandler(ExceptionTranslator<?> exceptionTranslator) {
        this.exceptionTranslator = exceptionTranslator;
    }

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return defaultErrorHandler.hasError(clientHttpResponse);
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        try {
            defaultErrorHandler.handleError(clientHttpResponse);
        } catch (HttpStatusCodeException e) {
            HttpMethod httpMethod = null;
            URI requestUri = null;
            if (clientHttpResponse instanceof UriAwareResponseInterceptor.ResponseWrapper) {
                UriAwareResponseInterceptor.ResponseWrapper uriAwareResponse
                        = (UriAwareResponseInterceptor.ResponseWrapper) clientHttpResponse;
                httpMethod = uriAwareResponse.getHttpMethod();
                requestUri = uriAwareResponse.getUri();
            }
            exceptionTranslator.translateException(e, httpMethod, requestUri);
        }
    }
}
