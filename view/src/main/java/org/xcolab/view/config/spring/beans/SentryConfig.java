package org.xcolab.view.config.spring.beans;

import io.sentry.Sentry;
import io.sentry.SentryClient;
import io.sentry.spring.SentryServletContextInitializer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.commons.servlet.ManifestUtil;
import org.xcolab.view.config.spring.filters.SentryUserInfoFilter;

import java.util.Optional;

import javax.servlet.ServletContext;

@Configuration
public class SentryConfig {

    @Bean
    public ServletContextInitializer sentryServletContextInitializer() {
        return new SentryServletContextInitializer();
    }

    @Bean
    public SentryUserInfoFilter sentryUserInfoFilter() {
        return new SentryUserInfoFilter();
    }

    @Component
    public static class SentryConfigurer implements ApplicationRunner {

        private final ServletContext servletContext;

        public SentryConfigurer(ServletContext servletContext) {
            this.servletContext = servletContext;
        }

        @Override
        public void run(ApplicationArguments applicationArguments) {

            final SentryClient sentryClient = Sentry.init(PlatformAttributeKey.SENTRY_BACKEND_DSN.get());
            final ServerEnvironment serverEnvironment = PlatformAttributeKey.SERVER_ENVIRONMENT.get();
            sentryClient.setEnvironment(serverEnvironment.name().toLowerCase());

            final Package runtimePackage = Runtime.class.getPackage();
            sentryClient.addTag("javaVersion", runtimePackage.getImplementationVersion());

            final Optional<String> buildCommitOpt = ManifestUtil.getBuildCommit(servletContext);
            buildCommitOpt.ifPresent(sentryClient::setRelease);
        }
    }
}
