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
    public long ratingTypeId;
    public String label;

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(5);

        sb.append("{ratingTypeId=");
        sb.append(ratingTypeId);
        sb.append(", label=");
        sb.append(label);
        sb.append("}");

        return sb.toString();
    }

    @Override
    public ProposalRatingType toEntityModel() {
        ProposalRatingTypeImpl proposalRatingTypeImpl = new ProposalRatingTypeImpl();

        proposalRatingTypeImpl.setRatingTypeId(ratingTypeId);

        if (label == null) {
            proposalRatingTypeImpl.setLabel(StringPool.BLANK);
        } else {
            proposalRatingTypeImpl.setLabel(label);
        }

        proposalRatingTypeImpl.resetOriginalValues();

        return proposalRatingTypeImpl;
    }

    @Override
    public void readExternal(ObjectInput objectInput) throws IOException {
        ratingTypeId = objectInput.readLong();
        label = objectInput.readUTF();
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput)
        throws IOException {
        objectOutput.writeLong(ratingTypeId);

        if (label == null) {
            objectOutput.writeUTF(StringPool.BLANK);
        } else {
            objectOutput.writeUTF(label);
        }
    }
}
