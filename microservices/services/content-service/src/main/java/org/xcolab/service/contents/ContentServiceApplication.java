package org.xcolab.service.contents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ContentServiceApplication {

    private ContentServiceApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(ContentServiceApplication.class, args);
    }

}
