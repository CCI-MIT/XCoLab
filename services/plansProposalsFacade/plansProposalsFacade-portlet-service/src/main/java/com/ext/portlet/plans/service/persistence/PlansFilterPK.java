package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlansFilterPK implements Comparable<PlansFilterPK>, Serializable {
    public long userId;
    public long planTypeId;

    public PlansFilterPK() {
    }

    public PlansFilterPK(long userId, long planTypeId) {
        this.userId = userId;
        this.planTypeId = planTypeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPlanTypeId() {
        return planTypeId;
    }

    public void setPlanTypeId(long planTypeId) {
        this.planTypeId = planTypeId;
    }

    public int compareTo(PlansFilterPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (userId < pk.userId) {
            value = -1;
        } else if (userId > pk.userId) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (planTypeId < pk.planTypeId) {
            value = -1;
        } else if (planTypeId > pk.planTypeId) {
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

        PlansFilterPK pk = null;

        try {
            pk = (PlansFilterPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((userId == pk.userId) && (planTypeId == pk.planTypeId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(userId) + String.valueOf(planTypeId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("planTypeId");
        sb.append(StringPool.EQUAL);
        sb.append(planTypeId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
