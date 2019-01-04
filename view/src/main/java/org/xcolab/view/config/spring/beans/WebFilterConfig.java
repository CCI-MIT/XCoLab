package org.xcolab.view.config.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.view.auth.tracking.UserTrackingFilter;
import org.xcolab.view.auth.tracking.UserTrackingService;
import org.xcolab.view.config.rewrite.RewriteInitializer;
import org.xcolab.view.config.spring.filters.CdnUrlEncodingFilter;
import org.xcolab.view.theme.ThemeVariableFilter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebFilterConfig {

    private final ThemeVariableFilter themeVariableFilter;

    @Autowired
    public WebFilterConfig(ThemeVariableFilter themeVariableFilter) {
        this.themeVariableFilter = themeVariableFilter;
    }

    @Bean
    public FilterRegistrationBean resourceEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ResourceUrlEncodingFilter());
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean cdnUrlEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        Map<String, String> cdnUrlMappings = new HashMap<>();
        cdnUrlMappings.put("/css/**", PlatformAttributeKey.CDN_URL_SCRIPTS.get());
        cdnUrlMappings.put("/js/**", PlatformAttributeKey.CDN_URL_SCRIPTS.get());
        cdnUrlMappings.put("/vendor/**", PlatformAttributeKey.CDN_URL_SCRIPTS.get());
        cdnUrlMappings.put("/images/**", PlatformAttributeKey.CDN_URL_IMAGES_STATIC.get());
        cdnUrlMappings.put("/image/**", PlatformAttributeKey.CDN_URL_IMAGES_UPLOADED.get());
        registrationBean.setFilter(new CdnUrlEncodingFilter(cdnUrlMappings));
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean etagFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ShallowEtagHeaderFilter());
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean trackingFilter(UserTrackingService userTrackingService) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new UserTrackingFilter(userTrackingService));
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean themeContextFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(themeVariableFilter);
        return registrationBean;
    }

    @Bean
    public RewriteInitializer rewriteInitializer() {
        return new RewriteInitializer();
    }
}
