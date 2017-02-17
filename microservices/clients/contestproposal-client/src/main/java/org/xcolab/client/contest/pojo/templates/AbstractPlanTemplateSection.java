package org.xcolab.client.contest.pojo.templates;

import java.io.Serializable;

abstract class AbstractPlanTemplateSection implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long plantemplateid;
    private Long plansectionid;
    private Integer weight;

    public AbstractPlanTemplateSection() {}

    public AbstractPlanTemplateSection(AbstractPlanTemplateSection value) {
        this.plantemplateid = value.plantemplateid;
        this.plansectionid = value.plansectionid;
        this.weight = value.weight;
    }

    public AbstractPlanTemplateSection(Long plantemplateid, Long plansectionid, Integer weight) {
        this.plantemplateid = plantemplateid;
        this.plansectionid = plansectionid;
        this.weight = weight;
    }

    public Long getPlanTemplateId() {
        return this.plantemplateid;
    }

    public void setPlanTemplateId(Long plantemplateid) {
        this.plantemplateid = plantemplateid;
    }

    public Long getPlanSectionId() {
        return this.plansectionid;
    }

    public void setPlanSectionId(Long plansectionid) {
        this.plansectionid = plansectionid;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((plantemplateid == null) ? 0 : plantemplateid.hashCode());
        result = prime * result + ((plansectionid == null) ? 0 : plansectionid.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
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
        final AbstractPlanTemplateSection other = (AbstractPlanTemplateSection) obj;
        if (plantemplateid == null) {
            if (other.plantemplateid != null) {
                return false;
            }
        } else if (!plantemplateid.equals(other.plantemplateid)) {
            return false;
        }
        if (plansectionid == null) {
            if (other.plansectionid != null) {
                return false;
            }
        } else if (!plansectionid.equals(other.plansectionid)) {
            return false;
        }
        if (weight == null) {
            if (other.weight != null) {
                return false;
            }
        } else if (!weight.equals(other.weight)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "PlanTemplateSection (" + plantemplateid +
                ", " + plansectionid +
                ", " + weight +
                ")";
    }
}
