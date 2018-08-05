package org.xcolab.client.proposals.pojo.evaluation.judges;

import java.io.Serializable;

class AbstractProposalRatingType implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String label;
    private String description;
    private Integer judgetype;
    private Boolean isactive;

    public AbstractProposalRatingType() {}

    public AbstractProposalRatingType(AbstractProposalRatingType value) {
        this.id = value.id;
        this.label = value.label;
        this.description = value.description;
        this.judgetype = value.judgetype;
        this.isactive = value.isactive;
    }

    public AbstractProposalRatingType(
            Long id,
            String label,
            String description,
            Integer judgetype,
            Boolean isactive
    ) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.judgetype = judgetype;
        this.isactive = isactive;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getJudgeType() {
        return this.judgetype;
    }

    public void setJudgeType(Integer judgetype) {
        this.judgetype = judgetype;
    }

    public Boolean getIsActive() {
        return this.isactive;
    }

    public void setIsActive(Boolean isactive) {
        this.isactive = isactive;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((judgetype == null) ? 0 : judgetype.hashCode());
        result = prime * result + ((isactive == null) ? 0 : isactive.hashCode());
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
        final AbstractProposalRatingType other = (AbstractProposalRatingType) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (label == null) {
            if (other.label != null) {
                return false;
            }
        } else if (!label.equals(other.label)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (judgetype == null) {
            if (other.judgetype != null) {
                return false;
            }
        } else if (!judgetype.equals(other.judgetype)) {
            return false;
        }
        if (isactive == null) {
            if (other.isactive != null) {
                return false;
            }
        } else if (!isactive.equals(other.isactive)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb =
                "ProposalRatingType (" + id + ", " + label + ", " + description + ", " + judgetype
                        + ", " + isactive + ")";

        return sb;
    }
}
