package org.xcolab.client.contest.pojo.impact;

import java.io.Serializable;

abstract class AbstractImpactDefaultSeriesData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long seriesid;
    private Integer year;
    private Double value;

    public AbstractImpactDefaultSeriesData() {}

    public AbstractImpactDefaultSeriesData(AbstractImpactDefaultSeriesData value) {
        this.seriesid = value.seriesid;
        this.year = value.year;
        this.value = value.value;
    }

    public AbstractImpactDefaultSeriesData(Long seriesid, Integer year, Double value) {
        this.seriesid = seriesid;
        this.year = year;
        this.value = value;
    }

    public Long getSeriesId() {
        return this.seriesid;
    }

    public void setSeriesId(Long seriesid) {
        this.seriesid = seriesid;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((seriesid == null) ? 0 : seriesid.hashCode());
        result = prime * result + ((year == null) ? 0 : year.hashCode());
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        final AbstractImpactDefaultSeriesData other = (AbstractImpactDefaultSeriesData) obj;
        if (seriesid == null) {
            if (other.seriesid != null) {
                return false;
            }
        } else if (!seriesid.equals(other.seriesid)) {
            return false;
        }
        if (year == null) {
            if (other.year != null) {
                return false;
            }
        } else if (!year.equals(other.year)) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ImpactDefaultSeriesData (" + seriesid +
                ", " + year +
                ", " + value +
                ")";
    }
}
