package org.xcolab.util.autoconfigure;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

public abstract class AbstractFeignConfig {

    @Bean
    public ErrorDecoder getErrorDecoder() {
        return new XColabErrorDecoder();
    }
}
