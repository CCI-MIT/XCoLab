package org.xcolab.view.auth;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;

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

import static org.xcolab.view.auth.AuthenticationContext.IMPERSONATE_MEMBER_ID_COOKIE_NAME;

public class ImpersonationFilter implements Filter {

    private void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (request.getParameter("logout") != null) {
            logout(response);
        } else if (request.getParameter("memberId") != null) {
            impersonate(request, response);
        } else {
            response.sendError(400, "Invalid request");
        }
    }

    private void impersonate(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        long impersonateMemberId = Long.valueOf(request.getParameter("memberId"));

        try {
            MembersClient.getMember(impersonateMemberId);
            Cookie cookie = new Cookie(IMPERSONATE_MEMBER_ID_COOKIE_NAME,
                    Long.toString(impersonateMemberId));
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendRedirect("/");
        } catch (MemberNotFoundException e) {
            response.sendError(404, "Member to impersonate does not exist");
        }
    }

    private void logout(HttpServletResponse response) throws IOException {
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
    }

    @Override
    public void destroy() {

    }
}
