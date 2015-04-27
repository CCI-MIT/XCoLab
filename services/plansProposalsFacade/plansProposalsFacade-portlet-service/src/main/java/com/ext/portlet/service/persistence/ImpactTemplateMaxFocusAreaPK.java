package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ImpactTemplateMaxFocusAreaPK implements Comparable<ImpactTemplateMaxFocusAreaPK>,
    Serializable {
    public long focusAreaListId;
    public long focusAreaId;

    public ImpactTemplateMaxFocusAreaPK() {
    }

    public ImpactTemplateMaxFocusAreaPK(long focusAreaListId, long focusAreaId) {
        this.focusAreaListId = focusAreaListId;
        this.focusAreaId = focusAreaId;
    }

    public long getFocusAreaListId() {
        return focusAreaListId;
    }

    public void setFocusAreaListId(long focusAreaListId) {
        this.focusAreaListId = focusAreaListId;
    }

    public long getFocusAreaId() {
        return focusAreaId;
    }

    public void setFocusAreaId(long focusAreaId) {
        this.focusAreaId = focusAreaId;
    }

    @Override
    public int compareTo(ImpactTemplateMaxFocusAreaPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (focusAreaListId < pk.focusAreaListId) {
            value = -1;
        } else if (focusAreaListId > pk.focusAreaListId) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        if (focusAreaId < pk.focusAreaId) {
            value = -1;
        } else if (focusAreaId > pk.focusAreaId) {
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

        if (!(obj instanceof ImpactTemplateMaxFocusAreaPK)) {
            return false;
        }

        ImpactTemplateMaxFocusAreaPK pk = (ImpactTemplateMaxFocusAreaPK) obj;

        if ((focusAreaListId == pk.focusAreaListId) &&
                (focusAreaId == pk.focusAreaId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(focusAreaListId) + String.valueOf(focusAreaId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("focusAreaListId");
        sb.append(StringPool.EQUAL);
        sb.append(focusAreaListId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("focusAreaId");
        sb.append(StringPool.EQUAL);
        sb.append(focusAreaId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
