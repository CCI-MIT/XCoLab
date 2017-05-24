package org.xcolab.view.config.spring;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.librato.metrics.reporter.Librato;
import com.librato.metrics.reporter.LibratoReporter;
import org.apache.commons.lang3.StringUtils;
import org.coursera.metrics.datadog.DatadogReporter;
import org.coursera.metrics.datadog.DatadogReporter.Expansion;
import org.coursera.metrics.datadog.transport.HttpTransport;
import org.coursera.metrics.datadog.transport.HttpTransport.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.xcolab.client.admin.enums.PlatformAttributeKey;
import org.xcolab.util.metrics.MetricsUtil;
import org.xcolab.view.config.spring.properties.MetricsProperties;
import org.xcolab.view.config.spring.properties.MetricsProperties.ReportingConfig;
import org.xcolab.view.config.spring.properties.MetricsProperties.ReportingConfig.DatadogConfig;
import org.xcolab.view.config.spring.properties.MetricsProperties.ReportingConfig.LibratoConfig;

import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.coursera.metrics.datadog.DatadogReporter.Expansion.COUNT;
import static org.coursera.metrics.datadog.DatadogReporter.Expansion.MEDIAN;
import static org.coursera.metrics.datadog.DatadogReporter.Expansion.P95;
import static org.coursera.metrics.datadog.DatadogReporter.Expansion.P99;
import static org.coursera.metrics.datadog.DatadogReporter.Expansion.RATE_15_MINUTE;
import static org.coursera.metrics.datadog.DatadogReporter.Expansion.RATE_1_MINUTE;

@Configuration
@EnableConfigurationProperties(MetricsProperties.class)
public class MetricsConfig {

    private static final Logger _log = LoggerFactory.getLogger(MetricsConfig.class);

    private static final String LIBRATO_ENABLED_PROPERTY = "metrics.reporting.librato.enabled";
    private static final String DATADOG_ENABLED_PROPERTY = "metrics.reporting.datadog.enabled";

    private static final String METRIC_PREFIX = "xcolab.view";

    private final ReportingConfig reportingConfig;
    private final LibratoConfig libratoConfig;
    private final DatadogConfig datadogConfig;

    @Autowired
    public MetricsConfig(MetricsProperties metricsProperties) {
        reportingConfig = metricsProperties.getReporting();
        libratoConfig = reportingConfig.getLibrato();
        datadogConfig = reportingConfig.getDatadog();

        if (libratoConfig.isEnabled() && !isLibratoConfigValid()) {
            throw new IllegalStateException("Librato metric reporting is enabled but "
                    + "the configuration is invalid.");
        }
        if (datadogConfig.isEnabled() && !isDatadogConfigValid()) {
            throw new IllegalStateException("Datadog metric reporting is enabled but "
                    + "the configuration is invalid.");
        }
    }

    private boolean isLibratoConfigValid() {
        return StringUtils.isNotBlank(libratoConfig.getEmail())
                && StringUtils.isNoneBlank(libratoConfig.getApiToken());
    }

    private boolean isDatadogConfigValid() {
        return true;
    }

    @Bean
    public MetricRegistry metricRegistry() {
        return MetricsUtil.REGISTRY;
    }

    @Bean
    @ConditionalOnProperty(value = LIBRATO_ENABLED_PROPERTY, havingValue = "true")
    LibratoReporter libratoReporter() {
        _log.info("Starting librato metrics reporter for {}", libratoConfig.getEmail());
        return Librato.reporter(metricRegistry(),
                    libratoConfig.getEmail(), libratoConfig.getApiToken())
                .setSource(PlatformAttributeKey.PLATFORM_COLAB_URL.get())
                .setFilter(new PrefixMetricFilter(
                        reportingConfig.getIncludes(), reportingConfig.getExcludes()))
                .setPrefix(METRIC_PREFIX)
                .start(10, TimeUnit.SECONDS);
    }

    @Bean
    @ConditionalOnProperty(value = DATADOG_ENABLED_PROPERTY, havingValue = "true")
    DatadogReporter datadogReporter() {
        _log.info("Starting datadog metrics reporter");
        final EnumSet<Expansion> expansions =
                EnumSet.of(COUNT, RATE_1_MINUTE, RATE_15_MINUTE, MEDIAN, P95, P99);
        final HttpTransport transport = new Builder().withApiKey(datadogConfig.getApiKey()).build();
        final DatadogReporter datadogReporter =
                DatadogReporter.forRegistry(metricRegistry())
                        .withHost(PlatformAttributeKey.PLATFORM_COLAB_URL.get())
                        .withTransport(transport)
                        .withPrefix(METRIC_PREFIX)
                        .withExpansions(expansions)
                        .filter(new PrefixMetricFilter(
                                reportingConfig.getIncludes(), reportingConfig.getExcludes()))
                        .build();
        datadogReporter.start(10, TimeUnit.SECONDS);
        return datadogReporter;
    }

    private static class PrefixMetricFilter implements MetricFilter {

        private final List<String> includedPrefixes;
        private final List<String> excludedPrefixes;

        public PrefixMetricFilter(List<String> includedPrefixes, List<String> excludedPrefixes) {
            this.includedPrefixes = includedPrefixes;
            this.excludedPrefixes = excludedPrefixes;
        }

        @Override
        public boolean matches(String name, Metric metric) {
            return excludedPrefixes.stream().noneMatch(name::startsWith)
                    && (includedPrefixes.isEmpty()
                            || includedPrefixes.stream().anyMatch(name::startsWith));
        }
    }
}
