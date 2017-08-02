package org.xcolab.view.errors;

import org.springframework.http.HttpStatus;

import org.xcolab.client.members.pojo.Member;

public class AccessDeniedPage extends ErrorPage {

    public AccessDeniedPage(Member member) {
        super("errors/accessDenied",
                member == null ? HttpStatus.UNAUTHORIZED : HttpStatus.FORBIDDEN);
    }
}
