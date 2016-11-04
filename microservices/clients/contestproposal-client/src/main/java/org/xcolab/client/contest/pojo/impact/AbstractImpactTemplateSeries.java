package org.xcolab.client.contest.pojo.impact;

abstract class AbstractImpactTemplateSeries {

    private Long seriesid;
    private Long iterationid;
    private String name;

    public AbstractImpactTemplateSeries() {}

    public AbstractImpactTemplateSeries(AbstractImpactTemplateSeries value) {
        this.seriesid = value.seriesid;
        this.iterationid = value.iterationid;
        this.name = value.name;
    }

    public AbstractImpactTemplateSeries(Long seriesid, Long iterationid, String name) {
        this.seriesid = seriesid;
        this.iterationid = iterationid;
        this.name = name;
    }

    public Long getSeriesId() {
        return this.seriesid;
    }

    public void setSeriesId(Long seriesid) {
        this.seriesid = seriesid;
    }

    public Long getIterationId() {
        return this.iterationid;
    }

    public void setIterationId(Long iterationid) {
        this.iterationid = iterationid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((seriesid == null) ? 0 : seriesid.hashCode());
        result = prime * result + ((iterationid == null) ? 0 : iterationid.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        final AbstractImpactTemplateSeries other = (AbstractImpactTemplateSeries) obj;
        if (seriesid == null) {
            if (other.seriesid != null) {
                return false;
            }
        } else if (!seriesid.equals(other.seriesid)) {
            return false;
        }
        if (iterationid == null) {
            if (other.iterationid != null) {
                return false;
            }
        } else if (!iterationid.equals(other.iterationid)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ImpactTemplateSeries (" + seriesid +
                ", " + iterationid +
                ", " + name +
                ")";
    }
}
