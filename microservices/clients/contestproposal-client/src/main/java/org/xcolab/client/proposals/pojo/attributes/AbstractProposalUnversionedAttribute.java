package org.xcolab.client.proposals.pojo.attributes;

import org.xcolab.commons.attributes.AbstractAttribute;

import java.sql.Timestamp;
import java.util.Objects;

class AbstractProposalUnversionedAttribute extends AbstractAttribute {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private Long proposalid;
    private Long createauthorUserid;
    private Long lastauthorUserid;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public AbstractProposalUnversionedAttribute() {}

    public AbstractProposalUnversionedAttribute(AbstractProposalUnversionedAttribute value) {
        super(value);
        this.id_ = value.id_;
        this.proposalid = value.proposalid;
        this.createauthorUserid = value.createauthorUserid;
        this.lastauthorUserid = value.lastauthorUserid;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public AbstractProposalUnversionedAttribute(Long id_, Long proposalid, Long createauthorUserid,
            Long lastauthorUserid, Timestamp createdAt, Timestamp updatedAt, String name,
            Integer additionalId, Long numericvalue, String stringvalue, Double realvalue) {
        super(name, additionalId, null, numericvalue, stringvalue, realvalue);
        this.id_ = id_;
        this.proposalid = proposalid;
        this.createauthorUserid = createauthorUserid;
        this.lastauthorUserid = lastauthorUserid;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public Long getCreateauthorUserid() {
        return this.createauthorUserid;
    }

    public void setCreateauthorUserid(Long createauthorUserid) {
        this.createauthorUserid = createauthorUserid;
    }

    public Long getLastauthorUserid() {
        return this.lastauthorUserid;
    }

    public void setLastauthorUserid(Long lastauthorUserid) {
        this.lastauthorUserid = lastauthorUserid;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getupdatedAt() {
        return this.updatedAt;
    }

    public void setupdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractProposalUnversionedAttribute)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AbstractProposalUnversionedAttribute that = (AbstractProposalUnversionedAttribute) o;
        return Objects.equals(getId_(), that.getId_())
                && Objects.equals(proposalid, that.proposalid)
                && Objects.equals(createauthorUserid, that.createauthorUserid)
                && Objects.equals(lastauthorUserid, that.lastauthorUserid)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId_(), proposalid, createauthorUserid, lastauthorUserid,
                createdAt, updatedAt);
    }

    @Override
    public String toString() {
        String sb = super.toString() + "(" + id_ + ", " + proposalid + ", " + createauthorUserid + ", "
                + lastauthorUserid + ", " + createdAt + ", " + updatedAt + ")";

        return sb;
    }
}
