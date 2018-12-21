package org.xcolab.view;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ColabViewApplication extends SpringBootServletInitializer {

    // required to run view as .war (which is required to use jsps)
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ColabViewApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ColabViewApplication.class, args);
    }
}

