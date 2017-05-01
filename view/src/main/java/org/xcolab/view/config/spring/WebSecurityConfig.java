package org.xcolab.view.config.spring;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.header.writers.DelegatingRequestMatcherHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import org.xcolab.util.autoconfigure.XCoLabProperties;
import org.xcolab.view.auth.AuthenticationContext;
import org.xcolab.view.auth.handlers.AuthenticationFailureHandler;
import org.xcolab.view.auth.handlers.AuthenticationSuccessHandler;
import org.xcolab.view.auth.handlers.LogoutSuccessHandler;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.auth.login.spring.MemberPasswordEncoder;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(XCoLabProperties.class)
@SuppressWarnings("ProhibitedExceptionDeclared")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger _log = LoggerFactory.getLogger(WebSecurityConfig.class);

    private final XCoLabProperties properties;

    @Autowired
    public WebSecurityConfig(XCoLabProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/admin/management/**").hasRole("ADMIN")
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(new AuthenticationSuccessHandler(new AuthenticationContext()))
                    .failureHandler(new AuthenticationFailureHandler())
                    .and()
                .rememberMe()
                    .rememberMeServices(rememberMeServices())
                    .and()
                .logout()
                    .permitAll()
                    .logoutSuccessHandler(new LogoutSuccessHandler())
                    .and()
                .csrf()
                    .disable()
                .headers().addHeaderWriter(new DelegatingRequestMatcherHeaderWriter(
                        new NegatedRequestMatcher(
                                new OrRequestMatcher(getWhiteList())),
                                new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN)));
    }

    private void generateSecretIfNecessary() {
        // Make sure we have a secret key even when not configured
        if (StringUtils.isBlank(properties.getSecret())) {
            _log.warn("No application secret configured - generating one-time secret.");
            SecureRandom random = new SecureRandom();
            String secret = new BigInteger(130, random).toString(32);
            _log.warn("Generated secret key: {}", secret);
            properties.setSecret(secret);
        }
    }

    @Bean
    public RememberMeServices rememberMeServices() {
        generateSecretIfNecessary();
        final TokenBasedRememberMeServices rememberMeServices =
                new TokenBasedRememberMeServices(properties.getSecret(), memberDetailsService());
        rememberMeServices.setTokenValiditySeconds(properties.getRememberMe().getTokenValiditySeconds());
        return rememberMeServices;
    }

    @Bean
    public MemberDetailsService memberDetailsService() {
        return new MemberDetailsService();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberDetailsService())
                .passwordEncoder(new MemberPasswordEncoder());
    }

    private List<RequestMatcher> getWhiteList(){
        List<RequestMatcher> whitelist = new ArrayList<>();
        whitelist.add(new RegexRequestMatcher(".*climatecolab.org.*", HttpMethod.POST.name()));
        whitelist.add(new RegexRequestMatcher(".*solvecolab.mit.edu.*",HttpMethod.POST.name()));
        whitelist.add(new RegexRequestMatcher(".*kcc.mit.edu.org.*",HttpMethod.POST.name()));
        whitelist.add(new RegexRequestMatcher(".*colab2.mit.edu.org.*",HttpMethod.POST.name()));
        return whitelist;
    }
}
