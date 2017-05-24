package org.xcolab.view.config.spring;

import com.codahale.metrics.MetricRegistry;
import com.librato.metrics.reporter.Librato;
import com.librato.metrics.reporter.LibratoReporter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import org.xcolab.client.admin.enums.PlatformAttributeKey;
import org.xcolab.util.metrics.MetricsUtil;

import java.util.concurrent.TimeUnit;

@Configuration
public class MetricsConfig {

    private static final String LIBRATO_ENABLED_PROPERTY = "metrics.reporting.librato.enabled";
    private static final String LIBRATO_EMAIL_PROPERTY = "metrics.reporting.librato.email";
    private static final String LIBRATO_API_TOKEN_PROPERTY = "metrics.reporting.librato.api-token";

    private final String libratoEmail;
    private final String libratoApiToken;

    @Autowired
    public MetricsConfig(Environment environment) {
        boolean libratoEnabled =
                environment.getProperty(LIBRATO_ENABLED_PROPERTY, Boolean.class, false);
        libratoEmail = environment.getProperty(LIBRATO_EMAIL_PROPERTY);
        libratoApiToken = environment.getProperty(LIBRATO_API_TOKEN_PROPERTY);
        if (libratoEnabled && ((StringUtils.isBlank(libratoEmail)) || StringUtils.isBlank(libratoApiToken))) {
            throw new IllegalStateException("Librato configuration is invalid");
        }
    }

    @Bean
    public MetricRegistry metricRegistry() {
        return MetricsUtil.REGISTRY;
    }

    @Bean
    @ConditionalOnProperty(value = LIBRATO_ENABLED_PROPERTY, havingValue = "true")
    LibratoReporter libratoReporter() {
        return Librato.reporter(metricRegistry(), libratoEmail, libratoApiToken)
                .setSource(PlatformAttributeKey.PLATFORM_COLAB_URL.get())
                .start(10, TimeUnit.SECONDS);
    }
}
