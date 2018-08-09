package org.xcolab.client.members.exceptions;

public class UncheckedMemberNotFoundException extends IllegalStateException {
    public UncheckedMemberNotFoundException(long userId) {
        super("Member does not exist: " + userId);
    }

    public UncheckedMemberNotFoundException(String msg) {
        super(msg);
    }
}
