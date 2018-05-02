package org.xcolab.view.config.spring.beans;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.config.spring.sso.ClimateXPrincipalExtractor;
import org.xcolab.view.config.spring.sso.ColabPrincipalExtractor;
import org.xcolab.view.config.spring.sso.CustomPrincipalExtractor;
import org.xcolab.view.config.spring.sso.FacebookPrincipalExtractor;
import org.xcolab.view.config.spring.sso.GooglePrincipalExtractor;
import org.xcolab.view.pages.loginregister.LoginRegisterService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

@EnableOAuth2Client
@EnableAuthorizationServer
@Configuration
@RestController
public class SsoConfig {

    private static final Logger log = LoggerFactory.getLogger(SsoConfig.class);
    
    private final OAuth2ClientContext oauth2ClientContext;
    private final LoginRegisterService loginRegisterService;
    private final MemberDetailsService memberDetailsService;

    @Autowired
    public SsoConfig(OAuth2ClientContext oauth2ClientContext,
            LoginRegisterService loginRegisterService, MemberDetailsService memberDetailsService) {
        this.oauth2ClientContext = oauth2ClientContext;
        this.loginRegisterService = loginRegisterService;
        this.memberDetailsService = memberDetailsService;
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    public SsoFilter ssoFilter() {
        SsoFilter ssoFilter = new SsoFilter(oauth2ClientContext);
        ssoFilter.addFilter(facebook(), "/sso/facebook",
                new FacebookPrincipalExtractor(loginRegisterService, memberDetailsService));
        ssoFilter.addFilter(google(), "/sso/google",
                new GooglePrincipalExtractor(loginRegisterService, memberDetailsService));
        ssoFilter.addFilter(xcolab(), "/sso/xcolab",
                new ColabPrincipalExtractor(loginRegisterService, memberDetailsService));
        ssoFilter.addFilter(climateX(), "/sso/climatex",
                new ClimateXPrincipalExtractor(loginRegisterService, memberDetailsService));
        return ssoFilter;
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

    @GetMapping("/api/user")
    public Map<String, String> user(Member member) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("sub", Long.toString(member.getId_()));
        map.put("name", member.getFullName());
        map.put("given_name", member.getFirstName());
        map.put("family_name", member.getLastName());
        map.put("email", member.getEmailAddress());
        map.put("preferred_username", member.getScreenName());
        if (StringUtils.isNotEmpty(member.getAbsoluteImageUrl())) {
            map.put("picture", member.getAbsoluteImageUrl());
        }
        return map;
    }

    public static class SsoFilter {

        private final OAuth2ClientContext oAuth2ClientContext;
        private final List<Filter> filters = new ArrayList<>();

        public SsoFilter(OAuth2ClientContext oAuth2ClientContext) {
            this.oAuth2ClientContext = oAuth2ClientContext;
        }

        public Filter getFilter() {
            final CompositeFilter compositeFilter = new CompositeFilter();
            compositeFilter.setFilters(filters);
            return compositeFilter;
        }

        public void addFilter(SsoClientResources clientResources, String path,
                CustomPrincipalExtractor principalExtractor) {
            filters.add(ssoFilter(clientResources, path, principalExtractor));
        }

        private Filter ssoFilter(SsoClientResources client, String path,
                CustomPrincipalExtractor principalExtractor) {
            OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
            OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oAuth2ClientContext);
            filter.setRestTemplate(template);
            UserInfoTokenServices tokenServices = new UserInfoTokenServices(
                    client.getResource().getUserInfoUri(), client.getClient().getClientId());
            tokenServices.setPrincipalExtractor(principalExtractor);
            tokenServices.setRestTemplate(template);
            filter.setTokenServices(tokenServices);
            return filter;
        }
    }

    public static class SsoClientResources {

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
    }
}
