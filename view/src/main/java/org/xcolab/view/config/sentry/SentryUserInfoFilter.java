package org.xcolab.view.config.sentry;

import io.sentry.Sentry;
import io.sentry.event.UserBuilder;
import org.springframework.web.filter.GenericFilterBean;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
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

        final UserWrapper realMemberOrNull = authenticationContext.getRealMemberOrNull();
        if (realMemberOrNull != null) {
            //noinspection UnnecessaryLocalVariable
            final UserWrapper member = realMemberOrNull;
            Sentry.getContext().setUser(new UserBuilder()
                    .setId(Long.toString(member.getId()))
                    .setUsername(member.getScreenName())
                    .setIpAddress(request.getRemoteAddr())
                    .build());
        }

        chain.doFilter(request, response);
    }
}
