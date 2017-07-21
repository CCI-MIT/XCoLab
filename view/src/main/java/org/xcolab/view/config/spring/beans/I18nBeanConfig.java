package org.xcolab.view.config.spring.beans;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.util.i18n.I18nUtils;
import org.xcolab.view.i18n.MemberLocaleResolver;

@Configuration
public class I18nBeanConfig {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(
                "i18n/homepage",
                "i18n/register",
                "i18n/contests",
                "i18n/contact",
                "i18n/profile",
                "i18n/message",
                "i18n/colab",
                "i18n/subscription",
                "i18n/community",
                "i18n/discussion",
                "i18n/activity",
                "i18n/search"
        );
        messageSource.setDefaultEncoding("utf-8");
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver(AuthenticationService authenticationService) {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setLocaleAttributeName(I18nUtils.MEMBER_LOCALE_SESSION_IDENTIFIER);
        return new MemberLocaleResolver(authenticationService, sessionLocaleResolver);
    }
}
