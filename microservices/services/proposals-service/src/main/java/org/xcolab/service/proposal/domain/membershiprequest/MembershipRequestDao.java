package org.xcolab.service.proposal.domain.membershiprequest;

import org.xcolab.model.tables.pojos.Membershiprequest;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

public interface MembershipRequestDao {

    Membershiprequest create(Membershiprequest membershipRequest);
    Membershiprequest get(Long membershipRequestId) throws NotFoundException;
    List<Membershiprequest> findByGiven(String groupId, String statusId);
}
