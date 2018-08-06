package org.xcolab.client.proposals.pojo.attributes;

import org.xcolab.commons.attributes.AbstractAttribute;

import java.sql.Timestamp;
import java.util.Objects;

class AbstractProposalUnversionedAttribute extends AbstractAttribute {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long proposalid;
    private Long createauthorUserId;
    private Long lastauthorUserId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public AbstractProposalUnversionedAttribute() {}

    public AbstractProposalUnversionedAttribute(AbstractProposalUnversionedAttribute value) {
        super(value);
        this.id = value.id;
        this.proposalid = value.proposalid;
        this.createauthorUserId = value.createauthorUserId;
        this.lastauthorUserId = value.lastauthorUserId;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public AbstractProposalUnversionedAttribute(Long id, Long proposalid, Long createauthorUserId,
            Long lastauthorUserId, Timestamp createdAt, Timestamp updatedAt, String name,
            Integer additionalId, Long numericvalue, String stringvalue, Double realvalue) {
        super(name, additionalId, null, numericvalue, stringvalue, realvalue);
        this.id = id;
        this.proposalid = proposalid;
        this.createauthorUserId = createauthorUserId;
        this.lastauthorUserId = lastauthorUserId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProposalId() {
        return this.proposalid;
    }

    public void setProposalId(Long proposalid) {
        this.proposalid = proposalid;
    }

    public Long getCreateauthorUserId() {
        return this.createauthorUserId;
    }

    public void setCreateauthorUserId(Long createauthorUserId) {
        this.createauthorUserId = createauthorUserId;
    }

    public Long getLastauthorUserId() {
        return this.lastauthorUserId;
    }

    public void setLastauthorUserId(Long lastauthorUserId) {
        this.lastauthorUserId = lastauthorUserId;
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
        return Objects.equals(getId(), that.getId())
                && Objects.equals(proposalid, that.proposalid)
                && Objects.equals(createauthorUserId, that.createauthorUserId)
                && Objects.equals(lastauthorUserId, that.lastauthorUserId)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), proposalid, createauthorUserId, lastauthorUserId,
                createdAt, updatedAt);
    }

    @Override
    public String toString() {
        String sb = super.toString() + "(" + id + ", " + proposalid + ", " + createauthorUserId + ", "
                + lastauthorUserId + ", " + createdAt + ", " + updatedAt + ")";

        return sb;
    }
}
