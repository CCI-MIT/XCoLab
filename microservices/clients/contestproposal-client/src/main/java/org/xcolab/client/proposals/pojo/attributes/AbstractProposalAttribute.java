package org.xcolab.client.proposals.pojo.attributes;

import org.xcolab.util.attributes.AbstractAttribute;

class AbstractProposalAttribute extends AbstractAttribute {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private Long proposalid;
    private Integer version;
    private Integer versionwhencreated;

    public AbstractProposalAttribute() {}

    public AbstractProposalAttribute(AbstractProposalAttribute value) {
        super(value);
        this.id_ = value.id_;
        this.proposalid = value.proposalid;
        this.version = value.version;
        this.versionwhencreated = value.versionwhencreated;
    }

    public AbstractProposalAttribute(Long id_, Long proposalid, Integer version,
            Integer versionwhencreated, String name, Long additionalid, Long numericvalue,
            String stringvalue, Double realvalue) {
        super(name, additionalid, null, numericvalue, stringvalue, realvalue);
        this.id_ = id_;
        this.proposalid = proposalid;
        this.version = version;
        this.versionwhencreated = versionwhencreated;
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

    @Override
    public String toString() {

        return super.toString()
                + "("
                    + id_ + ", "
                    + proposalid + ", "
                    + version + ", "
                    + versionwhencreated
                + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        result =
                prime * result + ((versionwhencreated == null) ? 0 : versionwhencreated.hashCode());
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
        if (getName() == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!getName().equals(other.getName())) {
            return false;
        }
        if (getAdditionalId() != other.getAdditionalId()) {
            return false;
        }
        if (getNumericValue() == null) {
            if (other.getNumericValue() != null) {
                return false;
            }
        } else if (!getNumericValue().equals(other.getNumericValue())) {
            return false;
        }
        if (getStringValue() == null) {
            if (other.getStringValue() != null) {
                return false;
            }
        } else if (!getStringValue().equals(other.getStringValue())) {
            return false;
        }
        if (getRealValue() == null) {
            if (other.getRealValue() != null) {
                return false;
            }
        } else if (!getRealValue().equals(other.getRealValue())) {
            return false;
        }
        return true;
    }
}
