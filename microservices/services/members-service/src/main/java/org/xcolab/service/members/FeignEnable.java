package org.xcolab.service.members;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableFeignClients(basePackages = { "org.xcolab.client.tracking" })
@Profile("!test")
public class FeignEnable {

}
