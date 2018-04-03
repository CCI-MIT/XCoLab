package org.xcolab.util.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("http-client")
public class HttpClientProperties {

    private Duration connectTimeout = Duration.ofSeconds(5);
    private Duration connectionRequestTimeout = Duration.ofSeconds(5);
    private Duration socketTimeout = Duration.ofSeconds(5);
    private final ConnectionPool connectionPool = new ConnectionPool();

    public Duration getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Duration connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Duration getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(Duration connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public Duration getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Duration socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public static class ConnectionPool {
        private int maxTotal = 20;
        private int defaultMaxPerRoute = 2;
        private final List<HttpHostConfiguration> maxPerRoutes = new ArrayList<>();

        public int getMaxTotal() {
            return maxTotal;
        }

        public void setMaxTotal(int maxTotal) {
            this.maxTotal = maxTotal;
        }

        public int getDefaultMaxPerRoute() {
            return defaultMaxPerRoute;
        }

        public void setDefaultMaxPerRoute(int defaultMaxPerRoute) {
            this.defaultMaxPerRoute = defaultMaxPerRoute;
        }

        public List<HttpHostConfiguration> getMaxPerRoutes() {
            return maxPerRoutes;
        }
    }

    public static class HttpHostConfiguration {

        private String scheme;
        private String host;
        private int port;
        private int maxPerRoute;

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public int getMaxPerRoute() {
            return maxPerRoute;
        }

        public void setMaxPerRoute(int maxPerRoute) {
            this.maxPerRoute = maxPerRoute;
        }
    }
}
