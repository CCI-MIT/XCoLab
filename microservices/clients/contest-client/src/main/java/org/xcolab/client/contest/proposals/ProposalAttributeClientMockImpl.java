package org.xcolab.client.contest.proposals;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttributeHelperDataDto;
import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttributeHelperDataDto;
import org.xcolab.client.contest.proposals.exceptions.ProposalAttributeNotFoundException;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ProposalAttributeClientMockImpl implements IProposalAttributeClient {

    @Override
    public ProposalAttribute getProposalAttribute(Long proposalAttributeId)
            throws ProposalAttributeNotFoundException {
        return null;
    }

    @Override
    public boolean deleteProposalAttribute(Long id) {
        return false;
    }

    @Override
    public boolean updateProposalAttribute(ProposalAttribute proposalAttribute) {
        return false;
    }

    @Override
    public List<ProposalAttribute> getProposalAttributes(Long proposalId, String name,
            Long additionalId, Integer version) {
        return Collections.emptyList();
    }

    @Override
    public ProposalAttributeHelperDataDto getProposalAttributeHelperData(Long proposalId,
            Integer version) {
        return null;
    }

    @Override
    public ProposalUnversionedAttributeHelperDataDto getProposalUnversionedAttributeHelperData(
            Long proposalId) {
        return null;
    }

    @Override
    public ProposalAttribute setProposalAttribute(ProposalAttribute proposalAttribute,
            Long authorUserId) {
        return null;
    }

    @Override
    public boolean deleteProposalUnversionedAttribute(Long id) {
        return false;
    }

    @Override
    public List<ProposalUnversionedAttribute> getProposalUnversionedAttributesByProposalId(
            Long proposalId) {
        return Collections.emptyList();
    }

    @Override
    public ProposalUnversionedAttribute createProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return null;
    }

    @Override
    public ProposalUnversionedAttribute getProposalUnversionedAttribute(Long proposalId,
            String name) throws EntityNotFoundException {
        return null;
    }

    @Override
    public boolean updateProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return false;
    }
}
