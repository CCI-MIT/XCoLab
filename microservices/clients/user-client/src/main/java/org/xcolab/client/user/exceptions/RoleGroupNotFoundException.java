package org.xcolab.client.user.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class RoleGroupNotFoundException  extends EntityNotFoundException {
    public RoleGroupNotFoundException(String msg) {
        super(msg);
    }
    public RoleGroupNotFoundException() {
        super("");
    }
}
