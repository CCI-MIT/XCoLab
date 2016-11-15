package org.xcolab.client.proposals.pojo;

import java.sql.Timestamp;

class AbstractProposalVersion {

    private Long proposalid;
    private Integer version;
    private Long authorid;
    private Timestamp createdate;
    private String updatetype;
    private Long updateadditionalid;

    public AbstractProposalVersion() {}

    public AbstractProposalVersion(AbstractProposalVersion value) {
        this.proposalid = value.proposalid;
        this.version = value.version;
        this.authorid = value.authorid;
        this.createdate = value.createdate;
        this.updatetype = value.updatetype;
        this.updateadditionalid = value.updateadditionalid;
    }

    public AbstractProposalVersion(
            Long proposalid,
            Integer version,
            Long authorid,
            Timestamp createdate,
            String updatetype,
            Long updateadditionalid
    ) {
        this.proposalid = proposalid;
        this.version = version;
        this.authorid = authorid;
        this.createdate = createdate;
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

    public Long getAuthorId() {
        return this.authorid;
    }

    public void setAuthorId(Long authorid) {
        this.authorid = authorid;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
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
        result = prime * result + ((authorid == null) ? 0 : authorid.hashCode());
        result = prime * result + ((createdate == null) ? 0 : createdate.hashCode());
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
        if (authorid == null) {
            if (other.authorid != null) {
                return false;
            }
        } else if (!authorid.equals(other.authorid)) {
            return false;
        }
        if (createdate == null) {
            if (other.createdate != null) {
                return false;
            }
        } else if (!createdate.equals(other.createdate)) {
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
        StringBuilder sb = new StringBuilder("ProposalVersion (");

        sb.append(proposalid);
        sb.append(", ").append(version);
        sb.append(", ").append(authorid);
        sb.append(", ").append(createdate);
        sb.append(", ").append(updatetype);
        sb.append(", ").append(updateadditionalid);

        sb.append(")");
        return sb.toString();
    }
}
