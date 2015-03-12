package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ImpactDefaultSeriesDataPK implements Comparable<ImpactDefaultSeriesDataPK>,
    Serializable {
    public long seriesId;
    public int year;
    public double value;

    public ImpactDefaultSeriesDataPK() {
    }

    public ImpactDefaultSeriesDataPK(long seriesId, int year, double value) {
        this.seriesId = seriesId;
        this.year = year;
        this.value = value;
    }

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public int compareTo(ImpactDefaultSeriesDataPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (seriesId < pk.seriesId) {
            value = -1;
        } else if (seriesId > pk.seriesId) {
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

        if (value < pk.value) {
            value = -1;
        } else if (value > pk.value) {
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

        if (!(obj instanceof ImpactDefaultSeriesDataPK)) {
            return false;
        }

        ImpactDefaultSeriesDataPK pk = (ImpactDefaultSeriesDataPK) obj;

        if ((seriesId == pk.seriesId) && (year == pk.year) &&
                (value == pk.value)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(seriesId) + String.valueOf(year) +
        String.valueOf(value)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("seriesId");
        sb.append(StringPool.EQUAL);
        sb.append(seriesId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("year");
        sb.append(StringPool.EQUAL);
        sb.append(year);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("value");
        sb.append(StringPool.EQUAL);
        sb.append(value);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
