package org.xcolab.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"org.xcolab.view", "org.xcolab.client" })
@EnableDiscoveryClient
public class ColabViewApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ColabViewApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ColabViewApplication.class, args);
    }
}

