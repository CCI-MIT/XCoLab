package org.xcolab.client.contest.pojo.wrapper;

import org.xcolab.commons.attributes.AbstractAttribute;

import java.sql.Timestamp;
import java.util.Objects;

class AbstractProposalUnversionedAttribute extends AbstractAttribute {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long proposalId;
    private Long firstAuthorUserId;
    private Long lastAuthorUserId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public AbstractProposalUnversionedAttribute() {}

    public AbstractProposalUnversionedAttribute(AbstractProposalUnversionedAttribute value) {
        super(value);
        this.id = value.id;
        this.proposalId = value.proposalId;
        this.firstAuthorUserId = value.firstAuthorUserId;
        this.lastAuthorUserId = value.lastAuthorUserId;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public AbstractProposalUnversionedAttribute(Long id, Long proposalId, Long firstAuthorUserId,
            Long lastAuthorUserId, Timestamp createdAt, Timestamp updatedAt, String name,
            Integer additionalId, Long numericvalue, String stringvalue, Double realvalue) {
        super(name, additionalId, null, numericvalue, stringvalue, realvalue);
        this.id = id;
        this.proposalId = proposalId;
        this.firstAuthorUserId = firstAuthorUserId;
        this.lastAuthorUserId = lastAuthorUserId;
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
        return this.proposalId;
    }

    public void setProposalId(Long proposalid) {
        this.proposalId = proposalid;
    }

    public Long getFirstAuthorUserId() {
        return this.firstAuthorUserId;
    }

    public void setFirstAuthorUserId(Long firstAuthorUserId) {
        this.firstAuthorUserId = firstAuthorUserId;
    }

    public Long getLastAuthorUserId() {
        return this.lastAuthorUserId;
    }

    public void setLastAuthorUserId(Long lastAuthorUserId) {
        this.lastAuthorUserId = lastAuthorUserId;
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
                && Objects.equals(proposalId, that.proposalId)
                && Objects.equals(firstAuthorUserId, that.firstAuthorUserId)
                && Objects.equals(lastAuthorUserId, that.lastAuthorUserId)
                && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), proposalId, firstAuthorUserId,
                lastAuthorUserId,
                createdAt, updatedAt);
    }

    @Override
    public String toString() {
        String sb = super.toString() + "(" + id + ", " + proposalId + ", " + firstAuthorUserId + ", "
                + lastAuthorUserId + ", " + createdAt + ", " + updatedAt + ")";

        return sb;
    }
}
