package org.xcolab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
// @EnableDiscoveryClient  //REENABLE FOR EUREKA
public class MembersServiceApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MembersServiceApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(MembersServiceApplication.class, args);
	}
}
