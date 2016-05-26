package org.xcolab.service.files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
// @EnableDiscoveryClient  //REENABLE FOR EUREKA
public class FilesServiceApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FilesServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FilesServiceApplication.class, args);
    }
}
