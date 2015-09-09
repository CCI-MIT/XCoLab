package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalVotePK implements Comparable<ProposalVotePK>, Serializable {
    public long contestPhaseId;
    public long userId;

    public ProposalVotePK() {
    }

    public ProposalVotePK(long contestPhaseId, long userId) {
        this.contestPhaseId = contestPhaseId;
        this.userId = userId;
    }

    public long getContestPhaseId() {
        return contestPhaseId;
    }

    public void setContestPhaseId(long contestPhaseId) {
        this.contestPhaseId = contestPhaseId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(ProposalVotePK pk) {
        if (pk == null) {
            return -1;
        }

        int value = 0;

        if (contestPhaseId < pk.contestPhaseId) {
            value = -1;
        } else if (contestPhaseId > pk.contestPhaseId) {
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

        if (!(obj instanceof ProposalVotePK)) {
            return false;
        }

        ProposalVotePK pk = (ProposalVotePK) obj;

        if ((contestPhaseId == pk.contestPhaseId) && (userId == pk.userId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(contestPhaseId) + String.valueOf(userId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(10);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("contestPhaseId");
        sb.append(StringPool.EQUAL);
        sb.append(contestPhaseId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("userId");
        sb.append(StringPool.EQUAL);
        sb.append(userId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
