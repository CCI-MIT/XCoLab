package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlanTemplateSectionPK implements Comparable<PlanTemplateSectionPK>,
    Serializable {
    public Long planTemplateId;
    public Long planSectionId;

    public PlanTemplateSectionPK() {
    }

    public PlanTemplateSectionPK(Long planTemplateId, Long planSectionId) {
        this.planTemplateId = planTemplateId;
        this.planSectionId = planSectionId;
    }

    public Long getPlanTemplateId() {
        return planTemplateId;
    }

    public void setPlanTemplateId(Long planTemplateId) {
        this.planTemplateId = planTemplateId;
    }

    public Long getPlanSectionId() {
        return planSectionId;
    }

    public void setPlanSectionId(Long planSectionId) {
        this.planSectionId = planSectionId;
    }

    public int compareTo(PlanTemplateSectionPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (planTemplateId < pk.planTemplateId) {
            value = -1;
        } else if (planTemplateId > pk.planTemplateId) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (planSectionId < pk.planSectionId) {
            value = -1;
        } else if (planSectionId > pk.planSectionId) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanTemplateSectionPK pk = null;

        try {
            pk = (PlanTemplateSectionPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((planTemplateId == pk.planTemplateId) &&
                (planSectionId == pk.planSectionId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(planTemplateId) + String.valueOf(planSectionId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("planTemplateId");
        sb.append(StringPool.EQUAL);
        sb.append(planTemplateId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("planSectionId");
        sb.append(StringPool.EQUAL);
        sb.append(planSectionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
