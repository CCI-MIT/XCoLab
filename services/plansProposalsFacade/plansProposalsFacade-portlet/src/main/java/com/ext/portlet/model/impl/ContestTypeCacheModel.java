package com.ext.portlet.model.impl;

import com.ext.portlet.model.ContestType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ContestType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ContestType
 * @generated
 */
public class ContestTypeCacheModel implements CacheModel<ContestType>,
    Externalizable {
    public long id;
    public String contestName;
    public String contestNamePlural;
    public String proposalName;
    public String proposalNamePlural;
    public String portletName;
    public boolean hasDiscussion;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(id);
        sb.append(", contestName=");
        sb.append(contestName);
        sb.append(", contestNamePlural=");
        sb.append(contestNamePlural);
        sb.append(", proposalName=");
        sb.append(proposalName);
        sb.append(", proposalNamePlural=");
        sb.append(proposalNamePlural);
        sb.append(", portletName=");
        sb.append(portletName);
        sb.append(", hasDiscussion=");
        sb.append(hasDiscussion);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ContestType toEntityModel() {
        ContestTypeImpl contestTypeImpl = new ContestTypeImpl();

        contestTypeImpl.setId(id);

        if (contestName == null) {
            contestTypeImpl.setContestName(StringPool.BLANK);
        } else {
            contestTypeImpl.setContestName(contestName);
        }

        if (contestNamePlural == null) {
            contestTypeImpl.setContestNamePlural(StringPool.BLANK);
        } else {
            contestTypeImpl.setContestNamePlural(contestNamePlural);
        }

        if (proposalName == null) {
            contestTypeImpl.setProposalName(StringPool.BLANK);
        } else {
            contestTypeImpl.setProposalName(proposalName);
        }

        if (proposalNamePlural == null) {
            contestTypeImpl.setProposalNamePlural(StringPool.BLANK);
        } else {
            contestTypeImpl.setProposalNamePlural(proposalNamePlural);
        }

        if (portletName == null) {
            contestTypeImpl.setPortletName(StringPool.BLANK);
        } else {
            contestTypeImpl.setPortletName(portletName);
        }

        contestTypeImpl.setHasDiscussion(hasDiscussion);

        contestTypeImpl.resetOriginalValues();

        return contestTypeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        contestName = objectInput.readUTF();
        contestNamePlural = objectInput.readUTF();
        proposalName = objectInput.readUTF();
        proposalNamePlural = objectInput.readUTF();
        portletName = objectInput.readUTF();
        hasDiscussion = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);

        if (contestName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contestName);
        }

        if (contestNamePlural == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(contestNamePlural);
        }

        if (proposalName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(proposalName);
        }

        if (proposalNamePlural == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(proposalNamePlural);
        }

        if (portletName == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(portletName);
        }

        objectOutput.writeBoolean(hasDiscussion);
    }
}
