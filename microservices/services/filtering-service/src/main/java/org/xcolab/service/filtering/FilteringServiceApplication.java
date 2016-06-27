package org.xcolab.service.filtering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
// @EnableDiscoveryClient  //REENABLE FOR EUREKA
public class FilteringServiceApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FilteringServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FilteringServiceApplication.class, args);
    }
}
