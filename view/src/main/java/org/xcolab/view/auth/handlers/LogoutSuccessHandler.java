package org.xcolab.view.auth.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import org.xcolab.view.util.entity.flash.AlertMessage;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    public LogoutSuccessHandler() {
        super();
    }

    @Override
    public void onLogoutSuccess(javax.servlet.http.HttpServletRequest request,
            javax.servlet.http.HttpServletResponse response,
            Authentication authentication)
            throws java.io.IOException,
            javax.servlet.ServletException{

        AlertMessage.success("We hope you come back soon!").flash(request);
        response.sendRedirect("/");


    }

}
