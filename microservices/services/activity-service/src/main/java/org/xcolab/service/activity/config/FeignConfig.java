package org.xcolab.service.activity.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.util.autoconfigure.AbstractFeignConfig;

@Component
@Profile("!test")
@EnableFeignClients(basePackages = {"org.xcolab.client.contest",
        "org.xcolab.client.admin", "org.xcolab.client.comment"})
public class FeignConfig extends AbstractFeignConfig {
}
