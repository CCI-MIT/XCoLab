package com.ext.portlet.plans.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class PlanVotePK implements Comparable<PlanVotePK>, Serializable {
    public long userId;
    public long contestId;

    public PlanVotePK() {
    }

    public PlanVotePK(long userId, long contestId) {
        this.userId = userId;
        this.contestId = contestId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getContestId() {
        return contestId;
    }

    public void setContestId(long contestId) {
        this.contestId = contestId;
    }

    public int compareTo(PlanVotePK pk) {
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

        if (contestId < pk.contestId) {
            value = -1;
        } else if (contestId > pk.contestId) {
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

        PlanVotePK pk = null;

        try {
            pk = (PlanVotePK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((userId == pk.userId) && (contestId == pk.contestId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(userId) + String.valueOf(contestId)).hashCode();
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
        sb.append("contestId");
        sb.append(StringPool.EQUAL);
        sb.append(contestId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
