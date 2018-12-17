package org.xcolab.service.flagging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FlaggingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlaggingServiceApplication.class, args);
    }

}
