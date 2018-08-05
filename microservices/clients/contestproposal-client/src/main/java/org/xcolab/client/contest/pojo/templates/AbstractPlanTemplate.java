package org.xcolab.client.contest.pojo.templates;

import java.io.Serializable;

abstract class AbstractPlanTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private Long basetemplateid;
    private Long impactseriestemplateid;
    private Long focusarealisttemplateid;

    public AbstractPlanTemplate() {}

    public AbstractPlanTemplate(AbstractPlanTemplate value) {
        this.id = value.id;
        this.name = value.name;
        this.basetemplateid = value.basetemplateid;
        this.impactseriestemplateid = value.impactseriestemplateid;
        this.focusarealisttemplateid = value.focusarealisttemplateid;
    }

    public AbstractPlanTemplate(Long id, String name, Long basetemplateid,
            Long impactseriestemplateid, Long focusarealisttemplateid) {
        this.id = id;
        this.name = name;
        this.basetemplateid = basetemplateid;
        this.impactseriestemplateid = impactseriestemplateid;
        this.focusarealisttemplateid = focusarealisttemplateid;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBaseTemplateId() {
        return this.basetemplateid;
    }

    public void setBaseTemplateId(Long basetemplateid) {
        this.basetemplateid = basetemplateid;
    }

    public Long getImpactSeriesTemplateId() {
        return this.impactseriestemplateid;
    }

    public void setImpactSeriesTemplateId(Long impactseriestemplateid) {
        this.impactseriestemplateid = impactseriestemplateid;
    }

    public Long getFocusAreaListTemplateId() {
        return this.focusarealisttemplateid;
    }

    public void setFocusAreaListTemplateId(Long focusarealisttemplateid) {
        this.focusarealisttemplateid = focusarealisttemplateid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((basetemplateid == null) ? 0 : basetemplateid.hashCode());
        result = prime * result + ((impactseriestemplateid == null) ? 0
                : impactseriestemplateid.hashCode());
        result = prime * result + ((focusarealisttemplateid == null) ? 0
                : focusarealisttemplateid.hashCode());
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
        final AbstractPlanTemplate other = (AbstractPlanTemplate) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (basetemplateid == null) {
            if (other.basetemplateid != null) {
                return false;
            }
        } else if (!basetemplateid.equals(other.basetemplateid)) {
            return false;
        }
        if (impactseriestemplateid == null) {
            if (other.impactseriestemplateid != null) {
                return false;
            }
        } else if (!impactseriestemplateid.equals(other.impactseriestemplateid)) {
            return false;
        }
        if (focusarealisttemplateid == null) {
            if (other.focusarealisttemplateid != null) {
                return false;
            }
        } else if (!focusarealisttemplateid.equals(other.focusarealisttemplateid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "PlanTemplate (" + id +
                ", " + name +
                ", " + basetemplateid +
                ", " + impactseriestemplateid +
                ", " + focusarealisttemplateid +
                ")";
    }
}
