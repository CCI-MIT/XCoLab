package org.xcolab.view.config.spring.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.switchuser.SwitchUserFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.session.web.http.SessionRepositoryFilter;

import org.xcolab.util.autoconfigure.XCoLabProperties;
import org.xcolab.view.auth.handlers.AuthenticationFailureHandler;
import org.xcolab.view.auth.handlers.AuthenticationSuccessHandler;
import org.xcolab.view.auth.handlers.LogoutSuccessHandler;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.auth.login.spring.MemberPasswordEncoder;
import org.xcolab.view.config.spring.beans.SsoClientConfig.SsoFilter;
import org.xcolab.view.config.spring.errors.CustomAccessDeniedHandler;
import org.xcolab.view.config.spring.properties.WebProperties;
import org.xcolab.view.config.spring.properties.WebProperties.GuestAccess;

import java.util.Optional;

import javax.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@SuppressWarnings("ProhibitedExceptionDeclared")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    private final RememberMeServices rememberMeServices;
    private final MemberDetailsService memberDetailsService;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final SsoFilter ssoFilter;

    private final WebProperties webProperties;
    private final XCoLabProperties xCoLabProperties;

    @Autowired
    public WebSecurityConfig(RememberMeServices rememberMeServices,
            MemberDetailsService memberDetailsService, WebProperties webProperties,
            XCoLabProperties xCoLabProperties,
            AuthenticationSuccessHandler authenticationSuccessHandler, SsoFilter ssoFilter) {
        this.rememberMeServices = rememberMeServices;
        this.memberDetailsService = memberDetailsService;
        this.webProperties = webProperties;
        this.xCoLabProperties = xCoLabProperties;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.ssoFilter = ssoFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ADMIN")
                .antMatchers("/oauth/authorize").authenticated()
                .antMatchers("/impersonate")
                    .hasAnyRole("ADMIN", SwitchUserFilter.ROLE_PREVIOUS_ADMINISTRATOR);

        final GuestAccess guestAccessProperties = webProperties.getGuestAccess();

        if (!guestAccessProperties.isAllowSso()) {
            httpSecurity.authorizeRequests()
                    .antMatchers("/sso/**").denyAll();
        }

        if (!guestAccessProperties.isAllowRegistration()) {
            httpSecurity.authorizeRequests()
                    .antMatchers("/register/**").denyAll();
        }

        if (guestAccessProperties.isAllowAll()) {
            httpSecurity.authorizeRequests()
                    .anyRequest().permitAll();
        } else {
            if (guestAccessProperties.isAlwaysAllowHomepage()) {
                httpSecurity.authorizeRequests()
                        .antMatchers("/").permitAll();
            }
            if (guestAccessProperties.isAlwaysAllowContentPages()) {
                httpSecurity.authorizeRequests()
                        .antMatchers("/page/**").permitAll();
            }
            httpSecurity.authorizeRequests()
                    .antMatchers("/api/**").permitAll()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/vendor/**").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/reportError**").permitAll()
                    .anyRequest().authenticated();
        }

        httpSecurity
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(authenticationSuccessHandler)
                    .failureHandler(new AuthenticationFailureHandler())
                    .and()
                .rememberMe()
                    .rememberMeServices(rememberMeServices)
                    // need to specify same key that is used in rememberMeServices
                    .key(xCoLabProperties.getSecret())
                    .and()
                .addFilterBefore(ssoFilter, BasicAuthenticationFilter.class)
                .logout()
                    .permitAll()
                    .logoutSuccessHandler(new LogoutSuccessHandler())
                    .and()
                .exceptionHandling()
                    .accessDeniedHandler(new CustomAccessDeniedHandler())
                    .and()
                .csrf()
                    .ignoringAntMatchers("/webhooks/**")
                    //COLAB-2480: Fixes 403 when double clicking login button (CSRF token is reset)
                    .csrfTokenRepository(new CookieCsrfTokenRepository())
                    .and()
                .headers().defaultsDisabled()
                    .referrerPolicy(ReferrerPolicy.ORIGIN_WHEN_CROSS_ORIGIN)
                        .and()
                    .contentTypeOptions()
                        .and()
                    .xssProtection()
                        .block(true)
                        .and()
                    .frameOptions()
                        .sameOrigin();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberDetailsService)
                .passwordEncoder(new MemberPasswordEncoder());
    }

    @Bean
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public Optional<FilterRegistrationBean> sessionFilterErrorDispatch(
            Optional<SessionRepositoryFilter> sessionRepositoryFilter) {
        if (sessionRepositoryFilter.isPresent()) {

            final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
            registrationBean.setFilter(sessionRepositoryFilter.get());
            //override registration to ensure sessions are initialized correctly for errors
            registrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC,
                    DispatcherType.ERROR);
            registrationBean.setOrder(SessionRepositoryFilter.DEFAULT_ORDER);
            return Optional.of(registrationBean);
        } else {
            log.warn("No SessionRepositoryFilter found - defaulting to regular session.");
            return Optional.empty() ;
        }
    }

    @Bean
    public SwitchUserFilter switchUserFilter() {
        SwitchUserFilter filter = new SwitchUserFilter();
        filter.setUserDetailsService(memberDetailsService);
        filter.setSwitchUserUrl("/impersonate");
        filter.setExitUserUrl("/impersonate/logout");
        filter.setTargetUrl("/");
        return filter;
    }
}
