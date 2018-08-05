package org.xcolab.client.proposals.pojo.attributes;

import org.xcolab.commons.attributes.AbstractAttribute;

import java.sql.Timestamp;
import java.util.Objects;

class AbstractProposalUnversionedAttribute extends AbstractAttribute {

    private static final long serialVersionUID = 1L;

    private Long id_;
    private Long proposalid;
    private Long createauthorid;
    private Long lastauthorid;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public AbstractProposalUnversionedAttribute() {}

    public AbstractProposalUnversionedAttribute(AbstractProposalUnversionedAttribute value) {
        super(value);
        this.id_ = value.id_;
        this.proposalid = value.proposalid;
        this.createauthorid = value.createauthorid;
        this.lastauthorid = value.lastauthorid;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public AbstractProposalUnversionedAttribute(Long id_, Long proposalid, Long createauthorid,
            Long lastauthorid, Timestamp createdAt, Timestamp updatedAt, String name,
            Integer additionalId, Long numericvalue, String stringvalue, Double realvalue) {
        super(name, additionalId, null, numericvalue, stringvalue, realvalue);
        this.id_ = id_;
        this.proposalid = proposalid;
        this.createauthorid = createauthorid;
        this.lastauthorid = lastauthorid;
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
                && Objects.equals(createauthorid, that.createauthorid)
                && Objects.equals(lastauthorid, that.lastauthorid)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId_(), proposalid, createauthorid, lastauthorid,
                createdAt, updatedAt);
    }

    @Override
    public String toString() {
        String sb = super.toString() + "(" + id_ + ", " + proposalid + ", " + createauthorid + ", "
                + lastauthorid + ", " + createdAt + ", " + updatedAt + ")";

        return sb;
    }
}
