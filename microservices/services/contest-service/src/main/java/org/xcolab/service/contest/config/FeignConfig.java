package org.xcolab.service.contest.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.util.autoconfigure.AbstractFeignConfig;

@Component
@Profile("!test")
@EnableFeignClients(basePackages = {"org.xcolab.client.comment",
                                    "org.xcolab.client.user",
                                    "org.xcolab.client.activity",
                                    "org.xcolab.client.modeling",
                                    "org.xcolab.client.admin"})
public class FeignConfig extends AbstractFeignConfig {
}
