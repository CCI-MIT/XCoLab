package org.xcolab.service.contest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ContestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContestServiceApplication.class, args);
    }
}
