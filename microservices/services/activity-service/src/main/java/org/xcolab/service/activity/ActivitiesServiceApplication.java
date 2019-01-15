package org.xcolab.service.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ActivitiesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitiesServiceApplication.class, args);
    }
}
