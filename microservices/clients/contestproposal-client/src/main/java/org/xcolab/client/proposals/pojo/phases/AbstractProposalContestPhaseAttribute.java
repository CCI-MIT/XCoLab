package org.xcolab.client.proposals.pojo.phases;

import java.io.Serializable;

class AbstractProposalContestPhaseAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private Long proposalid;
    private Long contestphaseid;
    private String name;
    private Long additionalid;
    private Long numericvalue;
    private String stringvalue;
    private Double realvalue;

    public AbstractProposalContestPhaseAttribute() {}

    public AbstractProposalContestPhaseAttribute(AbstractProposalContestPhaseAttribute value) {
        this.id_ = value.id_;
        this.proposalid = value.proposalid;
        this.contestphaseid = value.contestphaseid;
        this.name = value.name;
        this.additionalid = value.additionalid;
        this.numericvalue = value.numericvalue;
        this.stringvalue = value.stringvalue;
        this.realvalue = value.realvalue;
    }

    public AbstractProposalContestPhaseAttribute(
            Long id_,
            Long proposalid,
            Long contestphaseid,
            String name,
            Long additionalid,
            Long numericvalue,
            String stringvalue,
            Double realvalue
    ) {
        this.id_ = id_;
        this.proposalid = proposalid;
        this.contestphaseid = contestphaseid;
        this.name = name;
        this.additionalid = additionalid;
        this.numericvalue = numericvalue;
        this.stringvalue = stringvalue;
        this.realvalue = realvalue;
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

    public Long getContestPhaseId() {
        return this.contestphaseid;
    }

    public void setContestPhaseId(Long contestphaseid) {
        this.contestphaseid = contestphaseid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAdditionalId() {
        return this.additionalid;
    }

    public void setAdditionalId(Long additionalid) {
        this.additionalid = additionalid;
    }

    public Long getNumericValue() {
        return this.numericvalue;
    }

    public void setNumericValue(Long numericvalue) {
        this.numericvalue = numericvalue;
    }

    public String getStringValue() {
        return this.stringvalue;
    }

    public void setStringValue(String stringvalue) {
        this.stringvalue = stringvalue;
    }

    public Double getRealValue() {
        return this.realvalue;
    }

    public void setRealValue(Double realvalue) {
        this.realvalue = realvalue;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
        result = prime * result + ((contestphaseid == null) ? 0 : contestphaseid.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((additionalid == null) ? 0 : additionalid.hashCode());
        result = prime * result + ((numericvalue == null) ? 0 : numericvalue.hashCode());
        result = prime * result + ((stringvalue == null) ? 0 : stringvalue.hashCode());
        result = prime * result + ((realvalue == null) ? 0 : realvalue.hashCode());
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
        final AbstractProposalContestPhaseAttribute other = (AbstractProposalContestPhaseAttribute) obj;
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
        if (contestphaseid == null) {
            if (other.contestphaseid != null) {
                return false;
            }
        } else if (!contestphaseid.equals(other.contestphaseid)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (additionalid == null) {
            if (other.additionalid != null) {
                return false;
            }
        } else if (!additionalid.equals(other.additionalid)) {
            return false;
        }
        if (numericvalue == null) {
            if (other.numericvalue != null) {
                return false;
            }
        } else if (!numericvalue.equals(other.numericvalue)) {
            return false;
        }
        if (stringvalue == null) {
            if (other.stringvalue != null) {
                return false;
            }
        } else if (!stringvalue.equals(other.stringvalue)) {
            return false;
        }
        if (realvalue == null) {
            if (other.realvalue != null) {
                return false;
            }
        } else if (!realvalue.equals(other.realvalue)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ProposalContestPhaseAttribute (");

        sb.append(id_);
        sb.append(", ").append(proposalid);
        sb.append(", ").append(contestphaseid);
        sb.append(", ").append(name);
        sb.append(", ").append(additionalid);
        sb.append(", ").append(numericvalue);
        sb.append(", ").append(stringvalue);
        sb.append(", ").append(realvalue);

        sb.append(")");
        return sb.toString();
    }
}
