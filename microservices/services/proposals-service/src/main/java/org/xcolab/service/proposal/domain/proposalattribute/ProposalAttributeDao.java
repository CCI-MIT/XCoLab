package org.xcolab.service.proposal.domain.proposalattribute;

import org.xcolab.model.tables.pojos.ProposalAttribute;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

public interface ProposalAttributeDao {

    ProposalAttribute create(ProposalAttribute proposalAttribute);
    ProposalAttribute get(Long id_) throws NotFoundException;
    boolean update(ProposalAttribute proposalAttribute);
    List<ProposalAttribute> findByGiven(Long proposalId, String name, Long additionalId, Integer version);
    int delete(Long id_);
    List<ProposalAttribute> findByProposalIdVersionAndImpact(Long proposalId, Integer version);
}
