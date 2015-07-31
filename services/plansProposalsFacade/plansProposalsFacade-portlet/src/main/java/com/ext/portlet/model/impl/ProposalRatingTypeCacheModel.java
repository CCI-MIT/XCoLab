package com.ext.portlet.model.impl;

import com.ext.portlet.model.ProposalRatingType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing ProposalRatingType in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingType
 * @generated
 */
public class ProposalRatingTypeCacheModel implements CacheModel<ProposalRatingType>,
    Externalizable {
    public long id;
    public String label;
    public String description;
    public int judgeType;
    public boolean isActive;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(11);

        sb.append("{id=");
        sb.append(id);
        sb.append(", label=");
        sb.append(label);
        sb.append(", description=");
        sb.append(description);
        sb.append(", judgeType=");
        sb.append(judgeType);
        sb.append(", isActive=");
        sb.append(isActive);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalRatingType toEntityModel() {
        ProposalRatingTypeImpl proposalRatingTypeImpl = new ProposalRatingTypeImpl();

        proposalRatingTypeImpl.setId(id);

        if (label == null) {
            proposalRatingTypeImpl.setLabel(StringPool.BLANK);
        } else {
            proposalRatingTypeImpl.setLabel(label);
        }

        if (description == null) {
            proposalRatingTypeImpl.setDescription(StringPool.BLANK);
        } else {
            proposalRatingTypeImpl.setDescription(description);
        }

        proposalRatingTypeImpl.setJudgeType(judgeType);
        proposalRatingTypeImpl.setIsActive(isActive);

        proposalRatingTypeImpl.resetOriginalValues();

        return proposalRatingTypeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        id = objectInput.readLong();
        label = objectInput.readUTF();
        description = objectInput.readUTF();
        judgeType = objectInput.readInt();
        isActive = objectInput.readBoolean();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(id);

        if (label == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(label);
        }

        if (description == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(description);
        }

        objectOutput.writeInt(judgeType);
        objectOutput.writeBoolean(isActive);
    }
}
