package org.xcolab.client.contest.pojo;

import java.io.Serializable;

abstract class AbstractImpactTemplateMaxFocusArea implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long focusarealistid;
    private Long focusareaid;

    public AbstractImpactTemplateMaxFocusArea() {}

    public AbstractImpactTemplateMaxFocusArea(AbstractImpactTemplateMaxFocusArea value) {
        this.focusarealistid = value.focusarealistid;
        this.focusareaid = value.focusareaid;
    }

    public AbstractImpactTemplateMaxFocusArea(Long focusarealistid, Long focusareaid) {
        this.focusarealistid = focusarealistid;
        this.focusareaid = focusareaid;
    }

    public Long getFocusAreaListId() {
        return this.focusarealistid;
    }

    public void setFocusAreaListId(Long focusarealistid) {
        this.focusarealistid = focusarealistid;
    }

    public Long getFocusAreaId() {
        return this.focusareaid;
    }

    public void setFocusAreaId(Long focusareaid) {
        this.focusareaid = focusareaid;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((focusarealistid == null) ? 0 : focusarealistid.hashCode());
        result = prime * result + ((focusareaid == null) ? 0 : focusareaid.hashCode());
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
        final AbstractImpactTemplateMaxFocusArea other = (AbstractImpactTemplateMaxFocusArea) obj;
        if (focusarealistid == null) {
            if (other.focusarealistid != null) {
                return false;
            }
        } else if (!focusarealistid.equals(other.focusarealistid)) {
            return false;
        }
        if (focusareaid == null) {
            if (other.focusareaid != null) {
                return false;
            }
        } else if (!focusareaid.equals(other.focusareaid)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ImpactTemplateMaxFocusArea (" + focusarealistid +
                ", " + focusareaid +
                ")";
    }
}
