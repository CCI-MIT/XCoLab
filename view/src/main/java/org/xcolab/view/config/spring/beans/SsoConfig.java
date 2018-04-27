package org.xcolab.view.config.spring.beans;

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

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

@EnableOAuth2Client
@Configuration
public class SsoConfig {

    private static final Logger log = LoggerFactory.getLogger(SsoConfig.class);
    
    private final OAuth2ClientContext oauth2ClientContext;

    @Autowired
    public SsoConfig(OAuth2ClientContext oauth2ClientContext) {
        this.oauth2ClientContext = oauth2ClientContext;
    }

    @Bean
    public SsoFilter ssoFilter() {
        SsoFilter ssoFilter = new SsoFilter(oauth2ClientContext);
        ssoFilter.addFilter(facebook(), "/sso/facebook");
        ssoFilter.addFilter(google(), "/sso/google");
        ssoFilter.addFilter(xcolab(), "/sso/xcolab");
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
    public PrincipalExtractor principalExtractor() {
        return map -> {
            String principalId = (String) map.get("id");
            Member user;
            try {
                user = MembersClient.findMemberByFacebookId(Long.parseLong(principalId));
            } catch (MemberNotFoundException e) {
                log.info("No user found, generating profile for {}", principalId);
                user = new Member();
                user.setFacebookId(Long.parseLong(principalId));
            }
            return user;
        };
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

        public void addFilter(SsoClientResources clientResources, String path) {
            filters.add(ssoFilter(clientResources, path));
        }

        private Filter ssoFilter(SsoClientResources client, String path) {
            OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
            OAuth2RestTemplate template = new OAuth2RestTemplate(client.getClient(), oAuth2ClientContext);
            filter.setRestTemplate(template);
            UserInfoTokenServices tokenServices = new UserInfoTokenServices(
                    client.getResource().getUserInfoUri(), client.getClient().getClientId());
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
