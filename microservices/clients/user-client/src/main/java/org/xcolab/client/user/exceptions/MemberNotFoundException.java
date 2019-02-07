package org.xcolab.client.user.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class MemberNotFoundException extends EntityNotFoundException {
    public MemberNotFoundException(String msg) {
        super(msg, MemberNotFoundException.class);
    }
    public MemberNotFoundException() {
        super("");
    }
}
