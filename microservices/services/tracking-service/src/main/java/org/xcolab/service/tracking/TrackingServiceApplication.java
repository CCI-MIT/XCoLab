package org.xcolab.service.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TrackingServiceApplication {

    private TrackingServiceApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(TrackingServiceApplication.class, args);
    }
}
