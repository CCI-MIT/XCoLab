package org.xcolab.view.config.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.commons.servlet.ManifestUtil;

import java.util.Optional;

import javax.servlet.ServletContext;

@Component
public class StartupVerifier implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(StartupVerifier.class);

    private final ServletContext servletContext;

    public StartupVerifier(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {

        final ServerEnvironment serverEnvironment = PlatformAttributeKey.SERVER_ENVIRONMENT.get();

        final Package runtimePackage = Runtime.class.getPackage();
        log.info("Running on Java {} from {}", runtimePackage.getImplementationVersion(),
                runtimePackage.getImplementationVendor());
        final Optional<String> buildCommitOpt = ManifestUtil.getBuildCommit(servletContext);
        buildCommitOpt.ifPresent(s -> log.info("Running binaries from commit {}", s));

        log.info("Running in environment: {}", serverEnvironment);
        if (serverEnvironment != ServerEnvironment.PRODUCTION) {
            log.warn("Running in NON PRODUCTION environment. For production use, please set the"
                            + " server environment to {}.", ServerEnvironment.PRODUCTION);
        }
    }
}
