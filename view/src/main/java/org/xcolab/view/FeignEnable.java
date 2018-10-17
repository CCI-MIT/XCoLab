package org.xcolab.view;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableFeignClients(basePackages = {"org.xcolab.view", "org.xcolab.client"})
@Profile("!test")
public class FeignEnable {

}
