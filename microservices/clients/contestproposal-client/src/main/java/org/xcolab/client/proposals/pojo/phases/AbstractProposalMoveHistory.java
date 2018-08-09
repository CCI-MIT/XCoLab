package org.xcolab.client.proposals.pojo.phases;

import java.io.Serializable;
import java.sql.Timestamp;

class AbstractProposalMoveHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long sourceproposalid;
    private Long sourcecontestid;
    private Long sourcephaseid;
    private Long targetproposalid;
    private Long targetcontestid;
    private Long targetphaseid;
    private Long movinguserid;
    private Timestamp movedAt;
    private String movetype;

    public AbstractProposalMoveHistory() {}

    public AbstractProposalMoveHistory(AbstractProposalMoveHistory value) {
        this.id = value.id;
        this.sourceproposalid = value.sourceproposalid;
        this.sourcecontestid = value.sourcecontestid;
        this.sourcephaseid = value.sourcephaseid;
        this.targetproposalid = value.targetproposalid;
        this.targetcontestid = value.targetcontestid;
        this.targetphaseid = value.targetphaseid;
        this.movinguserid = value.movinguserid;
        this.movedAt = value.movedAt;
        this.movetype = value.movetype;
    }

    public AbstractProposalMoveHistory(
            Long id,
            Long sourceproposalid,
            Long sourcecontestid,
            Long sourcephaseid,
            Long targetproposalid,
            Long targetcontestid,
            Long targetphaseid,
            Long movinguserid,
            Timestamp movedAt,
            String movetype
    ) {
        this.id = id;
        this.sourceproposalid = sourceproposalid;
        this.sourcecontestid = sourcecontestid;
        this.sourcephaseid = sourcephaseid;
        this.targetproposalid = targetproposalid;
        this.targetcontestid = targetcontestid;
        this.targetphaseid = targetphaseid;
        this.movinguserid = movinguserid;
        this.movedAt = movedAt;
        this.movetype = movetype;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceProposalId() {
        return this.sourceproposalid;
    }

    public void setSourceProposalId(Long sourceproposalid) {
        this.sourceproposalid = sourceproposalid;
    }

    public Long getSourceContestId() {
        return this.sourcecontestid;
    }

    public void setSourceContestId(Long sourcecontestid) {
        this.sourcecontestid = sourcecontestid;
    }

    public Long getSourcePhaseId() {
        return this.sourcephaseid;
    }

    public void setSourcePhaseId(Long sourcephaseid) {
        this.sourcephaseid = sourcephaseid;
    }

    public Long getTargetProposalId() {
        return this.targetproposalid;
    }

    public void setTargetProposalId(Long targetproposalid) {
        this.targetproposalid = targetproposalid;
    }

    public Long getTargetContestId() {
        return this.targetcontestid;
    }

    public void setTargetContestId(Long targetcontestid) {
        this.targetcontestid = targetcontestid;
    }

    public Long getTargetPhaseId() {
        return this.targetphaseid;
    }

    public void setTargetPhaseId(Long targetphaseid) {
        this.targetphaseid = targetphaseid;
    }

    public Long getMovingUserId() {
        return this.movinguserid;
    }

    public void setMovingUserId(Long movinguserid) {
        this.movinguserid = movinguserid;
    }

    public Timestamp getMovedAt() {
        return this.movedAt;
    }

    public void setMovedAt(Timestamp movedAt) {
        this.movedAt = movedAt;
    }

    public String getMoveType() {
        return this.movetype;
    }

    public void setMoveType(String movetype) {
        this.movetype = movetype;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((sourceproposalid == null) ? 0 : sourceproposalid.hashCode());
        result = prime * result + ((sourcecontestid == null) ? 0 : sourcecontestid.hashCode());
        result = prime * result + ((sourcephaseid == null) ? 0 : sourcephaseid.hashCode());
        result = prime * result + ((targetproposalid == null) ? 0 : targetproposalid.hashCode());
        result = prime * result + ((targetcontestid == null) ? 0 : targetcontestid.hashCode());
        result = prime * result + ((targetphaseid == null) ? 0 : targetphaseid.hashCode());
        result = prime * result + ((movinguserid == null) ? 0 : movinguserid.hashCode());
        result = prime * result + ((movedAt == null) ? 0 : movedAt.hashCode());
        result = prime * result + ((movetype == null) ? 0 : movetype.hashCode());
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
        final AbstractProposalMoveHistory other = (AbstractProposalMoveHistory) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (sourceproposalid == null) {
            if (other.sourceproposalid != null) {
                return false;
            }
        } else if (!sourceproposalid.equals(other.sourceproposalid)) {
            return false;
        }
        if (sourcecontestid == null) {
            if (other.sourcecontestid != null) {
                return false;
            }
        } else if (!sourcecontestid.equals(other.sourcecontestid)) {
            return false;
        }
        if (sourcephaseid == null) {
            if (other.sourcephaseid != null) {
                return false;
            }
        } else if (!sourcephaseid.equals(other.sourcephaseid)) {
            return false;
        }
        if (targetproposalid == null) {
            if (other.targetproposalid != null) {
                return false;
            }
        } else if (!targetproposalid.equals(other.targetproposalid)) {
            return false;
        }
        if (targetcontestid == null) {
            if (other.targetcontestid != null) {
                return false;
            }
        } else if (!targetcontestid.equals(other.targetcontestid)) {
            return false;
        }
        if (targetphaseid == null) {
            if (other.targetphaseid != null) {
                return false;
            }
        } else if (!targetphaseid.equals(other.targetphaseid)) {
            return false;
        }
        if (movinguserid == null) {
            if (other.movinguserid != null) {
                return false;
            }
        } else if (!movinguserid.equals(other.movinguserid)) {
            return false;
        }
        if (movedAt == null) {
            if (other.movedAt != null) {
                return false;
            }
        } else if (!movedAt.equals(other.movedAt)) {
            return false;
        }
        if (movetype == null) {
            if (other.movetype != null) {
                return false;
            }
        } else if (!movetype.equals(other.movetype)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb = "ProposalMoveHistory (" + id + ", " + sourceproposalid + ", " + sourcecontestid
                + ", " + sourcephaseid + ", " + targetproposalid + ", " + targetcontestid + ", "
                + targetphaseid + ", " + movinguserid + ", " + movedAt + ", " + movetype + ")";

        return sb;
    }
}
