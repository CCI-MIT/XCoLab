package org.xcolab.view.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.xcolab.view.auth.handlers.AuthenticationFailureHandler;
import org.xcolab.view.auth.handlers.AuthenticationSuccessHandler;
import org.xcolab.view.auth.login.spring.MemberDetailsService;
import org.xcolab.view.auth.login.spring.MemberPasswordEncoder;
import org.xcolab.view.auth.handlers.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
@SuppressWarnings("ProhibitedExceptionDeclared")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/admin").authenticated()
                    .anyRequest().permitAll()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(new AuthenticationSuccessHandler("/"))
                    .failureHandler(new AuthenticationFailureHandler())
                    .and()
                .logout()
                    .permitAll()
                    .logoutSuccessHandler(new LogoutSuccessHandler())
                    .and()
                .csrf()
                    .disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new MemberDetailsService())
                .passwordEncoder(new MemberPasswordEncoder());
    }

}
