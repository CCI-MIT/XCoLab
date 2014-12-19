package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalVersion;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ProposalVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVersion
 * @generated
 */
public class ProposalVersionCacheModel implements CacheModel<ProposalVersion>,
    Externalizable {
    public long proposalId;
    public int version;
    public long authorId;
    public long createDate;
    public String updateType;
    public long updateAdditionalId;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(13);

        sb.append("{proposalId=");
        sb.append(proposalId);
        sb.append(", version=");
        sb.append(version);
        sb.append(", authorId=");
        sb.append(authorId);
        sb.append(", createDate=");
        sb.append(createDate);
        sb.append(", updateType=");
        sb.append(updateType);
        sb.append(", updateAdditionalId=");
        sb.append(updateAdditionalId);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalVersion toEntityModel() {
        ProposalVersionImpl proposalVersionImpl = new ProposalVersionImpl();

        proposalVersionImpl.setProposalId(proposalId);
        proposalVersionImpl.setVersion(version);
        proposalVersionImpl.setAuthorId(authorId);

        if (createDate == Long.MIN_VALUE) {
            proposalVersionImpl.setCreateDate(null);
        } else {
            proposalVersionImpl.setCreateDate(new Date(createDate));
        }

        if (updateType == null) {
            proposalVersionImpl.setUpdateType(StringPool.BLANK);
        } else {
            proposalVersionImpl.setUpdateType(updateType);
        }

        proposalVersionImpl.setUpdateAdditionalId(updateAdditionalId);

        proposalVersionImpl.resetOriginalValues();

        return proposalVersionImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        proposalId = objectInput.readLong();
        version = objectInput.readInt();
        authorId = objectInput.readLong();
        createDate = objectInput.readLong();
        updateType = objectInput.readUTF();
        updateAdditionalId = objectInput.readLong();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(proposalId);
        objectOutput.writeInt(version);
        objectOutput.writeLong(authorId);
        objectOutput.writeLong(createDate);

        if (updateType == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(updateType);
        }

        objectOutput.writeLong(updateAdditionalId);
    }
}
