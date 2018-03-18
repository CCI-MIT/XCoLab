package org.xcolab.view.config.spring.beans;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import org.xcolab.commons.autoconfigure.XCoLabProperties;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.auth.handlers.AuthenticationSuccessHandler;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.config.spring.properties.WebProperties;
import org.xcolab.view.pages.redballoon.utils.BalloonService;

import java.util.UUID;

@Configuration
@EnableConfigurationProperties(XCoLabProperties.class)
public class WebSecurityBeansConfig {

    private static final Logger _log = LoggerFactory.getLogger(WebSecurityBeansConfig.class);

    private final XCoLabProperties xCoLabProperties;
    private final WebProperties webProperties;

    @Autowired
    public WebSecurityBeansConfig(XCoLabProperties xCoLabProperties, WebProperties webProperties) {
        this.xCoLabProperties = xCoLabProperties;
        this.webProperties = webProperties;
    }

    private void generateSecretIfNecessary() {
        // Make sure we have a secret key even when not configured
        if (StringUtils.isBlank(xCoLabProperties.getSecret())) {
            _log.warn("No application secret configured - generating one-time secret.");
            String secret = UUID.randomUUID().toString();
            _log.warn("Generated secret key: {}", secret);
            xCoLabProperties.setSecret(secret);
        }
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        generateSecretIfNecessary();
        final TokenBasedRememberMeServices rememberMeServices =
                new TokenBasedRememberMeServices(xCoLabProperties.getSecret(),
                        memberDetailsService());
        rememberMeServices.setTokenValiditySeconds(
                xCoLabProperties.getRememberMe().getTokenValiditySeconds());
        return rememberMeServices;
    }

    @Bean
    public MemberDetailsService memberDetailsService() {
        return new MemberDetailsService();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(
            AuthenticationService authenticationService, BalloonService balloonService) {
        final boolean allowLogin = webProperties.getGuestAccess().isAllowLogin();
        return new AuthenticationSuccessHandler(authenticationService, balloonService, allowLogin);
    }
}
