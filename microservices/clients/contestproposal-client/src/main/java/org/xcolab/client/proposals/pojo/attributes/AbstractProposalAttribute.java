package org.xcolab.client.proposals.pojo.attributes;

import org.xcolab.commons.attributes.AbstractAttribute;

import java.util.Objects;

class AbstractProposalAttribute extends AbstractAttribute {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private Long proposalid;
    private Integer version;

    public AbstractProposalAttribute() {}

    public AbstractProposalAttribute(AbstractProposalAttribute value) {
        super(value);
        this.id_ = value.id_;
        this.proposalid = value.proposalid;
        this.version = value.version;
    }

    public AbstractProposalAttribute(Long id_, Long proposalid, Integer version, String name,
            Long additionalid, Long numericvalue,
            String stringvalue, Double realvalue) {
        super(name, additionalid, null, numericvalue, stringvalue, realvalue);
        this.id_ = id_;
        this.proposalid = proposalid;
        this.version = version;
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

    @Override
    public String toString() {

        return super.toString()
                + "("
                    + id_ + ", "
                    + proposalid + ", "
                    + version
                + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractProposalAttribute)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AbstractProposalAttribute that = (AbstractProposalAttribute) o;
        return Objects.equals(getId_(), that.getId_())
                && Objects.equals(proposalid, that.proposalid)
                && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(super.hashCode(), getId_(), proposalid, getVersion());
    }
}
