package org.xcolab.client.contest.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

class AbstractProposalVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long proposalid;
    private Integer version;
    private Long authorUserId;
    private Timestamp createdAt;
    private String updatetype;
    private Long updateadditionalid;

    public AbstractProposalVersion() {}

    public AbstractProposalVersion(AbstractProposalVersion value) {
        this.proposalid = value.proposalid;
        this.version = value.version;
        this.authorUserId = value.authorUserId;
        this.createdAt = value.createdAt;
        this.updatetype = value.updatetype;
        this.updateadditionalid = value.updateadditionalid;
    }

    public AbstractProposalVersion(
            Long proposalid,
            Integer version,
            Long authorUserId,
            Timestamp createdAt,
            String updatetype,
            Long updateadditionalid
    ) {
        this.proposalid = proposalid;
        this.version = version;
        this.authorUserId = authorUserId;
        this.createdAt = createdAt;
        this.updatetype = updatetype;
        this.updateadditionalid = updateadditionalid;
    }

    public Long getProposalId() {
        return this.proposalid;
    }

    public void setProposalId(Long proposalid) {
        this.proposalid = proposalid;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getAuthorUserId() {
        return this.authorUserId;
    }

    public void setAuthorUserId(Long authorUserId) {
        this.authorUserId = authorUserId;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateType() {
        return this.updatetype;
    }

    public void setUpdateType(String updatetype) {
        this.updatetype = updatetype;
    }

    public Long getUpdateAdditionalId() {
        return this.updateadditionalid;
    }

    public void setUpdateAdditionalId(Long updateadditionalid) {
        this.updateadditionalid = updateadditionalid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        result = prime * result + ((authorUserId == null) ? 0 : authorUserId.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((updatetype == null) ? 0 : updatetype.hashCode());
        result =
                prime * result + ((updateadditionalid == null) ? 0 : updateadditionalid.hashCode());
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
        final AbstractProposalVersion other = (AbstractProposalVersion) obj;
        if (proposalid == null) {
            if (other.proposalid != null) {
                return false;
            }
        } else if (!proposalid.equals(other.proposalid)) {
            return false;
        }
        if (version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!version.equals(other.version)) {
            return false;
        }
        if (authorUserId == null) {
            if (other.authorUserId != null) {
                return false;
            }
        } else if (!authorUserId.equals(other.authorUserId)) {
            return false;
        }
        if (createdAt == null) {
            if (other.createdAt != null) {
                return false;
            }
        } else if (!createdAt.equals(other.createdAt)) {
            return false;
        }
        if (updatetype == null) {
            if (other.updatetype != null) {
                return false;
            }
        } else if (!updatetype.equals(other.updatetype)) {
            return false;
        }
        if (updateadditionalid == null) {
            if (other.updateadditionalid != null) {
                return false;
            }
        } else if (!updateadditionalid.equals(other.updateadditionalid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb = "ProposalVersion (" + proposalid + ", " + version + ", " + authorUserId + ", "
                + createdAt + ", " + updatetype + ", " + updateadditionalid + ")";

        return sb;
    }
}
