package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PlanTemplateSectionPK implements Comparable<PlanTemplateSectionPK>,
    Serializable {
    public long planTemplateId;
    public long planSectionId;

    public PlanTemplateSectionPK() {
    }

    public PlanTemplateSectionPK(long planTemplateId, long planSectionId) {
        this.planTemplateId = planTemplateId;
        this.planSectionId = planSectionId;
    }

    public long getPlanTemplateId() {
        return planTemplateId;
    }

    public void setPlanTemplateId(long planTemplateId) {
        this.planTemplateId = planTemplateId;
    }

    public long getPlanSectionId() {
        return planSectionId;
    }

    public void setPlanSectionId(long planSectionId) {
        this.planSectionId = planSectionId;
    }

    @Override
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
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanTemplateSectionPK)) {
            return false;
        }

        PlanTemplateSectionPK pk = (PlanTemplateSectionPK) obj;

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
