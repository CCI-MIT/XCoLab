package org.xcolab.service.members.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.util.autoconfigure.AbstractFeignConfig;

@Component
@Profile("!test")
@EnableFeignClients(basePackages = {"org.xcolab.client.tracking"})
public class FeignConfig extends AbstractFeignConfig {
}
