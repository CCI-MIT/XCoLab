package org.xcolab.service.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
// @EnableDiscoveryClient  //REENABLE FOR EUREKA
public class SearchServiceApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SearchServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SearchServiceApplication.class, args);
    }
}
