package org.xcolab.view.config.spring.filters;

import io.sentry.Sentry;
import io.sentry.event.UserBuilder;
import org.springframework.web.filter.GenericFilterBean;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.auth.AuthenticationContext;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SentryUserInfoFilter extends GenericFilterBean {

    private final AuthenticationContext authenticationContext = new AuthenticationContext();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            throw new ServletException("SentryUserInfoFilter just supports HTTP requests");
        }

        final Member realMemberOrNull = authenticationContext.getRealMemberOrNull();
        if (realMemberOrNull != null) {
            //noinspection UnnecessaryLocalVariable
            final Member member = realMemberOrNull;
            Sentry.getContext().setUser(new UserBuilder()
                    .setId(Long.toString(member.getId()))
                    .build());
        }

        chain.doFilter(request, response);
    }
}
