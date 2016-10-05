package org.xcolab.service.proposal.domain.proposalunversionedattribute;

import org.xcolab.model.tables.pojos.ProposalUnversionedAttribute;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;


public interface ProposalUnversionedAttributeDao {
    int delete(Long id_);

    ProposalUnversionedAttribute getByProposalIdName(Long proposalId, String name);

    boolean update(ProposalUnversionedAttribute proposalUnversionedAttribute);

    ProposalUnversionedAttribute create(ProposalUnversionedAttribute proposalUnversionedAttribute);

    ProposalUnversionedAttribute get(Long id_) throws NotFoundException;

    List<ProposalUnversionedAttribute> findByGiven(Long proposalId);
}
