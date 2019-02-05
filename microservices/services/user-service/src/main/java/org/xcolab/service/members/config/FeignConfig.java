package org.xcolab.service.members.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.util.autoconfigure.AbstractFeignConfig;

@Component
@Profile("!test")
@EnableFeignClients(basePackages = {
        "org.xcolab.client.activity",
        "org.xcolab.client.contest",
        "org.xcolab.client.tracking",
        "org.xcolab.client.email",
        "org.xcolab.client.admin"})
public class FeignConfig extends AbstractFeignConfig {
}
