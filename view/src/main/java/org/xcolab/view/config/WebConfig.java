package org.xcolab.view.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
}
