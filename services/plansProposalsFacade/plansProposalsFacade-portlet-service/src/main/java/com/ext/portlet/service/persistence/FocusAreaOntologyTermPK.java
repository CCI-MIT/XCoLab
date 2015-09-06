package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FocusAreaOntologyTermPK implements Comparable<FocusAreaOntologyTermPK>,
    Serializable {
    public long focusAreaId;
    public long ontologyTermId;

    public FocusAreaOntologyTermPK() {
    }

    public FocusAreaOntologyTermPK(long focusAreaId, long ontologyTermId) {
        this.focusAreaId = focusAreaId;
        this.ontologyTermId = ontologyTermId;
    }

    public long getFocusAreaId() {
        return focusAreaId;
    }

    public void setFocusAreaId(long focusAreaId) {
        this.focusAreaId = focusAreaId;
    }

    public long getOntologyTermId() {
        return ontologyTermId;
    }

    public void setOntologyTermId(long ontologyTermId) {
        this.ontologyTermId = ontologyTermId;
    }

    @Override
    public int compareTo(FocusAreaOntologyTermPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

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

        if (ontologyTermId < pk.ontologyTermId) {
            value = -1;
        } else if (ontologyTermId > pk.ontologyTermId) {
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

        if (!(obj instanceof FocusAreaOntologyTermPK)) {
            return false;
        }

        FocusAreaOntologyTermPK pk = (FocusAreaOntologyTermPK) obj;

        if ((focusAreaId == pk.focusAreaId) &&
                (ontologyTermId == pk.ontologyTermId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(focusAreaId) + String.valueOf(ontologyTermId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("focusAreaId");
        sb.append(StringPool.EQUAL);
        sb.append(focusAreaId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("ontologyTermId");
        sb.append(StringPool.EQUAL);
        sb.append(ontologyTermId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
