package org.xcolab.util.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("http-client")
public class HttpClientProperties {

    //TODO: Duration only works in spring boot 2.0
//    private Duration connectTimeout = Duration.ofSeconds(5);
//    private Duration connectionRequestTimeout = Duration.ofSeconds(5);
//    private Duration socketTimeout = Duration.ofSeconds(5);
    private long connectTimeout = 5000;
    private long connectionRequestTimeout = 5000;
    private long socketTimeout = 5000;
    private final ConnectionPool connectionPool = new ConnectionPool();

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(long connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public long getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(long connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public long getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(long socketTimeout) {
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
