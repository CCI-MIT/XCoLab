package org.xcolab.service.content;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.xcolab.commons.monitoring.Prometheus;

@SpringBootApplication
@EnableDiscoveryClient
public class ContentServiceApplication {

    public static void main(String[] args) {
        Prometheus prometheus = new Prometheus();
        prometheus.run();
        SpringApplication.run(ContentServiceApplication.class, args);
    }
}
