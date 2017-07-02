package org.xcolab.view.config.spring;

import org.apache.catalina.Context;
import org.apache.catalina.webresources.StandardRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import org.xcolab.view.auth.AuthenticationContext;
import org.xcolab.view.auth.resolver.MemberArgumentResolver;
import org.xcolab.view.config.ConfigurationService;
import org.xcolab.view.config.rewrite.RewriteInitializer;
import org.xcolab.view.config.spring.properties.WebProperties;
import org.xcolab.view.config.tomcat.AjpConnector;
import org.xcolab.view.config.tomcat.ForwardedHostValve;
import org.xcolab.view.pages.proposals.interceptors.PopulateProposalModelInterceptor;
import org.xcolab.view.pages.proposals.interceptors.ValidateTabPermissionsInterceptor;
import org.xcolab.view.theme.ThemeResourceResolver;
import org.xcolab.view.theme.ThemeVariableInterceptor;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(WebProperties.class)
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    private static final String LOCAL_WEBAPP_DIR_LOCATION = "view/src/main/webapp";

    private final ThemeVariableInterceptor themeVariableInterceptor;
    private final PopulateProposalModelInterceptor populateContextInterceptor;
    private final ValidateTabPermissionsInterceptor validateTabPermissionsInterceptor;

    private final ConfigurationService configurationService;

    private final WebProperties webProperties;

    @Autowired
    public WebConfig(ThemeVariableInterceptor themeVariableInterceptor,
            PopulateProposalModelInterceptor populateContextInterceptor,
            ValidateTabPermissionsInterceptor validateTabPermissionsInterceptor,
            ConfigurationService configurationService, WebProperties webProperties) {
        Assert.notNull(configurationService, "ConfigurationService bean is required");
        Assert.notNull(themeVariableInterceptor, "ThemeVariableInterceptor bean is required");
        Assert.notNull(populateContextInterceptor, "PopulateContextInterceptor bean is required");
        Assert.notNull(validateTabPermissionsInterceptor,
                "ValidateTabPermissionsInterceptor bean is required");
        Assert.notNull(webProperties, "webProperties bean is required");
        this.configurationService = configurationService;
        this.themeVariableInterceptor = themeVariableInterceptor;
        this.populateContextInterceptor = populateContextInterceptor;
        this.validateTabPermissionsInterceptor = validateTabPermissionsInterceptor;
        this.webProperties = webProperties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(themeVariableInterceptor);

        registry.addInterceptor(populateContextInterceptor).addPathPatterns("/contests/**");
        registry.addInterceptor(validateTabPermissionsInterceptor).addPathPatterns("/contests/**");
        registry.addInterceptor(localeChangeInterceptor());
    }

    private LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    @Bean
    public FilterRegistrationBean resourceEncodingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ResourceUrlEncodingFilter());
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean etagFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ShallowEtagHeaderFilter());
        return registrationBean;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory());
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

        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {

                final File webappDir = new File(LOCAL_WEBAPP_DIR_LOCATION);
                if (webappDir.exists()) {
                    log.info("Configuring webapp dir at {}", webappDir.getAbsolutePath());
                    context.setDocBase(webappDir.getAbsolutePath());
                }

                final int cacheSizeInKb = 128 * 1024;
                StandardRoot standardRoot = new StandardRoot(context);
                standardRoot.setCacheMaxSize(cacheSizeInKb);
                context.setResources(standardRoot);

                log.info("Set tomcat cache size to {} MB",
                        context.getResources().getCacheMaxSize() / 1024);
            }
        };
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
                .setCacheControl(
                        webProperties.getCache().getScripts().isActive()
                                ? CacheControl.maxAge(webProperties.getCache().getScripts().getMaxAgeDays(),
                                TimeUnit.DAYS)
                                : CacheControl.noCache())
                .resourceChain(true)
                .addResolver(new VersionResourceResolver()
                        .addContentVersionStrategy("/js/**", "/css/**"));

        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/")
                .setCacheControl(
                        webProperties.getCache().getImages().isActive()
                                ? CacheControl.maxAge(webProperties.getCache().getImages().getMaxAgeDays(),
                                TimeUnit.DAYS)
                                : CacheControl.noCache())
                .resourceChain(true)
                .addResolver(new ThemeResourceResolver());
    }
}
