package org.xcolab.view.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
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

import org.xcolab.view.auth.AuthenticationContext;
import org.xcolab.view.auth.handlers.AuthenticationFailureHandler;
import org.xcolab.view.auth.handlers.AuthenticationSuccessHandler;
import org.xcolab.view.auth.handlers.LogoutSuccessHandler;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.auth.login.spring.MemberPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@SuppressWarnings("ProhibitedExceptionDeclared")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final RememberMeServices rememberMeServices;
    private final MemberDetailsService memberDetailsService;
    private final WebProperties webProperties;

    @Autowired
    public WebSecurityConfig(RememberMeServices rememberMeServices,
            MemberDetailsService memberDetailsService,
            WebProperties webProperties) {
        this.rememberMeServices = rememberMeServices;
        this.memberDetailsService = memberDetailsService;
        this.webProperties = webProperties;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/admin/management/**").hasRole("ADMIN");

        if (webProperties.getGuestAccess().isAllowAll()) {
            httpSecurity.authorizeRequests()
                    .anyRequest().permitAll();
        } else {
            if (webProperties.getGuestAccess().isAlwaysAllowHomepage()) {
                httpSecurity.authorizeRequests()
                        .antMatchers("/").permitAll();
            }
            httpSecurity.authorizeRequests()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/login/**").permitAll()
                    .anyRequest().authenticated();
        }

        httpSecurity
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(new AuthenticationSuccessHandler(new AuthenticationContext()))
                    .failureHandler(new AuthenticationFailureHandler())
                    .and()
                .rememberMe()
                    .rememberMeServices(rememberMeServices)
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

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberDetailsService)
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
