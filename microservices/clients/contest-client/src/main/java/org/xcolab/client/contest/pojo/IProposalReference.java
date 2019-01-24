package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalReference;

@JsonDeserialize(as = ProposalReference.class)
public interface IProposalReference {

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getSubProposalId();

    void setSubProposalId(Long subProposalId);

    Long getSectionAttributeId();

    void setSectionAttributeId(Long sectionAttributeId);
}
