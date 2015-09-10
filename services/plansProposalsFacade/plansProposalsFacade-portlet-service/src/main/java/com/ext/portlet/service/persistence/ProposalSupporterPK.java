package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalSupporterPK implements Comparable<ProposalSupporterPK>,
    Serializable {
    public long proposalId;
    public long userId;

    public ProposalSupporterPK() {
    }

    public ProposalSupporterPK(long proposalId, long userId) {
        this.proposalId = proposalId;
        this.userId = userId;
    }

    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(ProposalSupporterPK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (proposalId < pk.proposalId) {
            value = -1;
        } else if (proposalId > pk.proposalId) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

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

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ProposalSupporterPK)) {
            return false;
        }

        ProposalSupporterPK pk = (ProposalSupporterPK) obj;

        if ((proposalId == pk.proposalId) && (userId == pk.userId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(proposalId) + String.valueOf(userId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("proposalId");
        sb.append(StringPool.EQUAL);
        sb.append(proposalId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
