package org.xcolab.hooks.climatecolab.impersonation;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.xcolab.entity.utils.members.MemberAuthUtil
        .IMPERSONATE_MEMBER_ID_COOKIE_NAME;

public class ImpersonationLogoutFilter implements Filter {

    private void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Cookie cookie = new Cookie(IMPERSONATE_MEMBER_ID_COOKIE_NAME, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("/");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        doPost((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
