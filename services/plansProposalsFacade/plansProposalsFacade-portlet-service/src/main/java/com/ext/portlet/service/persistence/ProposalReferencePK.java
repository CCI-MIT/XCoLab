package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalReferencePK implements Comparable<ProposalReferencePK>,
    Serializable {
    public long proposalId;
    public long subProposalId;

    public ProposalReferencePK() {
    }

    public ProposalReferencePK(long proposalId, long subProposalId) {
        this.proposalId = proposalId;
        this.subProposalId = subProposalId;
    }

    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    public long getSubProposalId() {
        return subProposalId;
    }

    public void setSubProposalId(long subProposalId) {
        this.subProposalId = subProposalId;
    }

    @Override
    public int compareTo(ProposalReferencePK pk) {
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

        if (subProposalId < pk.subProposalId) {
            value = -1;
        } else if (subProposalId > pk.subProposalId) {
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

        if (!(obj instanceof ProposalReferencePK)) {
            return false;
        }

        ProposalReferencePK pk = (ProposalReferencePK) obj;

        if ((proposalId == pk.proposalId) &&
                (subProposalId == pk.subProposalId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(proposalId) + String.valueOf(subProposalId)).hashCode();
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
        sb.append("subProposalId");
        sb.append(StringPool.EQUAL);
        sb.append(subProposalId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
