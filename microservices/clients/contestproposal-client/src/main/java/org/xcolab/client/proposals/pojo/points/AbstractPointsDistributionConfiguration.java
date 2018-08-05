package org.xcolab.client.proposals.pojo.points;

import java.io.Serializable;
import java.sql.Timestamp;

class AbstractPointsDistributionConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long proposalid;
    private Long pointtypeid;
    private Long targetuserid;
    private Long targetsubproposalid;
    private Long targetplansectiondefinitionid;
    private Double percentage;
    private Long creator;
    private Timestamp createdAt;

    public AbstractPointsDistributionConfiguration() {}

    public AbstractPointsDistributionConfiguration(AbstractPointsDistributionConfiguration value) {
        this.id = value.id;
        this.proposalid = value.proposalid;
        this.pointtypeid = value.pointtypeid;
        this.targetuserid = value.targetuserid;
        this.targetsubproposalid = value.targetsubproposalid;
        this.targetplansectiondefinitionid = value.targetplansectiondefinitionid;
        this.percentage = value.percentage;
        this.creator = value.creator;
        this.createdAt = value.createdAt;
    }

    public AbstractPointsDistributionConfiguration(
            Long id,
            Long proposalid,
            Long pointtypeid,
            Long targetuserid,
            Long targetsubproposalid,
            Long targetplansectiondefinitionid,
            Double percentage,
            Long creator,
            Timestamp createdAt
    ) {
        this.id = id;
        this.proposalid = proposalid;
        this.pointtypeid = pointtypeid;
        this.targetuserid = targetuserid;
        this.targetsubproposalid = targetsubproposalid;
        this.targetplansectiondefinitionid = targetplansectiondefinitionid;
        this.percentage = percentage;
        this.creator = creator;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProposalId() {
        return this.proposalid;
    }

    public void setProposalId(Long proposalid) {
        this.proposalid = proposalid;
    }

    public Long getPointTypeId() {
        return this.pointtypeid;
    }

    public void setPointTypeId(Long pointtypeid) {
        this.pointtypeid = pointtypeid;
    }

    public Long getTargetUserId() {
        return this.targetuserid;
    }

    public void setTargetUserId(Long targetuserid) {
        this.targetuserid = targetuserid;
    }

    public Long getTargetSubProposalId() {
        return this.targetsubproposalid;
    }

    public void setTargetSubProposalId(Long targetsubproposalid) {
        this.targetsubproposalid = targetsubproposalid;
    }

    public Long getTargetPlanSectionDefinitionId() {
        return this.targetplansectiondefinitionid;
    }

    public void setTargetPlanSectionDefinitionId(Long targetplansectiondefinitionid) {
        this.targetplansectiondefinitionid = targetplansectiondefinitionid;
    }

    public Double getPercentage() {
        return this.percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Long getCreator() {
        return this.creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
        result = prime * result + ((pointtypeid == null) ? 0 : pointtypeid.hashCode());
        result = prime * result + ((targetuserid == null) ? 0 : targetuserid.hashCode());
        result = prime * result + ((targetsubproposalid == null) ? 0
                : targetsubproposalid.hashCode());
        result = prime * result + ((targetplansectiondefinitionid == null) ? 0
                : targetplansectiondefinitionid.hashCode());
        result = prime * result + ((percentage == null) ? 0 : percentage.hashCode());
        result = prime * result + ((creator == null) ? 0 : creator.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
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
        final AbstractPointsDistributionConfiguration
                other = (AbstractPointsDistributionConfiguration) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (proposalid == null) {
            if (other.proposalid != null) {
                return false;
            }
        } else if (!proposalid.equals(other.proposalid)) {
            return false;
        }
        if (pointtypeid == null) {
            if (other.pointtypeid != null) {
                return false;
            }
        } else if (!pointtypeid.equals(other.pointtypeid)) {
            return false;
        }
        if (targetuserid == null) {
            if (other.targetuserid != null) {
                return false;
            }
        } else if (!targetuserid.equals(other.targetuserid)) {
            return false;
        }
        if (targetsubproposalid == null) {
            if (other.targetsubproposalid != null) {
                return false;
            }
        } else if (!targetsubproposalid.equals(other.targetsubproposalid)) {
            return false;
        }
        if (targetplansectiondefinitionid == null) {
            if (other.targetplansectiondefinitionid != null) {
                return false;
            }
        } else if (!targetplansectiondefinitionid.equals(other.targetplansectiondefinitionid)) {
            return false;
        }
        if (percentage == null) {
            if (other.percentage != null) {
                return false;
            }
        } else if (!percentage.equals(other.percentage)) {
            return false;
        }
        if (creator == null) {
            if (other.creator != null) {
                return false;
            }
        } else if (!creator.equals(other.creator)) {
            return false;
        }
        if (createdAt == null) {
            if (other.createdAt != null) {
                return false;
            }
        } else if (!createdAt.equals(other.createdAt)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb =
                "PointsDistributionConfiguration (" + id + ", " + proposalid + ", " + pointtypeid
                        + ", " + targetuserid + ", " + targetsubproposalid + ", "
                        + targetplansectiondefinitionid + ", " + percentage + ", " + creator + ", "
                        + createdAt + ")";

        return sb;
    }
}
