package org.xcolab.service.members.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@EnableFeignClients(basePackages = {"org.xcolab.client.tracking"})
@Profile("!test")
public class FeignConfig {

}
