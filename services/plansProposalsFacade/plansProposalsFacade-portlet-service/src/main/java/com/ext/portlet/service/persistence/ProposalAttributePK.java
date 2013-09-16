package com.ext.portlet.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;


public class ProposalAttributePK implements Comparable<ProposalAttributePK>,
    Serializable {
    public long proposalId;
    public int version;
    public String name;
    public long additionalId;

    public ProposalAttributePK() {
    }

    public ProposalAttributePK(long proposalId, int version, String name,
        long additionalId) {
        this.proposalId = proposalId;
        this.version = version;
        this.name = name;
        this.additionalId = additionalId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAdditionalId() {
        return additionalId;
    }

    public void setAdditionalId(long additionalId) {
        this.additionalId = additionalId;
    }

    public int compareTo(ProposalAttributePK pk) {
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

        value = name.compareTo(pk.name);

        if (value != 0) {
            return value;
        }

        if (additionalId < pk.additionalId) {
            value = -1;
        } else if (additionalId > pk.additionalId) {
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

        ProposalAttributePK pk = null;

        try {
            pk = (ProposalAttributePK) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        if ((proposalId == pk.proposalId) && (version == pk.version) &&
                (name.equals(pk.name)) && (additionalId == pk.additionalId)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (String.valueOf(proposalId) + String.valueOf(version) +
        String.valueOf(name) + String.valueOf(additionalId)).hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(20);

        sb.append(StringPool.OPEN_CURLY_BRACE);

        sb.append("proposalId");
        sb.append(StringPool.EQUAL);
        sb.append(proposalId);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("version");
        sb.append(StringPool.EQUAL);
        sb.append(version);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("name");
        sb.append(StringPool.EQUAL);
        sb.append(name);

        sb.append(StringPool.COMMA);
        sb.append(StringPool.SPACE);
        sb.append("additionalId");
        sb.append(StringPool.EQUAL);
        sb.append(additionalId);

        sb.append(StringPool.CLOSE_CURLY_BRACE);

        return sb.toString();
    }
}
