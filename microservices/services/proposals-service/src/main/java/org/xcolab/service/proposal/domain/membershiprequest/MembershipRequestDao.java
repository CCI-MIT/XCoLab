package org.xcolab.service.proposal.domain.membershiprequest;

import org.xcolab.model.tables.pojos.MembershipRequest;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

public interface MembershipRequestDao {

    List<MembershipRequest> findByGiven(Long groupId, Integer statusId);
    MembershipRequest create(MembershipRequest membershipRequest);
    MembershipRequest get(Long membershipRequestId) throws NotFoundException;
}
