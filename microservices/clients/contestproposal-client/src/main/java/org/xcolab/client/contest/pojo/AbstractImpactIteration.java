package org.xcolab.client.contest.pojo;

import java.io.Serializable;

abstract class AbstractImpactIteration implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long iterationid;
    private Integer year;

    public AbstractImpactIteration() {}

    public AbstractImpactIteration(AbstractImpactIteration value) {
        this.iterationid = value.iterationid;
        this.year = value.year;
    }

    public AbstractImpactIteration(Long iterationid, Integer year) {
        this.iterationid = iterationid;
        this.year = year;
    }

    public Long getIterationId() {
        return this.iterationid;
    }

    public void setIterationId(Long iterationid) {
        this.iterationid = iterationid;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((iterationid == null) ? 0 : iterationid.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
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
        final AbstractImpactIteration other = (AbstractImpactIteration) obj;
        if (iterationid == null) {
            if (other.iterationid != null) {
                return false;
            }
        } else if (!iterationid.equals(other.iterationid)) {
            return false;
        }
        if (year == null) {
            if (other.year != null) {
                return false;
            }
        } else if (!year.equals(other.year)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ImpactIteration (" + iterationid +
                ", " + year +
                ")";
    }
}
