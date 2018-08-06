package org.xcolab.client.contest.pojo.ontology;

import java.io.Serializable;

abstract class AbstractFocusAreaOntologyTerm implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long focusareaid;
    private Long ontologytermid;
    private Integer sortOrder;

    public AbstractFocusAreaOntologyTerm() {}

    public AbstractFocusAreaOntologyTerm(AbstractFocusAreaOntologyTerm value) {
        this.focusareaid = value.focusareaid;
        this.ontologytermid = value.ontologytermid;
        this.sortOrder = value.sortOrder;
    }

    public AbstractFocusAreaOntologyTerm(Long focusareaid, Long ontologytermid, Integer sortOrder) {
        this.focusareaid = focusareaid;
        this.ontologytermid = ontologytermid;
        this.sortOrder = sortOrder;
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

    public Integer getSortOrder() {
        return this.sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((focusareaid == null) ? 0 : focusareaid.hashCode());
        result = prime * result + ((ontologytermid == null) ? 0 : ontologytermid.hashCode());
        result = prime * result + ((sortOrder == null) ? 0 : sortOrder.hashCode());
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
        if (sortOrder == null) {
            if (other.sortOrder != null) {
                return false;
            }
        } else if (!sortOrder.equals(other.sortOrder)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "FocusAreaOntologyTerm (" + focusareaid +
                ", " + ontologytermid +
                ", " + sortOrder +
                ")";
    }
}
