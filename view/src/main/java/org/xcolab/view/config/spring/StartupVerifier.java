package org.xcolab.view.config.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;

public class StartupVerifier implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(StartupVerifier.class);

    @Override
    public void run(ApplicationArguments applicationArguments) {

        final ServerEnvironment serverEnvironment = PlatformAttributeKey.SERVER_ENVIRONMENT.get();

        log.info("Running in environment: {}", serverEnvironment);
        if (serverEnvironment != ServerEnvironment.PRODUCTION) {
            log.warn("Running in NON PRODUCTION environment. For production use, please configure the server environment." );
        }
    }
}
