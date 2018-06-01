package org.xcolab.view.config.spring.beans;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.filter.CompositeFilter;

import org.xcolab.view.auth.handlers.AuthenticationSuccessHandler;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.config.spring.sso.ClimateXPrincipalExtractor;
import org.xcolab.view.config.spring.sso.ColabPrincipalExtractor;
import org.xcolab.view.config.spring.sso.FacebookPrincipalExtractor;
import org.xcolab.view.config.spring.sso.GooglePrincipalExtractor;
import org.xcolab.view.pages.loginregister.LoginRegisterService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import javax.servlet.Filter;

@EnableOAuth2Client
@Configuration
public class SsoClientConfig {

    private static final Logger log = LoggerFactory.getLogger(SsoClientConfig.class);

    private final OAuth2ClientContext oauth2ClientContext;
    private final LoginRegisterService loginRegisterService;
    private final MemberDetailsService memberDetailsService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public SsoClientConfig(OAuth2ClientContext oauth2ClientContext,
            LoginRegisterService loginRegisterService, MemberDetailsService memberDetailsService,
            AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.oauth2ClientContext = oauth2ClientContext;
        this.loginRegisterService = loginRegisterService;
        this.memberDetailsService = memberDetailsService;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Bean
    public SsoFilter ssoFilter() {
        SsoFilter ssoFilter = new SsoFilter(oauth2ClientContext, authenticationSuccessHandler);
        configureSsoFilter(ssoFilter, facebook(), "/sso/facebook", false,
                FacebookPrincipalExtractor::new);
        configureSsoFilter(ssoFilter, google(), "/sso/google", false,
                GooglePrincipalExtractor::new);
        configureSsoFilter(ssoFilter, xcolab(), "/sso/xcolab", true,
                ColabPrincipalExtractor::new);
        configureSsoFilter(ssoFilter, climateX(), "/sso/climatex", true,
                ClimateXPrincipalExtractor::new);
        return ssoFilter;
    }

    @Bean
    public SsoServices ssoServices() {
        final boolean isFacebookEnabled = StringUtils.isNotEmpty(facebook().getClient().getClientId());
        final boolean isGoogleEnabled = StringUtils.isNotEmpty(google().getClient().getClientId());
        final boolean isClimateXEnabled = StringUtils.isNotEmpty(climateX().getClient().getClientId());
        return new SsoServices(isFacebookEnabled, isGoogleEnabled, isClimateXEnabled);
    }

    private void configureSsoFilter(SsoFilter ssoFilter, SsoClientResources clientResources,
            String path, boolean requireHostname,
            BiFunction<LoginRegisterService, MemberDetailsService, PrincipalExtractor> principalExtractorSupplier) {
        if (isClientConfigured(clientResources)) {
            if (requireHostname) {
                if (StringUtils.isEmpty(clientResources.getHostname())) {
                    throw new IllegalStateException("Hostname is not configured for SsoFilter " + path);
                }
            }
            log.info("Configuring SsoFilter {} (clientId = {})", path,
                    clientResources.client.getClientId());
            ssoFilter.addFilter(clientResources, path, principalExtractorSupplier.apply(
                    loginRegisterService, memberDetailsService));
        }
    }

    private boolean isClientConfigured(SsoClientResources clientResources) {
        final AuthorizationCodeResourceDetails client = clientResources.getClient();
        return StringUtils.isNoneEmpty(client.getClientId(), client.getClientSecret());
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    @Bean
    @ConfigurationProperties("sso.facebook")
    public SsoClientResources facebook() {
        return new SsoClientResources();
    }

    @Bean
    @ConfigurationProperties("sso.google")
    public SsoClientResources google() {
        return new SsoClientResources();
    }

    @Bean
    @ConfigurationProperties("sso.xcolab")
    public SsoClientResources xcolab() {
        return new SsoClientResources();
    }

    @Bean
    @ConfigurationProperties("sso.climatex")
    public SsoClientResources climateX() {
        return new SsoClientResources();
    }

    public static class SsoFilter {

        private final OAuth2ClientContext oAuth2ClientContext;
        private final AuthenticationSuccessHandler authenticationSuccessHandler;
        private final List<Filter> filters = new ArrayList<>();

        public SsoFilter(OAuth2ClientContext oAuth2ClientContext,
                AuthenticationSuccessHandler authenticationSuccessHandler) {
            this.oAuth2ClientContext = oAuth2ClientContext;
            this.authenticationSuccessHandler = authenticationSuccessHandler;
        }

        public Filter getFilter() {
            final CompositeFilter compositeFilter = new CompositeFilter();
            compositeFilter.setFilters(filters);
            return compositeFilter;
        }

        public void addFilter(SsoClientResources clientResources, String path,
                PrincipalExtractor principalExtractor) {
            filters.add(ssoFilter(clientResources, path, principalExtractor));
        }

        private Filter ssoFilter(SsoClientResources client, String path,
                PrincipalExtractor principalExtractor) {
            OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
            OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oAuth2ClientContext);
            filter.setRestTemplate(template);
            UserInfoTokenServices tokenServices = new UserInfoTokenServices(
                    client.getResource().getUserInfoUri(), client.getClient().getClientId());
            tokenServices.setPrincipalExtractor(principalExtractor);
            tokenServices.setRestTemplate(template);
            filter.setTokenServices(tokenServices);
            filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
            return filter;
        }
    }

    public static class SsoClientResources {

        /**
         * Hostname for OAuth endpoints, if not well-known.
         */
        private String hostname;

        @NestedConfigurationProperty
        private final AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

        @NestedConfigurationProperty
        private final ResourceServerProperties resource = new ResourceServerProperties();

        public AuthorizationCodeResourceDetails getClient() {
            return client;
        }

        public ResourceServerProperties getResource() {
            return resource;
        }

        public String getHostname() {
            return hostname;
        }

        @SuppressWarnings("unused")
        public void setHostname(String hostname) {
            this.hostname = hostname;
        }
    }

    public static class SsoServices {

        private final boolean isFacebookEnabled;
        private final boolean isGoogleEnabled;
        private final boolean isClimateXEnabled;

        public SsoServices(boolean isFacebookEnabled, boolean isGoogleEnabled,
                boolean isClimateXEnabled) {
            this.isFacebookEnabled = isFacebookEnabled;
            this.isGoogleEnabled = isGoogleEnabled;
            this.isClimateXEnabled = isClimateXEnabled;
        }

        public boolean isFacebookEnabled() {
            return isFacebookEnabled;
        }

        public boolean isGoogleEnabled() {
            return isGoogleEnabled;
        }

        public boolean isClimateXEnabled() {
            return isClimateXEnabled;
        }
    }
}
