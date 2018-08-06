package org.xcolab.client.contest.pojo.templates;

import java.io.Serializable;

abstract class AbstractProposalTemplateSection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long sectionDefinitionId;
    private Integer weight;

    public AbstractProposalTemplateSection() {}

    public AbstractProposalTemplateSection(AbstractProposalTemplateSection value) {
        this.id = value.id;
        this.sectionDefinitionId = value.sectionDefinitionId;
        this.weight = value.weight;
    }

    public AbstractProposalTemplateSection(Long id, Long sectionDefinitionId, Integer weight) {
        this.id = id;
        this.sectionDefinitionId = sectionDefinitionId;
        this.weight = weight;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long plantemplateid) {
        this.id = plantemplateid;
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
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
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

        return "PlanTemplateSection (" + id +
                ", " + sectionDefinitionId +
                ", " + weight +
                ")";
    }
}
