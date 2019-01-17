package org.xcolab.client.contest.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

class AbstractProposalSupporter implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long proposalid;
    private Long userid;
    private Timestamp createdAt;

    public AbstractProposalSupporter() {}

    public AbstractProposalSupporter(AbstractProposalSupporter value) {
        this.proposalid = value.proposalid;
        this.userid = value.userid;
        this.createdAt = value.createdAt;
    }

    public AbstractProposalSupporter(
            Long proposalid,
            Long userid,
            Timestamp createdAt
    ) {
        this.proposalid = proposalid;
        this.userid = userid;
        this.createdAt = createdAt;
    }

    public Long getProposalId() {
        return this.proposalid;
    }

    public void setProposalId(Long proposalid) {
        this.proposalid = proposalid;
    }

    public Long getUserId() {
        return this.userid;
    }

    public void setUserId(Long userid) {
        this.userid = userid;
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
        result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
        result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
        final AbstractProposalSupporter other = (AbstractProposalSupporter) obj;
        if (proposalid == null) {
            if (other.proposalid != null) {
                return false;
            }
        } else if (!proposalid.equals(other.proposalid)) {
            return false;
        }
        if (userid == null) {
            if (other.userid != null) {
                return false;
            }
        } else if (!userid.equals(other.userid)) {
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
        String sb = "ProposalSupporter (" + proposalid + ", " + userid + ", " + createdAt + ")";

        return sb;
    }
}
