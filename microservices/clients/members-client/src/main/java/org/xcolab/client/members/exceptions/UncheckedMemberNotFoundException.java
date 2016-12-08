package org.xcolab.client.members.exceptions;

public class UncheckedMemberNotFoundException extends IllegalStateException {
    public UncheckedMemberNotFoundException(long memberId) {
        super("Member does not exist: " + memberId);
    }
}
