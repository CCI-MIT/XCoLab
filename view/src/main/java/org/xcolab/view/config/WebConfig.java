package org.xcolab.view.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import org.xcolab.view.theme.ThemeResourceResolver;
import org.xcolab.view.theme.ThemeVariableInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private final ThemeVariableInterceptor themeVariableInterceptor;

    @Autowired
    public WebConfig(ThemeVariableInterceptor themeVariableInterceptor) {
        Assert.notNull(themeVariableInterceptor, "ThemeVariableInterceptor bean is required");
        this.themeVariableInterceptor = themeVariableInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(themeVariableInterceptor);
    }

    @Bean
    public FilterRegistrationBean resourceEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ResourceUrlEncodingFilter());
        return registrationBean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/dist/")
                .resourceChain(true)
                .addResolver(new VersionResourceResolver()
                        .addContentVersionStrategy("/js/**", "/css/**"));

        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/")
                .resourceChain(true)
                .addResolver(new ThemeResourceResolver());
    }
}
