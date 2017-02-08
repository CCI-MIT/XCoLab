package org.xcolab.view.auth.handlers;

import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    public LogoutSuccessHandler() {
        super();
        setDefaultTargetUrl("/");
    }
}
