package org.xcolab.view.config.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import org.xcolab.view.auth.AuthenticationContext;
import org.xcolab.view.auth.resolver.MemberArgumentResolver;
import org.xcolab.view.config.ConfigurationService;
import org.xcolab.view.config.rewrite.RewriteInitializer;
import org.xcolab.view.config.tomcat.AjpConnector;
import org.xcolab.view.config.tomcat.ForwardedHostValve;
import org.xcolab.view.pages.proposals.view.interceptors.PopulateProposalModelInterceptor;
import org.xcolab.view.pages.proposals.view.interceptors.ValidateTabPermissionsInterceptor;
import org.xcolab.view.theme.ThemeResourceResolver;
import org.xcolab.view.theme.ThemeVariableInterceptor;

import java.util.List;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    private final ThemeVariableInterceptor themeVariableInterceptor;
    private final PopulateProposalModelInterceptor populateContextInterceptor;
    private final ValidateTabPermissionsInterceptor validateTabPermissionsInterceptor;

    private final ConfigurationService configurationService;

    @Autowired
    public WebConfig(ThemeVariableInterceptor themeVariableInterceptor,
            PopulateProposalModelInterceptor populateContextInterceptor,
            ValidateTabPermissionsInterceptor validateTabPermissionsInterceptor,
            ConfigurationService configurationService) {
        Assert.notNull(configurationService, "ConfigurationService bean is required");
        Assert.notNull(themeVariableInterceptor, "ThemeVariableInterceptor bean is required");
        Assert.notNull(populateContextInterceptor, "PopulateContextInterceptor bean is required");
        Assert.notNull(validateTabPermissionsInterceptor,
                "ValidateTabPermissionsInterceptor bean is required");
        this.configurationService = configurationService;
        this.themeVariableInterceptor = themeVariableInterceptor;
        this.populateContextInterceptor = populateContextInterceptor;
        this.validateTabPermissionsInterceptor = validateTabPermissionsInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(themeVariableInterceptor);

        registry.addInterceptor(populateContextInterceptor).addPathPatterns("/contests/**");
        registry.addInterceptor(validateTabPermissionsInterceptor).addPathPatterns("/contests/**");
    }

    @Bean
    public FilterRegistrationBean resourceEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ResourceUrlEncodingFilter());
        return registrationBean;
    }

    @Bean
    public RewriteInitializer rewriteInitializer() {
        return new RewriteInitializer();
    }

    @Bean
    public EmbeddedServletContainerCustomizer customizer() {
        return container -> {
            if (configurationService.isUseForwardHeaders()) {
                if (container instanceof TomcatEmbeddedServletContainerFactory) {
                    ((TomcatEmbeddedServletContainerFactory) container)
                            .addContextValves(new ForwardedHostValve());
                } else {
                    log.warn("Non-tomcat servlet container "
                            + "- X-Forwarded-Host header not initialized.");
                }
            }
        };
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {

        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        if (configurationService.isTomcatAjpEnabled()) {
            final AjpConnector ajpConnector =
                    new AjpConnector(configurationService.getTomcatAjpPort());
            tomcat.addAdditionalTomcatConnectors(ajpConnector);
            log.info("Configured AJP connector on port {}", configurationService.getTomcatAjpPort());
        }

        return tomcat;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new MemberArgumentResolver(new AuthenticationContext()));
        super.addArgumentResolvers(argumentResolvers);
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
