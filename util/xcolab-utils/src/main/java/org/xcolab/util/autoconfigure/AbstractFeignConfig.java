package org.xcolab.util.autoconfigure;

import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;

public abstract class AbstractFeignConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public ErrorDecoder getErrorDecoder() {
        return new XColabErrorDecoder();
    }

    @Bean public Decoder getDecoder() {
        return new XColabOptionalDecoder(new ResponseEntityDecoder(new SpringDecoder(this.messageConverters)));
    }
}
