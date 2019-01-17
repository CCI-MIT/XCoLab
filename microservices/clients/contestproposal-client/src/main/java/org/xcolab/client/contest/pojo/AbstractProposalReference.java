package org.xcolab.client.contest.pojo;

import java.io.Serializable;

class AbstractProposalReference implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long proposalid;
    private Long subproposalid;
    private Long sectionattributeid;

    public AbstractProposalReference() {}

    public AbstractProposalReference(AbstractProposalReference value) {
        this.proposalid = value.proposalid;
        this.subproposalid = value.subproposalid;
        this.sectionattributeid = value.sectionattributeid;
    }

    public AbstractProposalReference(
            Long proposalid,
            Long subproposalid,
            Long sectionattributeid
    ) {
        this.proposalid = proposalid;
        this.subproposalid = subproposalid;
        this.sectionattributeid = sectionattributeid;
    }

    public Long getProposalId() {
        return this.proposalid;
    }

    public void setProposalId(Long proposalid) {
        this.proposalid = proposalid;
    }

    public Long getSubProposalId() {
        return this.subproposalid;
    }

    public void setSubProposalId(Long subproposalid) {
        this.subproposalid = subproposalid;
    }

    public Long getSectionAttributeId() {
        return this.sectionattributeid;
    }

    public void setSectionAttributeId(Long sectionattributeid) {
        this.sectionattributeid = sectionattributeid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
        result = prime * result + ((subproposalid == null) ? 0 : subproposalid.hashCode());
        result =
                prime * result + ((sectionattributeid == null) ? 0 : sectionattributeid.hashCode());
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
        final AbstractProposalReference other = (AbstractProposalReference) obj;
        if (proposalid == null) {
            if (other.proposalid != null) {
                return false;
            }
        } else if (!proposalid.equals(other.proposalid)) {
            return false;
        }
        if (subproposalid == null) {
            if (other.subproposalid != null) {
                return false;
            }
        } else if (!subproposalid.equals(other.subproposalid)) {
            return false;
        }
        if (sectionattributeid == null) {
            if (other.sectionattributeid != null) {
                return false;
            }
        } else if (!sectionattributeid.equals(other.sectionattributeid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb = "ProposalReference (" + proposalid + ", " + subproposalid + ", "
                + sectionattributeid + ")";

        return sb;
    }
}
