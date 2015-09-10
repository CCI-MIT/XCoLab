package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ProposalVersionPK implements Comparable<ProposalVersionPK>,
    Serializable {
    public long proposalId;
    public int version;

    public ProposalVersionPK() {
    }

    public ProposalVersionPK(long proposalId, int version) {
        this.proposalId = proposalId;
        this.version = version;
    }

    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int compareTo(ProposalVersionPK pk) {
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

        if (version < pk.version) {
            value = -1;
        } else if (version > pk.version) {
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

        if (!(obj instanceof ProposalVersionPK)) {
            return false;
        }

        ProposalVersionPK pk = (ProposalVersionPK) obj;

        if ((proposalId == pk.proposalId) && (version == pk.version)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(proposalId) + String.valueOf(version)).hashCode();
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
        sb.append("version");
        sb.append(StringPool.EQUAL);
        sb.append(version);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
