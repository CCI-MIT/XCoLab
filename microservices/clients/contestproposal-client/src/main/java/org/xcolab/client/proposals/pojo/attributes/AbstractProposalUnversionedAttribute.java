package org.xcolab.client.proposals.pojo.attributes;

import org.xcolab.util.attributes.AbstractAttribute;

import java.sql.Timestamp;

class AbstractProposalUnversionedAttribute extends AbstractAttribute {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private Long proposalid;
    private Long createauthorid;
    private Long lastauthorid;
    private Timestamp createdate;
    private Timestamp lastupdatedate;

    public AbstractProposalUnversionedAttribute() {}

    public AbstractProposalUnversionedAttribute(AbstractProposalUnversionedAttribute value) {
        super(value);
        this.id_ = value.id_;
        this.proposalid = value.proposalid;
        this.createauthorid = value.createauthorid;
        this.lastauthorid = value.lastauthorid;
        this.createdate = value.createdate;
        this.lastupdatedate = value.lastupdatedate;
    }

    public AbstractProposalUnversionedAttribute(Long id_, Long proposalid, Long createauthorid,
            Long lastauthorid, Timestamp createdate, Timestamp lastupdatedate, String name,
            Integer additionalId, Long numericvalue, String stringvalue, Double realvalue) {
        super(name, additionalId, numericvalue, stringvalue, realvalue);
        this.id_ = id_;
        this.proposalid = proposalid;
        this.createauthorid = createauthorid;
        this.lastauthorid = lastauthorid;
        this.createdate = createdate;
        this.lastupdatedate = lastupdatedate;
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

    public Long getCreateAuthorId() {
        return this.createauthorid;
    }

    public void setCreateAuthorId(Long createauthorid) {
        this.createauthorid = createauthorid;
    }

    public Long getLastAuthorId() {
        return this.lastauthorid;
    }

    public void setLastAuthorId(Long lastauthorid) {
        this.lastauthorid = lastauthorid;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Timestamp getLastUpdateDate() {
        return this.lastupdatedate;
    }

    public void setLastUpdateDate(Timestamp lastupdatedate) {
        this.lastupdatedate = lastupdatedate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((proposalid == null) ? 0 : proposalid.hashCode());
        result = prime * result + ((createauthorid == null) ? 0 : createauthorid.hashCode());
        result = prime * result + ((lastauthorid == null) ? 0 : lastauthorid.hashCode());
        result = prime * result + ((createdate == null) ? 0 : createdate.hashCode());
        result = prime * result + ((lastupdatedate == null) ? 0 : lastupdatedate.hashCode());
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
        final AbstractProposalUnversionedAttribute other =
                (AbstractProposalUnversionedAttribute) obj;
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
        if (createauthorid == null) {
            if (other.createauthorid != null) {
                return false;
            }
        } else if (!createauthorid.equals(other.createauthorid)) {
            return false;
        }
        if (lastauthorid == null) {
            if (other.lastauthorid != null) {
                return false;
            }
        } else if (!lastauthorid.equals(other.lastauthorid)) {
            return false;
        }
        if (createdate == null) {
            if (other.createdate != null) {
                return false;
            }
        } else if (!createdate.equals(other.createdate)) {
            return false;
        }
        if (lastupdatedate == null) {
            if (other.lastupdatedate != null) {
                return false;
            }
        } else if (!lastupdatedate.equals(other.lastupdatedate)) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString()).append("(");

        sb.append(id_);
        sb.append(", ").append(proposalid);
        sb.append(", ").append(createauthorid);
        sb.append(", ").append(lastauthorid);
        sb.append(", ").append(createdate);
        sb.append(", ").append(lastupdatedate);

        sb.append(")");
        return sb.toString();
    }
}
