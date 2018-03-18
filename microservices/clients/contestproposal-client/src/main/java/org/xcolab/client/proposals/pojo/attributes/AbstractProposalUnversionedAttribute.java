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
        super(name, additionalId, null, numericvalue, stringvalue, realvalue);
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
                && Objects.equals(createdate, that.createdate)
                && Objects.equals(lastupdatedate, that.lastupdatedate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getId_(), proposalid, createauthorid, lastauthorid,
                createdate, lastupdatedate);
    }

    @Override
    public String toString() {
        String sb = super.toString() + "(" + id_ + ", " + proposalid + ", " + createauthorid + ", "
                + lastauthorid + ", " + createdate + ", " + lastupdatedate + ")";

        return sb;
    }
}
