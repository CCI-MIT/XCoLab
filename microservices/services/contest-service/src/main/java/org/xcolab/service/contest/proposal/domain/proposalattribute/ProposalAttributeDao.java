package org.xcolab.service.contest.proposal.domain.proposalattribute;

import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface ProposalAttributeDao {

    ProposalAttribute create(ProposalAttribute proposalAttribute);

    ProposalAttribute get(Long id) throws NotFoundException;

    boolean update(ProposalAttribute proposalAttribute);

    List<ProposalAttribute> findByGiven(Long proposalId, String name, Long additionalId,
            Integer version);

    int delete(Long id);
}
