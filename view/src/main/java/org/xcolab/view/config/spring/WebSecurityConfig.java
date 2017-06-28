package org.xcolab.view.config.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.header.writers.DelegatingRequestMatcherHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.session.web.http.SessionRepositoryFilter;

import org.xcolab.util.autoconfigure.XCoLabProperties;
import org.xcolab.view.auth.AuthenticationService;
import org.xcolab.view.auth.handlers.AuthenticationFailureHandler;
import org.xcolab.view.auth.handlers.AuthenticationSuccessHandler;
import org.xcolab.view.auth.handlers.LogoutSuccessHandler;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.auth.login.spring.MemberPasswordEncoder;
import org.xcolab.view.config.spring.properties.WebProperties;
import org.xcolab.view.config.spring.properties.WebProperties.GuestAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
@SuppressWarnings("ProhibitedExceptionDeclared")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

    private final RememberMeServices rememberMeServices;
    private final MemberDetailsService memberDetailsService;
    private final WebProperties webProperties;
    private final XCoLabProperties xCoLabProperties;
    private final AuthenticationService authenticationService;

    @Autowired
    public WebSecurityConfig(RememberMeServices rememberMeServices,
            MemberDetailsService memberDetailsService, WebProperties webProperties,
            XCoLabProperties xCoLabProperties, AuthenticationService authenticationService) {
        this.rememberMeServices = rememberMeServices;
        this.memberDetailsService = memberDetailsService;
        this.webProperties = webProperties;
        this.xCoLabProperties = xCoLabProperties;
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/admin/management/**").hasRole("ADMIN");

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
            httpSecurity.authorizeRequests()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers("/image/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/login/**").permitAll()
                    .antMatchers("/reportError**").permitAll()
                    .anyRequest().authenticated();
        }

        httpSecurity
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(new AuthenticationSuccessHandler(authenticationService,
                            guestAccessProperties.isAllowLogin()))
                    .failureHandler(new AuthenticationFailureHandler())
                    .and()
                .rememberMe()
                    .rememberMeServices(rememberMeServices)
                    // need to specify same key that is used in rememberMeServices
                    .key(xCoLabProperties.getSecret())
                    .and()
                .logout()
                    .permitAll()
                    .logoutSuccessHandler(new LogoutSuccessHandler())
                    .and()
                .csrf()
                    .disable()
                .headers()
                    .defaultsDisabled()
                    .addHeaderWriter(
                        new DelegatingRequestMatcherHeaderWriter(
                                new NegatedRequestMatcher(new OrRequestMatcher(getWhiteList())),
                                new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN))
                    );

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberDetailsService)
                .passwordEncoder(new MemberPasswordEncoder());
    }

    @Bean
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public FilterRegistrationBean sessionFilterErrorDispatch(
            Optional<SessionRepositoryFilter> sessionRepositoryFilter) {
        if (sessionRepositoryFilter.isPresent()) {

            final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
            registrationBean.setFilter(sessionRepositoryFilter.get());
            //override registration to ensure sessions are initialized correctly for errors
            registrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ASYNC,
                    DispatcherType.ERROR);
            registrationBean.setOrder(SessionRepositoryFilter.DEFAULT_ORDER);
            return registrationBean;
        } else {
            log.warn("No SessionRepositoryFilter found - defaulting to regular session.");
            return null;
        }
    }

    private List<RequestMatcher> getWhiteList(){
        List<RequestMatcher> whitelist = new ArrayList<>();
        whitelist.add(new RegexRequestMatcher(".*localhost*", HttpMethod.POST.name()));
        whitelist.add(new RegexRequestMatcher(".*climatecolab.org.*", HttpMethod.POST.name()));
        whitelist.add(new RegexRequestMatcher(".*solvecolab.mit.edu.*",HttpMethod.POST.name()));
        whitelist.add(new RegexRequestMatcher(".*kcc.mit.edu.org.*",HttpMethod.POST.name()));
        whitelist.add(new RegexRequestMatcher(".*colab2.mit.edu.org.*",HttpMethod.POST.name()));
        return whitelist;
    }
}
