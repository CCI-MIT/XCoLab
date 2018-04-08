package org.xcolab.util.autoconfigure;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.HttpHost;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import org.xcolab.util.autoconfigure.HttpClientProperties.ConnectionPool;
import org.xcolab.util.autoconfigure.HttpClientProperties.HttpHostConfiguration;
import org.xcolab.util.http.ServiceRequestUtils;

@Configuration
@EnableConfigurationProperties({XCoLabProperties.class, HttpClientProperties.class})
@ConditionalOnClass(LoadBalanced.class)
public class DiscoveryAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DiscoveryAutoConfiguration.class);

    private final HttpClientProperties httpClientProperties;

    @Autowired
    public DiscoveryAutoConfiguration(HttpClientProperties httpClientProperties) {
        this.httpClientProperties = httpClientProperties;
    }

    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        final ConnectionPool connectionPoolProperties = httpClientProperties.getConnectionPool();
        connManager.setMaxTotal(connectionPoolProperties.getMaxTotal());
        connManager.setDefaultMaxPerRoute(connectionPoolProperties.getDefaultMaxPerRoute());
        log.debug("PoolingHttpClientConnectionManager: maxTotal={} ({} per route)",
                connectionPoolProperties.getMaxTotal(),
                connectionPoolProperties.getDefaultMaxPerRoute());

        if (CollectionUtils.isNotEmpty(connectionPoolProperties.getMaxPerRoutes())) {
            for (HttpHostConfiguration httpHostConfig : connectionPoolProperties.getMaxPerRoutes()) {
                HttpHost host = new HttpHost(httpHostConfig.getHost(), httpHostConfig.getPort(),
                        httpHostConfig.getScheme());
                connManager.setMaxPerRoute(new HttpRoute(host), httpHostConfig.getMaxPerRoute());
                log.debug("Set max connections to {} for route {}",
                        httpHostConfig.getMaxPerRoute(), host.toString());
            }
        }
        return connManager;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        final CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(poolingHttpClientConnectionManager())
                //TODO: temporarily disabled pending resolution of a timeout problem
//                .setDefaultRequestConfig(RequestConfig.custom()
//                        .setConnectTimeout(
//                                (int) httpClientProperties.getConnectTimeout())
//                        .setConnectionRequestTimeout(
//                                (int) httpClientProperties.getConnectionRequestTimeout())
//                        .setSocketTimeout((int) httpClientProperties.getSocketTimeout())
//                        .build())
                .build();
        log.debug("HttpClient: connectTimeout={}, connectionRequestTimeout={}, socketTimeout={}",
                httpClientProperties.getConnectTimeout(),
                httpClientProperties.getConnectionRequestTimeout(),
                httpClientProperties.getSocketTimeout());

        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

    @LoadBalanced
    @Bean
    public EurekaClientInitializer commandLineRunner(RestTemplate restTemplate,
            XCoLabProperties properties) {
        return new EurekaClientInitializer(restTemplate, properties);
    }

    private static class EurekaClientInitializer implements CommandLineRunner {

        private final RestTemplate restTemplate;
        private final XCoLabProperties properties;

        @Autowired
        public EurekaClientInitializer(RestTemplate restTemplate, XCoLabProperties properties) {
            this.restTemplate = restTemplate;
            this.properties = properties;
        }

        @Override
        public void run(String... strings) {
            log.debug("Initializing ServiceRequestUtils in namespace {} with {}",
                    properties.getNamespace(), restTemplate);
            ServiceRequestUtils.initialize(restTemplate, properties.getNamespace());
        }
    }
}
