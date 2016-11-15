package org.xcolab.client.proposals.exceptions;

public class MembershipRequestNotFoundException extends Exception {
    public MembershipRequestNotFoundException(long proposalAttributeId) {
        this("MembershipRequestNotFoundException with id " + proposalAttributeId + " does not exist");
    }

    public MembershipRequestNotFoundException(String msg) {
        super(msg);
    }
}
