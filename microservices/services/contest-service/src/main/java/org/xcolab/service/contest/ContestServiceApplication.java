package org.xcolab.service.contest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class ContestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContestServiceApplication.class, args);
    }
}
