package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class AnalyticsUserEventPK implements Comparable<AnalyticsUserEventPK>,
    Serializable {
    public long userId;
    public String idString;

    public AnalyticsUserEventPK() {
    }

    public AnalyticsUserEventPK(long userId, String idString) {
        this.userId = userId;
        this.idString = idString;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getIdString() {
        return idString;
    }

    public void setIdString(String idString) {
        this.idString = idString;
    }

    public int compareTo(AnalyticsUserEventPK pk) {
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

        value = idString.compareTo(pk.idString);

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

        AnalyticsUserEventPK pk = null;

        try {
            pk = (AnalyticsUserEventPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((userId == pk.userId) && (idString.equals(pk.idString))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(userId) + String.valueOf(idString)).hashCode();
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
        sb.append("idString");
        sb.append(StringPool.EQUAL);
        sb.append(idString);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
