package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ImpactIterationPK implements Comparable<ImpactIterationPK>,
    Serializable {
    public long iterationId;
    public int year;

    public ImpactIterationPK() {
    }

    public ImpactIterationPK(long iterationId, int year) {
        this.iterationId = iterationId;
        this.year = year;
    }

    public long getIterationId() {
        return iterationId;
    }

    public void setIterationId(long iterationId) {
        this.iterationId = iterationId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(ImpactIterationPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (iterationId < pk.iterationId) {
            value = -1;
        } else if (iterationId > pk.iterationId) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (year < pk.year) {
            value = -1;
        } else if (year > pk.year) {
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

        if (!(obj instanceof ImpactIterationPK)) {
            return false;
        }

        ImpactIterationPK pk = (ImpactIterationPK) obj;

        if ((iterationId == pk.iterationId) && (year == pk.year)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(iterationId) + String.valueOf(year)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("iterationId");
        sb.append(StringPool.EQUAL);
        sb.append(iterationId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("year");
        sb.append(StringPool.EQUAL);
        sb.append(year);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
