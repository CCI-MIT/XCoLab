package org.xcolab.commons.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import org.xcolab.commons.http.ServiceRequestUtils;

@Configuration
@EnableConfigurationProperties(XCoLabProperties.class)
@ConditionalOnClass(LoadBalanced.class)
public class DiscoveryAutoConfiguration {

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
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
            ServiceRequestUtils.initialize(restTemplate, properties.getNamespace());
        }
    }
}
