package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.ProposalTemplateSection;

@JsonDeserialize(as = ProposalTemplateSection.class)
public interface IProposalTemplateSection {

    Long getProposalTemplateId();

    void setProposalTemplateId(Long proposalTemplateId);

    Long getSectionDefinitionId();

    void setSectionDefinitionId(Long sectionDefinitionId);

    Integer getWeight();

    void setWeight(Integer weight);
}
