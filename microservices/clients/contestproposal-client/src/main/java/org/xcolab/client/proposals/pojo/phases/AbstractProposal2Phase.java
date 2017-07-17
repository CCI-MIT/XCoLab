package org.xcolab.client.proposals.pojo.phases;

import java.io.Serializable;

class AbstractProposal2Phase implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long proposalid;
    private Long contestphaseid;
    private Integer versionfrom;
    private Integer versionto;
    private Integer sortweight;
    private Boolean autopromotecandidate;

    public AbstractProposal2Phase() {}

    public AbstractProposal2Phase(AbstractProposal2Phase value) {
        this.proposalid = value.proposalid;
        this.contestphaseid = value.contestphaseid;
        this.versionfrom = value.versionfrom;
        this.versionto = value.versionto;
        this.sortweight = value.sortweight;
        this.autopromotecandidate = value.autopromotecandidate;
    }

    public AbstractProposal2Phase(
            Long proposalid,
            Long contestphaseid,
            Integer versionfrom,
            Integer versionto,
            Integer sortweight,
            Boolean autopromotecandidate
    ) {
        this.proposalid = proposalid;
        this.contestphaseid = contestphaseid;
        this.versionfrom = versionfrom;
        this.versionto = versionto;
        this.sortweight = sortweight;
        this.autopromotecandidate = autopromotecandidate;
    }

    public Long getProposalId() {
        return this.proposalid;
    }

    public void setProposalId(Long proposalid) {
        this.proposalid = proposalid;
    }

    public Long getContestPhaseId() {
        return this.contestphaseid;
    }

    public void setContestPhaseId(Long contestphaseid) {
        this.contestphaseid = contestphaseid;
    }

    public Integer getVersionFrom() {
        return this.versionfrom;
    }

    public void setVersionFrom(Integer versionfrom) {
        this.versionfrom = versionfrom;
    }

    public Integer getVersionTo() {
        return this.versionto;
    }

    public void setVersionTo(Integer versionto) {
        this.versionto = versionto;
    }

    public Integer getSortWeight() {
        return this.sortweight;
    }

    public void setSortWeight(Integer sortweight) {
        this.sortweight = sortweight;
    }

    public Boolean getAutopromoteCandidate() {
        return this.autopromotecandidate;
    }

    public void setAutopromoteCandidate(Boolean autopromotecandidate) {
        this.autopromotecandidate = autopromotecandidate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
        result = prime * result + ((contestphaseid == null) ? 0 : contestphaseid.hashCode());
        result = prime * result + ((versionfrom == null) ? 0 : versionfrom.hashCode());
        result = prime * result + ((versionto == null) ? 0 : versionto.hashCode());
        result = prime * result + ((sortweight == null) ? 0 : sortweight.hashCode());
        result = prime * result + ((autopromotecandidate == null) ? 0
                : autopromotecandidate.hashCode());
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
        final AbstractProposal2Phase other = (AbstractProposal2Phase) obj;
        if (proposalid == null) {
            if (other.proposalid != null) {
                return false;
            }
        } else if (!proposalid.equals(other.proposalid)) {
            return false;
        }
        if (contestphaseid == null) {
            if (other.contestphaseid != null) {
                return false;
            }
        } else if (!contestphaseid.equals(other.contestphaseid)) {
            return false;
        }
        if (versionfrom == null) {
            if (other.versionfrom != null) {
                return false;
            }
        } else if (!versionfrom.equals(other.versionfrom)) {
            return false;
        }
        if (versionto == null) {
            if (other.versionto != null) {
                return false;
            }
        } else if (!versionto.equals(other.versionto)) {
            return false;
        }
        if (sortweight == null) {
            if (other.sortweight != null) {
                return false;
            }
        } else if (!sortweight.equals(other.sortweight)) {
            return false;
        }
        if (autopromotecandidate == null) {
            if (other.autopromotecandidate != null) {
                return false;
            }
        } else if (!autopromotecandidate.equals(other.autopromotecandidate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String sb =
                "Proposal2Phase (" + proposalid + ", " + contestphaseid + ", " + versionfrom + ", "
                        + versionto + ", " + sortweight + ", " + autopromotecandidate + ")";

        return sb;
    }
}
