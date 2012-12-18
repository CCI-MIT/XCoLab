package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlanSectionPlanMapPK implements Comparable<PlanSectionPlanMapPK>,
    Serializable {
    public long sectionId;
    public long relatedPlanId;

    public PlanSectionPlanMapPK() {
    }

    public PlanSectionPlanMapPK(long sectionId, long relatedPlanId) {
        this.sectionId = sectionId;
        this.relatedPlanId = relatedPlanId;
    }

    public long getSectionId() {
        return sectionId;
    }

    public void setSectionId(long sectionId) {
        this.sectionId = sectionId;
    }

    public long getRelatedPlanId() {
        return relatedPlanId;
    }

    public void setRelatedPlanId(long relatedPlanId) {
        this.relatedPlanId = relatedPlanId;
    }

    public int compareTo(PlanSectionPlanMapPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (sectionId < pk.sectionId) {
            value = -1;
        } else if (sectionId > pk.sectionId) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (relatedPlanId < pk.relatedPlanId) {
            value = -1;
        } else if (relatedPlanId > pk.relatedPlanId) {
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

        PlanSectionPlanMapPK pk = null;

        try {
            pk = (PlanSectionPlanMapPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((sectionId == pk.sectionId) && (relatedPlanId == pk.relatedPlanId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(sectionId) + String.valueOf(relatedPlanId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("sectionId");
        sb.append(StringPool.EQUAL);
        sb.append(sectionId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("relatedPlanId");
        sb.append(StringPool.EQUAL);
        sb.append(relatedPlanId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
