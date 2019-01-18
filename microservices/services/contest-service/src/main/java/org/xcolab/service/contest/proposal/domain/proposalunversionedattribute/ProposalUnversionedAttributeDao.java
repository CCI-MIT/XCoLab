package org.xcolab.service.contest.proposal.domain.proposalunversionedattribute;

import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttribute;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;


public interface ProposalUnversionedAttributeDao {
    int delete(Long id);

    ProposalUnversionedAttribute getByProposalIdName(Long proposalId, String name);

    boolean update(ProposalUnversionedAttribute proposalUnversionedAttribute);

    ProposalUnversionedAttribute create(ProposalUnversionedAttribute proposalUnversionedAttribute);

    ProposalUnversionedAttribute get(Long id) throws NotFoundException;

    List<ProposalUnversionedAttribute> findByGiven(Long proposalId);
}
