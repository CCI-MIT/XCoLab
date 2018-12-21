package org.xcolab.service.modeling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ModelingServiceApplication {

    private ModelingServiceApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(ModelingServiceApplication.class, args);
    }
}
