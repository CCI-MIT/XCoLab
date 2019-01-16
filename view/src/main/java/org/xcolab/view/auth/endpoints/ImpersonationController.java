package org.xcolab.view.auth.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.MembersClient;
import org.xcolab.client.user.exceptions.MemberNotFoundException;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.xcolab.view.auth.AuthenticationContext.IMPERSONATE_MEMBER_ID_COOKIE_NAME;

@Controller
@RequestMapping("/impersonate")
public class ImpersonationController {

    @PostMapping
    public void impersonate(HttpServletRequest request, HttpServletResponse response,
            @RequestParam long userId) throws IOException {
        try {
            MembersClient.getMember(userId);
            Cookie cookie = new Cookie(IMPERSONATE_MEMBER_ID_COOKIE_NAME, Long.toString(userId));
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendRedirect("/");
        } catch (MemberNotFoundException e) {
            response.sendError(404, "Member to impersonate does not exist");
        }
    }

    @PostMapping("logout")
    public void logout(HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie(IMPERSONATE_MEMBER_ID_COOKIE_NAME, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect("/");
    }
}
