package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ProposalRatingValuePK implements Comparable<ProposalRatingValuePK>,
    Serializable {
    public long ratingTypeId;
    public long value;

    public ProposalRatingValuePK() {
    }

    public ProposalRatingValuePK(long ratingTypeId, long value) {
        this.ratingTypeId = ratingTypeId;
        this.value = value;
    }

    public long getRatingTypeId() {
        return ratingTypeId;
    }

    public void setRatingTypeId(long ratingTypeId) {
        this.ratingTypeId = ratingTypeId;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public int compareTo(ProposalRatingValuePK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (ratingTypeId < pk.ratingTypeId) {
            value = -1;
        } else if (ratingTypeId > pk.ratingTypeId) {
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

        if (!(obj instanceof ProposalRatingValuePK)) {
            return false;
        }

        ProposalRatingValuePK pk = (ProposalRatingValuePK) obj;

        if ((ratingTypeId == pk.ratingTypeId) && (value == pk.value)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(ratingTypeId) + String.valueOf(value)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("ratingTypeId");
        sb.append(StringPool.EQUAL);
        sb.append(ratingTypeId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("value");
        sb.append(StringPool.EQUAL);
        sb.append(value);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
