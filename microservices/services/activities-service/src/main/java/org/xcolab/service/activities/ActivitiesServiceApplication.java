package org.xcolab.service.activities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
// @EnableDiscoveryClient  //REENABLE FOR EUREKA

public class ActivitiesServiceApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ActivitiesServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ActivitiesServiceApplication.class, args);
    }

}
