package org.xcolab.view.config.spring;

import com.codahale.metrics.MetricRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.xcolab.util.metrics.MetricsUtil;

@Configuration
public class MetricConfiguration {

    @Bean
    public MetricRegistry metricRegistry() {
        return MetricsUtil.REGISTRY;
    }
}
