package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlanPositionPK implements Comparable<PlanPositionPK>, Serializable {
    public long planId;
    public long positionId;

    public PlanPositionPK() {
    }

    public PlanPositionPK(long planId, long positionId) {
        this.planId = planId;
        this.positionId = positionId;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }

    public int compareTo(PlanPositionPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (planId < pk.planId) {
            value = -1;
        } else if (planId > pk.planId) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (positionId < pk.positionId) {
            value = -1;
        } else if (positionId > pk.positionId) {
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

        PlanPositionPK pk = null;

        try {
            pk = (PlanPositionPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((planId == pk.planId) && (positionId == pk.positionId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(planId) + String.valueOf(positionId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("planId");
        sb.append(StringPool.EQUAL);
        sb.append(planId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("positionId");
        sb.append(StringPool.EQUAL);
        sb.append(positionId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
