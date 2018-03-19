package org.xcolab.commons.http.interceptors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.AbstractClientHttpResponse;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class UriAwareResponseInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
            ClientHttpRequestExecution execution) throws IOException {
        return new ResponseWrapper(execution.execute(request, body), request.getMethod(), request.getURI());
    }

    public static class ResponseWrapper extends AbstractClientHttpResponse {

        private final ClientHttpResponse wrappedResponse;
        private final HttpMethod httpMethod;
        private final URI uri;

        public ResponseWrapper(ClientHttpResponse wrappedResponse, HttpMethod httpMethod, URI uri) {
            this.wrappedResponse = wrappedResponse;
            this.httpMethod = httpMethod;
            this.uri = uri;
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return wrappedResponse.getRawStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return wrappedResponse.getStatusText();
        }

        @Override
        public void close() {
            wrappedResponse.close();
        }

        @Override
        public InputStream getBody() throws IOException {
            return wrappedResponse.getBody();
        }

        @Override
        public HttpHeaders getHeaders() {
            return wrappedResponse.getHeaders();
        }

        public URI getUri() {
            return uri;
        }

        public HttpMethod getHttpMethod() {
            return httpMethod;
        }
    }
}
