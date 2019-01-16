package org.xcolab.view.errors;

import org.springframework.http.HttpStatus;

import org.xcolab.client.user.pojo.Member;

public class AccessDeniedPage extends AbstractErrorPage {

    public AccessDeniedPage(Member member) {
        super("errors/accessDenied",
                member == null ? HttpStatus.UNAUTHORIZED : HttpStatus.FORBIDDEN);
    }
}
