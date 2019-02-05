package org.xcolab.view.config.feign;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignRequestConfigure {

    private final int CONNECT_TIMEOUT_MILLIS = 16000;
    private final int READ_TIMEOUT_MILLIS = 16000;
    @Bean
    public Request.Options options() {
        return new Request.Options(CONNECT_TIMEOUT_MILLIS, READ_TIMEOUT_MILLIS);
    }
}
