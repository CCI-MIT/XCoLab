package org.xcolab.client.contest.pojo.ontology;

abstract class AbstractFocusAreaOntologyTerm {

    private Long focusareaid;
    private Long ontologytermid;
    private Integer order_;

    public AbstractFocusAreaOntologyTerm() {}

    public AbstractFocusAreaOntologyTerm(AbstractFocusAreaOntologyTerm value) {
        this.focusareaid = value.focusareaid;
        this.ontologytermid = value.ontologytermid;
        this.order_ = value.order_;
    }

    public AbstractFocusAreaOntologyTerm(Long focusareaid, Long ontologytermid, Integer order_) {
        this.focusareaid = focusareaid;
        this.ontologytermid = ontologytermid;
        this.order_ = order_;
    }

    public Long getFocusAreaId() {
        return this.focusareaid;
    }

    public void setFocusAreaId(Long focusareaid) {
        this.focusareaid = focusareaid;
    }

    public Long getOntologyTermId() {
        return this.ontologytermid;
    }

    public void setOntologyTermId(Long ontologytermid) {
        this.ontologytermid = ontologytermid;
    }

    public Integer getOrder_() {
        return this.order_;
    }

    public void setOrder_(Integer order_) {
        this.order_ = order_;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((focusareaid == null) ? 0 : focusareaid.hashCode());
        result = prime * result + ((ontologytermid == null) ? 0 : ontologytermid.hashCode());
        result = prime * result + ((order_ == null) ? 0 : order_.hashCode());
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
        final AbstractFocusAreaOntologyTerm other = (AbstractFocusAreaOntologyTerm) obj;
        if (focusareaid == null) {
            if (other.focusareaid != null) {
                return false;
            }
        } else if (!focusareaid.equals(other.focusareaid)) {
            return false;
        }
        if (ontologytermid == null) {
            if (other.ontologytermid != null) {
                return false;
            }
        } else if (!ontologytermid.equals(other.ontologytermid)) {
            return false;
        }
        if (order_ == null) {
            if (other.order_ != null) {
                return false;
            }
        } else if (!order_.equals(other.order_)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "FocusAreaOntologyTerm (" + focusareaid +
                ", " + ontologytermid +
                ", " + order_ +
                ")";
    }
}
