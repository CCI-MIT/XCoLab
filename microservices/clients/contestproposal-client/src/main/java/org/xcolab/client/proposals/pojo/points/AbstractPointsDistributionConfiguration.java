package org.xcolab.client.proposals.pojo.points;

import java.io.Serializable;
import java.sql.Timestamp;

class AbstractPointsDistributionConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private Long proposalid;
    private Long pointtypeid;
    private Long targetuserid;
    private Long targetsubproposalid;
    private Long targetplansectiondefinitionid;
    private Double percentage;
    private Long creator;
    private Timestamp createdate;

    public AbstractPointsDistributionConfiguration() {}

    public AbstractPointsDistributionConfiguration(AbstractPointsDistributionConfiguration value) {
        this.id_ = value.id_;
        this.proposalid = value.proposalid;
        this.pointtypeid = value.pointtypeid;
        this.targetuserid = value.targetuserid;
        this.targetsubproposalid = value.targetsubproposalid;
        this.targetplansectiondefinitionid = value.targetplansectiondefinitionid;
        this.percentage = value.percentage;
        this.creator = value.creator;
        this.createdate = value.createdate;
    }

    public AbstractPointsDistributionConfiguration(
            Long id_,
            Long proposalid,
            Long pointtypeid,
            Long targetuserid,
            Long targetsubproposalid,
            Long targetplansectiondefinitionid,
            Double percentage,
            Long creator,
            Timestamp createdate
    ) {
        this.id_ = id_;
        this.proposalid = proposalid;
        this.pointtypeid = pointtypeid;
        this.targetuserid = targetuserid;
        this.targetsubproposalid = targetsubproposalid;
        this.targetplansectiondefinitionid = targetplansectiondefinitionid;
        this.percentage = percentage;
        this.creator = creator;
        this.createdate = createdate;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
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

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
        result = prime * result + ((pointtypeid == null) ? 0 : pointtypeid.hashCode());
        result = prime * result + ((targetuserid == null) ? 0 : targetuserid.hashCode());
        result = prime * result + ((targetsubproposalid == null) ? 0
                : targetsubproposalid.hashCode());
        result = prime * result + ((targetplansectiondefinitionid == null) ? 0
                : targetplansectiondefinitionid.hashCode());
        result = prime * result + ((percentage == null) ? 0 : percentage.hashCode());
        result = prime * result + ((creator == null) ? 0 : creator.hashCode());
        result = prime * result + ((createdate == null) ? 0 : createdate.hashCode());
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
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        } else if (!id_.equals(other.id_)) {
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
        if (createdate == null) {
            if (other.createdate != null) {
                return false;
            }
        } else if (!createdate.equals(other.createdate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PointsDistributionConfiguration (");

        sb.append(id_);
        sb.append(", ").append(proposalid);
        sb.append(", ").append(pointtypeid);
        sb.append(", ").append(targetuserid);
        sb.append(", ").append(targetsubproposalid);
        sb.append(", ").append(targetplansectiondefinitionid);
        sb.append(", ").append(percentage);
        sb.append(", ").append(creator);
        sb.append(", ").append(createdate);

        sb.append(")");
        return sb.toString();
    }
}
