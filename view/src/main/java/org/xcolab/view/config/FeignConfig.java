package org.xcolab.view.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.util.autoconfigure.AbstractFeignConfig;

@Component
@Profile("!test")
@EnableFeignClients(basePackages = {"org.xcolab.client"})
public class FeignConfig extends AbstractFeignConfig {
}
