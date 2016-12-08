package org.xcolab.hooks.climatecolab.impersonation;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.entity.utils.members.MemberAuthUtil;

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

public class ImpersonationFilter implements Filter {

    private void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        long impersonateMemberId = Long.valueOf(request.getParameter("memberId"));

        try {
            MembersClient.getMember(impersonateMemberId);
            Cookie cookie =
                    new Cookie(MemberAuthUtil.IMPERSONATE_MEMBER_ID_COOKIE_NAME, Long.toString(impersonateMemberId));
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendRedirect("/");
        } catch (MemberNotFoundException e) {
            response.sendError(404, "Member to impersonate does not exist");
        }
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
