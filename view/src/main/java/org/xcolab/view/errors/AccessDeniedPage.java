package org.xcolab.view.errors;

import org.springframework.http.HttpStatus;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;

public class AccessDeniedPage extends AbstractErrorPage {

    public AccessDeniedPage(UserWrapper member) {
        super("errors/accessDenied",
                member == null ? HttpStatus.UNAUTHORIZED : HttpStatus.FORBIDDEN);
    }
}
