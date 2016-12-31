package org.xcolab.util.http.exceptions;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResponseErrorHandler;

import org.xcolab.util.http.interceptors.UriAwareResponseInterceptor;

import java.io.IOException;
import java.net.URI;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

    private final ResponseErrorHandler defaultErrorHandler = new DefaultResponseErrorHandler();

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
            ServiceExceptionTranslatorUtil.translateException(e, httpMethod, requestUri);
        }
    }
}
