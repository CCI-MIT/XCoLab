package org.xcolab.view.config.spring.beans;

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
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.resource.ResourceUrlEncodingFilter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import org.xcolab.view.auth.AuthenticationContext;
import org.xcolab.view.config.rewrite.RewriteInitializer;
import org.xcolab.view.config.spring.converters.CaseInsensitiveStringToEnumConverterFactory;
import org.xcolab.view.config.spring.properties.ServerProperties;
import org.xcolab.view.config.spring.properties.TomcatProperties;
import org.xcolab.view.config.spring.properties.WebProperties;
import org.xcolab.view.config.spring.properties.WebProperties.CacheSettings;
import org.xcolab.view.config.spring.resolvers.MemberArgumentResolver;
import org.xcolab.view.config.spring.resolvers.ProposalContextArgumentResolver;
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
@EnableConfigurationProperties(
        {WebProperties.class, TomcatProperties.class, ServerProperties.class})
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    private static final String LOCAL_WEBAPP_DIR_LOCATION = "view/src/main/webapp";

    // Configuration properties
    private final TomcatProperties tomcatProperties;
    private final ServerProperties serverProperties;
    private final WebProperties webProperties;

    // Interceptors
    private final ThemeVariableInterceptor themeVariableInterceptor;
    private final PopulateProposalModelInterceptor populateContextInterceptor;
    private final ValidateTabPermissionsInterceptor validateTabPermissionsInterceptor;

    // Other beans
    private final LocaleResolver localeResolver;

    @Autowired
    public WebConfig(ThemeVariableInterceptor themeVariableInterceptor,
            PopulateProposalModelInterceptor populateContextInterceptor,
            ValidateTabPermissionsInterceptor validateTabPermissionsInterceptor,
            TomcatProperties tomcatProperties, ServerProperties serverProperties,
            WebProperties webProperties, LocaleResolver localeResolver) {
        Assert.notNull(tomcatProperties, "TomcatProperties bean is required");
        Assert.notNull(serverProperties, "ServerProperties bean is required");
        Assert.notNull(webProperties, "webProperties bean is required");
        this.tomcatProperties = tomcatProperties;
        this.serverProperties = serverProperties;
        this.webProperties = webProperties;

        Assert.notNull(themeVariableInterceptor, "ThemeVariableInterceptor bean is required");
        Assert.notNull(populateContextInterceptor, "PopulateContextInterceptor bean is required");
        Assert.notNull(validateTabPermissionsInterceptor,
                "ValidateTabPermissionsInterceptor bean is required");
        this.themeVariableInterceptor = themeVariableInterceptor;
        this.populateContextInterceptor = populateContextInterceptor;
        this.validateTabPermissionsInterceptor = validateTabPermissionsInterceptor;

        Assert.notNull(localeResolver, "LocaleResolver bean is required");
        this.localeResolver = localeResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(themeVariableInterceptor);

        registry.addInterceptor(populateContextInterceptor).addPathPatterns("/contests/**");
        registry.addInterceptor(validateTabPermissionsInterceptor).addPathPatterns("/contests/**");
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new CaseInsensitiveStringToEnumConverterFactory());
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
            if (serverProperties.isUseForwardHeaders()) {
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
        if (tomcatProperties.getAjp().isEnabled()) {
            final AjpConnector ajpConnector = new AjpConnector(tomcatProperties.getAjp().getPort());
            tomcat.addAdditionalTomcatConnectors(ajpConnector);
            log.info("Configured AJP connector on port {}", tomcatProperties.getAjp().getPort());
        }

        return tomcat;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new MemberArgumentResolver(new AuthenticationContext()));
        argumentResolvers.add(new ProposalContextArgumentResolver(localeResolver));
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        addScriptResourceHandlers(registry);
        addThemeImageResourceResolvers(registry);
    }

    private void addScriptResourceHandlers(ResourceHandlerRegistry registry) {
        final CacheSettings cacheSettings = webProperties.getCache().getScripts();
        final CacheControl cacheControl = cacheSettings.isActive()
                ? CacheControl.maxAge(cacheSettings.getMaxAgeDays(), TimeUnit.DAYS)
                : CacheControl.noCache();
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/", "classpath:/dist/")
                .setCacheControl(cacheControl)
                .resourceChain(true)
                .addResolver(new VersionResourceResolver()
                        .addContentVersionStrategy("/js/**", "/css/**"));
    }

    private void addThemeImageResourceResolvers(ResourceHandlerRegistry registry) {
        final CacheSettings cacheSettings = webProperties.getCache().getImages();
        final CacheControl cacheControl = cacheSettings.isActive()
                ? CacheControl.maxAge(cacheSettings.getMaxAgeDays(), TimeUnit.DAYS)
                : CacheControl.noCache();
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/")
                .setCacheControl(cacheControl)
                .resourceChain(true)
                .addResolver(new ThemeResourceResolver());
    }
}
