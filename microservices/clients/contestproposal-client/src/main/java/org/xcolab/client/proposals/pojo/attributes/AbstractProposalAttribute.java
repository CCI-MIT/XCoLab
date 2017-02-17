package org.xcolab.client.proposals.pojo.attributes;

import java.io.Serializable;

class AbstractProposalAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private Long proposalid;
    private Integer version;
    private Integer versionwhencreated;
    private String name;
    private Long additionalid;
    private Long numericvalue;
    private String stringvalue;
    private Double realvalue;

    public AbstractProposalAttribute() {}

    public AbstractProposalAttribute(AbstractProposalAttribute value) {
        this.id_ = value.id_;
        this.proposalid = value.proposalid;
        this.version = value.version;
        this.versionwhencreated = value.versionwhencreated;
        this.name = value.name;
        this.additionalid = value.additionalid;
        this.numericvalue = value.numericvalue;
        this.stringvalue = value.stringvalue;
        this.realvalue = value.realvalue;
    }

    public AbstractProposalAttribute(
            Long id_,
            Long proposalid,
            Integer version,
            Integer versionwhencreated,
            String name,
            Long additionalid,
            Long numericvalue,
            String stringvalue,
            Double realvalue
    ) {
        this.id_ = id_;
        this.proposalid = proposalid;
        this.version = version;
        this.versionwhencreated = versionwhencreated;
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

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getVersionWhenCreated() {
        return this.versionwhencreated;
    }

    public void setVersionWhenCreated(Integer versionwhencreated) {
        this.versionwhencreated = versionwhencreated;
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
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        result =
                prime * result + ((versionwhencreated == null) ? 0 : versionwhencreated.hashCode());
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
        final AbstractProposalAttribute other = (AbstractProposalAttribute) obj;
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
        if (version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!version.equals(other.version)) {
            return false;
        }
        if (versionwhencreated == null) {
            if (other.versionwhencreated != null) {
                return false;
            }
        } else if (!versionwhencreated.equals(other.versionwhencreated)) {
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
        StringBuilder sb = new StringBuilder("ProposalAttribute (");

        sb.append(id_);
        sb.append(", ").append(proposalid);
        sb.append(", ").append(version);
        sb.append(", ").append(versionwhencreated);
        sb.append(", ").append(name);
        sb.append(", ").append(additionalid);
        sb.append(", ").append(numericvalue);
        sb.append(", ").append(stringvalue);
        sb.append(", ").append(realvalue);

        sb.append(")");
        return sb.toString();
    }
}
