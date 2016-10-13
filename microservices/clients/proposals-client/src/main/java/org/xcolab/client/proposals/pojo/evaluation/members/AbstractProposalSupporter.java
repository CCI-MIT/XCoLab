package org.xcolab.client.proposals.pojo.evaluation.members;

import java.sql.Timestamp;

class AbstractProposalSupporter {

    private Long proposalid;
    private Long userid;
    private Timestamp createdate;

    public AbstractProposalSupporter() {}

    public AbstractProposalSupporter(AbstractProposalSupporter value) {
        this.proposalid = value.proposalid;
        this.userid = value.userid;
        this.createdate = value.createdate;
    }

    public AbstractProposalSupporter(
            Long proposalid,
            Long userid,
            Timestamp createdate
    ) {
        this.proposalid = proposalid;
        this.userid = userid;
        this.createdate = createdate;
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
        result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
        result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
        StringBuilder sb = new StringBuilder("ProposalSupporter (");

        sb.append(proposalid);
        sb.append(", ").append(userid);
        sb.append(", ").append(createdate);

        sb.append(")");
        return sb.toString();
    }
}
