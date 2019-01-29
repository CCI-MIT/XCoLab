package org.xcolab.client.contest.proposals.exceptions;

import org.xcolab.util.http.exceptions.EntityNotFoundException;

public class MembershipRequestNotFoundException extends EntityNotFoundException {

    public MembershipRequestNotFoundException(Long proposalAttributeId) {
        super("MembershipRequest with id " + proposalAttributeId + " does not exist",
                MembershipRequestNotFoundException.class);
    }
}
