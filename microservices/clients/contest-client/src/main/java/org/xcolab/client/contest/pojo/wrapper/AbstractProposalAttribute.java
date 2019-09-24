package org.xcolab.client.contest.pojo.wrapper;

import org.xcolab.commons.attributes.AbstractAttribute;

import java.util.Objects;

class AbstractProposalAttribute extends AbstractAttribute {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long proposalid;
    private Integer version;

    public AbstractProposalAttribute() {}

    public AbstractProposalAttribute(AbstractProposalAttribute value) {
        super(value);
        this.id = value.id;
        this.proposalid = value.proposalid;
        this.version = value.version;
    }

    public AbstractProposalAttribute(Long id, Long proposalid, Integer version, String name,
            Long additionalid, Long numericvalue,
            String stringvalue, Double realvalue) {
        super(name, additionalid, null, numericvalue, stringvalue, realvalue);
        this.id = id;
        this.proposalid = proposalid;
        this.version = version;
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
                    + id + ", "
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
        return Objects.equals(getId(), that.getId())
                && Objects.equals(proposalid, that.proposalid)
                && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(super.hashCode(), getId(), proposalid, getVersion());
    }
}
