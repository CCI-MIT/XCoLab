package org.xcolab.mailhandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import org.xcolab.mailhandler.config.MailProperties;

@SpringBootApplication
@EnableConfigurationProperties(MailProperties.class)
public class MailHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailHandlerApplication.class, args);
	}
}
