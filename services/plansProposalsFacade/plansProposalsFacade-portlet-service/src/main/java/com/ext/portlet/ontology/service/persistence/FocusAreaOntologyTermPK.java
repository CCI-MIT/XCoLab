package com.ext.portlet.ontology.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class FocusAreaOntologyTermPK implements Comparable<FocusAreaOntologyTermPK>,
    Serializable {
    public Long focusAreaId;
    public Long ontologyTermId;

    public FocusAreaOntologyTermPK() {
    }

    public FocusAreaOntologyTermPK(Long focusAreaId, Long ontologyTermId) {
        this.focusAreaId = focusAreaId;
        this.ontologyTermId = ontologyTermId;
    }

    public Long getFocusAreaId() {
        return focusAreaId;
    }

    public void setFocusAreaId(Long focusAreaId) {
        this.focusAreaId = focusAreaId;
    }

    public Long getOntologyTermId() {
        return ontologyTermId;
    }

    public void setOntologyTermId(Long ontologyTermId) {
        this.ontologyTermId = ontologyTermId;
    }

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
        if (obj == null) {
            return false;
        }

        FocusAreaOntologyTermPK pk = null;

        try {
            pk = (FocusAreaOntologyTermPK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

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
