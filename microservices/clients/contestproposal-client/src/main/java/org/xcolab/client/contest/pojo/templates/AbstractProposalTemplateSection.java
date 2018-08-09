package org.xcolab.client.contest.pojo.templates;

import java.io.Serializable;

abstract class AbstractProposalTemplateSection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long proposalTemplateId;
    private Long sectionDefinitionId;
    private Integer weight;

    public AbstractProposalTemplateSection() {}

    public AbstractProposalTemplateSection(AbstractProposalTemplateSection value) {
        this.proposalTemplateId = value.proposalTemplateId;
        this.sectionDefinitionId = value.sectionDefinitionId;
        this.weight = value.weight;
    }

    public AbstractProposalTemplateSection(Long proposalTemplateId, Long sectionDefinitionId, Integer weight) {
        this.proposalTemplateId = proposalTemplateId;
        this.sectionDefinitionId = sectionDefinitionId;
        this.weight = weight;
    }

    public Long getProposalTemplateId() {
        return this.proposalTemplateId;
    }

    public void setProposalTemplateId(Long proposalTemplateId) {
        this.proposalTemplateId = proposalTemplateId;
    }

    public Long getSectionDefinitionId() {
        return this.sectionDefinitionId;
    }

    public void setSectionDefinitionId(Long sectionDefinitionId) {
        this.sectionDefinitionId = sectionDefinitionId;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((proposalTemplateId == null) ? 0 : proposalTemplateId.hashCode());
        result = prime * result + ((sectionDefinitionId == null) ? 0 : sectionDefinitionId.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractProposalTemplateSection other = (AbstractProposalTemplateSection) obj;
        if (proposalTemplateId == null) {
            if (other.proposalTemplateId != null) {
                return false;
            }
        } else if (!proposalTemplateId.equals(other.proposalTemplateId)) {
            return false;
        }
        if (sectionDefinitionId == null) {
            if (other.sectionDefinitionId != null) {
                return false;
            }
        } else if (!sectionDefinitionId.equals(other.sectionDefinitionId)) {
            return false;
        }
        if (weight == null) {
            if (other.weight != null) {
                return false;
            }
        } else if (!weight.equals(other.weight)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "PlanTemplateSection (" + proposalTemplateId +
                ", " + sectionDefinitionId +
                ", " + weight +
                ")";
    }
}
