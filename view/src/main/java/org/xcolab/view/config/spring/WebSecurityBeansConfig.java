package org.xcolab.view.config.spring;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import org.xcolab.util.autoconfigure.XCoLabProperties;
import org.xcolab.view.auth.login.spring.MemberDetailsService;

import java.math.BigInteger;
import java.security.SecureRandom;

@Configuration
@EnableConfigurationProperties(XCoLabProperties.class)
public class WebSecurityBeansConfig {

    private static final Logger _log = LoggerFactory.getLogger(WebSecurityBeansConfig.class);

    private final XCoLabProperties properties;

    @Autowired
    public WebSecurityBeansConfig(XCoLabProperties properties) {
        this.properties = properties;
    }

    private void generateSecretIfNecessary() {
        // Make sure we have a secret key even when not configured
        if (StringUtils.isBlank(properties.getSecret())) {
            _log.warn("No application secret configured - generating one-time secret.");
            SecureRandom random = new SecureRandom();
            String secret = new BigInteger(130, random).toString(32);
            _log.warn("Generated secret key: {}", secret);
            properties.setSecret(secret);
        }
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        generateSecretIfNecessary();
        final TokenBasedRememberMeServices rememberMeServices =
                new TokenBasedRememberMeServices(properties.getSecret(), memberDetailsService());
        rememberMeServices.setTokenValiditySeconds(properties.getRememberMe().getTokenValiditySeconds());
        return rememberMeServices;
    }

    @Bean
    public MemberDetailsService memberDetailsService() {
        return new MemberDetailsService();
    }
}
