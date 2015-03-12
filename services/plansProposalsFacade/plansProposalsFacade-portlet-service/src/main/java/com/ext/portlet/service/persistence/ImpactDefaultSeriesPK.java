package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ImpactDefaultSeriesPK implements Comparable<ImpactDefaultSeriesPK>,
    Serializable {
    public long seriesId;
    public String name;

    public ImpactDefaultSeriesPK() {
    }

    public ImpactDefaultSeriesPK(long seriesId, String name) {
        this.seriesId = seriesId;
        this.name = name;
    }

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(ImpactDefaultSeriesPK pk) {
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

        value = name.compareTo(pk.name);

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

        if (!(obj instanceof ImpactDefaultSeriesPK)) {
            return false;
        }

        ImpactDefaultSeriesPK pk = (ImpactDefaultSeriesPK) obj;

        if ((seriesId == pk.seriesId) && (name.equals(pk.name))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(seriesId) + String.valueOf(name)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("seriesId");
        sb.append(StringPool.EQUAL);
        sb.append(seriesId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("name");
        sb.append(StringPool.EQUAL);
        sb.append(name);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
