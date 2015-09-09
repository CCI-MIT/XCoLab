package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class Proposal2PhasePK implements Comparable<Proposal2PhasePK>,
    Serializable {
    public long proposalId;
    public long contestPhaseId;

    public Proposal2PhasePK() {
    }

    public Proposal2PhasePK(long proposalId, long contestPhaseId) {
        this.proposalId = proposalId;
        this.contestPhaseId = contestPhaseId;
    }

    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    public long getContestPhaseId() {
        return contestPhaseId;
    }

    public void setContestPhaseId(long contestPhaseId) {
        this.contestPhaseId = contestPhaseId;
    }

    @Override
    public int compareTo(Proposal2PhasePK pk) {
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

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Proposal2PhasePK)) {
            return false;
        }

        Proposal2PhasePK pk = (Proposal2PhasePK) obj;

        if ((proposalId == pk.proposalId) &&
                (contestPhaseId == pk.contestPhaseId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(proposalId) + String.valueOf(contestPhaseId)).hashCode();
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
        sb.append("contestPhaseId");
        sb.append(StringPool.EQUAL);
        sb.append(contestPhaseId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
